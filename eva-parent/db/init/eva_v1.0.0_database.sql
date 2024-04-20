-- 系统用户
CREATE TABLE t_user
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_username    varchar(50) NOT NULL COMMENT '用户名',
    c_password    varchar(100) COMMENT '密码',
    c_real_name   varchar(50) COMMENT '姓名',
    c_head_url    varchar(200) COMMENT '头像',
    c_gender      tinyint unsigned COMMENT '性别   0：男   1：女    2：保密',
    c_email       varchar(100) COMMENT '邮箱',
    c_mobile      varchar(100) COMMENT '手机号',
    c_dept_id     bigint COMMENT '部门ID',
    c_super_admin tinyint unsigned COMMENT '超级管理员   0：否   1：是',
    c_status      tinyint COMMENT '状态  0：停用   1：正常',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    c_updater     bigint COMMENT '更新者',
    c_update_date datetime COMMENT '更新时间',
    primary key (c_id),
    unique key uk_c_username (c_username),
    key           idx_c_create_date (c_create_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- 部门
CREATE TABLE t_dept
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_pid         bigint COMMENT '上级ID',
    c_pids        varchar(500) COMMENT '所有上级ID，用逗号分开',
    c_name        varchar(50) COMMENT '部门名称',
    c_sort        int unsigned COMMENT '排序',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    c_updater     bigint COMMENT '更新者',
    c_update_date datetime COMMENT '更新时间',
    primary key (c_id),
    key           idx_c_pid (c_pid),
    key           idx_c_sort (c_sort)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门管理';

-- 角色管理
create table t_role
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_name        varchar(50) COMMENT '角色名称',
    c_remark      varchar(100) COMMENT '备注',
    c_dept_id     bigint COMMENT '部门ID',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    c_updater     bigint COMMENT '更新者',
    c_update_date datetime COMMENT '更新时间',
    primary key (c_id),
    key           idx_c_dept_id (c_dept_id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='角色管理';

-- 菜单管理
create table t_menu
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_pid         bigint COMMENT '上级ID，一级菜单为0',
    c_name        varchar(200) COMMENT '名称',
    c_url         varchar(200) COMMENT '菜单URL',
    c_permissions varchar(500) COMMENT '授权(多个用逗号分隔，如：sys:signatureUser:list,sys:signatureUser:save)',
    c_type        tinyint unsigned COMMENT '类型   0：菜单   1：按钮',
    c_icon        varchar(50) COMMENT '菜单图标',
    c_sort        int COMMENT '排序',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    c_updater     bigint COMMENT '更新者',
    c_update_date datetime COMMENT '更新时间',
    primary key (c_id),
    key           idx_c_pid (c_pid),
    key           idx_c_sort (c_sort)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='菜单管理';

-- 角色用户关系
create table t_role_user
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_role_id     bigint COMMENT '角色ID',
    c_user_id     bigint COMMENT '用户ID',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    primary key (c_id),
    key           idx_c_role_id (c_role_id),
    key           idx_c_user_id (c_user_id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='角色用户关系';

-- 角色菜单关系
create table t_role_menu
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_role_id     bigint COMMENT '角色ID',
    c_menu_id     bigint COMMENT '菜单ID',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    primary key (c_id),
    key           idx_c_role_id (c_role_id),
    key           idx_c_menu_id (c_menu_id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='角色菜单关系';

-- 角色数据权限
create table t_role_data_scope
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_role_id     bigint COMMENT '角色ID',
    c_dept_id     bigint COMMENT '部门ID',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    primary key (c_id),
    key           idx_c_role_id (c_role_id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='角色数据权限';

-- 参数管理
create table t_params
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_param_code  varchar(32) COMMENT '参数编码',
    c_param_value varchar(2000) COMMENT '参数值',
    c_param_type  tinyint unsigned default 1 COMMENT '类型   0：系统参数   1：非系统参数',
    c_remark      varchar(200) COMMENT '备注',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    c_updater     bigint COMMENT '更新者',
    c_update_date datetime COMMENT '更新时间',
    primary key (c_id),
    unique key uk_c_param_code (c_param_code),
    key           idx_c_create_date (c_create_date)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='参数管理';

-- 字典类型
create table t_dict_type
(
    c_id          varchar(64)  NOT NULL COMMENT 'id',
    c_dict_type   varchar(100) NOT NULL COMMENT '字典类型',
    c_dict_name   varchar(255) NOT NULL COMMENT '字典名称',
    c_remark      varchar(255) COMMENT '备注',
    c_sort        int unsigned COMMENT '排序',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    c_updater     bigint COMMENT '更新者',
    c_update_date datetime COMMENT '更新时间',
    primary key (c_id),
    UNIQUE KEY (c_dict_type)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='字典类型';

-- 字典数据
create table t_dict_data
(
    c_id           varchar(64)  NOT NULL COMMENT 'id',
    c_dict_type_id bigint       NOT NULL COMMENT '字典类型ID',
    c_dict_label   varchar(255) NOT NULL COMMENT '字典标签',
    c_dict_value   varchar(255) COMMENT '字典值',
    c_remark       varchar(255) COMMENT '备注',
    c_sort         int unsigned COMMENT '排序',
    c_creator      bigint COMMENT '创建者',
    c_create_date  datetime COMMENT '创建时间',
    c_updater      bigint COMMENT '更新者',
    c_update_date  datetime COMMENT '更新时间',
    primary key (c_id),
    unique key uk_c_dict_type_value (c_dict_type_id, c_dict_value),
    key            idx_c_sort (c_sort)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='字典数据';

-- 登录日志
create table t_log_login
(
    c_id           varchar(64) NOT NULL COMMENT 'id',
    c_operation    tinyint unsigned COMMENT '用户操作   0：用户登录   1：用户退出',
    c_status       tinyint unsigned NOT NULL COMMENT '状态  0：失败    1：成功    2：账号已锁定',
    c_user_agent   varchar(500) COMMENT '用户代理',
    c_ip           varchar(32) COMMENT '操作IP',
    c_creator_name varchar(50) COMMENT '用户名',
    c_creator      bigint COMMENT '创建者',
    c_create_date  datetime COMMENT '创建时间',
    primary key (c_id),
    key            idx_c_status (c_status),
    key            idx_c_create_date (c_create_date)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='登录日志';

-- 操作日志
create table t_log_operation
(
    c_id             varchar(64) NOT NULL COMMENT 'id',
    c_operation      varchar(50) COMMENT '用户操作',
    c_request_uri    varchar(200) COMMENT '请求URI',
    c_request_method varchar(20) COMMENT '请求方式',
    c_request_params text COMMENT '请求参数',
    c_request_time   int unsigned NOT NULL COMMENT '请求时长(毫秒)',
    c_user_agent     varchar(500) COMMENT '用户代理',
    c_ip             varchar(32) COMMENT '操作IP',
    c_status         tinyint unsigned NOT NULL COMMENT '状态  0：失败   1：成功',
    c_creator_name   varchar(50) COMMENT '用户名',
    c_creator        bigint COMMENT '创建者',
    c_create_date    datetime COMMENT '创建时间',
    primary key (c_id),
    key              idx_c_create_date (c_create_date)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='操作日志';

-- 异常日志
create table t_log_error
(
    c_id             varchar(64) NOT NULL COMMENT 'id',
    c_request_uri    varchar(200) COMMENT '请求URI',
    c_request_method varchar(20) COMMENT '请求方式',
    c_request_params text COMMENT '请求参数',
    c_user_agent     varchar(500) COMMENT '用户代理',
    c_ip             varchar(32) COMMENT '操作IP',
    c_error_info     text COMMENT '异常信息',
    c_creator        bigint COMMENT '创建者',
    c_create_date    datetime COMMENT '创建时间',
    primary key (c_id),
    key              idx_c_create_date (c_create_date)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='异常日志';


-- 文件上传
CREATE TABLE t_oss
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_url         varchar(200) COMMENT 'URL地址',
    c_creator     bigint COMMENT '创建者',
    c_create_date datetime COMMENT '创建时间',
    PRIMARY KEY (c_id),
    key           idx_c_create_date (c_create_date)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='文件上传';

-- 定时任务
CREATE TABLE t_schedule_job
(
    c_id              varchar(64) NOT NULL COMMENT 'id',
    c_bean_name       varchar(200)  DEFAULT NULL COMMENT 'spring bean名称',
    c_params          varchar(2000) DEFAULT NULL COMMENT '参数',
    c_cron_expression varchar(100)  DEFAULT NULL COMMENT 'cron表达式',
    c_status          tinyint unsigned COMMENT '任务状态  0：暂停  1：正常',
    c_remark          varchar(255)  DEFAULT NULL COMMENT '备注',
    c_creator         bigint COMMENT '创建者',
    c_create_date     datetime COMMENT '创建时间',
    c_updater         bigint COMMENT '更新者',
    c_update_date     datetime COMMENT '更新时间',
    PRIMARY KEY (c_id),
    key               idx_c_create_date (c_create_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';

-- 定时任务日志
CREATE TABLE t_schedule_job_log
(
    c_id          varchar(64) NOT NULL COMMENT 'id',
    c_job_id      bigint      NOT NULL COMMENT '任务id',
    c_bean_name   varchar(200)  DEFAULT NULL COMMENT 'spring bean名称',
    c_params      varchar(2000) DEFAULT NULL COMMENT '参数',
    c_status      tinyint unsigned NOT NULL COMMENT '任务状态    0：失败    1：成功',
    c_error       varchar(2000) DEFAULT NULL COMMENT '失败信息',
    c_times       int         NOT NULL COMMENT '耗时(单位：毫秒)',
    c_create_date datetime COMMENT '创建时间',
    PRIMARY KEY (c_id),
    key           idx_c_job_id (c_job_id),
    key           idx_c_create_date (c_create_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务日志';

-- 系统用户Token
CREATE TABLE t_user_token
(
    c_id          varchar(64)  NOT NULL COMMENT 'id',
    c_user_id     bigint       NOT NULL COMMENT '用户id',
    c_token       varchar(100) NOT NULL COMMENT '用户token',
    c_expire_date datetime COMMENT '过期时间',
    c_update_date datetime COMMENT '更新时间',
    c_create_date datetime COMMENT '创建时间',
    PRIMARY KEY (c_id),
    UNIQUE KEY c_user_id (c_user_id),
    UNIQUE KEY c_token (c_token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';
