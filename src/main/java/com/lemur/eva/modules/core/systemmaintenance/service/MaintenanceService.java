package com.lemur.eva.modules.core.systemmaintenance.service;


import com.lemur.eva.core.result.DataResult;
import com.lemur.eva.core.result.DataResultMeta;
import com.lemur.eva.core.setting.AppContext;
import com.lemur.eva.modules.core.systemmaintenance.pojo.MaintenanceMeta;
import com.lemur.eva.modules.core.systemmaintenance.pojo.ServiceStatus;
import com.lemur.eva.modules.core.systemmaintenance.utils.ProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {

    private final Logger logger = LoggerFactory.getLogger(MaintenanceService.class);

    /**
     * 恢复出厂设置
     *
     * @return
     */

    public boolean reset(DataResult<?> rv) {
        boolean ret = false;

        try {
            Thread.sleep(1000);
            ProcessUtils processUtils = new ProcessUtils();
            ret = processUtils.exec(AppContext.FACTORY_REST_SHELL);
        } catch (Exception e) {
            if (rv != null) {
                rv.setDetail(e.getMessage());
                rv.setCode(DataResultMeta.INTERNAL_SERVER_ERROR);
            }
        }

        return ret;
    }

    /**
     * 重启
     *
     * @param rv
     * @return
     */
    public boolean reboot(DataResult<?> rv) {
        boolean ret = false;

        try {
            Thread.sleep(1000);
            ProcessUtils processUtils = new ProcessUtils();
            ret = processUtils.exec(MaintenanceMeta.CMD_RESTART);

        } catch (Exception e) {
            if (rv != null) {
                rv.setDetail(e.getMessage());
                rv.setCode(DataResultMeta.INTERNAL_SERVER_ERROR);
            }
        }

        return ret;
    }

    /**
     * 关机
     *
     * @param rv
     * @return
     */
    public boolean shutdown(DataResult<?> rv) {
        boolean ret = false;

        try {
            Thread.sleep(1000);
            ProcessUtils processUtils = new ProcessUtils();
            ret = processUtils.exec(MaintenanceMeta.CMD_SHUTDOWN);

        } catch (Exception e) {
            if (rv != null) {
                rv.setDetail(e.getMessage());
                rv.setCode(DataResultMeta.INTERNAL_SERVER_ERROR);
            }
        }

        return ret;
    }

    /**
     * 查看数据库状态和启动时间
     *
     * @param svr
     * @param rv
     * @return
     */
    public boolean serviceMysqlStatus(ServiceStatus svr, DataResult<?> rv) {
        String[] vs = statusShell(AppContext.DB_STATUS_VIEW_SHELL, rv);
        if (vs == null) {
            return false;
        } else {
            boolean flag = !MaintenanceMeta.STATUS_STOP.equals(vs[0]);
            svr.setDbStatus(flag);
            svr.setDbStartTime(vs[1]);
        }

        return true;
    }

    public boolean serviceWebStatus(ServiceStatus svr, DataResult<?> rv) {
        String [] vs = statusShell(AppContext.WEB_STATUS_VIEW_SHELL, rv);
        if(vs == null ) {
            return false ;
        } else {
            boolean flag = !MaintenanceMeta.STATUS_STOP.equals(vs[0]);
            svr.setWebStatus(flag);
            svr.setWebStartTime(vs[1]);
        }

        return true ;
    }

    private String[] statusShell(String shell, DataResult<?> rv) {

        String[] vs = null;
        ProcessUtils processUtils = new ProcessUtils();
        try {

            String db_view = processUtils.exec_reply(shell, 3000);

            if (db_view != null && db_view.length() > 0) {
                vs = db_view.split(",");
            }

        } catch (Exception e) {
            if (rv != null) {
                rv.setDetail(e.getMessage());
                rv.setCode(DataResultMeta.INTERNAL_SERVER_ERROR);
            }
        }

        return vs;
    }


    /**
     * 服务启停
     *
     * @param Shell 服务Shell
     * @param op    操作 （start/stop/restart)
     * @return
     */
    public ServiceStatus serviceManager(String Shell, String op, DataResult<?> rv) {
        logger.info("系统维护脚本执行开始，操作:" + op);
        ServiceStatus svr = new ServiceStatus();
        boolean suc;
        try {
            ProcessUtils processUtils = new ProcessUtils();
            String cmd = Shell + " " + op;
            suc = processUtils.exec(cmd);

            logger.info("result:" + suc);
            if (!suc) {
                if (rv != null) {
                    rv.setCode(DataResultMeta.INTERNAL_SERVER_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (rv != null) {
                rv.setDetail(e.getMessage());
                rv.setCode(DataResultMeta.INTERNAL_SERVER_ERROR);
            }
        }
        logger.info("系统维护脚本执行结束，rv:" + rv);
        return svr;
    }

    //系统服务状态
    public ServiceStatus systemServiceStatus() {
        ServiceStatus svr = new ServiceStatus();
        DataResult<?> rv = new DataResult<>();

        serviceMysqlStatus(svr, rv);
        serviceWebStatus(svr, rv);

        return svr;
    }
}
