package com.lemur.eva.core.starter.server;

import com.lemur.eva.core.setting.AppContext;
import com.lemur.eva.core.setting.ApplicationPropertiesNaming;
import com.lemur.eva.core.starter.InitializeStarter;
import com.lemur.eva.core.starter.PropertiesReader;
import com.lemur.eva.core.starter.server.dynamic.SystemdCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Locale;

/**
 * 属性配置启动器
 */
public class PropertiesConfigStarter extends InitializeStarter {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigStarter.class);

    public PropertiesConfigStarter(String name) {
        super(name, false);
    }

    @Override
    public void exec() {
        PropertiesReader reader = new PropertiesReader();
        AppContext.DATABASE_CONFIG_PATH = reader.get(ApplicationPropertiesNaming.SERVER_CONF, "/usr/local/eva/conf/db_config.conf");
        AppContext.DEFAULT_LOCALE = Locale.forLanguageTag(reader.get(ApplicationPropertiesNaming.DEFAULT_LOCALE, "zh-CN"));

        loadDynamicConfig("systemd.properties");
    }

    /**
     * 动态配置加载
     * @param fileName
     */
    private static void loadDynamicConfig(String fileName) {
        PropertiesReader systemdReader = null;

        if ("systemd.properties".equals(fileName)) {
            systemdReader = new SystemdCommand("config/dynamic/" + fileName);
        }

        // 未找到对应的解析类，不做处理
        if (ObjectUtils.isEmpty(systemdReader)) {
            return;
        }

         systemdReader.execute();
    }

    /**
     * 重新加载配置文件
     * @param fileName 文件名
     */
    public static void reloadDynamicConfig(String fileName) {
        logger.info("reloading file......");
        loadDynamicConfig(fileName);
    }
}
