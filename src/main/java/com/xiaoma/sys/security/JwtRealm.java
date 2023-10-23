package com.xiaoma.sys.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoma.sys.entity.SysSuperAdminEntity;
import com.xiaoma.sys.entity.SysTokenEntity;
import com.xiaoma.sys.entity.SysUserEntity;
import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.security.userInfo.UserDetail;
import com.xiaoma.sys.service.SysSuperAdminService;
import com.xiaoma.sys.service.SysTokenService;
import com.xiaoma.sys.service.SysUserService;
import com.xiaoma.sys.utils.CodeEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private SysTokenService sysTokenService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysSuperAdminService sysSuperAdminService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JWTToken;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        // 获取token对应的实体类
        SysTokenEntity tokenEntity = sysTokenService.getOne(Wrappers.lambdaQuery(SysTokenEntity.class).eq(SysTokenEntity::getToken, accessToken));

        // Token 失效
        if (tokenEntity == null || tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
            throw new MyException(CodeEnum.SYS_ERROR_D0106.getCode());
        }

        Long userId = tokenEntity.getUserId();

        SysUserEntity userEntity = sysUserService.getById(userId);

        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(userEntity, userDetail);

        // 检查是否是超级管理员
        SysSuperAdminEntity superAdminEntity = sysSuperAdminService.getOne(Wrappers.lambdaQuery(SysSuperAdminEntity.class).eq(SysSuperAdminEntity::getUserId, userDetail.getId()));
        userDetail.setIsSuperAdmin(superAdminEntity != null);

        // TODO 如果是超级管理员,加载出所有的路由和权限

        // TODO 缺少路由加载和权限
        return new SimpleAuthenticationInfo(userDetail, accessToken, getName());
    }
}
