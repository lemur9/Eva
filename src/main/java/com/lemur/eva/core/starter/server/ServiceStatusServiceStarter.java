package com.lemur.eva.core.starter.server;

import com.lemur.eva.core.setting.AppContext;
import com.lemur.eva.core.starter.Environment;
import com.lemur.eva.core.starter.InitializeStarter;
import com.lemur.eva.core.starter.Starter;
import com.lemur.eva.core.utils.SpringBeanUtils;
import com.lemur.eva.modules.core.systemmaintenance.service.MaintenanceService;

/**
 * 属性配置启动器
 */
public class ServiceStatusServiceStarter extends InitializeStarter {
    public ServiceStatusServiceStarter(String name, Environment<Starter> env) {
        super(name, env);
    }

    @Override
    public void exec() {
        MaintenanceService maintenanceService = SpringBeanUtils.getBean(MaintenanceService.class);

        AppContext.serviceStatus = maintenanceService.systemServiceStatus();
    }
}