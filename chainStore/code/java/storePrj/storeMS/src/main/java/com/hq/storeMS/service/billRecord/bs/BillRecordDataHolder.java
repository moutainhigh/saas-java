package com.hq.storeMS.service.billRecord.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.billRecord.apiData.BillRecordQueryForm;
import com.hq.storeMS.service.billRecord.data.BillRecord;
import com.hq.storeMS.service.billRecord.data.BillRecordCacheDAO;
import com.hq.storeMS.service.billRecord.data.BillRecordDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class BillRecordDataHolder{

	public static BillRecordDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BillRecordDataHolder.class);
	}
	
	public void addAndReturnId(BillRecord target) {
		BillRecordDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		BillRecordCacheDAO.getInstance().delete(target);
	}

	public void update(BillRecord target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BillRecordDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		BillRecordCacheDAO.getInstance().delete(target);
	}
	
	public void delete(BillRecord target) {
		BillRecordDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		BillRecordCacheDAO.getInstance().delete(target);
	}

	public BillRecord get(long storeId, long id) {
		BillRecord target = BillRecordCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = BillRecordDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				BillRecordCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public BillRecord getByOutTradeNo(long bossId, String outTradeNo) {
		return BillRecordDAO.getInstance().getByOutTradeNo(bossId, outTradeNo);
	}
	
	public List<BillRecord> findList(BillRecordQueryForm queryForm) {
		List<BillRecord> list = BillRecordCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = BillRecordDAO.getInstance().findList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				BillRecordCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}

}
