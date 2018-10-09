package com.hq.chainMS.service.chainProduct.apiData;

import java.util.HashSet;
import java.util.Set;

/**
 * 批量修改项目分类的状态
 */
public class BatchUpdateProductTypeStateData {
	private Set<String> prdTypeIdSet = new HashSet<String>();// 项目id
	private int state;

	public static BatchUpdateProductTypeStateData newInstance() {
		return new BatchUpdateProductTypeStateData();
	}

	public Set<String> getPrdTypeIdSet() {
		return prdTypeIdSet;
	}

	public void setPrdTypeIdSet(Set<String> prdTypeIdSet) {
		this.prdTypeIdSet = prdTypeIdSet;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
