package com.lemur.eva.modules.core.log.repository;


import com.lemur.eva.modules.core.log.pojo.LogError;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogErrorRepository {

    @Insert("INSERT INTO t_log_error() VALUES()")
    void insert(LogError log);
}
