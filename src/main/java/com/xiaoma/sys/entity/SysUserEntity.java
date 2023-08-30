package com.xiaoma.sys.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import com.xiaoma.sys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 * 
 * @author mjh
 * @email 15234829838@163.com
 * @date 2023-08-30 15:09:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class SysUserEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
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
}
