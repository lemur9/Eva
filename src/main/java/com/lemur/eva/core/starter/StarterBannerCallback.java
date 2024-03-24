package com.lemur.eva.core.starter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarterBannerCallback implements StarterCallback {
    @Override
    public void callback(Starter starter) {
        final String placeholder = ".";
        final int length = 30;

        StringBuilder sb = new StringBuilder();

        sb.append("[Starter]").append(starter.getName());

        int padding = length - starter.getName().length();

        sb.append(placeholder.repeat(Math.max(0, padding)));

        sb.append("[SUCCESS]");

        log.info(sb.toString());
    }
}