package com.lemur.eva.modules.eva.login.repository;

import com.lemur.eva.modules.eva.login.pojo.LogLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogLoginRepository {

    @Insert("INSERT INTO t_log_login(c_id, c_operation, c_status, c_user_agent, c_ip, c_creator_name, c_creator, c_create_date) " +
            "VALUES (#{id}, #{operation}, #{status}, #{userAgent}, #{ip}, #{creatorName}, #{creator}, #{createDate})")
    void insert(LogLogin log);
}
