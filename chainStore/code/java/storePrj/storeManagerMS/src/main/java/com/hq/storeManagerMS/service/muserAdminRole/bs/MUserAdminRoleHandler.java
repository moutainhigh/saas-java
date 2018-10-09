package com.hq.storeManagerMS.service.muserAdminRole.bs;

import java.util.List;

import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleAddApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleDelApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleQueryApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleUpdApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserAdminRoleHandler {

	public static MUserAdminRoleHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MUserAdminRoleHandler.class);
	}
	
	private final LogModule logModule = LogModule.MUserAdminRole;
	private final String reason = "Exception happens.";
	
	public ReqResult<MUserAdminRole> addMUserAdminRole(MUserAdminRoleAddApiForm addForm) {
		ReqResult<MUserAdminRole> result = ReqResult.newInstance(false, MUserAdminRole.class);
		try {
			MUserAdminRole target = addForm.toMUserAdminRole();
			MUserAdminRoleMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "MUserAdminRoleHandler[addMUserAdminRole]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<MUserAdminRole> delMUserAdminRole(MUserAdminRoleDelApiForm delForm) {
		ReqResult<MUserAdminRole> result = ReqResult.newInstance(false, MUserAdminRole.class);
		try {
			MUserAdminRole muserAdminRole = MUserAdminRoleMgr.getInstance().get(delForm.getId());
			if(muserAdminRole!=null){
				MUserAdminRoleMgr.getInstance().delete(muserAdminRole);
				result.setSuccess(true);
			}else{
				result.setTips("角色不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "MUserAdminRoleHandler[delMUserAdminRole]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<MUserAdminRole> updateMUserAdminRole(MUserAdminRoleUpdApiForm updForm) {
		ReqResult<MUserAdminRole> result = ReqResult.newInstance(false, MUserAdminRole.class);
		try {
			MUserAdminRole muserAdminRole = MUserAdminRoleMgr.getInstance().get(updForm.getId());
			if(muserAdminRole!=null){
				updForm.updMUserAdminRole(muserAdminRole);
				MUserAdminRoleMgr.getInstance().update(muserAdminRole);
				result.setSuccess(true);
			}else{
				result.setTips("角色不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "MUserAdminRoleHandler[updateMUserAdminRole]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	public ReqResult<MUserAdminRole> getMUserAdminRole(long id) {
		ReqResult<MUserAdminRole> result = ReqResult.newInstance(false, MUserAdminRole.class);
		try {
			MUserAdminRole muserAdminRoleRO = MUserAdminRoleMgr.getInstance().get(id);
			if(muserAdminRoleRO!=null){
				result.setTarget(muserAdminRoleRO);
				result.setSuccess(true);
			}else{
				result.setTips("角色不存在");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String logId = "MUserAdminRoleHandler[getMUserAdminRole]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<MUserAdminRole> findMUserAdminRoles(MUserAdminRoleQueryApiForm queryForm) {
		ReqResult<MUserAdminRole> result = ReqResult.newInstance(false, MUserAdminRole.class);
		try {
			List<MUserAdminRole> muserAdminRoles = MUserAdminRoleMgr.getInstance().findMUserAdminRoles(queryForm);
			result.setTargetList(muserAdminRoles);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "MUserAdminRoleHandler[findMUserAdminRoles]";
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
