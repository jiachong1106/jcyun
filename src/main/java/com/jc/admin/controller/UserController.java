package com.jc.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.admin.bean.User;
import com.jc.admin.service.UserService;
import com.jc.utils.Msg;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	
	/**
	 * 跳转到用户查询页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/user/index";
	}
	/**
	 * 分页查询用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doIndex")
	public Msg doIndex(@RequestParam(value="pageno",defaultValue ="1")Integer pageno) {
		System.out.println("准备查询用户");
		//引入PageHelper插件
		//传入起始页码及每页的记录数
		PageHelper.startPage(pageno, 10);
		//startPage后紧跟的查询就是分页查询
		List<User> users= userService.queryALL();
		  //用PageInfo对结果进行包装,同时传入要连续显示的页数
        PageInfo page = new PageInfo(users, 5);
		return Msg.success().add("pageInfo", page);
	}
}
