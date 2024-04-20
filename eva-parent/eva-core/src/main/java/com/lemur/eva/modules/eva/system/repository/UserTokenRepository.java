package com.lemur.eva.modules.eva.system.repository;


import com.lemur.eva.core.security.pojo.UserToken;
import org.apache.ibatis.annotations.*;
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
    UserToken getByToken(String token);

    @Select("SELECT c_id, c_user_id, c_token, c_expire_date, c_update_date, c_create_date " +
            "FROM t_user_token WHERE c_user_id = #{userId}")
    @Results({
            @Result(column = "c_id", property = "id"),
            @Result(column = "c_user_id", property = "userId"),
            @Result(column = "c_token", property = "token"),
            @Result(column = "c_expire_date", property = "expireDate"),
            @Result(column = "c_update_date", property = "updateDate"),
            @Result(column = "c_create_date", property = "createDate"),
    })
    UserToken getByUserId(String userId);

    @Insert("INSERT INTO t_user_token(c_id, c_user_id, c_token, c_expire_date, c_update_date, c_create_date) " +
            "VALUES (#{id}, #{userId}, #{token}, #{expireDate}, #{updateDate}, #{createDate})")
    void insert(UserToken userToken);

    @UpdateProvider(type = UserTokenMapperSQL.class, method = "modifyById")
    void modifyById(UserToken userToken);

    @Update("UPDATE t_user_token SET c_token = #{token} WHERE c_user_id = #{userId}")
    void updateToken(String userId, String token);
}
