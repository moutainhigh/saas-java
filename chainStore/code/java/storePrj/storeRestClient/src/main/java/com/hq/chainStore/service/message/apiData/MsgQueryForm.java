package com.hq.chainStore.service.message.apiData;

import com.zenmind.dao.rest.ReqMap;

public class MsgQueryForm {
	private long storeId;// 店铺ID
	private long beauticianId;// 医美师ID

	public static MsgQueryForm newInstance() {
		MsgQueryForm data = new MsgQueryForm();
		data.storeId=0;
		data.beauticianId=0;
		return data;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("beauticianId", beauticianId);
		return reqMap;
	}

	public long getStoreId() {
		return storeId;
	}

	public MsgQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public MsgQueryForm setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
		return this;
	}
}
