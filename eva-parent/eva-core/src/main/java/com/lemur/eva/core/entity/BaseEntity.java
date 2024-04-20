package com.lemur.eva.core.entity;


import lombok.Data;

import java.util.Date;

/**
 * 基础实体类，所有实体都需要继承
 *
 */
@Data
public abstract class BaseEntity {
    /**
     * id
     */
    private String id;

    /**
     * 创建者
     */
    private String  creator;

    /**
     * 创建时间
     */
    private Date createDate;
}