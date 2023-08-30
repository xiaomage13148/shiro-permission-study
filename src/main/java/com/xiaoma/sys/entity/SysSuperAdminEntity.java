package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 超级管理员表
 *
 * @TableName sys_super_admin
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_super_admin")
@Data
public class SysSuperAdminEntity extends BaseEntity implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private Long username;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}