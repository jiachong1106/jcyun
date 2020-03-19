package com.jc.admin.service;

import java.util.List;

import com.jc.admin.bean.Role;
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
	/**
	 * 模糊查询
	 * @param queryTest
	 * @return
	 */
	public List<User> queryALL(String queryTest);
	/**
	 * 添加用户
	 * @param user 
	 * @return
	 */
	public int saveUser(User user);
	/**
	 * 用户信息回显
	 * @param id
	 * @return
	 */
	public User queryUserById(Integer id);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int deleteUser(Integer id);
	/**
	 * 批量删除用户
	 * @param id
	 * @return
	 */
	public void deleteBatchUser(List<Integer> list);
	/**
	 * 查询所有用户角色
	 * @return
	 */
	public List<Role> querAllRole();
	/**
	 * 根据用户id，查询用户拥有的角色
	 * @param id
	 * @return
	 */
	public List<Integer> queryRoleByUserid(Integer id);
	
	/**
	 * 修改用户角色
	 * @param ids
	 */
	public void updateUserRole(Integer[] ids, Integer userId);
}
