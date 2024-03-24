package com.lemur.eva.core.filter;

import com.lemur.eva.core.filter.web.WebVisitFilter;
import com.lemur.eva.core.filter.xss.XssFilter;
import jakarta.servlet.DispatcherType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Filter配置
 *
 */
@Configuration
public class FilterConfig {

    /**
     * 注册 过滤器 Filter
     */
    @Bean
    public FilterRegistrationBean<WebVisitFilter> webVisitFilterConfigRegistration() {
        //匹配拦截 URL
        String urlPatterns = "/eva/*,/api/*";
        FilterRegistrationBean<WebVisitFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new WebVisitFilter());
        registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        //设置名称
        registration.setName("webVisitFilter");
        //设置过滤器链执行顺序
        registration.setOrder(0);
        //启动标识
        registration.setEnabled(true);
        //添加初始化参数
        registration.addInitParameter("enabel", "true");
        return registration;
    }

    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> shiroFilterRegistration() {
        FilterRegistrationBean<DelegatingFilterProxy> registration = new FilterRegistrationBean<>();
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilter");
        registration.setFilter(shiroFilter);
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(2);
        return registration;
    }
}
