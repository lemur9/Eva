package com.lemur.eva.heyeva.message.controller;

import com.lemur.eva.heyeva.core.template.DataResult;
import com.lemur.eva.heyeva.message.pojo.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    @PostMapping("sendMsg")
    public DataResult index(@RequestBody Message message) {
        message.setId("1");
        message.setType((int) Math.round(Math.random()));
        return DataResult.ok(message);
    }

}

