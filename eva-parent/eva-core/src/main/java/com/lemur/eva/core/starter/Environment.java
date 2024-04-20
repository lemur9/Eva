package com.lemur.eva.core.starter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统环境
 * @param <T> 初始化服务
 */
public abstract class Environment<T extends Starter> {
    private final Map<String, T> container = new ConcurrentHashMap<>();

    // 注册组件
    public void register(T starter) {
        this.container.put(starter.getName(), starter);
    }

    // 获取组件
    public T get(String name) {
        return this.container.get(name);
    }
}

