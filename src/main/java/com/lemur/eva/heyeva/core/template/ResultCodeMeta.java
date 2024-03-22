package com.lemur.eva.heyeva.core.template;

public class ResultCodeMeta {
    /* 数据请求成功状态码*/
    public static final int STA_SUC = 200;
    public static final int STA_OBJ_NOT_EXIST = 210;

    /*客户端异常状态码*/
    public static final int STA_EXCEPTION_PARAM_ERR = 405;

    /*服务端异常状态码 550-599为公有状态码  610-699为私有状态码 */
    public static final int STA_EXCEPTION = 500;
}
