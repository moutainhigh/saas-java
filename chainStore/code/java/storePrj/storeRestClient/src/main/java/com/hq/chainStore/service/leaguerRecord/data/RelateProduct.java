package com.hq.chainStore.service.leaguerRecord.data;

import java.util.ArrayList;
import java.util.List;

public class RelateProduct {
	// 关联的项目列表
	private List<String> prdIds = new ArrayList<String>();

	public static RelateProduct newInstance() {
		RelateProduct data = new RelateProduct();
		return data;
	}

	public List<String> getPrdIds() {
		return prdIds;
	}

	public void setPrdIds(List<String> prdIds) {
		this.prdIds = prdIds;
	}

}
