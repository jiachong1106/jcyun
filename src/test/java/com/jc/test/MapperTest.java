package com.jc.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jc.admin.bean.User;
import com.jc.admin.dao.UserMapper;
import com.jc.utils.MD5Util;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	UserMapper userMapper;
	@Autowired
	SqlSession sqlsession;
	@Test
	 public void testCRUD() {
         //批量插入用户
         //方法1：效率较低
		/*  for() {
			 employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@163.com", 1)); 
		 }*/
         //方法2：使用可以批量操作的sqlsession
		  UserMapper userMapper =sqlsession.getMapper(UserMapper.class);
		 for(int i=0;i<100;i++) {
			// String uuid = UUID.randomUUID().toString().substring(0, 5)+i;
			 String pwd = MD5Util.digest("123456");
			 userMapper.insertSelective(new User(null,"user"+i,pwd,"user"+i+"@qq.com",null));
		 }
		 System.out.println("插入成功");
	}
}
