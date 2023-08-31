drop database if exists `xiaomage_permission`;
create database `xiaomage_permission`;
use xiaomage_permission;

# 用户表
drop table if exists `sys_user`;
create table `sys_user`
(
    `id`            bigint        not null,
    `username`      varchar(255)  not null comment '用户名',
    `password`      varchar(255)  not null comment '密码',
    `nickname`      varchar(255)  not null comment '昵称',
    `head_image`    varchar(1023) not null comment '头像',
    `creator`       varchar(255)       default null comment '创建人',
    `create_time`   timestamp     null default current_timestamp comment '创建时间',
    `updater`       varchar(255)       default null comment '修改人',
    `update_time`   timestamp     null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status` varchar(1)         default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

# 角色表
drop table if exists `sys_role`;
create table `sys_role`
(
    `id`            bigint    not null,
    `role_name`     varchar(255)   default null comment '角色名',
    `role_level`    int       not null comment '角色等级',
    `creator`       varchar(255)   default null comment '创建人',
    `create_time`   timestamp null default current_timestamp comment '创建时间',
    `updater`       varchar(255)   default null comment '修改人',
    `update_time`   timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status` varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

# 用户角色关联表
drop table if exists `sys_user_role`;
create table `sys_user_role`
(
    `id`            bigint    not null,
    `user_id`       bigint    not null comment '用户id',
    `role_id`       bigint    not null comment '角色id',
    `creator`       varchar(255)   default null comment '创建人',
    `create_time`   timestamp null default current_timestamp comment '创建时间',
    `updater`       varchar(255)   default null comment '修改人',
    `update_time`   timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status` varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';

# 路由表
drop table if exists `sys_route`;
create table `sys_route`
(
    `id`              bigint    not null,
    `route_name`      varchar(255)   default null comment '路由名称',
    `route_value`     varchar(255)   default null comment '路由路径',
    `route_component` varchar(255)   default null comment '路由对应组件',
    `creator`         varchar(255)   default null comment '创建人',
    `create_time`     timestamp null default current_timestamp comment '创建时间',
    `updater`         varchar(255)   default null comment '修改人',
    `update_time`     timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status`   varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='路由表';

# 角色路由关联表
drop table if exists `sys_role_route`;
create table `sys_role_route`
(
    `id`            bigint    not null,
    `role_id`       bigint    not null comment '角色id',
    `route_id`      bigint    not null comment '路由id',
    `creator`       varchar(255)   default null comment '创建人',
    `create_time`   timestamp null default current_timestamp comment '创建时间',
    `updater`       varchar(255)   default null comment '修改人',
    `update_time`   timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status` varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色路由关联表';

# 权限表
drop table if exists `sys_permission`;
create table `sys_permission`
(
    `id`              bigint    not null,
    `permission_code` varchar(255)   default null comment '权限代码',
    `permission_name` varchar(255)   default null comment '权限名称',
    `creator`         varchar(255)   default null comment '创建人',
    `create_time`     timestamp null default current_timestamp comment '创建时间',
    `updater`         varchar(255)   default null comment '修改人',
    `update_time`     timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status`   varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

# 权限路由关联表
drop table if exists `sys_permission_route`;
create table `sys_permission_route`
(
    `id`            bigint    not null,
    `permission_id` bigint    not null comment '权限id',
    `route_id`      bigint    not null comment '路由id',
    `creator`       varchar(255)   default null comment '创建人',
    `create_time`   timestamp null default current_timestamp comment '创建时间',
    `updater`       varchar(255)   default null comment '修改人',
    `update_time`   timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status` varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限路由关联表';

# 超级管理员表
drop table if exists `sys_super_admin`;
create table `sys_super_admin`
(
    `id`            bigint    not null,
    `user_id`       bigint    not null comment '用户id',
    `username`      bigint    not null comment '用户名称',
    `creator`       varchar(255)   default null comment '创建人',
    `create_time`   timestamp null default current_timestamp comment '创建时间',
    `updater`       varchar(255)   default null comment '修改人',
    `update_time`   timestamp null default current_timestamp on update current_timestamp comment '修改时间',
    `delete_status` varchar(1)     default '1' comment '是否有效  1有效  2无效',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='超级管理员表';

