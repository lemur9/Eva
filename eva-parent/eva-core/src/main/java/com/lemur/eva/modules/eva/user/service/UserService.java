package com.lemur.eva.modules.eva.user.service;

import com.lemur.eva.modules.eva.user.pojo.User;
import com.lemur.eva.modules.eva.system.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User getByUname(String username) {
        return userRepository.getByUsername(username);
    }
}
