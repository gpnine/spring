package com.zcl.study.spring.controller;

import com.zcl.study.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chenglin Zhu
 * @date 2020/6/3
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        return userService.getCode(phone);
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String code = request.getParameter("vCode");
        String password = request.getParameter("password");
        return userService.login(phone, password, code);
    }
}
