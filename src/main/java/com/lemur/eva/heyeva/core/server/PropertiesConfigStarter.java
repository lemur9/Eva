package com.lemur.eva.heyeva.core.server;

import com.lemur.eva.heyeva.core.starter.InitializeStarter;
import com.lemur.eva.heyeva.core.starter.PropertiesReader;
import com.lemur.eva.heyeva.setting.AppContext;
import com.lemur.eva.heyeva.setting.ApplicationPropertiesNaming;


public class PropertiesConfigStarter extends InitializeStarter {
    public PropertiesConfigStarter(String name) {
        super(name, false);
    }

    @Override
    public void exec() {
        PropertiesReader reader = new PropertiesReader();

        AppContext.DATABASE_CONFIG_PATH =  reader.get(ApplicationPropertiesNaming.SERVER_CONF, "/usr/local/lemur/conf/db_config.conf");
    }
}
