package com.lemur.eva.modules.eva.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    // 唯一标识
    @NotBlank(message="{user.uuid.require}")
    private String id;

    // 用户名
    @NotBlank(message="{user.username.require}")
    private String username;

    // 密码
    @NotBlank(message="{user.password.require}")
    private String password;

    // 验证码
    @NotBlank(message="{user.captcha.require}")
    private String captcha;
}
