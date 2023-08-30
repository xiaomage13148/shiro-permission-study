package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 路由表
 *
 * @TableName sys_route
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_route")
@Data
public class SysRouteEntity extends BaseEntity implements Serializable {


    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由路径
     */
    private String routeValue;

    /**
     * 路由对应组件
     */
    private String routeComponent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}