package com.xiaoma.sys.service;

import com.xiaoma.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoma.sys.vo.SysUserLoginByUsernameVo;
import com.xiaoma.sys.vo.SysUserRegisterVo;

/**
* @author 15234
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-08-30 16:33:32
*/
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 检查账户名称是否已经存在
     */
    Boolean checkSysUserIsExist(String username);

    /**
     * 校验用户的输入是否正确
     */
    SysUserEntity checkUserInputIsTrue(SysUserLoginByUsernameVo vo);

    /**
     * 用户注册完之后不返回信息
     */
    void userRegisterWithoutInfo(SysUserRegisterVo vo);

}
