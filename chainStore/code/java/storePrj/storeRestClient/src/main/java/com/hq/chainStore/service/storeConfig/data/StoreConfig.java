package com.hq.chainStore.service.storeConfig.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainStore.service.storeConfig.data.appoint.AppointTimeConfig;
import com.hq.chainStore.service.storeConfig.data.appoint.CancelAppointConfig;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerBaseAttribute;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerTypeConfig;
import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "storeConfig")
public class StoreConfig implements IntfSynData{
	@Id
	private long id;
	private long storeId;
	// 预约配置
	private AppointConfig appointConfig;
	// 客户配置
	private LeaguerConfig leaguerConfig;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreConfig newInstance() {
		return new StoreConfig();
	}
	
	public AppointTimeConfig takeAppointTimeConfig() {
		if(this.appointConfig!=null) {
			return this.appointConfig.getAppointTimeConfig();
		}
		return null;
	}
	
	public Map<Integer, CancelAppointConfig> takeCancelAppointConfigMap() {
		if(this.appointConfig!=null) {
			return this.appointConfig.getCancelAppointConfigMap();
		}
		return null;
	}
	
	//获取取消预约原因列表  根据添加的先后排序  [Id倒序]
	public List<CancelAppointConfig> takeCancelAppointConfigList() {
		List<CancelAppointConfig> list = new ArrayList<CancelAppointConfig>();
		if(this.appointConfig!=null) {
			Collection<CancelAppointConfig> values = this.appointConfig.getCancelAppointConfigMap().values();
			if(values != null && !values.isEmpty()) {
				list.addAll(values);
				Collections.sort(list, new Comparator<CancelAppointConfig>() {
					@Override
					public int compare(CancelAppointConfig o1, CancelAppointConfig o2) {
						return o2.getId() - o1.getId();
					}
				});
			}
		}
		return list;
	}
	
	public Map<Integer, LeaguerTypeConfig> takeLeaguerTypeConfigMap() {
		if(this.leaguerConfig!=null) {
			return this.leaguerConfig.getLeaguerTypeConfigMap();
		}
		return null;
	}
	
	public Map<Integer, LeaguerOriginConfig> takeLeaguerOriginConfigMap() {
		if(this.leaguerConfig!=null) {
			return this.leaguerConfig.getLeaguerOriginConfigMap();
		}
		return null;
	}
	
	//获取客户来源列表  根据添加的先后排序  [Id倒序]
	public List<LeaguerOriginConfig> takeLeaguerOriginConfigList() {
		List<LeaguerOriginConfig> list = new ArrayList<LeaguerOriginConfig>();
		if(this.leaguerConfig!=null) {
			Collection<LeaguerOriginConfig> values = this.leaguerConfig.getLeaguerOriginConfigMap().values();
			if(values != null && !values.isEmpty()) {
				list.addAll(values);
				Collections.sort(list, new Comparator<LeaguerOriginConfig>() {
					@Override
					public int compare(LeaguerOriginConfig o1, LeaguerOriginConfig o2) {
						return o2.getId() - o1.getId();
					}
				});
			}
		}
		return list;
	}
	
	//获取客户基础属性列表
	public List<LeaguerBaseAttribute> takeLeaguerBaseAttributeList() {
		if(this.leaguerConfig!=null) {
			return this.leaguerConfig.getLeaguerBaseAttributes();
		}
		return null;
	}
	
	public Map<Integer, LeaguerExpandAttribute> takeLeaguerExpandAttributeMap() {
		if(this.leaguerConfig!=null) {
			this.leaguerConfig.getLeaguerExpandAttributeMap();
		}
		return null;
	}
	
	//获取客户扩展属性列表  按sort排序  [sort升序]
	public List<LeaguerExpandAttribute> takeLeaguerExpandAttributeList() {
		List<LeaguerExpandAttribute> list = new ArrayList<LeaguerExpandAttribute>();
		if(this.leaguerConfig!=null) {
			Collection<LeaguerExpandAttribute> values = this.leaguerConfig.getLeaguerExpandAttributeMap().values();
			if(values != null && !values.isEmpty()) {
				list.addAll(values);
				Collections.sort(list, new Comparator<LeaguerExpandAttribute>() {
					@Override
					public int compare(LeaguerExpandAttribute o1, LeaguerExpandAttribute o2) {
						return o1.getSort() - o2.getSort();
					}
				});
			}
		}
		return list;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public AppointConfig getAppointConfig() {
		return appointConfig;
	}

	public void setAppointConfig(AppointConfig appointConfig) {
		this.appointConfig = appointConfig;
	}

	public LeaguerConfig getLeaguerConfig() {
		return leaguerConfig;
	}

	public void setLeaguerConfig(LeaguerConfig leaguerConfig) {
		this.leaguerConfig = leaguerConfig;
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

}
