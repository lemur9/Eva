package com.lemur.eva.modules.core.systemmaintenance.pojo;

import lombok.Data;

@Data
public class ServiceStatus {

    private boolean dbStatus;

    private String dbStartTime;

    private boolean webStatus;

    private String webStartTime;

    private boolean redisStatus;

    private String redisStartTime;

    private String esStartTime;

    private boolean esStatus;
}
