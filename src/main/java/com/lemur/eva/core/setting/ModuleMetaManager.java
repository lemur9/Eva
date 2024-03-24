package com.lemur.eva.core.setting;

import com.lemur.eva.core.utils.EnvScanner;

import java.io.IOException;
import java.util.UUID;

/**
 * 模块meta数据的管理类
 *
 */
public class ModuleMetaManager {
	public static String crtID() {
		return AppContext.serverId + "-" + UUID.randomUUID();
	}

	/**
	 * 加载本机服务器编号
	 * @return
	 */
	public static String loadServerId() {
		EnvScanner fu = new EnvScanner();

		try {
			fu.open(AppContext.serverConf);

			return fu.get("id");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fu.close();
		}

		return "00";
	}

	/**
	 * 加载本机服务器编号
	 * @return
	 */
	public static String loadServerIp() {
		EnvScanner fu = new EnvScanner();

		try {
			fu.open(AppContext.serverConf);

			return fu.get("ip");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fu.close();
		}

		return "127.0.0.1";
	}
}
