package com.hq.customerRestClient.common.dataSyn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.customerRestClient.common.dataSyn.bs.DataSynCacheMgr;
import com.hq.customerRestClient.common.dataSyn.bs.IntfClientDataHolder;
import com.hq.customerRestClient.common.dataSyn.info.DataSynItem;
import com.hq.customerRestClient.common.dataSyn.info.DataSynResp;
import com.hq.customerRestClient.common.dataSyn.info.DataSynType;
import com.hq.customerRestClient.common.dataSyn.info.DataSynVer;
import com.hq.customerRestClient.common.dataSyn.info.DataSynVerInfo;
import com.hq.customerRestClient.common.util.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDaoThreadLocal;
import com.zenmind.dao.rest.RestHeader;
import com.zenmind.dao.rest.RestResp;

public class DataSynVerCtrl {

	public static DataSynVerCtrl getInstance() {
		return HotSwap.getInstance().getSingleton(DataSynVerCtrl.class);
	}

	private final String DATA_SYN_REQ = "dsReq";

	private Map<DataSynType, IntfClientDataHolder> holderMap = new HashMap<DataSynType, IntfClientDataHolder>();

	private DataSynVerCtrl() {
	}

	public void preHandleReq(RestHeader httpHeaders) {
		String ownerId = RestDaoThreadLocal.getInstance().getOwnerId();
		if (StringUtils4Client.isNotBlank(ownerId)) {
			DataSynVerInfo synVerInfo = DataSynCacheMgr.getInstance()
					.getSynVerInfo(ownerId);
			if (synVerInfo != null) {
				String jsonData = JsonUtil.getInstance().toJson(synVerInfo);
				httpHeaders.addHeader(DATA_SYN_REQ, jsonData);
			}
		}

	}

	public void preHandleResp(RestResp restResp) {
		String respJson = restResp.getDsResp();
		if (StringUtils4Client.isNotBlank(respJson)) {

			DataSynResp synResp = JsonUtil.getInstance().fromJson(respJson,
					DataSynResp.class);
			List<DataSynItem> itemList = synResp.getItemList();

			synData(synResp.getOwnerId(), itemList);
		} else {
			restResp.setTips("restResp.getDsResp() is null");
		}

	}

	private void synData(String ownerId, List<DataSynItem> itemList) {
		if (itemList == null) {
			return;
		}
		for (DataSynItem dataSynItem : itemList) {
			DataSynVer synVer = dataSynItem.getSynVer();
			IntfClientDataHolder holderTmp = holderMap.get(synVer
					.getSynTypeEnum());
			if (holderTmp != null) {
				holderTmp.synData(ownerId, dataSynItem);
			}
		}

	}

}
