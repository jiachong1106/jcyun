package com.jc.admin.service;

import java.util.List;

import com.jc.admin.bean.Permission;

public interface PermissionService {
	/**
	 * 查找根节点
	 * @return
	 */
	Permission getRootPermission();
	/**
	 * 根据父节点id查询子节点
	 * @param id
	 * @return
	 */
	List<Permission> getChildrenPermissionByPid(Integer id);
	/**
	 * 查询出所有的许可
	 * @return
	 */
	List<Permission> queryAllPermission();
	/**
	 * 添加许可
	 * @param permission
	 */
	void savePermission(Permission permission);
	/**
	 * 根据id查找许可
	 * @param id
	 * @return
	 */
	Permission getPermission(Integer id);
	/**
	 * 修改许可
	 * @param permission
	 */
	void updatePermission(Permission permission);
	/**
	 * 删除许可
	 * @param id
	 */
	void deletePermission(Integer id);

}
