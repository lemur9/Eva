package com.lemur.eva.core.redis;

/**
 * redis存储的key
 */
public class RedisKeys {
    /**
     * 系统参数Key
     */
    public static String getSysParamsKey() {
        return "eva:params";
    }

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String uuid) {
        return "eva:captcha:" + uuid;
    }

    /**
     * 登录用户Key
     */
    public static String getSecurityUserKey(Long id) {
        return "eva:security:user:" + id;
    }

    /**
     * 系统日志Key
     */
    public static String getSysLogKey() {
        return "eva:log";
    }

    /**
     * 系统资源Key
     */
    public static String getSysResourceKey() {
        return "eva:resource";
    }

    /**
     * 用户菜单导航Key
     */
    public static String getUserMenuNavKey(Long userId) {
        return "eva:user:nav:" + userId;
    }

    /**
     * 用户权限标识Key
     */
    public static String getUserPermissionsKey(Long userId) {
        return "eva:user:permissions:" + userId;
    }
}
