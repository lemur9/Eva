package com.lemur.eva.modules.eva.system.repository;

import com.lemur.eva.core.security.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    @Select("SELECT c_id, c_username, c_password, c_real_name, c_head_url, c_gender, c_email, c_mobile, c_dept_id, c_super_admin, c_status, c_creator, c_create_date, c_updater, c_update_date " +
            "FROM t_user WHERE c_id = #{userId}")
    @Results({
            @Result(column = "c_id", property = "id"),
            @Result(column = "c_username", property = "username"),
            @Result(column = "c_password", property = "password"),
            @Result(column = "c_real_name", property = "realName"),
            @Result(column = "c_head_url", property = "headUrl"),
            @Result(column = "c_gender", property = "gender"),
            @Result(column = "c_email", property = "email"),
            @Result(column = "c_mobile", property = "mobile"),
            @Result(column = "c_dept_id", property = "deptId"),
            @Result(column = "c_super_admin", property = "superAdmin"),
            @Result(column = "c_status", property = "status"),
            @Result(column = "c_creator", property = "creator"),
            @Result(column = "c_create_date", property = "createDate"),
            @Result(column = "c_updater", property = "updater"),
            @Result(column = "c_update_date", property = "updateDate"),
    })
    User selectById(Long userId);
}
