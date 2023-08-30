package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 权限路由关联表
 * @TableName sys_permission_route
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_permission_route")
@Data
public class SysPermissionRouteEntity extends BaseEntity implements Serializable {

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 路由id
     */
    private Long routeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}