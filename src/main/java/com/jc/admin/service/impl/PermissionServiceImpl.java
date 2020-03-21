package com.jc.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc.admin.bean.Permission;
import com.jc.admin.bean.PermissionExample;
import com.jc.admin.bean.PermissionExample.Criteria;
import com.jc.admin.dao.PermissionMapper;
import com.jc.admin.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public Permission getRootPermission() {
		
		return permissionMapper.getRootPermission();
	}

	@Override
	public List<Permission> getChildrenPermissionByPid(Integer id) {
		
		return permissionMapper.getChildrenPermissionByPid(id);
	}

	@Override
	public List<Permission> queryAllPermission() {
		
		return permissionMapper.selectByExample(null);
	}

	@Override
	public void savePermission(Permission permission) {
		permissionMapper.insert(permission);
	}

	@Override
	public Permission getPermission(Integer id) {
		
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updatePermission(Permission permission) {
		PermissionExample example=new PermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(permission.getId());
		permissionMapper.updateByExampleSelective(permission, example);
	}

	@Override
	public void deletePermission(Integer id) {
		permissionMapper.deleteByPrimaryKey(id);
	}
}
