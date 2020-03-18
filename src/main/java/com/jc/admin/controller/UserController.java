package com.jc.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jc.admin.bean.User;
import com.jc.admin.service.UserService;
import com.jc.utils.Msg;
import com.mysql.fabric.xmlrpc.base.Array;

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
	 * 跳转到用户添加页面
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/user/add";
	}
	/**
	 * 跳转到用户修改页面
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Integer id,Map map) {
		User user=userService.queryUserById(id);
		map.put("user", user);
		return "/user/update";
	}
	
	/**
	 * 分页查询用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doIndex")
	public Msg doIndex(@RequestParam(value="pageno",defaultValue ="1")Integer pageno,String queryText) {
		System.out.println("准备查询用户");
		//引入PageHelper插件
		//传入起始页码及每页的记录数
		PageHelper.startPage(pageno, 10);
		//startPage后紧跟的查询就是分页查询
		List<User> users=null;
		if(StringUtil.isNotEmpty(queryText)) {
			if(queryText.contains("%")) {
				queryText=queryText.replaceAll("%", "\\\\%");
			}
			System.out.println("不为空");
			queryText="%"+queryText+"%";
			users= userService.queryALL(queryText);
		}else {
			users= userService.queryALL();
		}
		//用PageInfo对结果进行包装,同时传入要连续显示的页数
		PageInfo page = new PageInfo(users, 5);
		return Msg.success().add("pageInfo", page);
	}
	/**
	 * 添加用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Msg doAdd(User user) {
		userService.saveUser(user);
		return Msg.success();
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Msg doUpdate(User user){
		userService.updateUser(user);
		return Msg.success();
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id){
		userService.deleteUser(id);
		return Msg.success(); 
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doBatchDelete")
	 public Msg doBatchDelete(Integer[] id) {
		//id=1$id=2
		System.out.println("批量删除");
		List<Integer> list = Arrays.asList(id);
		userService.deleteBatchUser(list);
		
		/*	id=id.replaceAll("id=", "");
			
			if(id.contains("&")) {
		    List<Integer> del_ids = new ArrayList<Integer>();
		    String[] str_ids=id.split("&");
		    for (String string : str_ids) {
		         del_ids.add(Integer.parseInt(string));
		    }
		    userService.deleteBatchUser(del_ids);
		}else {
		    Integer del_id=Integer.parseInt(id);
		    userService.deleteUser(del_id);
		}*/
		return Msg.success();
   }
	
}
