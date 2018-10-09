package com.hq.storeMS.service.serverConfig.bs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigAddForm;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigQueryForm;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigUpdForm;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ServerConfigHandler {

	public static ServerConfigHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigHandler.class);
	}

	public ReqResult<ServerConfig> findServerConfigs(ServerConfigQueryForm queryForm) {
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		try {
			List<ServerConfig> serverConfigs = ServerConfigMgr.getInstance().findServerConfigs(queryForm);
			result.setTargetList(serverConfigs);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ServerConfig, "ServerConfigHandler[findServerConfigs]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ServerConfig> findOneServerConfig(String appNameCh, String appNameEn, String appVersion) {
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		try {
			OperateTips operateTips = checkCond(appNameCh, appNameEn);
			if(!operateTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
				return result;
			}
			ServerConfig serverConfig = ServerConfigMgr.getInstance().findServerConfigByOne(appNameCh, appNameEn, appVersion);
			result.setTarget(serverConfig);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(appNameCh, appNameEn, appVersion);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ServerConfig, "ServerConfigHandler[findServerConfigs]", reason, e);
		}
		return result;
	}
	
	private OperateTips checkCond(String appNameCh, String appNameEn){
		OperateTips tips = OperateTips.newInstance();
		if(StringUtils.isBlank(appNameCh) && StringUtils.isBlank(appNameEn)){
			tips.setSuccess(false);
			tips.setTips("应用名称必须有一个不为空");
		}
		return tips;
	}
	
	public ReqResult<ServerConfig> getServerConfig(long serverConfigId) {
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		try {
			ServerConfig serverConfig = ServerConfigMgr.getInstance().getServerConfig(serverConfigId);
			result.setTarget(serverConfig);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(serverConfigId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ServerConfig, "ServerConfigHandler[getServerConfig]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ServerConfig> updServerConfig(long serverConfigId, ServerConfigUpdForm updForm) {
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		try {
			ServerConfig target = ServerConfigMgr.getInstance().getServerConfig(serverConfigId);
			FastBeanCopyer.getInstance().copy(updForm, target);
			ServerConfigMgr.getInstance().updServerConfig(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(serverConfigId, updForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ServerConfig, "ServerConfigHandler[updServerConfig]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ServerConfig> addServerConfig(ServerConfigAddForm addForm) {
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		try {
			ServerConfig target = ServerConfig.newInstance();
			FastBeanCopyer.getInstance().copy(addForm, target);
			ServerConfigMgr.getInstance().addServerConfig(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.ServerConfig, "ServerConfigHandler[addServerConfig]", reason, e);
		}
		return result;
	}

}
