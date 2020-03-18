package com.jc.utils;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	//状态码 100-成功 200-失败
	private boolean success;
	//提示信息
	private String msg;
	//用户要返回给浏览器的数据
	private Map<String,Object> data= new HashMap<String,Object>();
	
	public static Msg success() {
		Msg result = new Msg();
		result.setSuccess(true);
		result.setMsg("操作成功！");
		return result;
	}
	public static Msg fail() {
		Msg result = new Msg();
		result.setSuccess(false);
		result.setMsg("操作失败！");
		return result;
	}
	//链式操作，添加自定义信息		
	public Msg add(String key,Object value) {
		this.getData().put(key, value);
		return this;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}
