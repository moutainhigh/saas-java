package com.hq.storeMS.service.storeMenu.data;

import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
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
	
	public static SecondMenu newInstance(StoreAdminPermEnum adminPermEnum) {
		SecondMenu data = newInstance();
		data.name = adminPermEnum.getDescript();
		data.perm = adminPermEnum.ordinal();
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
