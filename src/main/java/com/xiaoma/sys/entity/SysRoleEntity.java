package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色表
 *
 * @TableName sys_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
@Data
public class SysRoleEntity extends BaseEntity implements Serializable {

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色等级
     */
    private Integer roleLevel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}