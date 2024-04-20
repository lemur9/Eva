package com.lemur.eva.modules.core.log.repository;


import com.lemur.eva.modules.core.log.pojo.LogError;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogErrorRepository {

    @Insert("INSERT INTO t_log_error(c_id, c_request_uri, c_request_method, c_request_params, c_user_agent, c_ip, c_error_info, c_creator, c_create_date) " +
            "VALUES(#{id}, #{requestUri}, #{requestMethod}, #{requestParams}, #{userAgent}, #{ip}, #{errorInfo}, #{creator}, #{createDate})")
    void insert(LogError log);
}