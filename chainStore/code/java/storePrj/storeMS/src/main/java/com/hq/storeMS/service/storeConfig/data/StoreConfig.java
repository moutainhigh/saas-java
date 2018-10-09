package com.hq.storeMS.service.storeConfig.data;

import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.storeConfig.data.appoint.AppointTimeConfig;
import com.hq.storeMS.service.storeConfig.data.appoint.CancelAppointConfig;
import com.hq.storeMS.service.storeConfig.data.chain.ShareDataConfig;
import com.hq.storeMS.service.storeConfig.data.chain.ShareEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBaseAttribute;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBirthdayConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerTypeConfig;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeConfig")
public class StoreConfig {
	@Id
	private long id;
	private long storeId;
	// 预约配置
	private AppointConfig appointConfig;
	// 客户配置
	private LeaguerConfig leaguerConfig;
	
	private ChainConfig chainConfig;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreConfig newInstance(long storeId) {
		StoreConfig data = new StoreConfig();
		data.id = storeId;
		data.storeId = storeId;

		data.appointConfig = AppointConfig.newInstance();
		data.leaguerConfig = LeaguerConfig.newInstance();
		data.chainConfig = ChainConfig.newInstance();

		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public boolean shareData2Chain(long chainId) {
		ShareDataConfig config = chainConfig.takeShareDataConfigByChainId(chainId);
		if(config!=null) {
			return config.getShareFlag() == ShareEnum.SHARE.ordinal();
		}
		return false;
	}
	
	public List<LeaguerBirthdayConfig> takeLeaguerBirthdayConfigs(){
		if(this.leaguerConfig!=null) {
			return this.leaguerConfig.getBirthdayConfigs();
		}
		return null;
	}
	
	public Map<Integer, LeaguerAnalysisConfig> takeLeaguerAnalysisConfig(){
		if(this.leaguerConfig!=null) {
			return this.leaguerConfig.getLeaguerAnalysisConfigMap();
		}
		return null;
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
	
	public void incrVer() {
		this.ver = ver+1;
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

	public ChainConfig getChainConfig() {
		return chainConfig;
	}

	public void setChainConfig(ChainConfig chainConfig) {
		this.chainConfig = chainConfig;
	}

}
