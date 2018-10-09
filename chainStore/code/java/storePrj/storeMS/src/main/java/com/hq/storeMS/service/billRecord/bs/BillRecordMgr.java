package com.hq.storeMS.service.billRecord.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.billRecord.apiData.BillRecordAddForm;
import com.hq.storeMS.service.billRecord.apiData.BillRecordQueryForm;
import com.hq.storeMS.service.billRecord.data.BillRecord;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;

public class BillRecordMgr {

	public static BillRecordMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BillRecordMgr.class);
	}
	
	public OperateTips add(BillRecordAddForm addForm) {
		OperateTips operateTips = OperateTips.newInstance(true);
		if((addForm.getStoreId()>0l) 
				&& StringUtils.isNotBlank(addForm.getLeaguerId())){
			BillRecord billReocrd = addForm.toBillRecord();
			BillRecordDataHolder.getInstance().addAndReturnId(billReocrd);
		}else{
			operateTips.setSuccess(false);
			operateTips.setTips("参数错误");
		}
		return operateTips;
	}
	
	public void delete(BillRecord target) {
		BillRecordDataHolder.getInstance().delete(target);
	}

	public void update(BillRecord target) {
		BillRecordDataHolder.getInstance().update(target);
	}
	
	public BillRecord get(long storeId, long id) {
		return BillRecordDataHolder.getInstance().get(storeId, id);
	}
	
	public BillRecord getByOutTradeNo(long bossId,String outTradeNo) {
		return BillRecordDataHolder.getInstance().getByOutTradeNo(bossId,outTradeNo);
	}

	public List<BillRecord> findList(BillRecordQueryForm queryForm) {
		List<BillRecord> list = BillRecordDataHolder.getInstance().findList(queryForm);
		List<BillRecord> result = filterRecord(queryForm, list);
		return PageUtil.getInstance().getPageItemList(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public PageResp<BillRecord> findPageInfo(BillRecordQueryForm queryForm) {
		List<BillRecord> list = BillRecordDataHolder.getInstance().findList(queryForm);
		List<BillRecord> result = filterRecord(queryForm,list);
		return PageUtil.getInstance().buildPageResp(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<BillRecord> filterRecord(BillRecordQueryForm queryForm, List<BillRecord> list){
		List<BillRecord> result = new ArrayList<BillRecord>();
		if(CollectionUtils.isNotEmpty(list)){
			for (BillRecord record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		
		Collections.sort(result, new Comparator<BillRecord>() {
			@Override
			public int compare(BillRecord o1, BillRecord o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(BillRecordQueryForm queryForm, BillRecord record){
		if(!checkTradeNoOrName(queryForm.getTradeNoOrName(), record)){
			return false;
		}
		if(!checkBillType(queryForm.getBillType(), record)){
			return false;
		}
		if(!checkPayType(queryForm.getPayType(), record)){
			return false;
		}
		if(!checkLeaguerId(queryForm.getLeaguerId(), record)){
			return false;
		}
		if(!checkState(queryForm.getState(), record)){
			return false;
		}
		return true;
	}
	
	private boolean checkTradeNoOrName(String tradeNoOrName, BillRecord record){
		if(StringUtils.isBlank(tradeNoOrName)){
			return true;
		}
		if(record.getLeaguerName()!=null && record.getLeaguerName().contains(tradeNoOrName)){
			return true;
		}
		if(record.getTradeNo()!=null && record.getTradeNo().contains(tradeNoOrName)){
			return true;
		}
		return false;
	}
	
	private boolean checkBillType(int billType, BillRecord record){
		if(billType == -1){
			return true;
		}
		if(billType == record.getBillType()){
			return true;
		}
		return false;
	}
	
	private boolean checkState(int state, BillRecord record){
		if(state == -1){
			return true;
		}
		if(state == record.getBillType()){
			return true;
		}
		return false;
	}
	
	private boolean checkPayType(Set<Integer> payTypeSet, BillRecord record){
		if(CollectionUtils.isEmpty(payTypeSet)){
			return true;
		}
		if(payTypeSet.contains(record.getPayType())){
			return true;
		}
		return false;
	}
	
	private boolean checkLeaguerId(String leaguerId,BillRecord record){
		if(StringUtils.isBlank(leaguerId)){
			return true;
		}
		if(StringUtils.equalsIgnoreCase(leaguerId, record.getLeaguerId())){
			return true;
		}
		return false;
	}
	
}
