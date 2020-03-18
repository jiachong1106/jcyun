package com.jc.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jc.admin.bean.User;
import com.jc.admin.dao.UserMapper;
import com.jc.admin.exception.LoginFailException;
import com.jc.admin.service.UserService;
import com.jc.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper usermapper;

	@Override
	public User queryUserLogin(String loginacct, String userpswd) {
		System.out.println("service");
		User user = usermapper.queryUserlogin(loginacct,userpswd);
		if (user == null) {
			System.out.println("查询失败");
			//如果没有查到，则抛出异常，此处需新建错误类。
			throw new LoginFailException("帐号或密码错误");
		}
		return user;
	}

}
