package com.hq.common.dataSyn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.beauticianProduct.data.BeauticianProductSynDataHolder;
import com.hq.chainStore.service.bonus.data.BonusRecordSynDataHolder;
import com.hq.chainStore.service.buser.data.BUserSynDataHolder;
import com.hq.chainStore.service.cardRecord.data.CardRecordSynDataHolder;
import com.hq.chainStore.service.clerkSalary.data.ClerkSalarySynDataHolder;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.chainStore.service.materialRecords.data.MaterialRecordsSynDataHolder;
import com.hq.chainStore.service.opuser.data.OPUserSynDataHolder;
import com.hq.chainStore.service.orderComment.data.OrderCommentSynDataHolder;
import com.hq.chainStore.service.report.data.MaterialReportSynDataHolder;
import com.hq.chainStore.service.report.data.OrderReportSynDataHolder;
import com.hq.chainStore.service.serverConfig.data.ServerConfigSynDataHolder;
import com.hq.chainStore.service.store.data.StoreSynDataHolder;
import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfoSynDataHolder;
import com.hq.chainStore.service.storeCardInfo.data.StoreCardInfoSynDataHolder;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfoSynDataHolder;
import com.hq.chainStore.service.storeConfig.data.StoreConfigSynDataHolder;
import com.hq.chainStore.service.storeGoods.data.StoreGoodsSynDataHolder;
import com.hq.chainStore.service.storeIncomePay.data.StoreIncomePaySynDataHolder;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfoSynDataHolder;
import com.hq.chainStore.service.storeMaterialInfo.data.StoreMaterialInfoSynDataHolder;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProjectSynDataHolder;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoSynDataHolder;
import com.hq.common.StringUtils4Client;
import com.hq.common.dataSyn.bs.DataSynCacheMgr;
import com.hq.common.dataSyn.bs.IntfClientDataHolder;
import com.hq.common.dataSyn.info.DataSynItem;
import com.hq.common.dataSyn.info.DataSynResp;
import com.hq.common.dataSyn.info.DataSynType;
import com.hq.common.dataSyn.info.DataSynVer;
import com.hq.common.dataSyn.info.DataSynVerInfo;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDaoThreadLocal;
import com.zenmind.dao.rest.RestHeader;
import com.zenmind.dao.rest.RestResp;

public class DataSynVerCtrl{
	public static DataSynVerCtrl getInstance() {
		return HotSwap.getInstance().getSingleton(DataSynVerCtrl.class);
	}

	private final String DATA_SYN_REQ = "dsReq";
	
	private Map<DataSynType,IntfClientDataHolder> holderMap = new HashMap<DataSynType,IntfClientDataHolder>();
	
	private DataSynVerCtrl(){
		holderMap.put(StoreSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(BUserSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				BUserSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreClerkInfoSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreClerkInfoSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreLeaguerInfoSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreLeaguerInfoSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(OrderCommentSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				OrderCommentSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreBeauticianInfoSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreBeauticianInfoSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreProductInfoSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreProductInfoSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreMaterialInfoSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreMaterialInfoSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(MaterialRecordsSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				MaterialRecordsSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(CardRecordSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				CardRecordSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(ClerkSalarySynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				ClerkSalarySynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(MaterialReportSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				MaterialReportSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(OrderReportSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				OrderReportSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreCardInfoSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreCardInfoSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(OPUserSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				OPUserSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(BeauticianProductSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				BeauticianProductSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(ServerConfigSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				ServerConfigSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreGoodsSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreGoodsSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(DetailDataVersionSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				DetailDataVersionSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(BonusRecordSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				BonusRecordSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StoreConfigSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreConfigSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		holderMap.put(StorePackageProjectSynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StorePackageProjectSynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
		
		holderMap.put(StoreIncomePaySynDataHolder.getInstance().getSynType(), new IntfClientDataHolder() {
			@Override
			public void synData(String ownerId, DataSynItem dataSynItem) {
				StoreIncomePaySynDataHolder.getInstance().synData(ownerId,dataSynItem);
			}
		});
	}

	public void preHandleReq(RestHeader httpHeaders) {
		String ownerId = RestDaoThreadLocal.getInstance().getOwnerId();
		if(StringUtils4Client.isNotBlank(ownerId)){
			DataSynVerInfo synVerInfo = DataSynCacheMgr.getInstance().getSynVerInfo(ownerId);
			if(synVerInfo!=null){
				String jsonData = JsonUtil.getInstance().toJson(synVerInfo);
				httpHeaders.addHeader(DATA_SYN_REQ, jsonData);
			}
		}
	}
	
	public void preHandleResp(RestResp restResp) {
		String respJson = restResp.getDsResp();
		if(StringUtils4Client.isNotBlank(respJson)){
			
			DataSynResp synResp = JsonUtil.getInstance().fromJson(respJson, DataSynResp.class);
			List<DataSynItem> itemList = synResp.getItemList();
			
			synData(synResp.getOwnerId(),itemList);
		}else{
			restResp.setTips("restResp.getDsResp() is null");
		}
	}

	private void synData(String ownerId, List<DataSynItem> itemList) {
		if(itemList == null){
			return;
		}
		for (DataSynItem dataSynItem : itemList) {
			DataSynVer synVer = dataSynItem.getSynVer();
			IntfClientDataHolder holderTmp = holderMap.get(synVer.getSynTypeEnum());
			if(holderTmp!=null){
				holderTmp.synData(ownerId,dataSynItem);
			}
		}
	}

}
