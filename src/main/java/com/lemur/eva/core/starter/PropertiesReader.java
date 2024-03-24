package com.lemur.eva.core.starter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesReader {
    private Properties properties;

    public PropertiesReader() {
        try (InputStream in = this.getClass().getResourceAsStream("/application.properties")) {
            this.properties = new Properties();
            this.properties.load(in);
            this.reloadProperty();
        } catch (IOException e) {
            this.properties = null;

            log.error("new PropertiesReader() : ", e);
        }
    }

    public PropertiesReader(@NonNull String filename) {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename)) {
            this.properties = new Properties();
            this.properties.load(in);
            this.reloadProperty();
        } catch (IOException e) {
            this.properties = null;

            log.error("new PropertiesReader() filename : ", e);
        }
    }

    public int get(final String KEY, int defaultValue) {
        String value = this.properties.getProperty(KEY);

        return value == null ? defaultValue : Integer.parseInt(value);
    }

    public String get(final String KEY, String defaultValue) {
        String value = this.properties.getProperty(KEY);

        return value == null ? defaultValue : value;
    }

    public void reloadProperty() {
        String property = this.properties.getProperty("spring.profiles.active");
        if (!StringUtils.hasText(property)) {
            return;
        }

        //构建实际需要使用的Profile
        String useProfile = "application" + "-" + property + ".properties";

        try (InputStream in = PropertiesReader.class.getResourceAsStream("/" + useProfile)) {
            this.properties = new Properties();
            this.properties.load(in);

        } catch (IOException e) {
            this.properties = null;
            log.error("new PropertiesReader() : ", e);
        }
    }

    public String get(final String KEY) {
        String value = this.properties.getProperty(KEY);

        return value == null ? "" : value;
    }

    public boolean get(final String KEY, boolean defaultValue) {
        String value = this.properties.getProperty(KEY);

        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }
}