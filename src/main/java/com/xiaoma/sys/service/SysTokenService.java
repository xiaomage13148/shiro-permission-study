package com.xiaoma.sys.service;

import com.xiaoma.sys.dto.TokenInfoDTO;
import com.xiaoma.sys.entity.SysTokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoma.sys.entity.SysUserEntity;

/**
* @author 15234
* @description 针对表【sys_token(用户Token表)】的数据库操作Service
* @createDate 2023-09-01 14:22:26
*/
public interface SysTokenService extends IService<SysTokenEntity> {

    /**
     * 根据用户信息生成token
     */
    TokenInfoDTO generateTokenByUserInfo(SysUserEntity user);
}
