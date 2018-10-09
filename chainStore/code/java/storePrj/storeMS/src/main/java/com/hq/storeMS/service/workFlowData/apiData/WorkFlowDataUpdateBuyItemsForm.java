package com.hq.storeMS.service.workFlowData.apiData;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.workFlowData.data.DecreasePrdCardRecord;
import com.hq.storeMS.service.workFlowData.data.GoodsRecord;
import com.hq.storeMS.service.workFlowData.data.PrdCardRecord;
import com.hq.storeMS.service.workFlowData.data.ProdRecord;

public class WorkFlowDataUpdateBuyItemsForm {
	// 项目
	private Map<String, ProdRecord> prodRecordMap = new HashMap<String, ProdRecord>();
	// 划卡
	private Map<String, DecreasePrdCardRecord> decreasePrdCardRecordMap = new HashMap<String, DecreasePrdCardRecord>();
	// 购买次卡
	private Map<String, PrdCardRecord> prdCardRecordMap = new HashMap<String, PrdCardRecord>();
	// 购买商品
	private Map<String, GoodsRecord> goodsRecordMap = new HashMap<String, GoodsRecord>();

	public static WorkFlowDataUpdateBuyItemsForm newInstance() {
		return new WorkFlowDataUpdateBuyItemsForm();
	}

	public Map<String, ProdRecord> getProdRecordMap() {
		return prodRecordMap;
	}

	public void setProdRecordMap(Map<String, ProdRecord> prodRecordMap) {
		this.prodRecordMap = prodRecordMap;
	}

	public Map<String, DecreasePrdCardRecord> getDecreasePrdCardRecordMap() {
		return decreasePrdCardRecordMap;
	}

	public void setDecreasePrdCardRecordMap(
			Map<String, DecreasePrdCardRecord> decreasePrdCardRecordMap) {
		this.decreasePrdCardRecordMap = decreasePrdCardRecordMap;
	}

	public Map<String, PrdCardRecord> getPrdCardRecordMap() {
		return prdCardRecordMap;
	}

	public void setPrdCardRecordMap(Map<String, PrdCardRecord> prdCardRecordMap) {
		this.prdCardRecordMap = prdCardRecordMap;
	}

	public Map<String, GoodsRecord> getGoodsRecordMap() {
		return goodsRecordMap;
	}

	public void setGoodsRecordMap(Map<String, GoodsRecord> goodsRecordMap) {
		this.goodsRecordMap = goodsRecordMap;
	}

}
