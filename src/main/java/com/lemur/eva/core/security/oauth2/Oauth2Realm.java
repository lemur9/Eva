package com.lemur.eva.core.security.oauth2;


import com.lemur.eva.core.exception.ErrorCodeMeta;
import com.lemur.eva.core.security.pojo.SignatureUser;
import com.lemur.eva.core.security.pojo.SysUserToken;
import com.lemur.eva.core.security.pojo.User;
import com.lemur.eva.core.security.service.ShiroService;
import com.lemur.eva.core.utils.ConvertUtils;
import com.lemur.eva.core.utils.MessageUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * 认证
 */
@Component
public class Oauth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Oauth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SignatureUser signatureUser = (SignatureUser) principals.getPrimaryPrincipal();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(signatureUser);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        SysUserToken tokenEntity = shiroService.getByToken(accessToken);
        //token失效
        if (ObjectUtils.isEmpty(tokenEntity) || tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException(MessageUtils.getMessage(ErrorCodeMeta.TOKEN_INVALID));
        }

        //查询用户信息
        User user = shiroService.getUser(tokenEntity.getUserId());

        //转换成User对象
        SignatureUser signatureUser = ConvertUtils.sourceToTarget(user, SignatureUser.class);

        if (ObjectUtils.isEmpty(signatureUser)) {
            throw new AuthorizationException(MessageUtils.getMessage(ErrorCodeMeta.ACCOUNT_NOT_EXIST));
        }

        //获取用户对应的部门数据权限
        List<Long> deptIdList = shiroService.getDataScopeList(signatureUser.getId());
        signatureUser.setDeptIdList(deptIdList);

        //账号锁定
        if (signatureUser.getStatus() == 0) {
            throw new LockedAccountException(MessageUtils.getMessage(ErrorCodeMeta.ACCOUNT_LOCK));
        }

        return new SimpleAuthenticationInfo(signatureUser, accessToken, getName());
    }

}