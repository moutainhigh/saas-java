package com.hq.storeMS.service.storeBeauticianInfo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoBeanHelper {

	public static StoreBeauticianInfoBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoBeanHelper.class);
	}
	
	public boolean addBeauticianInfo(Map<Long,BeauticianInfo> beauticianInfoMap, BeauticianInfo beauticianInfo){
		if(!beauticianInfoMap.containsKey(beauticianInfo.getBuserId())){
			beauticianInfoMap.put(beauticianInfo.getBuserId(), beauticianInfo);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean updateBeauticianInfo(Map<Long,BeauticianInfo> beauticianInfoMap, BeauticianInfo beauticianInfo){
		if(beauticianInfoMap.containsKey(beauticianInfo.getBuserId())){
			beauticianInfoMap.put(beauticianInfo.getBuserId(), beauticianInfo);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeBeauticianInfo(Map<Long,BeauticianInfo> beauticianInfoMap, long buserId){
		if(beauticianInfoMap.containsKey(buserId)){
			beauticianInfoMap.remove(buserId);
			return true;
		}else{
			return false;
		}
	}
	
	public void removeBeautician(Map<Long,BeauticianInfo> beauticianInfoMap, long buserId){
		if(beauticianInfoMap.containsKey(buserId)){//删除员工同时删除医美师信息
			beauticianInfoMap.remove(buserId);
		}
	}
	
	public BeauticianInfo getBeautician(Map<Long,BeauticianInfo> beauticianInfoMap, long beauticianId){
		BeauticianInfo beauticianInfo = null;
		if(beauticianInfoMap.containsKey(beauticianId)){
			beauticianInfo = beauticianInfoMap.get(beauticianId);
		}
		return beauticianInfo;
	}
	
	public List<BeauticianInfo> getBeauticianList(Map<Long,BeauticianInfo> beauticianInfoMap){
		Collection<BeauticianInfo> collection = beauticianInfoMap.values();
		List<BeauticianInfo> beauticianList = new ArrayList<BeauticianInfo>(collection);
		
		return beauticianList;
	}
	
	
}
