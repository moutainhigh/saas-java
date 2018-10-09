package com.hq.storeClient.service.storeMenu.data;

import java.util.HashMap;
import java.util.Map;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class FirstMenu {
	// 菜单名称
	private String name;
	// 模块权限
	private Map<String, SecondMenu> secondMenuMap = new HashMap<String, SecondMenu>();
	private int sort;

	public static FirstMenu newInstance(String name) {
		FirstMenu data = new FirstMenu();
		data.name = name;
		return data;
	}

	public void addSecondMenu(SecondMenu... secondMenus) {
		for (SecondMenu secondMenu : secondMenus) {
			secondMenuMap.put(secondMenu.getName(), secondMenu);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, SecondMenu> getSecondMenuMap() {
		return secondMenuMap;
	}

	public void setSecondMenuMap(Map<String, SecondMenu> secondMenuMap) {
		this.secondMenuMap = secondMenuMap;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
