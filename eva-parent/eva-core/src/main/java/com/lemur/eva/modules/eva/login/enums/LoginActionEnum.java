package com.lemur.eva.modules.eva.login.enums;

/**
 * 登录操作枚举
 *
 */
public enum LoginActionEnum {
    /**
     * 用户登录
     */
    LOGIN(0),
    /**
     * 用户退出
     */
    LOGOUT(1);

    private final int value;

    LoginActionEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}