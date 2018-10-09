package com.hq.chainMS.common.datasyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.chainMS.common.datasyn.info.DataSynItem;
import com.hq.chainMS.common.datasyn.info.DataSynResp;
import com.hq.chainMS.common.datasyn.info.DataSynType;
import com.hq.chainMS.common.datasyn.info.DataSynVer;
import com.hq.chainMS.common.datasyn.info.DataSynVerInfo;
import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chainCard.bs.ChainCardDataHolder;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkDataHolder;
import com.hq.chainMS.service.chainGoods.bs.ChainGoodsDataHolder;
import com.hq.chainMS.service.chainPackageProject.bs.ChainPackageProjectDataHolder;
import com.hq.chainMS.service.chainProduct.bs.ChainProductDataHolder;
import com.hq.chainMS.service.chainUser.bs.ChainUserDataHolder;
import com.hq.chainMS.service.detailDataVersion.bs.DetailDataVersionDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class MsDataSynMgr {
	
	public final String DATA_SYN_REQ = "dsReq";
	
	public static MsDataSynMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MsDataSynMgr.class);
	}
	
	private Map<DataSynType,IntfDataHolder> holderMap = new HashMap<DataSynType,IntfDataHolder>();
	
	public MsDataSynMgr(){
		holderMap.put(ChainDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(ChainUserDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainUserDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(ChainClerkDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainClerkDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(ChainGoodsDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainGoodsDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(ChainProductDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainProductDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(ChainCardDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainCardDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		holderMap.put(ChainPackageProjectDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return ChainPackageProjectDataHolder.getInstance().getSynItem(clientSynVer);
			}
		});
		
		holderMap.put(DetailDataVersionDataHolder.getInstance().getSynType(), new IntfDataHolder(){
			@Override
			public DataSynItem getSynItem(DataSynVer clientSynVer) {
				return DetailDataVersionDataHolder.getInstance().getSynItem(clientSynVer);
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
