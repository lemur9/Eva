package com.lemur.eva.core.starter;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConfigLoader {
    private final List<Starter> container = new CopyOnWriteArrayList<>();

    public synchronized ConfigLoader register(Starter starter) {
        this.container.add(starter);
        return this;
    }

    public List<Starter> start() {
        ExecutorService service = Executors.newFixedThreadPool(1);

        StarterCallback callback = new StarterBannerCallback();

        try {
            for (Starter starter : this.container) {
                starter.setCallback(callback);

                if (starter.isLazyLoad()) {
                    service.submit(starter);
                } else {
                    // 非延迟加载直接执行，方法串行调用，非线程调用
                    starter.run();
                }
            }
        } catch (Exception e) {
            System.err.printf("启动服务线程时出现错误: %s\n",e);
        } finally {
            // 关闭线程池
            this.shutdown(service);
        }

        return this.container;
    }

    private void shutdown(ExecutorService service) {
        try {
            service.shutdown();
            if (!service.awaitTermination(30, TimeUnit.MINUTES)) {
                System.err.println("启动服务线程超时");
            }
        } catch (InterruptedException e) {
            System.err.printf("启动服务线程在关闭时出现错误: %s\n",e);
        }
    }
}