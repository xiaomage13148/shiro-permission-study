package com.xiaoma.sys.security;


import com.xiaoma.sys.utils.Constant;
import com.xiaoma.sys.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JWTFilter extends AuthenticatingFilter {
    /**
     * 获取token
     * @param request 请求
     * @param response 响应
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String token = getToken((HttpServletRequest) request);
        if (StringUtils.isNullOrEmpty(token)) {
            return null;
        }
        return new JWTToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    /**
     * 获取token
     */
    private String getToken(HttpServletRequest request) {
        // 从头部获取
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if (StringUtils.isNullOrEmpty(token)) {
            token = request.getParameter(Constant.TOKEN_HEADER);
        }
        return token;
    }
}
