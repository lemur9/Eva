package com.lemur.eva.core.security.enums;

/**
 * 超级管理员枚举
 *
 */
public enum SuperAdminEnum {
    YES(1),
    NO(0);

    private final int value;

    SuperAdminEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}