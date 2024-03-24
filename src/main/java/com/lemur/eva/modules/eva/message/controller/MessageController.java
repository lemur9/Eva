package com.lemur.eva.modules.eva.message.controller;

import com.lemur.eva.core.result.DataResult;
import com.lemur.eva.modules.eva.message.pojo.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {

    @PostMapping("sendMsg")
    public DataResult<?> index(@RequestBody Message message) {
        DataResult<Object> rv = new DataResult<>();
        message.setId("1");
        message.setType((int) Math.round(Math.random()));
        return rv.ok(message);
    }

}

