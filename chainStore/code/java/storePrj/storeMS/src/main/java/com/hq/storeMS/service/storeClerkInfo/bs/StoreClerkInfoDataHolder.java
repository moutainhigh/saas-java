package com.hq.storeMS.service.storeClerkInfo.bs;

import java.util.Map;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.message.IntfMessageHolder;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.hq.storeMS.service.storeClerkInfo.data.ApplyClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.ApplyState;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfoCacheDAO;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class StoreClerkInfoDataHolder implements IntfDataHolder,IntfMessageHolder{
	
	public static StoreClerkInfoDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.StoreClerkInfo;
	final private MessageTypeEnum messageType = MessageTypeEnum.CLERK_MNG;
	
	public void addWithId(StoreClerkInfo target) {
		StoreClerkInfoDAO.getInstance().addWithId(target);
	}

	public void update(StoreClerkInfo target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreClerkInfoDAO.getInstance().updpate(target);
		StoreClerkInfoCacheDAO.getInstance().delete(target);
	}
	
	public void delete(StoreClerkInfo target) {
		StoreClerkInfoDAO.getInstance().delete(target.getId());
		StoreClerkInfoCacheDAO.getInstance().delete(target);
	}

	public StoreClerkInfo get(String id) {
		StoreClerkInfo target = StoreClerkInfoCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreClerkInfoDAO.getInstance().get(id);
			if(target != null){
				StoreClerkInfoCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		StoreClerkInfo target = this.get(id);
		if(target != null){
			long newVer = target.getVer();
			if(clientSynVer.getVer() < newVer){
				String data = DataSynMgr.getInstance().toClientData(target);
				item = DataSynItem.newInstance(clientSynVer, newVer, data );
			}
		}else{
			MainLog.info(LogModule.StoreClerkInfo, "StoreClerkInfoDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
		}
		return item;
	}

	public MessageTypeEnum getMessageType(){
		return messageType;
	}

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		MessageResp messageResp = MessageResp.newInstance();
		messageResp.setMessageType(getMessageType().ordinal());
		messageResp.setCount(count(queryForm));
		return messageResp;
	}
	
	private long count(MsgQueryForm queryForm){
		String storeClerkInfoId = StoreClerkInfo.getIdByStoreId(queryForm.getStoreId());
		StoreClerkInfo storeClerkInfo = get(storeClerkInfoId);
		Map<Long, ApplyClerkInfo> applyClerkInfoMap = storeClerkInfo.getApplyClerkInfoMap();
		long applyCount = 0l;
		//遍历未审核员工
		for (long key : applyClerkInfoMap.keySet()) {
			if(applyClerkInfoMap.get(key).getState() == ApplyState.Pending.ordinal()){
				applyCount++;
			}
		}
		return applyCount;
	}
	
}
