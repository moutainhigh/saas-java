package com.hq.customerMS.common.datasyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.customerMS.common.datasyn.info.DataSynItem;
import com.hq.customerMS.common.datasyn.info.DataSynResp;
import com.hq.customerMS.common.datasyn.info.DataSynType;
import com.hq.customerMS.common.datasyn.info.DataSynVer;
import com.hq.customerMS.common.datasyn.info.DataSynVerInfo;
import com.hq.customerMS.service.store.bs.StoreDataHolder;
import com.hq.customerMS.service.storeBeauticianInfo.bs.StoreBeauticianInfoDataHolder;
import com.hq.customerMS.service.storeCardInfo.bs.StoreCardInfoDataHolder;
import com.hq.customerMS.service.storeGoods.bs.StoreGoodsDataHolder;
import com.hq.customerMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoDataHolder;
import com.hq.customerMS.service.storePackageProject.bs.StorePackageProjectDataHolder;
import com.hq.customerMS.service.storeProductInfo.bs.StoreProductInfoDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class MsDataSynMgr {
	
	public final String DATA_SYN_REQ = "dsReq";
	
	public static MsDataSynMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MsDataSynMgr.class);
	}
	
	private Map<DataSynType,IntfDataHolder> holderMap = new HashMap<DataSynType,IntfDataHolder>();
	
	public MsDataSynMgr(){
		holderMap.put(StoreDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(StoreProductInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreProductInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(StoreBeauticianInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreBeauticianInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(StoreCardInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreCardInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(StoreGoodsDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreGoodsDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(StorePackageProjectDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StorePackageProjectDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(StoreLeaguerInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreLeaguerInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
	} 
	
	public DataSynResp getSynResp(DataSynVerInfo synVerData){
		
		List<DataSynItem> synItemList = new ArrayList<DataSynItem>();
		
		List<DataSynVer> synVerList = synVerData.getSynVerList();
		for (DataSynVer dataSynVerTmp : synVerList) {
			DataSynType synTypeTmp = dataSynVerTmp.getSynType();
			IntfDataHolder holderTmp = holderMap.get(synTypeTmp);
			if(holderTmp != null){//处理holder为空  报异常的情况
				DataSynItem synItemTmp = holderTmp.getSynItem(dataSynVerTmp);
				if(synItemTmp!=null){
					synItemList.add(synItemTmp);
				}
			}
		}
		
		return DataSynResp.newInstance(synVerData.getOwnerId(),synItemList);
	}
	

}
