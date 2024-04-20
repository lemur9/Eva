package com.lemur.eva.modules.eva.login.enums;

/**
 * 操作状态枚举
 *
 */
public enum ActionStatusEnum {
    /**
     * 失败
     */
    FAIL(0),
    /**
     * 成功
     */
    SUCCESS(1);

    private final int value;

    ActionStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}