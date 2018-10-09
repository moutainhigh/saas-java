package com.hq.storeMS.common.datasyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynResp;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.datasyn.info.DataSynVerInfo;
import com.hq.storeMS.service.beauticianProduct.bs.BeauticianProductDataHolder;
import com.hq.storeMS.service.buser.bs.BUserDataHolder;
import com.hq.storeMS.service.cardRecord.bs.CardRecordDataHolder;
import com.hq.storeMS.service.clerkSalary.bs.ClerkSalaryDataHolder;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionDataHolder;
import com.hq.storeMS.service.materialRecords.bs.MaterialRecordsDataHolder;
import com.hq.storeMS.service.opuser.bs.OPUserDataHolder;
import com.hq.storeMS.service.serverConfig.bs.ServerConfigDataHolder;
import com.hq.storeMS.service.store.bs.StoreDataHolder;
import com.hq.storeMS.service.storeBeauticianInfo.bs.StoreBeauticianInfoDataHolder;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoDataHolder;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoDataHolder;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigDataHolder;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsDataHolder;
import com.hq.storeMS.service.storeIncomePay.bs.StoreIncomePayDataHolder;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoDataHolder;
import com.hq.storeMS.service.storeMaterialInfo.bs.StoreMaterialInfoDataHolder;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectDataHolder;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoDataHolder;
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
			}});
		holderMap.put(BUserDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return BUserDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(OPUserDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return OPUserDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreClerkInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreClerkInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreLeaguerInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreLeaguerInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreBeauticianInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreBeauticianInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreProductInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreProductInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreMaterialInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreMaterialInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreCardInfoDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreCardInfoDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(CardRecordDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return CardRecordDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(ClerkSalaryDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ClerkSalaryDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(MaterialRecordsDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return MaterialRecordsDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(BeauticianProductDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return BeauticianProductDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(ServerConfigDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ServerConfigDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreGoodsDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreGoodsDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(DetailDataVersionDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return DetailDataVersionDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StoreConfigDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreConfigDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		holderMap.put(StorePackageProjectDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StorePackageProjectDataHolder.getInstance().getSynItem(clientSynVer);
			}});
		
		holderMap.put(StoreIncomePayDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return StoreIncomePayDataHolder.getInstance().getSynItem(clientSynVer);
			}});

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
