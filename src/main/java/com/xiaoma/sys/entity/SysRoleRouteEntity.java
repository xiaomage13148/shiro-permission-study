package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色路由关联表
 * 
 * @author mjh
 * @email 15234829838@163.com
 * @date 2023-08-30 15:09:59
 */
@Data
@TableName("sys_role_route")
public class SysRoleRouteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 路由id
	 */
	private Long routeId;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人
	 */
	private String updater;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否有效  1有效  2无效
	 */
	private String deleteStatus;

}
