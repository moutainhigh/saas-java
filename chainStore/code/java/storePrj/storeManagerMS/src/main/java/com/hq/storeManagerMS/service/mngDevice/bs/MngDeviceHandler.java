package com.hq.storeManagerMS.service.mngDevice.bs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeClient.service.buser.data.BUserCount;
import com.hq.storeManagerMS.common.log.LogHelper;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.mngDevice.apiData.MngDeviceQueryForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BUserQueryApiForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BindDeviceForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.mctrl.MCtrlLockApiForm;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserBindInfo;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserDevice;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.DeviceDetail;
import com.hq.storeManagerMS.service.mngDevice.data.mclient.MClient;
import com.zenmind.common.hotSwap.HotSwap;

public class MngDeviceHandler {
		private final LogModule logModule = LogModule.MngDevice;
		
		public static MngDeviceHandler getInstance() {
			return HotSwap.getInstance().getSingleton(MngDeviceHandler.class);
		}
		
		public ReqResult<DeviceDetail> findDeviceDetailList(MngDeviceQueryForm params) {
			ReqResult<DeviceDetail> result = ReqResult.newInstance(false, DeviceDetail.class);
			try {
				List<DeviceDetail> list = MngDeviceMgr.getInstance().findDeviceDetailList(params);
				//按SN号查询时，对list进行过滤
				if(StringUtils.isNotBlank(params.getSnCode()) && CollectionUtils.isNotEmpty(list)) {
					list = filterDevice4SnCode(list,params.getSnCode());
				}
				result.setTargetList(list);
				result.setSuccess(true);
			} catch (Exception e) {
				final String logId = "MngDeviceHandler[findDeviceDetailList]";
				final String reason = LogHelper.getInstance().exceptionReason(params);
				ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
				HandlerHelper.getInstance().handleException(exceptionInfo, e);
			}
			return result;
		}
		
		private List<DeviceDetail> filterDevice4SnCode(List<DeviceDetail> list,String snCode) {
			for(Iterator<DeviceDetail> it =list.iterator(); it.hasNext(); ){
		       DeviceDetail deviceDetail = it.next();
		       if(!snCode.equals(deviceDetail.getSnCode())) {
		    	   it.remove();
		       }
		    }
			return list;
		}

		public ReqResult<MClient> lockDevice(long id, MCtrlLockApiForm lockForm) {
			ReqResult<MClient> result = ReqResult.newInstance(false,MClient.class);
			try {
				MClient mclient = MngDeviceMgr.getInstance().lockDevice(id,lockForm);
				result.setTarget(mclient);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} catch (Exception e) {
				final String logId = "MngDeviceHandler[lockDevice]";
				final String reason = LogHelper.getInstance().exceptionReason(lockForm);
				ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
				HandlerHelper.getInstance().handleException(exceptionInfo, e);
			}
			return result;
		}
		
		public ReqResult<BUserDevice> bindDevice(BindDeviceForm bindForm,String actionName) {
			ReqResult<BUserDevice> result =  ReqResult.newInstance(false,BUserDevice.class);
			try {
				BUserDevice buserDevice = MngDeviceMgr.getInstance().bindDevice(bindForm,actionName);
				result.setTarget(buserDevice);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} catch (Exception e) {
				final String logId = "MngDeviceHandler[bindDevice]";
				final String reason = LogHelper.getInstance().exceptionReason(bindForm);
				ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
				HandlerHelper.getInstance().handleException(exceptionInfo, e);
			}
			return result;
		}
		
		public ReqResult<BUserBindInfo> findBUserBindInfoList(BUserQueryApiForm params) {
			ReqResult<BUserBindInfo> result =  ReqResult.newInstance(false,BUserBindInfo.class);
			try {
				List<BUserBindInfo> list = MngDeviceMgr.getInstance().findBUserBindInfoList(params);
				result.setTargetList(list);
				result.setSuccess(true);
			} catch (Exception e) {
				final String logId = "MngDeviceHandler[findBUserBindInfoList]";
				final String reason = LogHelper.getInstance().exceptionReason(params);
				ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
				HandlerHelper.getInstance().handleException(exceptionInfo, e);
			}
			return result;
		}
		
		public ReqResult<BUserCount> getBUserBindInfoCount(BUserQueryApiForm params) {
			ReqResult<BUserCount> result = ReqResult.newInstance(false,BUserCount.class);
			try {
				BUserCount buserCount = MngDeviceMgr.getInstance().getBUserBindInfoCount(params);
				result.setTarget(buserCount);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} catch (Exception e) {
				final String logId = "MngDeviceHandler[getBUserBindInfoCount]";
				final String reason = LogHelper.getInstance().exceptionReason(params);
				ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
				HandlerHelper.getInstance().handleException(exceptionInfo, e);
			}
			return result;
		}
		
		public ReqResult<MClient> findMClientList(String snCode) {
			ReqResult<MClient> result =  ReqResult.newInstance(false,MClient.class);
			try {
				List<MClient> list = new ArrayList<MClient>();
				List<MClient> listTmp  = MngDeviceMgr.getInstance().findMClientList(snCode);
				if (CollectionUtils.isNotEmpty(listTmp)) list = listTmp;
				result.setTargetList(list);
				result.setSuccess(true);
			} catch (Exception e) {
				final String logId = "MngDeviceHandler[findMClientList]";
				final String reason = LogHelper.getInstance().exceptionReason(snCode);
				ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
				HandlerHelper.getInstance().handleException(exceptionInfo, e);
			}
			return result;
		}
}
