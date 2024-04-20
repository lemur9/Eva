package com.lemur.eva.modules.eva.login.controller;

import com.lemur.eva.core.exception.ErrorCodeMeta;
import com.lemur.eva.core.exception.EvaException;
import com.lemur.eva.core.password.PasswordUtil;
import com.lemur.eva.core.result.DataResult;
import com.lemur.eva.core.security.enums.UserStatusEnum;
import com.lemur.eva.core.security.pojo.SignatureUser;
import com.lemur.eva.core.utils.IpUtils;
import com.lemur.eva.core.validator.AssertUtils;
import com.lemur.eva.modules.eva.login.dto.LoginDTO;
import com.lemur.eva.modules.eva.login.enums.LoginActionEnum;
import com.lemur.eva.modules.eva.login.enums.LoginStatusEnum;
import com.lemur.eva.modules.eva.login.pojo.LogLogin;
import com.lemur.eva.modules.eva.login.service.CaptchaService;
import com.lemur.eva.modules.eva.login.service.LogLoginService;
import com.lemur.eva.modules.eva.login.service.UserTokenService;
import com.lemur.eva.modules.eva.user.pojo.User;
import com.lemur.eva.modules.eva.user.service.UserService;
import com.lemur.eva.modules.eva.user.utils.SecurityUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 登录
 */
@Controller
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
   private UserTokenService userTokenService;

    @Resource
    private LogLoginService logLoginService;

    @Resource
    private CaptchaService captchaService;

    @GetMapping("captcha")
    @ResponseBody
    public DataResult<?> captcha(HttpServletResponse response, String uuid) {
        DataResult<?> rv = new DataResult<>();
        //uuid不能为空
        AssertUtils.isBlank(uuid, ErrorCodeMeta.IDENTIFIER_NOT_NULL);

        //生成验证码
        try {
            captchaService.create(response, uuid);
        } catch (IOException e) {
            return rv.error();
        }
        return rv.ok();
    }

    @PostMapping("login")
    @ResponseBody
    public DataResult<?> login(HttpServletRequest request, @RequestBody @Valid LoginDTO login) {

        //验证码是否正确
        boolean flag = captchaService.validate(login.getId(), login.getCaptcha());
        if (!flag) {
            return new DataResult<>().error(ErrorCodeMeta.CAPTCHA_ERROR);
        }

        //用户信息
        User user = userService.getByUname(login.getUsername());

        LogLogin log = new LogLogin();
        log.setId(UUID.randomUUID().toString());
        log.setOperation(LoginActionEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));

        //用户不存在
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            logLoginService.save(log);

            throw new EvaException(ErrorCodeMeta.ACCOUNT_PASSWORD_ERROR);
        }

        //密码错误
        if (!PasswordUtil.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUserName());
            logLoginService.save(log);

            throw new EvaException(ErrorCodeMeta.ACCOUNT_PASSWORD_ERROR);
        }

        //账号停用
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUserName());
            logLoginService.save(log);

            throw new EvaException(ErrorCodeMeta.ACCOUNT_DISABLE);
        }

        //登录成功
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUserName());
        logLoginService.save(log);

        return userTokenService.createToken(user.getId());
    }

    @PostMapping("logout")
    @ResponseBody
    public DataResult<?> logout(HttpServletRequest request) {
        SignatureUser user = SecurityUser.getUser();

        //退出
        userTokenService.logout(user.getId());

        //用户信息
        LogLogin log = new LogLogin();
        log.setOperation(LoginActionEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUserName());
        log.setCreateDate(new Date());
        logLoginService.save(log);

        return new DataResult<>();
    }

    @GetMapping("/login")
    public String login() {
        return "/eva/model/login";
    }

}