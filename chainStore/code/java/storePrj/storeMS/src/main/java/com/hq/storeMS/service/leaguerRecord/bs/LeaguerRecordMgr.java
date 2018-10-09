package com.hq.storeMS.service.leaguerRecord.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerRecordMgr {

	public static LeaguerRecordMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerRecordMgr.class);
	}
	
	public void addAndReturnId(LeaguerRecord target) {
		LeaguerRecordDataHolder.getInstance().addAndReturnId(target);
	}

	public void update(LeaguerRecord target) {
		LeaguerRecordDataHolder.getInstance().update(target);
	}
	
	public void delete(LeaguerRecord target) {
		LeaguerRecordDataHolder.getInstance().delete(target);
	}

	public LeaguerRecord get(long storeId, long id) {
		return LeaguerRecordDataHolder.getInstance().get(storeId, id);
	}
	
	public PageResp<LeaguerRecord> getLeaguerRecordPageInfo(LeaguerRecordQueryForm queryForm) {
		List<LeaguerRecord> list = LeaguerRecordDataHolder.getInstance().findLeaguerRecordList(queryForm);
		List<LeaguerRecord> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<LeaguerRecord> filterRecord(LeaguerRecordQueryForm queryForm, List<LeaguerRecord> list){
		List<LeaguerRecord> result = new ArrayList<LeaguerRecord>();
		if(CollectionUtils.isNotEmpty(list)){
			for (LeaguerRecord record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<LeaguerRecord>() {
			@Override
			public int compare(LeaguerRecord o1, LeaguerRecord o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(LeaguerRecordQueryForm queryForm, LeaguerRecord record){
		if(!checkWorkFlowDataId(queryForm.getWorkFlowDataId(), record)){
			return false;
		}
		
		if(!checkLeaguer(queryForm.getLeaguerId(), record)){
			return false;
		}
		
		return true;
	}
	
	private boolean checkWorkFlowDataId(long workFlowDataId, LeaguerRecord record){
		if(workFlowDataId > 0 && workFlowDataId != record.getWorkFlowDataId()){
			return false;
		}
		return true;
	}
	
	private boolean checkLeaguer(String leaguerId, LeaguerRecord record){
		if(StringUtils.isNoneBlank(leaguerId) && !leaguerId.equals(record.getLeaguerId())){
			return false;
		}
		return true;
	}
}
