package com.hq.storeMS.service.buserDevice.bs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buserDevice.apiData.AddStoreDeviceData;
import com.hq.storeMS.service.buserDevice.apiData.BUserDeviceUpdateForm;
import com.hq.storeMS.service.buserDevice.apiData.BUserDeviceUpdateType;
import com.hq.storeMS.service.buserDevice.apiData.BindBUserDeviceData;
import com.hq.storeMS.service.buserDevice.apiData.BindDeviceForm;
import com.hq.storeMS.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.storeMS.service.buserDevice.apiData.OperateHistoryQueryForm;
import com.hq.storeMS.service.buserDevice.data.BUserDevice;
import com.hq.storeMS.service.buserDevice.data.DeviceInfo;
import com.hq.storeMS.service.buserDevice.data.MClient;
import com.hq.storeMS.service.buserDevice.data.MCtrlLockApiForm;
import com.hq.storeMS.service.buserDevice.data.OperateHistory;
import com.hq.storeMS.service.buserDevice.data.vo.DeviceDetail;
import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestProxyException;

/**
 * @ClassName: BUserDeviceHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年1月27日 上午11:28:53
 * 
 */
public class BUserDeviceHandler {

	public static BUserDeviceHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BUserDeviceHandler.class);
	}

	public ReqResult<MClient> lockDevice(long id, MCtrlLockApiForm lockForm) {
		ReqResult<MClient> result = ReqResult.newInstance(false, MClient.class);
		try {
			//iot 锁定仪器
			MClient mclient = BUserDeviceMgr.getInstance().lockDevice(id, lockForm);
	
			result.setTarget(mclient);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(id, lockForm);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getByBUser]", reason, e);
		}
		return result;
	}

	public ReqResult<BUserDevice> getByBUser(long buserId) {
		ReqResult<BUserDevice> result = ReqResult.newInstance(false, BUserDevice.class);
		try {
			BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserId);
			if (buserDevice != null) {
				result.setTarget(buserDevice);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} else {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户未绑定仪器");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(buserId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getByBUser]", reason, e);
		}
		return result;
	}

	public ReqResult<DeviceDetail> getList(long buserId) {
		ReqResult<DeviceDetail> result = ReqResult.newInstance(false, DeviceDetail.class);
		try {
			BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserId);
			List<DeviceDetail> targetList = new ArrayList<DeviceDetail>();
			if (buserDevice != null) {
				targetList = BUserDeviceBeanHelper.getInstance().getDeviceDetailList(buserDevice);
			}
		
			result.setTargetList(targetList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);

		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(buserId);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getList]", reason, e);
		}
		return result;

	}

	public ReqResult<BUserDevice> bindDevice(BindDeviceForm bindForm) {
		ReqResult<BUserDevice> result = ReqResult.newInstance(false, BUserDevice.class);
		try {
			MClient mclient = BUserDeviceMgr.getInstance().findBySnCode(bindForm.getSnCode());
			if(StringUtils.isNotBlank(mclient.getBandingAccount())){
				result.setSuccess(false);
				result.setTips("该仪器已被绑定");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}else{
				// iot 绑定仪器
				MClient mClient = BUserDeviceMgr.getInstance().bindDevice(bindForm);
				long iotRecordId = mClient.getId();

				//store 绑定仪器
				BindBUserDeviceData bindBUserDeviceData = BindBUserDeviceData.newInstance();
				bindBUserDeviceData.setBuserId(bindForm.getBuserId());
				bindBUserDeviceData.setIotRecordId(iotRecordId);
				bindBUserDeviceData.setSalesman(bindForm.getSalesman());
				bindBUserDeviceData.setSalesmanPhone(bindForm.getSalesmanPhone());

				BUserDeviceBeanHelper.getInstance().bindBUserDevice(bindBUserDeviceData);
				BUserDevice buserDevice = bindBUserDeviceData.toBUserDevice();
				
				result.setTarget(buserDevice);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}
		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(bindForm);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getByBUser]", reason, e);
		}
		return result;
	}

	public ReqResult<BUserDevice> unbindDevice(BindDeviceForm bindForm) {
		ReqResult<BUserDevice> result = ReqResult.newInstance(false, BUserDevice.class);
		try {

			// iotMS 解绑仪器
			MClient mClient = BUserDeviceMgr.getInstance().unbindDevice(bindForm);

			// storeMS 移除绑定数据
			long iotRecordId = mClient.getId();
			BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(bindForm.getBuserId());
			List<DeviceInfo> deviceList = buserDevice.getDeviceList();
			Iterator<DeviceInfo> itr = deviceList.iterator();
			while (itr.hasNext()) {
				DeviceInfo deviceInfo = itr.next();
				if (deviceInfo.getIotRecordId() == iotRecordId) {
					itr.remove();
				}
			}
			BUserDeviceMgr.getInstance().update(buserDevice);
			result.setTarget(buserDevice);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);

		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(bindForm);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[unbindDevice]", reason, e);
		}
		return result;
	}

	public ReqResult<BUserDevice> get(long id) {
		ReqResult<BUserDevice> result = ReqResult.newInstance(false, BUserDevice.class);
		try {
			BUserDevice buserDevice = BUserDeviceMgr.getInstance().get(id);
			if (buserDevice != null) {
				result.setTarget(buserDevice);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			} else {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("用户未绑定仪器");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getByBUser]", reason, e);
		}
		return result;
	}

	public ReqResult<BUserDevice> update(long id, BUserDeviceUpdateForm updateForm) {
		ReqResult<BUserDevice> result = ReqResult.newInstance(false, BUserDevice.class);
		try {
			BUserDeviceUpdateType updateType = BUserDeviceUpdateType.valueOf(updateForm.getUpdateType());
			boolean success = false;
			switch (updateType) {
			case AddStoreDevice:
				AddStoreDeviceData addStoreDeviceData = updateForm.getAddStoreDeviceData();
				success = BUserDeviceBeanHelper.getInstance().addStoreDevice(id, addStoreDeviceData);
				break;
			default:
				break;
			}
			if (success) {
				result.setSuccess(true);
			} else {
				result.setTips(updateType.getDescript() + "失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.BUserDevice;
			final String logId = "BUserDeviceHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(id, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<MClient> getMClient(long id) {
		ReqResult<MClient> result = ReqResult.newInstance(false, MClient.class);
		try {
			MClient mclient = BUserDeviceMgr.getInstance().getClient(id);
			result.setTarget(mclient);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(id);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getMClient]", reason, e);
		}
		return result;
	}

	public ReqResult<MClient> findByClientId(String clientId) {
		ReqResult<MClient> result = ReqResult.newInstance(false, MClient.class);
		try {
			if(StringUtils.isNotBlank(clientId)) {
				int index = clientId.indexOf("|");
				if(index != -1) {
					String[] split = clientId.split("|");
					clientId = split[2];
				}
			}
			MClient mclient = BUserDeviceMgr.getInstance().findByClientId(clientId);
			if(mclient == null) {
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("设备不存在");
			}else {
				result.setTarget(mclient);
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}
		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(clientId);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getMClient]", reason, e);
		}
		return result;
	}

	public ReqResult<IotKeyValue> getClientParam(long id) {
		ReqResult<IotKeyValue> result = ReqResult.newInstance(false, IotKeyValue.class);
		try {
			List<IotKeyValue> params = BUserDeviceMgr.getInstance().getClientParam(id);

			result.setTargetList(params);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);

		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(id);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getClientParam]", reason, e);
		}
		return result;
	}

	public ReqResult<OperateHistory> getOperateHistory(OperateHistoryQueryForm params) {
		ReqResult<OperateHistory> result = ReqResult.newInstance(false, OperateHistory.class);
		try {
			List<OperateHistory> list = BUserDeviceMgr.getInstance().getOperateHistory(params);
			result.setTargetList(list);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);

		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(params);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getOperateHistory]", reason, e);
		}
		return result;
	}
	
	public ReqResult<MClient> sendClientParam(MCtrlSendParamApiForm sendParamForm) {
		ReqResult<MClient> result = ReqResult.newInstance(false, MClient.class);
		try {
			MClient mclient = BUserDeviceMgr.getInstance().sendClientParam(sendParamForm);

			result.setTarget(mclient);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				String tips = errorTmp.getTips();
				RespStatus respStatus = RespStatus.getRespStatusEnumByCode(errorTmp.getStatusCode());
				result.setStatus(respStatus);
				result.setTips(tips);
			}
			final String reason = LogHelper.getInstance().exceptionReason(sendParamForm);
			MainLog.error(LogModule.BUserDevice, "BUserDeviceHandler[getMClient]", reason, e);
		}
		return result;
	}

}
