package com.lemur.eva.core.result;

import com.lemur.eva.core.exception.ErrorCodeMeta;
import com.lemur.eva.core.utils.MessageUtils;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DataResult<T> {

    // 数据响应状态
    private int code = DataResultMeta.STA_SUC;

    // 标题
    private String title;

    // 消息简介
    private String msgTitle;

    // 消息
    private String msg;

    // 详情
    private String detail;

    // 处理完成时间
    private String dateTime;

    private T meta;

    public DataResult() {
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.msg = "";
    }

    public DataResult(String title) {
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.title = title;
        this.msg = "";
    }

    public DataResult<T> ok() {
        return this;
    }

    public DataResult<T> ok(T meta) {
        this.meta = meta;
        return this;
    }

    public DataResult<T> error() {
        this.code = ErrorCodeMeta.INTERNAL_SERVER_ERROR;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public DataResult<T> error(int code) {
        this.code = code;
        return this;
    }

    public DataResult<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public DataResult<T> error(T meta, String title, String msg) {
        this.meta = meta;
        this.title = title;
        this.msg = msg;
        return this;
    }

    public boolean isSuc() {
        return this.code == DataResultMeta.STA_SUC;
    }

    public boolean notExist() {
        return this.code == DataResultMeta.STA_OBJ_NOT_EXIST;
    }
}