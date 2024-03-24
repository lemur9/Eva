package com.lemur.eva.core.result;

public interface DataResultMeta {
    // 数据请求成功状态码
    int STA_SUC = 200;

    // 对象不存在
    int STA_OBJ_NOT_EXIST = 210;

    // 客户端异常状态码
    int STA_EXCEPTION_PARAM_ERR = 405;

    // 服务器内部异常
    int INTERNAL_SERVER_ERROR = 500;

    // 未授权
    int UNAUTHORIZED = 401;

    // 重定向标头
    String STR_REDIRECT_HEADER = "Apollo-Redirect";

    // 重定向路径标头
    String STR_REDIRECT_PATH_HEADER = "Apollo-Redirect-Path";

    // 等待超时时间
    int WAIT_TIMEOUT = 600;
}
