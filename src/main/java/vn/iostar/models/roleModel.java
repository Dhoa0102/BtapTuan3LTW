package vn.iostar.models;

import java.io.Serializable;

public class roleModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int roleid;
	private String rolename;
	public roleModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public roleModel(int roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}
