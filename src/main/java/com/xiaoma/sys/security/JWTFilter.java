package com.xiaoma.sys.security;


import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.Constant;
import com.xiaoma.sys.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * 是否允许访问，如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = getToken(req);
        // 检查token是否存在
        if (token != null) {
            // 检查token是否正确
            try {
                executeLogin(request , response);
                return true;
            } catch (Exception e) {
                // token异常
                throw new MyException(CodeEnum.SYS_ERROR_D0102.getCode());
            }
        }
        // 不携带token，直接通过
        // TODO 可能存在问题
        return true;
    }

    /**
     * 处理被 isAccessAllowed 方法判定为拒绝访问的请求
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // TODO onAccessDenied 具体内容未完成
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
