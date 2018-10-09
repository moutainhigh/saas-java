package com.hq.payms.common.datasyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.payms.common.datasyn.info.DataSynItem;
import com.hq.payms.common.datasyn.info.DataSynResp;
import com.hq.payms.common.datasyn.info.DataSynType;
import com.hq.payms.common.datasyn.info.DataSynVer;
import com.hq.payms.common.datasyn.info.DataSynVerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class MsDataSynMgr {
	
	public final String DATA_SYN_REQ = "dsReq";
	
	public static MsDataSynMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MsDataSynMgr.class);
	}
	
	private Map<DataSynType,IntfDataHolder> holderMap = new HashMap<DataSynType,IntfDataHolder>();
	
	public MsDataSynMgr(){
//		holderMap.put(MClientDataHolder.getInstance().getSynType(), new IntfDataHolder(){
//			@Override
//			public DataSynItem getSynItem(DataSynVer clientSynVer) {
//				return MClientDataHolder.getInstance().getSynItem(clientSynVer);
//			}});
		
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
