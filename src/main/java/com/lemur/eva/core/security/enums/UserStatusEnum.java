package com.lemur.eva.core.security.enums;

/**
 * 用户状态
 *
 */
public enum UserStatusEnum {
    DISABLE(0),
    ENABLED(1);

    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
