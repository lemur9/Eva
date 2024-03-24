package com.lemur.eva.core.security.pojo;

import lombok.Data;

import java.util.List;

/**
 * 用户信息
 *
 */
@Data
public class SignatureUser {

    private String id;

    private String userName;

    private String realName;

    private String headUrl;

    private Integer gender;

    private String email;

    private String mobile;

    private Long deptId;

    private String password;

    private Integer status;

    private Integer superAdmin;

    /**
     * 部门数据权限
     */
    private List<Long> deptIdList;
}