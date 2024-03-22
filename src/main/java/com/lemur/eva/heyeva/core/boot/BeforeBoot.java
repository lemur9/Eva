package com.lemur.eva.heyeva.core.boot;

import com.lemur.eva.heyeva.setting.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * 在springboot启动之前执行，执行一些必要的预加载流程
 */
public class BeforeBoot implements ApplicationListener<ApplicationStartingEvent> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 全局环境变量上下文环境
     */
    private final AppContext appContext = new AppContext();

    /**
     * 在spring 容器初始化之前，开始执行必要的预加载流程
     *
     */
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {

        log.info("initialize start");

        appContext.initialize();

        log.info("initialize end");
    }
}
