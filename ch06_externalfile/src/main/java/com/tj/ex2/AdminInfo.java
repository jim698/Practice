package com.tj.ex2;

public class AdminInfo {
	private String adminId;
	private String adminPw; // ex1과 다른 점은 env를 이용하지 않는다. ex2 버전을 꼭 기억하자!! 얘를 쓴다
	private String sub_adminId;
	private String sub_adminPw;

	public String getSub_adminId() {
		return sub_adminId;
	}

	public void setSub_adminId(String sub_adminId) {
		this.sub_adminId = sub_adminId;
	}

	public String getSub_adminPw() {
		return sub_adminPw;
	}

	public void setSub_adminPw(String sub_adminPw) {
		this.sub_adminPw = sub_adminPw;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

}
