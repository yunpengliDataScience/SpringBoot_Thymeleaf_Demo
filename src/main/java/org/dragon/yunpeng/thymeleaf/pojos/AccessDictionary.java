package org.dragon.yunpeng.thymeleaf.pojos;

public class AccessDictionary {

	private int id;
	private String access;

	public AccessDictionary() {
	}

	public AccessDictionary(int id, String access) {
		this.id = id;
		this.access = access;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

}
