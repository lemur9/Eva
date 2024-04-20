package com.lemur.eva.core.listener;

import com.lemur.eva.core.starter.server.PropertiesConfigStarter;
import com.lemur.eva.core.utils.DateUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

public class FileListener extends FileAlterationListenerAdaptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onDirectoryCreate(File directory) {
//		System.out.println("新建：" + directory.getAbsolutePath());
    }

    @Override
    public void onDirectoryChange(File directory) {
//		System.out.println("修改：" + directory.getAbsolutePath());
    }

    @Override
    public void onDirectoryDelete(File directory) {
//		System.out.println("删除：" + directory.getAbsolutePath());
    }

    @Override
    public void onFileCreate(File file) {
//		System.out.println("新建：" + file.getAbsolutePath());
    }

    @Override
    public void onFileChange(File file) {
        String name = file.getName();
        logger.info("file content changes, name: {}, modify time: {}", name, DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        if (file.canRead()) {
			PropertiesConfigStarter.reloadDynamicConfig(name);
        }
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("删除：" + file.getAbsolutePath());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }
}