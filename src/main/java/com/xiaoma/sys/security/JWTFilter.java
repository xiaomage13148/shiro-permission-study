package com.xiaoma.sys.security;


import com.alibaba.fastjson.JSON;
import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.Constant;
import com.xiaoma.sys.utils.ResultInfo;
import com.xiaoma.sys.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends AuthenticatingFilter {

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

    /**
     * 创建token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String token = getToken((HttpServletRequest) request);
        if (StringUtils.isNullOrEmpty(token)) {
            return null;
        }
        return new JWTToken(token);
    }

    /**
     * 处理登录失败
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        // TODO 缺少跨域请求头

        ResultInfo<Object> resultInfo = new ResultInfo<>(CodeEnum.SYS_ERROR_D0104.getCode(), CodeEnum.SYS_ERROR_D0104.getDescription());
        String json = JSON.toJSONString(resultInfo);

        try {
            httpResponse.getWriter().print(json);
        } catch (IOException ex) {
            // TODO Filter 抛出的异常 , 在全局异常捕获中无法捕获到
            throw new MyException(CodeEnum.SYS_ERROR_D0105.getCode());
        }

        return false;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 预检请求,放行
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // 获取token
        String token = getToken((HttpServletRequest) request);

        if (StringUtils.isNullOrEmpty(token)) {
            // 抛出token为空的异常
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            // TODO 缺少跨域请求头

            ResultInfo<Object> resultInfo = new ResultInfo<>(CodeEnum.SYS_ERROR_D0102.getCode(), CodeEnum.SYS_ERROR_D0102.getDescription());
            String json = JSON.toJSONString(resultInfo);

            try {
                httpResponse.getWriter().print(json);
            } catch (IOException ex) {
                throw new MyException(CodeEnum.SYS_ERROR_D0105.getCode());
            }
            return false;
        }

        return executeLogin(request , response);
    }

}
