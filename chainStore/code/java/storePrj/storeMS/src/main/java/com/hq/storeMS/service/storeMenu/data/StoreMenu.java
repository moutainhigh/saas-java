package com.hq.storeMS.service.storeMenu.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeMenu")
public class StoreMenu {
	// 店铺限制
	private Map<String, FirstMenu> firstMenuMap = new HashMap<String, FirstMenu>();

	public static StoreMenu newInstance() {
		StoreMenu data = new StoreMenu();
		return data;
	}
	
	public void addFirstMenu(FirstMenu menu) {
		firstMenuMap.put(menu.getName(), menu);
	}

	public Map<String, FirstMenu> getFirstMenuMap() {
		return firstMenuMap;
	}

	public void setFirstMenuMap(Map<String, FirstMenu> firstMenuMap) {
		this.firstMenuMap = firstMenuMap;
	}

}
