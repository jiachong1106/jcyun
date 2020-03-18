package com.jc.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jc.admin.bean.User;
import com.jc.admin.service.UserService;
import com.jc.utils.MD5Util;
import com.jc.utils.Msg;


@Controller
public class DispatcherController {
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("login");
		return "/login";
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public Msg doLogin(String loginacct,String userpswd) {
		String newPwd=MD5Util.digest(userpswd);
		User user = userService.queryUserLogin(loginacct,newPwd);
		System.out.println("找到了用户");
		return Msg.success().add("user", user);
	}
	
	@RequestMapping("/main")
	public String main() {
		System.out.println("准备登陆main页面");
		//使用重定向，如果直接用请求转发，刷新页面后，表单会重复提交。
        return "main"; 
	}
	
}
