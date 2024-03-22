package com.lemur.eva.heyeva.core.template;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataResult {

    /* 数据响应状态 */
    private int status = ResultCodeMeta.STA_SUC;

    private String title;
    private String msgTitle;
    private String msg;
    private String detail;

    /* 处理完成时间 */
    private String dateTime = "1970-01-01 00:00";

    private Object meta;

    public DataResult() {
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.msg = "";
    }

    public DataResult(String title) {
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.title = title;
        this.msg = "";
    }

    public static DataResult ok(Object meta, String msg) {
        DataResult ok = new DataResult();
        ok.setMsg(msg);
        ok.setMeta(meta);
        return ok;
    }

    public static DataResult ok(Object meta) {
        DataResult ok = new DataResult();
        ok.setMeta(meta);
        return ok;
    }

    public static DataResult error(Object meta, String title, String msg) {
        DataResult ok = new DataResult(title);
        ok.setMsg(msg);
        ok.setMeta(meta);
        return ok;
    }

    public static DataResult error(String title, String msg) {
        DataResult ok = new DataResult(title);
        ok.setMsg(msg);
        return ok;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public boolean isSuc() {
        return this.status == ResultCodeMeta.STA_SUC;
    }

    public boolean notExist() {
        return this.status == ResultCodeMeta.STA_OBJ_NOT_EXIST;
    }
}