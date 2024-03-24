package com.lemur.eva.core.security.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 系统用户Token
 */
@Data
public class SysUserToken {
	/**
	 * id
	 */
	private String id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 用户token
	 */
	private String token;

	/**
	 * 过期时间
	 */
	private Date expireDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	/**
	 * 创建时间
	 */
	private Date createDate;

}