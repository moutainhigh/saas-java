package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TPackagePrjType {
	private String name;
	
	private TPackagePrjType(String name) {
		this.name = name;
	}

	public static List<TPackagePrjType> buildTPackagePrjTypes() {
		List<TPackagePrjType> list = new ArrayList<TPackagePrjType>();
		String[] tmpNames = {"减肥","美白","护肤","养生","脱毛"};
		for (String name : tmpNames) {
			TPackagePrjType data = new TPackagePrjType(name);
			list.add(data);
		}
		Collections.shuffle(list);
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
