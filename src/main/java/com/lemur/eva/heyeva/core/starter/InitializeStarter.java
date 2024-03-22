package com.lemur.eva.heyeva.core.starter;

import lombok.Getter;

@Getter
public abstract class InitializeStarter extends Starter {
    protected InitializeStarter(String name, boolean lazyLoad) {
        super(name, lazyLoad);
    }
}