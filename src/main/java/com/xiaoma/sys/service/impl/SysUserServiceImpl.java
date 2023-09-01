package com.xiaoma.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoma.sys.entity.SysUserEntity;
import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.service.SysUserService;
import com.xiaoma.sys.mapper.SysUserMapper;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.PasswordUtils;
import com.xiaoma.sys.vo.SysUserLoginByUsernameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.file.Watchable;

/**
* @author 15234
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-08-30 16:33:32
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
    implements SysUserService{

    @Override
    public Boolean checkSysUserIsExist(String username) {
        SysUserEntity sysUserEntity = this.getOne(Wrappers.lambdaQuery(SysUserEntity.class).eq(SysUserEntity::getUsername , username));
        if (ObjectUtils.isEmpty(sysUserEntity)) {
            return false; // 为空 不存在
        }
        return true;  // 不为空 存在
    }

    @Override
    public SysUserEntity checkUserInputIsTrue(SysUserLoginByUsernameVo vo) {
        SysUserEntity user = new SysUserEntity();

        SysUserEntity sysUserEntity = this.getOne(Wrappers.lambdaQuery(SysUserEntity.class).eq(SysUserEntity::getUsername, vo.getUsername()));
        if (ObjectUtils.isEmpty(sysUserEntity)) {
            throw new MyException(CodeEnum.USER_ERROR_A0110.getCode());
        }

        if (sysUserEntity.getPassword() == null || !(PasswordUtils.verifyPassword(vo.getPassword() , sysUserEntity.getPassword()))) {
            throw new MyException(CodeEnum.USER_ERROR_A0120.getCode());
        }

        BeanUtils.copyProperties(sysUserEntity , user);
        return user;
    }

}




