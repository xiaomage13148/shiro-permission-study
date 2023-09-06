package com.xiaoma.sys.security;


import com.alibaba.fastjson.JSON;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.Constant;
import com.xiaoma.sys.utils.ResultInfo;
import com.xiaoma.sys.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
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
     * 获取JWTToken
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
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        // 预检请求 , 放行
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return false;
    }

    /**
     * 处理被 isAccessAllowed 方法判定为拒绝访问的请求
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        //获取请求token，如果token不存在，直接返回401
        String token = getToken((HttpServletRequest) request);
        if(StringUtils.isNullOrEmpty(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            // TODO 跨域问题
//            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            String json = JSON.toJSONString(new ResultInfo<Object>(CodeEnum.SYS_ERROR_D0102.getCode(), CodeEnum.SYS_ERROR_D0102.getDescription()));
            httpResponse.getWriter().print(json);
            return false;
        }
        // TODO 登录操作
        return false;
    }


    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = getToken(req);
        JWTToken jwtToken = new JWTToken(token);
        getSubject(request , response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("preHandle");
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;
        res.setHeader("Access-control-Allow-Origin",req.getHeader("Origin"));
        res.setHeader("Access-control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        res.setHeader("Access-control-Allow-Headers",req.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        // TODO 跨域处理
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

}
