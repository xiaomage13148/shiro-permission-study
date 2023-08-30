package com.xiaoma.sys.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限表
 * @TableName sys_permission
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_permission")
@Data
public class SysPermissionEntity extends BaseEntity implements Serializable {

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}