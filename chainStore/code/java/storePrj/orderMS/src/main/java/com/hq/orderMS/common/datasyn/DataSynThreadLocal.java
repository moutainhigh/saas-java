package com.hq.orderMS.common.datasyn;

import com.hq.orderMS.common.datasyn.info.DataSynResp;
import com.hq.orderMS.common.datasyn.info.DataSynVerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class DataSynThreadLocal {
	

	public static DataSynThreadLocal getInstance(){
		return HotSwap.getInstance().getSingleton(DataSynThreadLocal.class);
	}
	
	private final static ThreadLocal<DataSynVerInfo>  verInfoTL = new ThreadLocal<DataSynVerInfo>();
	
	public void set(DataSynVerInfo verInfoP){
		verInfoTL.set(verInfoP);
	}
	public DataSynVerInfo get(){
		return verInfoTL.get();
	}
	
	public DataSynResp getDsResp(){
		DataSynVerInfo verInfo = get();
		DataSynResp synResp = null;
		if(verInfo!=null){
			synResp = MsDataSynMgr.getInstance().getSynResp(verInfo);
		}
		
		return synResp; 
	}
	
}
