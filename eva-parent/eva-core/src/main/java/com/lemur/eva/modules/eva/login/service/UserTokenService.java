package com.lemur.eva.modules.eva.login.service;

import com.lemur.eva.core.constant.Constant;
import com.lemur.eva.core.constant.DateConstant;
import com.lemur.eva.core.result.DataResult;
import com.lemur.eva.core.security.oauth2.TokenGenerator;
import com.lemur.eva.core.security.pojo.UserToken;
import com.lemur.eva.modules.eva.system.repository.UserTokenRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserTokenService {
    @Resource
    private UserTokenRepository userTokenRepository;

    /**
     * 生成token
     * @param userId  用户ID
     */
    public DataResult<?> createToken(String userId) {
        //用户token
        String token;

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + DateConstant.HALF_DAY_SECONDS * 1000);

        //判断是否生成过token
        UserToken userToken = userTokenRepository.getByUserId(userId);
        if(userToken == null){
            //生成一个token
            token = TokenGenerator.generateValue();

            userToken = new UserToken();
            userToken.setId(UUID.randomUUID().toString());
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setUpdateDate(now);
            userToken.setExpireDate(expireTime);

            //保存token
            this.insert(userToken);
        }else{
            //判断token是否过期
            if(userToken.getExpireDate().getTime() < System.currentTimeMillis()){
                //token过期，重新生成token
                token = TokenGenerator.generateValue();
            }else {
                token = userToken.getToken();
            }

            userToken.setToken(token);
            userToken.setUpdateDate(now);
            userToken.setExpireDate(expireTime);

            //更新token
            this.updateById(userToken);
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put(Constant.TOKEN_HEADER, token);
        map.put("expire", DateConstant.HALF_DAY_SECONDS);
        return new DataResult<>().ok(map);
    }

    private void updateById(UserToken userToken) {
        userTokenRepository.modifyById(userToken);
    }

    private void insert(UserToken userToken) {
        userTokenRepository.insert(userToken);
    }

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    public void logout(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        userTokenRepository.updateToken(userId, token);
    }
}
