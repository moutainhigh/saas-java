package com.hq.storeMS.service.storeBeauticianInfo.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeBeauticianInfo")
public class StoreBeauticianInfo {

	@Id	
	private long id;
	
	private long storeId;
	
	//服务人员列表  {buserId:BeauticianInfo}
	private Map<Long,BeauticianInfo> beauticianInfoMap = new HashMap<Long,BeauticianInfo>();
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	public static StoreBeauticianInfo newInstance(long storeId){
		StoreBeauticianInfo storeBeauticianInfo = new StoreBeauticianInfo();
		storeBeauticianInfo.id = storeId;
		storeBeauticianInfo.storeId = storeId;
		long curTime = System.currentTimeMillis();
		
		storeBeauticianInfo.createdTime = curTime;
		storeBeauticianInfo.lastUpdateTime = curTime;
		return storeBeauticianInfo;
	}
	
	public boolean addBeauticianInfo(BeauticianInfo beauticianInfo){
		return StoreBeauticianInfoBeanHelper.getInstance().addBeauticianInfo(beauticianInfoMap, beauticianInfo);
	}
	
	public boolean updateBeauticianInfo(BeauticianInfo beauticianInfo){
		return StoreBeauticianInfoBeanHelper.getInstance().updateBeauticianInfo(beauticianInfoMap, beauticianInfo);
	}
	
	public boolean removeBeauticianInfo(long buserId){
		return StoreBeauticianInfoBeanHelper.getInstance().removeBeauticianInfo(beauticianInfoMap, buserId);
	}
	
	public void removeBeautician(long buserId){
		StoreBeauticianInfoBeanHelper.getInstance().removeBeautician(beauticianInfoMap, buserId);
	}
	
	public BeauticianInfo getBeautician(long beauticianId){
		return StoreBeauticianInfoBeanHelper.getInstance().getBeautician(beauticianInfoMap, beauticianId);
	}
	
	public List<BeauticianInfo> getBeauticianList(){
		return StoreBeauticianInfoBeanHelper.getInstance().getBeauticianList(beauticianInfoMap);
	}
	
	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public long getVer() {
		return ver;
	}


	public void setVer(long ver) {
		this.ver = ver;
	}
	
	public void incrVer() {
		this.ver = ver+1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<Long, BeauticianInfo> getBeauticianInfoMap() {
		return beauticianInfoMap;
	}

	public void setBeauticianInfoMap(Map<Long, BeauticianInfo> beauticianInfoMap) {
		this.beauticianInfoMap = beauticianInfoMap;
	}
	
	
}
