package com.xiaoma.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 路由表
 * 
 * @author mjh
 * @email 15234829838@163.com
 * @date 2023-08-30 15:09:59
 */
@Data
@TableName("sys_route")
public class SysRouteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
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
