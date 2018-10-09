package com.hq.chainStore.service.storeConfig.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerBaseAttribute;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.hq.chainStore.service.storeConfig.data.leaguer.LeaguerTypeConfig;

public class LeaguerConfig {
	// 客户来源设置
	private int leaguerOriginConfigIndex = 0;
	private Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap = new HashMap<Integer, LeaguerOriginConfig>();

	// 类型设置
	private int leaguerTypeConfigIndex = 0;
	private Map<Integer, LeaguerTypeConfig> leaguerTypeConfigMap = new HashMap<Integer, LeaguerTypeConfig>();

	// 基础属性
	private List<LeaguerBaseAttribute> leaguerBaseAttributes = new ArrayList<LeaguerBaseAttribute>();

	// 扩展属性
	private int leaguerExpandAttributeIndex = 0;
	private Map<Integer, LeaguerExpandAttribute> leaguerExpandAttributeMap = new HashMap<Integer, LeaguerExpandAttribute>();

	public static LeaguerConfig newInstance() {
		return new LeaguerConfig();
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
}
