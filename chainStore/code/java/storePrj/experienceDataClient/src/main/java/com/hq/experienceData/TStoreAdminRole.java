package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TStoreAdminRole {
	private String name;
	private Set<Integer> permSet = new HashSet<Integer>();
	private String descript;
	
	public static List<TStoreAdminRole> buildTClerkList() {
		List<TStoreAdminRole> list = new ArrayList<TStoreAdminRole>();
		
		TStoreAdminRole data1 = new TStoreAdminRole();
		data1.setName("美容顾问");
		data1.setDescript("美容顾问");
		Integer[] permSet1 = {1,2,3,4,6,7,8,9,10,11,12,13,14};
		data1.getPermSet().addAll(Arrays.asList(permSet1));
		list.add(data1);
		
		TStoreAdminRole data2 = new TStoreAdminRole();
		data2.setName("美容师");
		data2.setDescript("美容师");
		Integer[] permSet2 = {3,12};
		data2.getPermSet().addAll(Arrays.asList(permSet2));
		list.add(data2);
		
		TStoreAdminRole data3 = new TStoreAdminRole();
		data3.setName("前台");
		data3.setDescript("前台");
		Integer[] permSet3 = {3,1};
		data3.getPermSet().addAll(Arrays.asList(permSet3));
		list.add(data3);
		
		TStoreAdminRole data4 = new TStoreAdminRole();
		data4.setName("财务");
		data4.setDescript("财务");
		Integer[] permSet4 = {6,9,12};
		data4.getPermSet().addAll(Arrays.asList(permSet4));
		list.add(data4);
		
		Collections.shuffle(list);
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
