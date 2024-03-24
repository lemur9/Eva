package com.lemur.eva.modules.eva.system.repository;


import com.lemur.eva.core.security.pojo.SysUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserTokenRepository {

    @Select("SELECT c_id, c_user_id, c_token, c_expire_date, c_update_date, c_create_date " +
            "FROM t_user_token WHERE c_token = #{value}")
    @Results({
            @Result(column = "c_id", property = "id"),
            @Result(column = "c_user_id", property = "userId"),
            @Result(column = "c_token", property = "token"),
            @Result(column = "c_expire_date", property = "expireDate"),
            @Result(column = "c_update_date", property = "updateDate"),
            @Result(column = "c_create_date", property = "createDate"),
    })
    SysUserToken getByToken(String token);
}
