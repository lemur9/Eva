package com.lemur.eva.core.starter;

import lombok.Getter;

@Getter
public abstract class ConfigStarter extends Starter {
    protected ConfigStarter(String name, Environment env) {
        super(name, false, env);
    }
}