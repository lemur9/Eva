package com.lemur.eva.core.starter.server;

import com.lemur.eva.core.starter.InitializeStarter;
import com.lemur.eva.core.starter.PropertiesReader;
import com.lemur.eva.core.setting.AppContext;
import com.lemur.eva.core.setting.ApplicationPropertiesNaming;

/**
 * 属性配置启动器
 */
public class PropertiesConfigStarter extends InitializeStarter {
    public PropertiesConfigStarter(String name) {
        super(name, false);
    }

    @Override
    public void exec() {
        PropertiesReader reader = new PropertiesReader();
        AppContext.DATABASE_CONFIG_PATH = reader.get(ApplicationPropertiesNaming.SERVER_CONF, "/usr/local/lemur/conf/db_config.conf");

        PropertiesReader systemdReader = new PropertiesReader("systemd.properties");
        AppContext.FACTORY_REST_SHELL = systemdReader.get(ApplicationPropertiesNaming.FACTORY_REST_SHELL);
        AppContext.DB_STATUS_VIEW_SHELL = systemdReader.get(ApplicationPropertiesNaming.DB_STATUS_VIEW_SHELL);
        AppContext.DB_SERVER_SHELL = systemdReader.get(ApplicationPropertiesNaming.DB_SERVER_SHELL);
        AppContext.REDIS_SERVER_SHELL = systemdReader.get(ApplicationPropertiesNaming.REDIS_SERVER_SHELL);
        AppContext.WEB_STATUS_VIEW_SHELL = systemdReader.get(ApplicationPropertiesNaming.WEB_STATUS_VIEW_SHELL);
        AppContext.WEB_SERVER_SHELL = systemdReader.get(ApplicationPropertiesNaming.WEB_SERVER_SHELL);
        AppContext.ES_STATUS_VIEW_SHELL = systemdReader.get(ApplicationPropertiesNaming.ES_STATUS_VIEW_SHELL);
        AppContext.ES_SERVER_SHELL = systemdReader.get(ApplicationPropertiesNaming.ES_SERVER_SHELL);
    }
}
