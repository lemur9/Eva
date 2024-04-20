package com.lemur.eva.modules.eva.system.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDataScopeRepository {

    /**
     * 获取用户的部门数据权限列表
     */
    @Select("SELECT t2.c_dept_id " +
            "FROM t_role_user t1 " +
            "JOIN t_role_data_scope t2 ON t1.c_role_id = t2.c_role_id " +
            "WHERE t1.c_user_id = #{value} ")
    List<String> getDataScopeList(String userId);
}
