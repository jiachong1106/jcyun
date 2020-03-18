package com.jc.admin.service;

import com.jc.admin.bean.User;

public interface UserService {
	/**
	 * 查询用户
	 * @param userpswd 
	 * @param loginacct 
	 * @return
	 */
	public User queryUserLogin(String loginacct, String userpswd);
	
	
}
