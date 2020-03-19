package com.jc.admin.bean;

public class UserRole {
    private Integer id;

    private Integer userid;

    private Integer roleid;

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRole(Integer id, Integer userid, Integer roleid) {
		super();
		this.id = id;
		this.userid = userid;
		this.roleid = roleid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}