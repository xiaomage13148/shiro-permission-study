package com.xiaoma.sys.security;


import com.xiaoma.sys.utils.Constant;
import com.xiaoma.sys.utils.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends AccessControlFilter {

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


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 获取token
        String token = getToken((HttpServletRequest) request);
        JWTToken jwtToken = new JWTToken(token);

        try {
            // 委托 realm 进行登录认证
            getSubject(request , response).login(jwtToken);
        }catch (Exception e) {
            // TODO 后续改成自定义异常
            e.printStackTrace();
            onLoginFail(response);
            //调用下面的方法向客户端返回错误信息
            return false;
        }

        return true;
    }

    //登录失败时默认返回 401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}
