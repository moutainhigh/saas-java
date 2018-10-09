package com.hq.storeMS.service.storeConfig.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBaseAttribute;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerBirthdayConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerTypeConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitBirthdayEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitExpandAttributeEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerAnalysisEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerBaseAttributeEnum;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerOriginEnum;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class LeaguerConfig {
	// 客户来源设置
	private int leaguerOriginConfigIndex = 0;
	private Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap = new HashMap<Integer, LeaguerOriginConfig>();

	// 基础属性
	private List<LeaguerBaseAttribute> leaguerBaseAttributes = new ArrayList<LeaguerBaseAttribute>();

	// 扩展属性
	private int leaguerExpandAttributeIndex = 0;
	private Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap = new HashMap<Integer, LeaguerExpandAttribute>();
	
	// 会员分析设置
	private int leaguerAnalysisConfigIndex = 0;
	private Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap = new HashMap<Integer, LeaguerAnalysisConfig>();
	
	//生日提醒设置
	private List<LeaguerBirthdayConfig> birthdayConfigs = new ArrayList<LeaguerBirthdayConfig>();
	/*************************************遗留字段********************************************/
	// 类型设置
	private int leaguerTypeConfigIndex = 0;
	private Map<Integer, LeaguerTypeConfig> leaguerTypeConfigMap = new HashMap<Integer, LeaguerTypeConfig>();

	public static LeaguerConfig newInstance() {
		LeaguerConfig data = new LeaguerConfig();
		
		SysInitLeaguerOriginEnum[] origins = SysInitLeaguerOriginEnum.values();
		data.leaguerOriginConfigIndex = origins.length;
		for (SysInitLeaguerOriginEnum origin : origins) {
			LeaguerOriginConfig config = LeaguerOriginConfig.newInstance(origin);
			data.leaguerOriginConfigMap.put(config.getId(), config);
		}
		
		SysInitLeaguerBaseAttributeEnum[] baseAttrs = SysInitLeaguerBaseAttributeEnum.values();
		for (SysInitLeaguerBaseAttributeEnum attr : baseAttrs) {
			data.leaguerBaseAttributes.add(LeaguerBaseAttribute.newInstance(attr));
		}
		
		SysInitExpandAttributeEnum[] expandAttrs = SysInitExpandAttributeEnum.values();
		data.leaguerExpandAttributeIndex = expandAttrs.length;
		for (SysInitExpandAttributeEnum attr : expandAttrs) {
			LeaguerExpandAttribute attrData = LeaguerExpandAttribute.newInstance(attr);
			data.leaguerExpandAttributeMap.put(attrData.getId(), attrData);
		}
		
		//会员分析
		SysInitLeaguerAnalysisEnum[] leaguerAnalysisValues = SysInitLeaguerAnalysisEnum.values();
		data.leaguerAnalysisConfigIndex = leaguerAnalysisValues.length;
		for (SysInitLeaguerAnalysisEnum sysInitLeaguerAnalysisEnum : leaguerAnalysisValues) {
			LeaguerAnalysisConfig leaguerAnalysisConfig = LeaguerAnalysisConfig.newInstance(sysInitLeaguerAnalysisEnum);
			data.leaguerAnalysisConfigMap.put(leaguerAnalysisConfig.getId(), leaguerAnalysisConfig);
		}
		
		SysInitBirthdayEnum[] birthdayEnums = SysInitBirthdayEnum.values();
		for (SysInitBirthdayEnum sysInitBirthdayEnum : birthdayEnums) {
			data.birthdayConfigs.add(LeaguerBirthdayConfig.newInstance(sysInitBirthdayEnum));
		}		
		return data;
	}

	public int getLeaguerOriginConfigIndex() {
		return leaguerOriginConfigIndex;
	}

	public void setLeaguerOriginConfigIndex(int leaguerOriginConfigIndex) {
		this.leaguerOriginConfigIndex = leaguerOriginConfigIndex;
	}

	public Map<Integer, LeaguerOriginConfig> getLeaguerOriginConfigMap() {
		return leaguerOriginConfigMap;
	}

	public void setLeaguerOriginConfigMap(Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap) {
		this.leaguerOriginConfigMap = leaguerOriginConfigMap;
	}

	public int getLeaguerTypeConfigIndex() {
		return leaguerTypeConfigIndex;
	}

	public void setLeaguerTypeConfigIndex(int leaguerTypeConfigIndex) {
		this.leaguerTypeConfigIndex = leaguerTypeConfigIndex;
	}

	public Map<Integer, LeaguerTypeConfig> getLeaguerTypeConfigMap() {
		return leaguerTypeConfigMap;
	}

	public void setLeaguerTypeConfigMap(Map<Integer, LeaguerTypeConfig> leaguerTypeConfigMap) {
		this.leaguerTypeConfigMap = leaguerTypeConfigMap;
	}

	public List<LeaguerBaseAttribute> getLeaguerBaseAttributes() {
		return leaguerBaseAttributes;
	}

	public void setLeaguerBaseAttributes(List<LeaguerBaseAttribute> leaguerBaseAttributes) {
		this.leaguerBaseAttributes = leaguerBaseAttributes;
	}

	public int getLeaguerExpandAttributeIndex() {
		return leaguerExpandAttributeIndex;
	}

	public void setLeaguerExpandAttributeIndex(int leaguerExpandAttributeIndex) {
		this.leaguerExpandAttributeIndex = leaguerExpandAttributeIndex;
	}

	public Map<Integer, LeaguerExpandAttribute> getLeaguerExpandAttributeMap() {
		return leaguerExpandAttributeMap;
	}

	public void setLeaguerExpandAttributeMap(Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap) {
		this.leaguerExpandAttributeMap = leaguerExpandAttributeMap;
	}

	public int getLeaguerAnalysisConfigIndex() {
		return leaguerAnalysisConfigIndex;
	}

	public void setLeaguerAnalysisConfigIndex(int leaguerAnalysisConfigIndex) {
		this.leaguerAnalysisConfigIndex = leaguerAnalysisConfigIndex;
	}

	public Map<Integer, LeaguerAnalysisConfig> getLeaguerAnalysisConfigMap() {
		return leaguerAnalysisConfigMap;
	}

	public void setLeaguerAnalysisConfigMap(Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap) {
		this.leaguerAnalysisConfigMap = leaguerAnalysisConfigMap;
	}

	public List<LeaguerBirthdayConfig> getBirthdayConfigs() {
		return birthdayConfigs;
	}

	public void setBirthdayConfigs(List<LeaguerBirthdayConfig> birthdayConfigs) {
		this.birthdayConfigs = birthdayConfigs;
	}
}
