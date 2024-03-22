package com.lemur.eva.heyeva;

import com.lemur.eva.heyeva.core.boot.BeforeBoot;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class HeyEvaApplication {

    public static void main(String[] args) throws UnknownHostException {

        SpringApplication springBootApplication = new SpringApplication(HeyEvaApplication.class);

        // 必须这样注入
         springBootApplication.addListeners(new BeforeBoot());
//        springBootApplication.addListeners(new InBoot());
        // run方法参数必须传args，否则不能多profile切换
        ConfigurableApplicationContext application = springBootApplication.run(args);

        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local Visit URL: \t\thttp://localhost:" + port + path + "\n\t" +
                "External Visit URL: \thttp://" + ip + ":" + port + path + "\n" +
                "----------------------------------------------------------");
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        log.info("Current project process number：" + jvmName.split("@")[0]);
    }

}