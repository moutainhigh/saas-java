package com.hq.chainClient.service.chainUser.apiData;

public class ChangePasswordForm {
	private long id;
	private String password;
	private String oldPassword;

	public static ChangePasswordForm newInstance() {
		return new ChangePasswordForm();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
