package com.xiaoma.sys.security;

import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JwtRealm extends AuthorizingRealm {

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

        String credentials = (String) token.getCredentials();
        String username = null;

        try {
            Boolean verify = JWTUtil.verify(credentials);
            // 校验不通过
            if (!verify) {
                throw new MyException(CodeEnum.SYS_ERROR_D0103.getCode());
            }

           username = JWTUtil.getClaim(credentials, JWTUtil.getUsername());
        }catch (Exception e) {
            throw new AuthenticationException("shiro认证异常");
        }

        return new SimpleAuthenticationInfo(
                username, //用户名
                credentials, //凭证
                getName()  //realm name
        );
    }
}
