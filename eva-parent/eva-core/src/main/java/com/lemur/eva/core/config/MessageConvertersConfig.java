package com.lemur.eva.core.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * FastJson对应的HttpMessageConverter不会被自动注册，所以无法进行Map到Json的转换
 * 参考文献：https://blog.csdn.net/yubin1285570923/article/details/117703624
 */
@Configuration
public class MessageConvertersConfig {

    @Bean
    public FormHttpMessageConverter formHttpMessageConverter() {
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.setCharset(StandardCharsets.UTF_8);
        return formHttpMessageConverter;
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(StandardCharsets.UTF_8);
//        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }


    @Bean
    public RestTemplate restTemplate(FormHttpMessageConverter formHttpMessageConverter, FastJsonHttpMessageConverter fastJsonHttpMessageConverter) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(formHttpMessageConverter);
        messageConverters.add(fastJsonHttpMessageConverter);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
