package com.hq.storeMS.service.sysConf.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "sysConf")
public class SysConf {
	// 微服务名称 如:storems/customerms/chainms/orderms等
	private String id;

	// 配置的键值对 如：{orderHose:"http://127.0.0.1:9118/orderms/ws/v1"}
	private Map<String, String> configMap = new HashMap<String, String>();

	public static SysConf newInstance() {
		SysConf data = new SysConf();
		return data;
	}

	public SysConf addConfig(String key, String value) {
		configMap.put(key, value);
		return this;
	}

	public String takeByKey(String key) {
		return configMap.get(key);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

}
