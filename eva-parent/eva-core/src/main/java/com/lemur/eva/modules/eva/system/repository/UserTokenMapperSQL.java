package com.lemur.eva.modules.eva.system.repository;

import com.lemur.eva.core.security.pojo.UserToken;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class UserTokenMapperSQL {
    public String modifyById(final UserToken userToken) {
        SQL sql = new SQL() {{
            UPDATE("t_user_token");
            if (StringUtils.hasText(userToken.getUserId())) {
                SET("c_user_id = #{userId}");
            }

            if (StringUtils.hasText(userToken.getToken())) {
                SET("c_token = #{token}");
            }

            if (!ObjectUtils.isEmpty(userToken.getExpireDate())) {
                SET("c_expire_date = #{expireDate}");
            }

            if (!ObjectUtils.isEmpty(userToken.getUpdateDate())) {
                SET("c_update_date = #{updateDate}");
            }

            if (!ObjectUtils.isEmpty(userToken.getCreateDate())) {
                SET("c_create_date = #{createDate}");
            }

            WHERE("c_id = #{id}");
        }};

        return sql.toString();
    }
}
