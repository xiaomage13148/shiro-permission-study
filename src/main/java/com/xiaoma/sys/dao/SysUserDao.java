package com.xiaoma.sys.dao;

import com.xiaoma.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author mjh
 * @email 15234829838@163.com
 * @date 2023-08-30 15:09:59
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
}
