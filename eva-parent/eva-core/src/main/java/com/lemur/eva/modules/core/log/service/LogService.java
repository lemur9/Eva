package com.lemur.eva.modules.core.log.service;

import com.lemur.eva.modules.core.log.pojo.LogError;
import com.lemur.eva.modules.core.log.repository.LogErrorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {

    @Resource
    private LogErrorRepository logErrorRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(LogError log){
        logErrorRepository.insert(log);
    }

}
