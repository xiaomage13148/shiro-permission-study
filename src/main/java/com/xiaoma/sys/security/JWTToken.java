package com.xiaoma.sys.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 封装token , 代替原生的shiro token
 */
public class JWTToken implements AuthenticationToken {

    private String jwtToken;

    public JWTToken(String token) {
        this.jwtToken = token;
    }

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }
}
