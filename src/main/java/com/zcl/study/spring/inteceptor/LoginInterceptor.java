package com.zcl.study.spring.inteceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取作用域的user信息,此user信息需要在登录操作的同事放到作用域,以便于此处获取
        String user=(String) request.getSession().getAttribute("user");
        //获取请求的路径
        String url=request.getRequestURI();
        //打印路径信息,作为后台日志进行查看
        System.out.println(url);
        //判断user对象的内容是否不为空;
        //或者是否是访问的后台登录方法(login);
        //因为我的页面直接导入的easyUI的js包为防止把页面样式拦截,所以加个判断,如果不是这样的话此判断可以省略
        if(user!=null|url.endsWith("项目名/login")|url.lastIndexOf("jquery-easyui-1.4")>-1) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }else {
            //判断没通过,证明用户没有进行登录操作,操作非法,进行返回登录页面(login.jsp)进行登录
            //返回错误提示信息("请先登录!")
            request.getSession().setAttribute("msg", "请先登录!");
            response.sendRedirect("login.jsp");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
