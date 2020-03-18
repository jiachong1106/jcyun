package com.jc.admin.service;

import java.util.List;

import com.jc.admin.bean.User;

public interface UserService {
	/**
	 * 查询用户是否存在
	 * @param userpswd 
	 * @param loginacct 
	 * @return
	 */
	public User queryUserLogin(String loginacct, String userpswd);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryALL();
	
	
}
