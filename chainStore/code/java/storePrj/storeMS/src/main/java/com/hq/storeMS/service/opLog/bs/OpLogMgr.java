package com.hq.storeMS.service.opLog.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.opLog.apiData.OpLogQueryForm;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.zenmind.common.hotSwap.HotSwap;

public class OpLogMgr {

	public static OpLogMgr getInstance(){
		return HotSwap.getInstance().getSingleton(OpLogMgr.class);
	}
	
	public void addAndReturnId(OpLog target) {
		OpLogDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void updateOpLog(OpLog target) {
		OpLogDataHolder.getInstance().update(target);
	}
	
	public OpLog get(long id){
		return OpLogDataHolder.getInstance().get(id);
	}
	
	public PageResp<OpLog> findPage(OpLogQueryForm queryForm) {
		List<OpLog> list = OpLogDataHolder.getInstance().findByCond(queryForm);
		List<OpLog> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<OpLog> filterRecord(OpLogQueryForm queryForm, List<OpLog> list){
		List<OpLog> result = new ArrayList<OpLog>();
		if(CollectionUtils.isNotEmpty(list)){
			for (OpLog record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<OpLog>() {
			@Override
			public int compare(OpLog o1, OpLog o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(OpLogQueryForm queryForm, OpLog record){
		if(!checkType(queryForm.getType(), record)) {
			return false;
		}
		if(!checkName(queryForm.getName(), record)) {
			return false;
		}
		if(!checkBuserName(queryForm.getBuserName(), record)) {
			return false;
		}
		return true;
	}
	
	private boolean checkType(Set<Integer> type, OpLog record){
		if(CollectionUtils.isEmpty(type)) {
			return true;
		}
		if(type.contains(record.getType())) {
			return true;
		}
		return false;
	}
	
	private boolean checkName(String name, OpLog record){
		if(StringUtils.isBlank(name)) {
			return true;
		}
		if(record.getNewName().contains(name)) {
			return true;
		}
		return false;
	}
	
	private boolean checkBuserName(String buserName, OpLog record){
		if(StringUtils.isBlank(buserName)) {
			return true;
		}
		if(record.getBuserName().contains(buserName)) {
			return true;
		}
		return false;
	}

}
