package com.lemur.eva.core.starter;

import lombok.Getter;

@Getter
public abstract class InitializeStarter extends Starter {
    protected InitializeStarter(String name, boolean lazyLoad) {
        super(name, lazyLoad);
    }

    protected InitializeStarter(String name, Environment<Starter> env) {
        super(name, true, env);
    }
}