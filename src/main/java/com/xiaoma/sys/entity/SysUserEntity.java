package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 * @TableName sys_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user")
@Data
public class SysUserEntity extends BaseEntity implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headImage;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}