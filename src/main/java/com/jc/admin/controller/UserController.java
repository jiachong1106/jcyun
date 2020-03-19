package com.jc.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.jc.admin.bean.Role;
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
	//跳转到分配角色页面并回显数据.
	@RequestMapping("/assignRole")
	public String assignRole(Integer id,Map map){	
		//查询出存在的所有角色
		List<Role> allListRole = userService.querAllRole();
		List<Integer> roleIds = userService.queryRoleByUserid(id);
		//查询出指定用户拥有的角色
		List<Role> leftRoleList = new ArrayList<Role>(); //未分配角色
		List<Role> rightRoleList = new ArrayList<Role>(); //已分配角色
		
		for(Role role : allListRole){			
			//roleIds中包含role的id，则为拥有该权限
			if(roleIds.contains(role.getId())){
				rightRoleList.add(role);
			}else{
				leftRoleList.add(role);
			}
		}
		//用户未拥有的权限
		map.put("leftRoleList", leftRoleList);
		//用户拥有的权限
		map.put("rightRoleList", rightRoleList);
		return "user/assignrole";
	}
	
	//执行角色分配操作
	@ResponseBody
	@RequestMapping("/doAssignRole")
	public Msg doAssignRole(Integer[] ids,Integer userId) {
		//先输出用户的所有角色
		userService.updateUserRole(ids,userId);
		return Msg.success();
	}
	
}
