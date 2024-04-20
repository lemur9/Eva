package com.lemur.eva.modules.eva.login.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lemur.eva.core.redis.RedisKeys;
import com.lemur.eva.core.redis.RedisUtils;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaService {

    @Autowired
    private RedisUtils redisUtils;
    @Value("${eva.redis.open: false}")
    private boolean open;

    /**
     * Local Cache  5分钟过期
     */
    Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();

    public void create(HttpServletResponse response, String uuid) throws IOException {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        captcha.out(response.getOutputStream());

        //保存到缓存
        setCache(uuid, captcha.text());
    }

    private void setCache(String key, String value) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            redisUtils.set(key, value, 300);
        } else {
            localCache.put(key, value);
        }
    }

    /**
     * 验证码效验
     *
     * @param id   id
     * @param code 验证码
     * @return true：成功  false：失败
     */
    public boolean validate(String id, String code) {
        //获取验证码
        String captcha = getCache(id);

        //效验成功
        return code.equalsIgnoreCase(captcha);
    }

    private String getCache(String key) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            String captcha = (String) redisUtils.get(key);
            //删除验证码
            if (captcha != null) {
                redisUtils.delete(key);
            }

            return captcha;
        }

        String captcha = localCache.getIfPresent(key);
        //删除验证码
        if (captcha != null) {
            localCache.invalidate(key);
        }
        return captcha;
    }
}
