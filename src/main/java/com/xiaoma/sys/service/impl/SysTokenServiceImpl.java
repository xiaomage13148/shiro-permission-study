package com.xiaoma.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoma.sys.dto.TokenInfoDTO;
import com.xiaoma.sys.entity.SysTokenEntity;
import com.xiaoma.sys.entity.SysUserEntity;
import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.service.SysTokenService;
import com.xiaoma.sys.mapper.SysTokenMapper;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.PasswordUtils;
import com.xiaoma.sys.validation.ObjectValidator;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;

/**
* @author 15234
* @description 针对表【sys_token(用户Token表)】的数据库操作Service实现
* @createDate 2023-09-01 14:22:26
*/
@Service
public class SysTokenServiceImpl extends ServiceImpl<SysTokenMapper, SysTokenEntity>
    implements SysTokenService{

    // 12小时之后过期
    private static final Integer EXPIRE = 3600 * 12;

    @SneakyThrows
    @Override
    public TokenInfoDTO generateTokenByUserInfo(SysUserEntity user) {

        // token
        String token;

        // 当前时间
        Date now = new Date();

        // 过期时间
        Date expireDate = new Date(now.getTime() + EXPIRE * 1000);

        // 判断是否生成过token
        SysTokenEntity tokenEntity = this.getOne(Wrappers.lambdaQuery(SysTokenEntity.class).eq(SysTokenEntity::getUserId, user.getId()));

        if (ObjectUtils.isEmpty(tokenEntity)) {
            // 没有生成过token
            token = PasswordUtils.hashPassword(UUID.randomUUID().toString());
            SysTokenEntity sysTokenEntity = new SysTokenEntity();
            sysTokenEntity.setUserId(user.getId());
            sysTokenEntity.setToken(token);
            sysTokenEntity.setUpdateDate(now);
            sysTokenEntity.setExpireDate(expireDate);
            baseMapper.insert(sysTokenEntity);
        }else {
            // 检验对象中的属性是否全部存在 , 排除脏数据的影响
            if (!ObjectValidator.validateObjectFields(tokenEntity)) {
                // 存在脏数据
                throw new MyException(CodeEnum.DIRTY_DATA_ERROR.getCode());
            }

            // 生成过token
            // 判断是否过期
            if (tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
                // 已经过期 , 重新生成token
                token = PasswordUtils.hashPassword(UUID.randomUUID().toString());
            }else {
                token = tokenEntity.getToken();
            }

            // 刷新
            tokenEntity.setToken(token);
            tokenEntity.setUpdateDate(now);
            tokenEntity.setExpireDate(expireDate);
            baseMapper.updateById(tokenEntity);
        }

        TokenInfoDTO tokenInfo = new TokenInfoDTO();

        tokenInfo.setToken(token);
        tokenInfo.setExpireDate(expireDate);

        return tokenInfo;
    }
}




