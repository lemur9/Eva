package com.lemur.eva.modules.eva.user.utils;

import com.lemur.eva.core.security.pojo.SignatureUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public class SecurityUser {

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static SignatureUser getUser() {
        Subject subject = getSubject();
        if(subject == null){
            return new SignatureUser();
        }

        SignatureUser user = (SignatureUser)subject.getPrincipal();
        if(user == null){
            return new SignatureUser();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static String getUserId() {
        return getUser().getId();
    }

    /**
     * 获取部门ID
     */
    public static String getDeptId() {
        return getUser().getDeptId();
    }
}