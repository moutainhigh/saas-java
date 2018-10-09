package com.hq.storeClient.service.storeConfig.apiData;

public class ExpandAttributeSortForm {
	//扩展属性的Id
	private int id;
	//上移或者下移 DirectionEnum
	private int sort;

	public static ExpandAttributeSortForm newInstance() {
		return new ExpandAttributeSortForm();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
