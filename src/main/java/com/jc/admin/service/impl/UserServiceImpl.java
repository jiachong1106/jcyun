package com.jc.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc.admin.bean.User;
import com.jc.admin.bean.UserExample;
import com.jc.admin.bean.UserExample.Criteria;
import com.jc.admin.dao.UserMapper;
import com.jc.admin.exception.LoginFailException;
import com.jc.admin.service.UserService;
import com.jc.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public User queryUserLogin(String loginacct, String userpswd) {
		User user = userMapper.queryUserlogin(loginacct,userpswd);
		if (user == null) {
			System.out.println("查询失败");
			//如果没有查到，则抛出异常，此处需新建错误类。
			throw new LoginFailException("帐号或密码错误");
		}
		return user;
	}

	@Override
	public List<User> queryALL() {
		List<User> users=userMapper.selectByExample(null);
		
		return users;
	}

	@Override
	public List<User> queryALL(String queryTest) {
		UserExample example=new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoginacctLike(queryTest);
		List<User> users=userMapper.selectByExample(example);
		System.out.println(users.size());
		return users;
	}

	@Override
	public int saveUser(User user) {
		//将日期转换为指定类型的字符串
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String createtime=sdf.format(date);
		//调用set方法，在user中添加时间
		user.setCreatetime(createtime);
		//调用set方法，在user中添加密码
		user.setUserpswd(MD5Util.digest("123456"));
		return userMapper.insertSelective(user);
	}

	@Override
	public User queryUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteUser(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);	
	}

	@Override
	public void deleteBatchUser(List<Integer> list) {
		UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        userMapper.deleteByExample(example);	
	}
}
