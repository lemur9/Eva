package com.lemur.eva.modules.eva.login.service;

import com.lemur.eva.modules.eva.login.pojo.LogLogin;
import com.lemur.eva.modules.eva.login.repository.LogLoginRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LogLoginService {
    @Resource
    private LogLoginRepository logLoginRepository;

    public void save(LogLogin log) {
        logLoginRepository.insert(log);
    }
}
