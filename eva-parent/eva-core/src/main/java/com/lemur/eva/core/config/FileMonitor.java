package com.lemur.eva.core.config;

import com.lemur.eva.core.constant.DateConstant;
import com.lemur.eva.core.listener.FileListener;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.net.URL;

@Component
public class FileMonitor {

    /**
     * 配置文件实时更新的守护线程
     */
    @PostConstruct
    public void init() {
        // 文件监视器
        FileAlterationMonitor monitor = new FileAlterationMonitor(DateConstant.ONE_THOUSAND_MILLISECONDS);
        try {
            URL resource = getClass().getResource("/config/dynamic");

            // 动态配置不存在，无需监听
            if (ObjectUtils.isEmpty(resource)) {
                return;
            }

            // 创建一个观察者
            FileAlterationObserver observer = new FileAlterationObserver(new File(resource.toURI()));

            // 给文件添加监听
            observer.addListener(new FileListener());

            // 添加观察者
            monitor.addObserver(observer);

            // 动态配置监控启动
            monitor.start();
        } catch (Exception e) {
            try {
                monitor.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}