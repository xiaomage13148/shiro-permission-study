package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色路由关联表
 *
 * @TableName sys_role_route
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_route")
@Data
public class SysRoleRouteEntity extends BaseEntity implements Serializable {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 路由id
     */
    private Long routeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}