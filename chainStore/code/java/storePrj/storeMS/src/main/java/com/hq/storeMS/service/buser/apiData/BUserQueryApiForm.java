package com.hq.storeMS.service.buser.apiData;

import java.util.HashSet;
import java.util.Set;

public class BUserQueryApiForm {
	// 商家账号
	private long buserPhone;
	// 每页条数
	private int pageItemCount;
	// 页号
	private int pageNo;
	// 角色
	private Set<Integer> roleSet = new HashSet<Integer>();

	public static BUserQueryApiForm newInstance() {
		BUserQueryApiForm params = new BUserQueryApiForm();
		params.pageItemCount = 20;
		params.pageNo = 1;
		return params;
	}
	
	public void addRole2Set(Integer role){
		this.roleSet.add(role);
	}

	public long getBuserPhone() {
		return buserPhone;
	}

	public void setBuserPhone(long buserPhone) {
		this.buserPhone = buserPhone;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}

}
