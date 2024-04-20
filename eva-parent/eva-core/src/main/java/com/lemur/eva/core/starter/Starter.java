package com.lemur.eva.core.starter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Starter implements Runnable {
    private final String name;

    private final boolean lazyLoad;

    @Setter(AccessLevel.PACKAGE)
    private StarterCallback callback;

    private final Environment<Starter> environment;

    protected Starter(String name, boolean lazyLoad, Environment<Starter> env) {
        this(name, lazyLoad, env, null);
    }

    protected Starter(String name, boolean lazyLoad, Environment<Starter> env, StarterCallback callback) {
        this.name = name;
        this.environment = env;
        this.lazyLoad = lazyLoad;
        this.callback = callback;

        if (env != null) {
            env.register(this);
        }
    }

    protected Starter(String name, boolean lazyLoad) {
        this(name, lazyLoad, null, null);
    }

    @Override
    public void run() {
        this.exec();

        if (this.getCallback() != null) {
            this.getCallback().callback(this);
        }
    }

    public abstract void exec();
}