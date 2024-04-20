package com.lemur.eva.core.exception;

import com.lemur.eva.core.result.DataResultMeta;
import com.lemur.eva.core.utils.MessageUtils;

/**
 * 自定义异常
 *
 */
public class EvaException extends RuntimeException {

	// 状态码
    private int code;

	// 异常信息
	private String msg;

	public EvaException(int code) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public EvaException(int code, String... params) {
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public EvaException(int code, Throwable e) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code);
	}

	public EvaException(int code, Throwable e, String... params) {
		super(e);
		this.code = code;
		this.msg = MessageUtils.getMessage(code, params);
	}

	public EvaException(String msg) {
		super(msg);
		this.code = DataResultMeta.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public EvaException(String msg, Throwable e) {
		super(msg, e);
		this.code = DataResultMeta.INTERNAL_SERVER_ERROR;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}