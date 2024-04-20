package com.lemur.eva.modules.eva.message.pojo;

import lombok.Data;

@Data
public class Message {

    // id
    private String id;

    // 消息内容
    private String text;

    // 消息类型
    private int type;
}
