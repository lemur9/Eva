package com.lemur.eva.core.config;

import com.lemur.eva.core.setting.AppContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * i18n 国际化配置
 */
@Configuration
public class DefaultLocaleConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        //会话区域解析器也就是说，你设置完只针对当前的会话有效，session失效，还原为默认状态
        SessionLocaleResolver srl = new SessionLocaleResolver();
        //设置默认区域
        srl.setDefaultLocale(AppContext.DEFAULT_LOCALE);
        return srl;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}