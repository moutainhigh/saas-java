package com.hq.storeManagerRestClient.service.storeMenu.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class SecondMenu {
	// 菜单名称
	private String name;
	// 模块权限 
	private int perm;
	private int sort;

	public static SecondMenu newInstance() {
		SecondMenu data = new SecondMenu();
		return data;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPerm() {
		return perm;
	}

	public void setPerm(int perm) {
		this.perm = perm;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
