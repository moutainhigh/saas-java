package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class GoodsRecordUpdListForm {
	private List<GoodsRecordAddForm> goodsRecordAddForms = new ArrayList<GoodsRecordAddForm>();

	public static GoodsRecordUpdListForm newInstance() {
		return new GoodsRecordUpdListForm();
	}

	public List<GoodsRecordAddForm> getGoodsRecordAddForms() {
		return goodsRecordAddForms;
	}

	public void setGoodsRecordAddForms(
			List<GoodsRecordAddForm> goodsRecordAddForms) {
		this.goodsRecordAddForms = goodsRecordAddForms;
	}
}
