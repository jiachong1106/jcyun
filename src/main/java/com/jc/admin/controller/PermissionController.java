package com.jc.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jc.admin.bean.Permission;
import com.jc.admin.service.PermissionService;
import com.jc.utils.Msg;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	PermissionService permissionService;
	/**
	 * 跳转到许可展示页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/permission/index";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("/add")
	public String add(){
		return "permission/add";
	}
	/**
	 * 跳转到修改许可页面，并进行数据回显
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/update")
	public String edit(Integer id,Map map){
		Permission permission = permissionService.getPermission(id);
		map.put("permission", permission);
		return "permission/update";
	}
	/**
	 * 修改许可
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(Permission permission){	
		permissionService.updatePermission(permission);
		return Msg.success();
	}
	/**
	 * 
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object deleteUpdate(Integer id){	
		permissionService.deletePermission(id);
		return Msg.success();
	}
	
	
	/**
	 * 添加许可
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission){	
		permissionService.savePermission(permission);
		return Msg.success();
	}
	
	/**
	 * Demo 5：一次性加载所有数据，将数据放在map中，减少循环的次数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermission")
	public Msg loadData() {
		//1.查询出所有的permission
		List<Permission> allPermission =permissionService.queryAllPermission();
		//2.建一个存放所有permission的容器
		Map<Integer,Permission> map =new HashMap<>();
		//3建一个存放根节点的容器
		List<Permission> root = new ArrayList<>();
		//4.循环所有的permission并放入map中,键为permission的id,值为permission对象
		for (Permission permission : allPermission) {
			map.put(permission.getId(), permission);
		}
		//5.循环所有的permission，组合父子关系
		for (Permission permission : allPermission) {
			//5.1没有pid的为根节点
			if(permission.getPid()==null) {
				root.add(permission);
			}else {
				//根据当前对象的pid在map中找到对应的对象，即为当前对象的父节点
				Permission parentPermission = map.get(permission.getPid());
				parentPermission.getChildren().add(permission);
			}
		}
		System.out.println("成功");
		return Msg.success().add("data", root);
	}	
	/**
	 * Demo 4：一次性加载所有数据，减少与数据库的交互
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermission4")
	public Msg loadData4() {
		//1.查询出所有的permission
		List<Permission> allPermission =permissionService.queryAllPermission();
		//2.判断哪些是父节点，哪些是子节点
		//2.1 建一个存放根节点的容器
		List<Permission> root = new ArrayList<>();
		for (Permission permission : allPermission) {
			//2.2没有pid的为根节点
			if(permission.getPid()==null) {
				root.add(permission);
			}else {
				//2.3 再次循环所有的许可，找出当前许可的父节点
				for (Permission parentPermission : allPermission) {
					if(permission.getPid()==parentPermission.getId()) {
						//2.4 组合父子关系
						//因为某次循环会循环到根节点,并把根节点的children加入到根节点中，
						//所有的许可就相当于放进了root中
						parentPermission.getChildren().add(permission);//传的是对象
						//parentPermission.setChildren(children);//传的是集合
						break;
					}
				}
			}
		}
		System.out.println("成功");
		return Msg.success().add("data", root);
	}	
	
	/**
	 * Demo 3：递归加载
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermission3")
	public Msg loadData3() {
			//根节点
			//List<Permission> root =new ArrayList<>();
			Permission permission=permissionService.getRootPermission();
			permission.setOpen(true);
			//root.add(permission);
			//子节点
			queryChildrenPermission(permission);
			
			System.out.println("成功");
			return Msg.success().add("data", permission);
	
	}	
	/**
	 * 采用递归加载数据
	 * 1.方法自己调自己
	 * 2.参数有规律
	 * 3.跳出方法条件
	 * @param permission
	 */
	private void queryChildrenPermission(Permission permission) {
		//子节点
		List<Permission> children =permissionService.getChildrenPermissionByPid(permission.getId());
		//设置父子关系
		permission.setChildren(children);
		//循环遍历子节点
		//不需要判断children是否为空，在mybatis中，若对象为空，返回空集合，不会报空指针异常
		//{"id":19,"pid":1,"name":"参数管理","icon":"glyphicon glyphicon-list-alt","url":"param/index.htm","open":true,"children":[]}
		for (Permission child : children) {
			child.setOpen(true);
			//递归子对象
			queryChildrenPermission(child);
		}
	}
	
	
	/**
	 * Demo 2：循环加载
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermission2")
	public Msg loadData2() {
			//根节点
			//List<Permission> root =new ArrayList<>();
			Permission permission=permissionService.getRootPermission();
			permission.setOpen(true);
			//root.add(permission);
			//子节点
			List<Permission> children =permissionService.getChildrenPermissionByPid(permission.getId());
			//设置父子关系
			permission.setChildren(children);
			for(Permission child:children) {
				child.setOpen(true);
				//根据子节点的id继续查找下一层数据
				List<Permission> secChildren=permissionService.getChildrenPermissionByPid(child.getId());
				child.setChildren(secChildren);
			}
			System.out.println("成功");
			return Msg.success().add("data", permission);

	}
	/**
	 * Demo 1 :模拟数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadPermission1")
	public Msg loadData1() {
			Permission permission = new Permission();
			
			permission.setId(1);
			permission.setName("系统权限菜单");
			permission.setOpen(true);
			
			Permission p1 = new Permission();
			p1.setName("用户管理");
			Permission p2 = new Permission();
			p2.setName("角色管理");
			Permission p3 = new Permission();
			p3.setName("许可管理");
			
			
			permission.getChildren().add(p1);
			permission.getChildren().add(p2);
			permission.getChildren().add(p3);
			System.out.println("成功");
			return Msg.success().add("data", permission);

	}

}
