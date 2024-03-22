package com.lemur.eva.heyeva.core.starter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarterBannerCallback implements StarterCallback {
    @Override
    public void callback(Starter starter) {
        final char placeholder = '·';
        final int length = 30;

        StringBuilder sb = new StringBuilder();

        sb.append("[Starter]").append(starter.getName());

        int padding = length - starter.getName().length();

        for (int i = 0; i < padding; i++) {
            sb.append(placeholder);
        }

        sb.append("[SUCCESS]");

        log.info("{}", sb.toString());
    }
}