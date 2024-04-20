package com.lemur.eva.core.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 国际化工具类
 */
public class MessageUtils {
    private static final MessageSource messageSource = (MessageSource) SpringBeanUtils.getBean("messageSource");

    public static String getMessage(int key, String... params) {
        return messageSource.getMessage(String.valueOf(key), params, LocaleContextHolder.getLocale());
    }

    /**
     * @param key : 对应messages配置的key.
     * @return messages
     */
    public static String getMessage(String key) {
        return messageSource.getMessage(key, new Object[]{}, LocaleContextHolder.getLocale());
    }

    /**
     * @param key : 对应messages配置的key.
     * @return messages
     */
    public static String getMessage(int key) {
        return getMessage(String.valueOf(key));
    }

    /**
     * @param key : 对应messages配置的key.
     * @param params : 数组参数.
     * @return messages
     */
    public static String getMessage(String key, Object[] params) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, params, locale);
    }

    public static String getMessage(String key, String param) {
        String[] params = {param};

        return getMessage(key, params);
    }

    public static String getMessage(String key, Object[] params, Locale locale) {
        return messageSource.getMessage(key, params, locale);
    }


}
