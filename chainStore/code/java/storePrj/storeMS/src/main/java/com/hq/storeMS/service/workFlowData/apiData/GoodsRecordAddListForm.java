package com.hq.storeMS.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class GoodsRecordAddListForm {
	private List<GoodsRecordAddForm> goodsRecordAddForms = new ArrayList<GoodsRecordAddForm>();

	public static GoodsRecordAddListForm newInstance() {
		return new GoodsRecordAddListForm();
	}

	public List<GoodsRecordAddForm> getGoodsRecordAddForms() {
		return goodsRecordAddForms;
	}

	public void setGoodsRecordAddForms(
			List<GoodsRecordAddForm> goodsRecordAddForms) {
		this.goodsRecordAddForms = goodsRecordAddForms;
	}
}
