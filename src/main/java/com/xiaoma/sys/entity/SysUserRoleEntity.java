package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户角色关联表
 *
 * @TableName sys_user_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_role")
@Data
public class SysUserRoleEntity extends BaseEntity implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}