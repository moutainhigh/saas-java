package com.hq.storeMS.service.storeConfig.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.hq.storeMS.service.storeConfig.apiData.CancelAppointAddForm;
import com.hq.storeMS.service.storeConfig.apiData.CancelAppointRemoveForm;
import com.hq.storeMS.service.storeConfig.apiData.CancelAppointUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeAddForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeSortForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeStatusForm;
import com.hq.storeMS.service.storeConfig.apiData.ExpandAttributeUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerOriginAddForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerOriginRemoveForm;
import com.hq.storeMS.service.storeConfig.apiData.LeaguerOriginUpdateForm;
import com.hq.storeMS.service.storeConfig.data.appoint.CancelAppointConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.DirectionEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigBeanHelper {

	public static StoreConfigBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StoreConfigBeanHelper.class);
	}
	
	public boolean addLeaguerOrigin(LeaguerConfig leaguerConfig, LeaguerOriginAddForm inputForm) {
		boolean success = false;
		Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap = leaguerConfig.getLeaguerOriginConfigMap();
		if (!leaguerOriginConfigMap.containsKey(inputForm.getId()) && leaguerConfig.getLeaguerOriginConfigIndex() + 1 == inputForm.getId()) {
			LeaguerOriginConfig data = inputForm.toLeaguerOriginConfig();
			leaguerOriginConfigMap.put(data.getId(), data);
			leaguerConfig.setLeaguerOriginConfigIndex(data.getId());
			success = true;
		}
		return success;
	}
	
	public boolean removeLeaguerOrigin(LeaguerConfig leaguerConfig, LeaguerOriginRemoveForm inputForm) {
		boolean success = false;
		Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap = leaguerConfig.getLeaguerOriginConfigMap();
		if (leaguerOriginConfigMap.containsKey(inputForm.getId())) {
			leaguerOriginConfigMap.remove(inputForm.getId());
			success = true;
		}
		return success;
	}
	
	public boolean updateLeaguerOrigin(LeaguerConfig leaguerConfig, LeaguerOriginUpdateForm inputForm) {
		boolean success = false;
		Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap = leaguerConfig.getLeaguerOriginConfigMap();
		if (leaguerOriginConfigMap.containsKey(inputForm.getId())) {
			LeaguerOriginConfig data = leaguerOriginConfigMap.get(inputForm.getId());
			inputForm.updateLeaguerOriginConfig(data);
			success = true;
		}
		return success;
	}
	
	public boolean addExpandAttribute(LeaguerConfig leaguerConfig, ExpandAttributeAddForm inputForm) {
		boolean success = false;
		Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap = leaguerConfig.getLeaguerExpandAttributeMap();
		if (!leaguerExpandAttributeMap.containsKey(inputForm.getId()) && leaguerConfig.getLeaguerExpandAttributeIndex() + 1 == inputForm.getId()) {
			LeaguerExpandAttribute data = inputForm.toLeaguerExpandAttribute();
			leaguerExpandAttributeMap.put(data.getId(), data);
			leaguerConfig.setLeaguerExpandAttributeIndex(data.getId());
			success = true;
		}
		return success;
	}
	
	public boolean sortExpandAttribute(LeaguerConfig leaguerConfig, ExpandAttributeSortForm inputForm) {
		Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap = leaguerConfig.getLeaguerExpandAttributeMap();
		if (leaguerExpandAttributeMap.containsKey(inputForm.getId())) {
			List<LeaguerExpandAttribute> list = new ArrayList<LeaguerExpandAttribute>();
			list.addAll(leaguerExpandAttributeMap.values());
			Collections.sort(list, new Comparator<LeaguerExpandAttribute>() {
				@Override
				public int compare(LeaguerExpandAttribute o1, LeaguerExpandAttribute o2) {
					return o1.getSort() - o2.getSort();
				}
			});
			
			int index = 0;
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getId() == inputForm.getId()) {
					index = i;
					break;
				}
			}
			
			if(inputForm.getSort() == DirectionEnum.UP.ordinal()) {
				if(index != 0) {
					LeaguerExpandAttribute data1 = list.get(index-1);
					LeaguerExpandAttribute data2 = list.get(index);
					int sort = data1.getSort();
					data1.setSort(data2.getSort());
					data2.setSort(sort);
				}
				return true;
			}else if(inputForm.getSort() == DirectionEnum.DOWN.ordinal()) {
				if(index != list.size()-1 ) {
					LeaguerExpandAttribute data1 = list.get(index+1);
					LeaguerExpandAttribute data2 = list.get(index);
					int sort = data1.getSort();
					data1.setSort(data2.getSort());
					data2.setSort(sort);
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean statusExpandAttribute(LeaguerConfig leaguerConfig, ExpandAttributeStatusForm inputForm) {
		boolean success = false;
		Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap = leaguerConfig.getLeaguerExpandAttributeMap();
		if (leaguerExpandAttributeMap.containsKey(inputForm.getId())) {
			LeaguerExpandAttribute data = leaguerExpandAttributeMap.get(inputForm.getId());
			inputForm.updateLeaguerExpandAttribute(data);
			success = true;
		}
		return success;
	}
	
	public boolean updateExpandAttribute(LeaguerConfig leaguerConfig, ExpandAttributeUpdateForm inputForm) {
		boolean success = false;
		Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap = leaguerConfig.getLeaguerExpandAttributeMap();
		if (leaguerExpandAttributeMap.containsKey(inputForm.getId())) {
			LeaguerExpandAttribute data = leaguerExpandAttributeMap.get(inputForm.getId());
			inputForm.updateLeaguerExpandAttribute(data);
			success = true;
		}
		return success;
	}
	
	public boolean addCancelAppointConfig(AppointConfig appointConfig, CancelAppointAddForm inputForm) {
		boolean success = false;
		Map<Integer, CancelAppointConfig> cancelAppointConfigMap = appointConfig.getCancelAppointConfigMap();
		if (!cancelAppointConfigMap.containsKey(inputForm.getId()) && appointConfig.getCancelAppointIndex() + 1 == inputForm.getId()) {
			CancelAppointConfig data = inputForm.toCancelAppointConfig();
			cancelAppointConfigMap.put(data.getId(), data);
			appointConfig.setCancelAppointIndex(data.getId());
			success = true;
		}
		return success;
	}
	
	public boolean removeCancelAppointConfig(AppointConfig appointConfig, CancelAppointRemoveForm inputForm) {
		boolean success = false;
		Map<Integer, CancelAppointConfig> cancelAppointConfigMap = appointConfig.getCancelAppointConfigMap();
		if (cancelAppointConfigMap.containsKey(inputForm.getId())) {
			cancelAppointConfigMap.remove(inputForm.getId());
			success = true;
		}
		return success;
	}
	
	public boolean updateCancelReason(AppointConfig appointConfig, CancelAppointUpdateForm inputForm) {
		boolean success = false;
		Map<Integer, CancelAppointConfig> cancelAppointConfigMap = appointConfig.getCancelAppointConfigMap();
		if (cancelAppointConfigMap.containsKey(inputForm.getId())) {
			CancelAppointConfig cancelAppointConfig = cancelAppointConfigMap.get(inputForm.getId());
			inputForm.updateCancelAppointConfig(cancelAppointConfig);
			success = true;
		}
		return success;
	}

}
