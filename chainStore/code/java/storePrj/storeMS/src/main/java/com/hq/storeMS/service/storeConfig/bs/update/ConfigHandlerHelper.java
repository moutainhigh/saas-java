package com.hq.storeMS.service.storeConfig.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateForm;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class ConfigHandlerHelper {

	public static ConfigHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ConfigHandlerHelper.class);
	}

	private Map<StoreConfigUpdateType, IConfigHandler> handleMapper = new HashMap<StoreConfigUpdateType, IConfigHandler>();

	public ConfigHandlerHelper() {
		//客户来源操作
		handleMapper.put(StoreConfigUpdateType.AddLeaguerOrigin, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerOriginMgr.getInstance().addLeaguerOrigin(storeId, formInfo.getLeaguerOriginAddForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.RemoveLeaguerOrigin, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerOriginMgr.getInstance().removeLeaguerOrigin(storeId, formInfo.getLeaguerOriginRemoveForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.UpdateLeaguerOrigin, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerOriginMgr.getInstance().updateLeaguerOrigin(storeId, formInfo.getLeaguerOriginUpdateForm());
			}
		});
		
		//客户扩展属性操作
		handleMapper.put(StoreConfigUpdateType.AddExpandAttribute, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return ExpandAttributeMgr.getInstance().addExpandAttribute(storeId, formInfo.getExpandAttributeAddForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.SortExpandAttribute, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return ExpandAttributeMgr.getInstance().sortExpandAttribute(storeId, formInfo.getExpandAttributeSortForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.StatusExpandAttribute, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return ExpandAttributeMgr.getInstance().statusExpandAttribute(storeId, formInfo.getExpandAttributeStatusForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.UpdateExpandAttribute, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return ExpandAttributeMgr.getInstance().updateExpandAttribute(storeId, formInfo.getExpandAttributeUpdateForm());
			}
		});
		
		//客户类型与基础属性操作
		handleMapper.put(StoreConfigUpdateType.UpdateLeaguerType, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerConfigMgr.getInstance().updateLeaguerType(storeId, formInfo.getLeaguerTypeUpdateForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.UpdateBaseAttribute, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerConfigMgr.getInstance().updateBaseAttribute(storeId, formInfo.getBaseAttributeStatusForm());
			}
		});
		
		//预约设置操作
		handleMapper.put(StoreConfigUpdateType.AddCancelReason, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return AppointConfigMgr.getInstance().addCancelReason(storeId, formInfo.getCancelAppointAddForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.RemoveCancelReason, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return AppointConfigMgr.getInstance().removeCancelReason(storeId, formInfo.getCancelAppointRemoveForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.UpdateCancelReason, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return AppointConfigMgr.getInstance().updateCancelReason(storeId, formInfo.getCancelAppointUpdateForm());
			}
		});
		handleMapper.put(StoreConfigUpdateType.UpdateAppointTime, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return AppointConfigMgr.getInstance().updateAppointTime(storeId, formInfo.getAppointTimeUpdateForm());
			}
		});

		//数据共享
		handleMapper.put(StoreConfigUpdateType.SaveShareData, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return ChainConfigMgr.getInstance().saveShareData(storeId, formInfo.getShareDataForm());
			}
		});
		
		//会员分析
		handleMapper.put(StoreConfigUpdateType.UpdateLeaguerAnalysis, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerConfigMgr.getInstance().updateLeaguerAnalysis(storeId, formInfo.getLeaguerAnalysisUpdateForm());
			}
		});
		
		handleMapper.put(StoreConfigUpdateType.UpdateLeaguerBirthday, new IConfigHandler(){
			@Override
			public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return LeaguerConfigMgr.getInstance().updateLeaguerBirthday(storeId, formInfo.getLeaguerBirthdayUpdateForm());
			}
		});
	}

	public OperateTips update(long storeId, StoreConfigUpdateForm formInfo) {
		StoreConfigUpdateType updateType = StoreConfigUpdateType.valueOf(formInfo.getUpdateType());
		IConfigHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(storeId, formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
