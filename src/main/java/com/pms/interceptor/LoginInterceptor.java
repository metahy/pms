package com.pms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的RUi:去除http:localhost:8080这部分剩下的
        String uri = request.getRequestURI();
        //uri:除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
        if (uri.contains("/user/login") || uri.contains("/user/logout") || uri.contains("/user/register")) {
            return true;
        }
        //获取session
        HttpSession session = request.getSession();
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (session.getAttribute("id") != null) {
            return true;
        }
        //不符合条件的给出提示信息，并转发到登录页面
        response.sendRedirect("/user/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}