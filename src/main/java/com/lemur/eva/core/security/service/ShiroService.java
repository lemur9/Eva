package com.lemur.eva.core.security.service;

import com.lemur.eva.core.security.enums.SuperAdminEnum;
import com.lemur.eva.core.security.pojo.SignatureUser;
import com.lemur.eva.core.security.pojo.SysUserToken;
import com.lemur.eva.core.security.pojo.User;
import com.lemur.eva.modules.eva.system.repository.MenuRepository;
import com.lemur.eva.modules.eva.system.repository.RoleDataScopeRepository;
import com.lemur.eva.modules.eva.system.repository.UserRepository;
import com.lemur.eva.modules.eva.system.repository.UserTokenRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ShiroService {

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserTokenRepository userTokenRepository;

    @Resource
    private RoleDataScopeRepository roleDataScopeRepository;

    /**
     * 获取用户权限列表
     */
    public Set<String> getUserPermissions(SignatureUser signatureUser) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;

        if(signatureUser.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = menuRepository.getPermissionsList();
        }else{
            permissionsList = menuRepository.getUserPermissionsList(signatureUser.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();

        for(String permissions : permissionsList){
            if(StringUtils.isBlank(permissions)){
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    /**
     * 获取系统用户Token
     */
    public SysUserToken getByToken(String token){
        return userTokenRepository.getByToken(token);
    }

    /**
     * 根据用户ID，查询用户
     * @param userId 用户ID
     */
    public User getUser(Long userId){
        return userRepository.selectById(userId);
    }

    /**
     * 获取用户对应的部门数据权限
     * @param userId  用户ID
     * @return        返回部门ID列表
     */
    public List<Long> getDataScopeList(String userId) {
        return roleDataScopeRepository.getDataScopeList(userId);
    }
}

