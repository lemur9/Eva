package com.lemur.eva.core.starter;

import lombok.Getter;
import org.springframework.context.ApplicationContext;

public class GlobalEnvironment<T extends Starter> extends Environment<T> {

    @Getter
    private final ApplicationContext context;

    public GlobalEnvironment(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }
}
