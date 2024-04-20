package com.lemur.eva.core.starter.server.dynamic;

import com.lemur.eva.core.setting.AppContext;
import com.lemur.eva.core.setting.ApplicationPropertiesNaming;
import com.lemur.eva.core.starter.PropertiesReader;

public class SystemdCommand extends PropertiesReader {

    public SystemdCommand(String fileName) {
        super(fileName);
    }

    public void execute() {
        AppContext.FACTORY_REST_SHELL = this.get(ApplicationPropertiesNaming.FACTORY_REST_SHELL);
        AppContext.DB_STATUS_VIEW_SHELL = this.get(ApplicationPropertiesNaming.DB_STATUS_VIEW_SHELL);
        AppContext.DB_SERVER_SHELL = this.get(ApplicationPropertiesNaming.DB_SERVER_SHELL);
        AppContext.REDIS_SERVER_SHELL = this.get(ApplicationPropertiesNaming.REDIS_SERVER_SHELL);
        AppContext.WEB_STATUS_VIEW_SHELL = this.get(ApplicationPropertiesNaming.WEB_STATUS_VIEW_SHELL);
        AppContext.WEB_SERVER_SHELL = this.get(ApplicationPropertiesNaming.WEB_SERVER_SHELL);
        AppContext.ES_STATUS_VIEW_SHELL = this.get(ApplicationPropertiesNaming.ES_STATUS_VIEW_SHELL);
        AppContext.ES_SERVER_SHELL = this.get(ApplicationPropertiesNaming.ES_SERVER_SHELL);
    }
}
