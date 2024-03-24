package com.lemur.eva.modules.eva.system.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuRepository {

    /**
     * 查询用户权限列表
     * @param userId  用户ID
     */
    @Select("SELECT t3.c_permissions " +
            "FROM t_role_user t1 " +
            "LEFT JOIN t_role_menu t2 ON t1.c_role_id = t2.c_role_id " +
            "LEFT JOIN t_menu t3 ON t2.c_menu_id = t3.c_id " +
            "WHERE t1.c_user_id = #{userId} ORDER BY t3.c_sort ASC")
    List<String> getUserPermissionsList(String userId);

    /**
     * 查询所有权限列表
     */
    @Select("select c_permissions from t_menu")
    List<String> getPermissionsList();

}
