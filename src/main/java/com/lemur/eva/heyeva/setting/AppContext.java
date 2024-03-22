package com.lemur.eva.heyeva.setting;

import com.lemur.eva.heyeva.core.server.PropertiesConfigStarter;
import com.lemur.eva.heyeva.core.starter.ConfigLoader;
import com.lemur.eva.heyeva.core.starter.GlobalEnvironment;
import com.lemur.eva.heyeva.core.starter.Starter;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局环境变量上下文环境
 *
 */
@Component
public class AppContext {

    public static String DATABASE_CONFIG_PATH;
    public static volatile GlobalEnvironment<Starter> GE;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * springboot执行之前初始化的服务
     */
    private static List<Starter> initializeConfig = new ArrayList<>();

    public void initialize() {
        initializeConfig = new ConfigLoader()
                .register(new PropertiesConfigStarter("Properties Config"))
                .start();
    }

    public void serviceInit() {
        GE = new GlobalEnvironment<>(applicationContext);

        for (Starter starter : initializeConfig) {
            // 将springboot启动之前注册的服务加载到环境中
            GE.register(starter);
        }

        // 服务注册与启动
//        new ConfigLoader()
//                .register(new PropertiesConfigStarter("Properties Config", GE))
//                .start();
    }
}
