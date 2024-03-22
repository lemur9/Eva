package com.lemur.eva.heyeva.core.config;

import com.lemur.eva.heyeva.core.utils.EnvScanner;
import com.lemur.eva.heyeva.setting.AppContext;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DBPoolDatasourceConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private Hikari hikari;
    private String driverClassName;
    private String url;
    private String username;

    private String password;

    @Bean
    public DataSource dataSource() {

        try {
            EnvScanner scanner = new EnvScanner();
            scanner.open(AppContext.DATABASE_CONFIG_PATH);
            if (scanner.get("spring.datasource.driverClassName") != null) {
                driverClassName = scanner.get("spring.datasource.driverClassName");
            }

            if (scanner.get("spring.datasource.url") != null) {
                url = scanner.get("spring.datasource.url");
            }

            if (scanner.get("spring.datasource.username") != null) {
                username = scanner.get("spring.datasource.username");
            }

            if (scanner.get("spring.datasource.password") != null) {
                password = scanner.get("spring.datasource.password");
            }

            if (scanner.get("spring.datasource.minimum_idle") != null) {
                hikari.minimum_idle = scanner.get("spring.datasource.hikari.minimum_idle");
            }

            if (scanner.get("spring.datasource.hikari.maximum_pool_size") != null) {
                hikari.maximum_pool_size = scanner.get("spring.datasource.hikari.maximum_pool_size");
            }

            if (scanner.get("spring.datasource.hikari.auto_commit") != null) {
                hikari.auto_commit = scanner.get("spring.datasource.hikari.auto_commit");
            }

            if (scanner.get("spring.datasource.hikari.idle_timeout") != null) {
                hikari.idle_timeout = scanner.get("spring.datasource.hikari.idle_timeout");
            }

            if (scanner.get("spring.datasource.hikari.pool_name") != null) {
                hikari.pool_name = scanner.get("spring.datasource.hikari.pool_name");
            }

            if (scanner.get("spring.datasource.hikari.max_lifetime") != null) {
                hikari.max_lifetime = scanner.get("spring.datasource.hikari.max_lifetime");
            }

            if (scanner.get("spring.datasource.hikari.connection_timeout") != null) {
                hikari.connection_timeout = scanner.get("spring.datasource.hikari.connection_timeout");
            }

            if (scanner.get("spring.datasource.hikari.connection_test_query") != null) {
                hikari.connection_test_query = scanner.get("spring.datasource.hikari.connection_test_query");
            }
        } catch (IOException e) {
            log.info("The database configuration file failed to load and the data source will be built using the default configuration.");
        }


        HikariConfig hikariConfig = new HikariConfig();

        if (StringUtils.hasText(driverClassName)) {
            hikariConfig.setDriverClassName(driverClassName);
        }
        if (StringUtils.hasText(url)) {
            hikariConfig.setJdbcUrl(url);
        }
        if (StringUtils.hasText(username)) {
            hikariConfig.setUsername(username);
        }
        if (StringUtils.hasText(password)) {
            hikariConfig.setPassword(password);
        }
        if (StringUtils.hasText(hikari.minimum_idle)) {
            hikariConfig.setMinimumIdle(Integer.parseInt(hikari.minimum_idle));
        }
        if (StringUtils.hasText(hikari.maximum_pool_size)) {
            hikariConfig.setMaximumPoolSize(Integer.parseInt(hikari.maximum_pool_size));
        }
        if (StringUtils.hasText(hikari.auto_commit)) {
            hikariConfig.setAutoCommit(Boolean.getBoolean(hikari.auto_commit));
        }
        if (StringUtils.hasText(hikari.idle_timeout)) {
            hikariConfig.setIdleTimeout(Long.parseLong(hikari.idle_timeout));
        }
        if (StringUtils.hasText(hikari.pool_name)) {
            hikariConfig.setPoolName(hikari.pool_name);
        }
        if (StringUtils.hasText(hikari.max_lifetime)) {
            hikariConfig.setMaxLifetime(Long.parseLong(hikari.max_lifetime));
        }
        if (StringUtils.hasText(hikari.connection_timeout)) {
            hikariConfig.setConnectionTimeout(Long.parseLong(hikari.connection_timeout));
        }

        if (StringUtils.hasText(hikari.connection_test_query)) {
            hikariConfig.setConnectionTestQuery(hikari.connection_test_query);
        }

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    //@Scope("prototype")  使用多实例还会出现mysql连接超8小时后连接不可用  如果用注入DataSource方式获取connection改方法可以删掉
    public Connection dbPoolDataSourceConfig(@Autowired DataSource dataSource) {
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            throw new IllegalArgumentException("获取数据库连接失败");
        }
    }

    @Data
    @Configuration
    @ConfigurationProperties("spring.datasource.hikari")
    class Hikari {
        String minimum_idle;
        String maximum_pool_size;
        String auto_commit;
        String idle_timeout;
        String pool_name;
        String max_lifetime;
        String connection_timeout;
        String connection_test_query;
    }
}
