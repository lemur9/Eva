package com.lemur.eva.core.exception;

import com.lemur.eva.core.result.DataResultMeta;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 *
 */
public interface ErrorCodeMeta extends DataResultMeta {
    /**
     * {0}不能为空
     */
    int NOT_NULL = 10001;

    /**
     * 数据库中已存在该记录
     */
    int DB_RECORD_EXISTS = 10002;

    /**
     * 获取参数失败
     */
    int PARAMS_GET_ERROR = 10003;

    /**
     * 账号或密码错误
     */
    int ACCOUNT_PASSWORD_ERROR = 10004;

    /**
     * 账号已被停用
     */
    int ACCOUNT_DISABLE = 10005;

    /**
     * 唯一标识不能为空
     */
    int IDENTIFIER_NOT_NULL = 10006;

    /**
     * 验证码不正确
     */
    int CAPTCHA_ERROR = 10007;

    /**
     * 先删除子菜单或按钮
     */
    int SUB_MENU_EXIST = 10008;

    /**
     * 原密码不正确
     */
    int PASSWORD_ERROR = 10009;

    /**
     * 账号不存在
     */
    int ACCOUNT_NOT_EXIST = 10010;

    /**
     * 上级部门选择错误
     */
    int SUPERIOR_DEPT_ERROR = 10011;

    /**
     * 上级菜单不能为自身
     */
    int SUPERIOR_MENU_ERROR = 10012;

    /**
     * 数据权限接口，只能是Map类型参数
     */
    int DATA_SCOPE_PARAMS_ERROR = 10013;

    /**
     * 请先删除下级部门
     */
    int DEPT_SUB_DELETE_ERROR = 10014;

    /**
     * 请先删除部门下的用户
     */
    int DEPT_USER_DELETE_ERROR = 10015;

    /**
     * 请上传文件
     */
    int UPLOAD_FILE_EMPTY = 10019;

    /**
     * token不能为空
     */
    int TOKEN_NOT_EMPTY = 10020;

    /**
     * token失效，请重新登录
     */
    int TOKEN_INVALID = 10021;

    /**
     * 账号已被锁定
     */
    int ACCOUNT_LOCK = 10022;

    /**
     * 上传文件失败：{0}
     */
    int OSS_UPLOAD_FILE_ERROR = 10024;

    /**
     * Redis服务异常
     */
    int REDIS_ERROR = 10027;

    /**
     * 定时任务失败
     */
    int JOB_ERROR = 10028;

    /**
     * 不能包含非法字符
     */
    int INVALID_SYMBOL = 10029;
}
