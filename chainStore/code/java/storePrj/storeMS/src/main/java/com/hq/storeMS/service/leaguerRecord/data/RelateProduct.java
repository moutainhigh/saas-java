package com.hq.storeMS.service.leaguerRecord.data;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class RelateProduct {
	// 关联的项目列表
	private List<String> prdIds = new ArrayList<String>();

	public static RelateProduct newInstance(List<String> prdIdsP) {
		RelateProduct data = new RelateProduct();
		data.prdIds = prdIdsP;
		return data;
	}

	public List<String> getPrdIds() {
		return prdIds;
	}

	public void setPrdIds(List<String> prdIds) {
		this.prdIds = prdIds;
	}

}
