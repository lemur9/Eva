package com.lemur.eva.modules.core.log.pojo;

import com.lemur.eva.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常日志
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LogError extends BaseEntity {

	/**
	 * 请求URI
	 */
	private String requestUri;

	/**
	 * 请求方式
	 */
	private String requestMethod;

	/**
	 * 请求参数
	 */
	private String requestParams;

	/**
	 * 用户代理
	 */
	private String userAgent;

	/**
	 * 操作IP
	 */
	private String ip;

	/**
	 * 异常信息
	 */
	private String errorInfo;

}