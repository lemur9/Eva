package com.lemur.eva.core.setting;

import com.lemur.eva.core.starter.ConfigLoader;
import com.lemur.eva.core.starter.GlobalEnvironment;
import com.lemur.eva.core.starter.Starter;
import com.lemur.eva.core.starter.server.PropertiesConfigStarter;
import com.lemur.eva.core.starter.server.ServiceStatusServiceStarter;
import com.lemur.eva.modules.core.systemmaintenance.pojo.ServiceStatus;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局环境变量上下文环境
 */
@Component
public class AppContext {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 数据库配置文件路径
     */
    public static String DATABASE_CONFIG_PATH;

    /**
     * 恢复出厂使用Shell
     */
    public static String FACTORY_REST_SHELL;

    /**
     * 查看数据库状态和启动时间
     */
    public static String DB_STATUS_VIEW_SHELL;

    /**
     * 查看数据库状态和启动时间
     */
    public static String DB_SERVER_SHELL;

    /**
     * 查看redis状态和启动时间
     */
    public static String REDIS_SERVER_SHELL;

    /**
     * 查看web状态和启动时间
     */
    public static String WEB_STATUS_VIEW_SHELL;

    /**
     * 查看web状态和启动时间
     */
    public static String WEB_SERVER_SHELL;

    /**
     * 查看搜索引擎状态和启动时间
     */
    public static String ES_STATUS_VIEW_SHELL;

    /**
     * 查看搜索引擎状态和启动时间
     */
    public static String ES_SERVER_SHELL;

    /**
     * 全局系统环境
     */
    public static volatile GlobalEnvironment<Starter> GE;

    /**
     * 服务器配置文件路径
     */
    public static String serverConf;

    /**
     * 服务器ID
     */
    public static String serverId;

    /**
     * 服务器IP
     */
    public static String serverIp;

    /**
     * 服务状态
     */
    public static ServiceStatus serviceStatus;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * springboot执行之前初始化的服务
     */
    private static List<Starter> initializeConfig = new ArrayList<>();

    public void initialize() {
        logger.info("initialize start.....");
        initializeConfig = new ConfigLoader()
                .register(new PropertiesConfigStarter("Properties Config"))
                .start();
        logger.info("initialize end.....");
    }

    public void serviceInit() {
        GE = new GlobalEnvironment<>(applicationContext);

        for (Starter starter : initializeConfig) {
            // 将springboot启动之前注册的服务加载到环境中
            GE.register(starter);
        }

        logger.info("serviceInit start.....");
        // 服务注册与启动
        new ConfigLoader()
                .register(new ServiceStatusServiceStarter("ServiceStatus Service", GE))
                .start();

        logger.info("serviceInit end.....");
    }
}
