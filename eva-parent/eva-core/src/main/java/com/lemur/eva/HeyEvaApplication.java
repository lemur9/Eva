package com.lemur.eva;

import com.lemur.eva.core.boot.BeforeBoot;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@MapperScan
@SpringBootApplication
public class HeyEvaApplication extends SpringBootServletInitializer {
    @Override
    protected WebApplicationContext run(SpringApplication application) {
        application.addListeners(new BeforeBoot());
        return (WebApplicationContext) application.run();
    }
}