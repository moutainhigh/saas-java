package com.hq.storeMS.service.serverConfig.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.serverConfig.apiData.ServerConfigQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ServerConfigDAO extends MongodbDao<ServerConfig> {
	
	public static ServerConfigDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ServerConfigDAO.class);
	}
	
	public List<ServerConfig> findList(ServerConfigQueryForm queryForm){
		Criteria criteria = buildCriteria(queryForm);
	    Query query = new Query(criteria);
	    return super.find(query);
	}

	private Criteria buildCriteria(ServerConfigQueryForm queryForm) {
		Criteria criteria = new Criteria();  
		//应用名称 中文
		if(StringUtils.isNoneBlank(queryForm.getAppNameCh())){
			criteria.and("appNameCh").is(queryForm.getAppNameCh());
		}
		//应用名称 英文
		if(StringUtils.isNoneBlank(queryForm.getAppNameEn())){
			criteria.and("appNameEn").is(queryForm.getAppNameEn());
		}
		//应用版本号
		if(StringUtils.isNoneBlank(queryForm.getAppVersion())){
			criteria.and("appVersion").is(queryForm.getAppVersion());
		}
		return criteria;
	}
	
	//取指定版本的应用配置信息
	public ServerConfig findOne(String appNameCh, String appNameEn, String appVersion){
		Criteria criteria = new Criteria();  
		//应用名称 中文
		if(StringUtils.isNoneBlank(appNameCh)){
			criteria.and("appNameCh").is(appNameCh);
		}
		//应用名称 英文
		if(StringUtils.isNoneBlank(appNameEn)){
			criteria.and("appNameEn").is(appNameEn);
		}
		
		criteria.and("appVersion").is(appVersion);
		Query query = new Query(criteria);
		return findOne(query);
	}
}
