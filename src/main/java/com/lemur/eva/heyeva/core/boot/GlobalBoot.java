package com.lemur.eva.heyeva.core.boot;

import com.lemur.eva.heyeva.setting.AppContext;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 容器全部初始化完成，执行业务的初始化流程
 */
@Component
public class GlobalBoot implements ApplicationListener<ContextRefreshedEvent> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private AppContext app;

	/**
	 * 等待spring 容器全部初始化完成，开始执行业务的初始化流程
	 * @param contextRefreshedEvent
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		log.info("service Init start");

		app.serviceInit();

		log.info("service Init end");
	}
}

