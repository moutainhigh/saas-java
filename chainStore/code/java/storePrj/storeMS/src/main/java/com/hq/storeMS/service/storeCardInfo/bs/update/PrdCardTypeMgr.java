package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeCardInfo.apiData.PrdCardTypeAddForm;
import com.hq.storeMS.service.storeCardInfo.apiData.PrdCardTypeRemoveForm;
import com.hq.storeMS.service.storeCardInfo.apiData.PrdCardTypeUpdateForm;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfoBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class PrdCardTypeMgr {

	public static PrdCardTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PrdCardTypeMgr.class);
	}

	// 添加分类
	public OperateTips addPrdCardType(long storeId, PrdCardTypeAddForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.AddPrdCardType.getMark() + "失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if (checkNameExists(inputForm.getName(), storeData)) {
			tips.setTips("次卡分类已存在");
			return tips;
		}
		
		if (StoreCardInfoBeanHelper.getInstance().addPrdCardType(storeData, inputForm)) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, inputForm.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.AddPrdCardType.getMark()));
		}
		return tips;
	}

	// 删除分类
	public OperateTips delPrdCardType(long storeId, PrdCardTypeRemoveForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.DelPrdCardType.getMark() + "失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if (StoreCardInfoBeanHelper.getInstance().delPrdCardType(storeData, inputForm)) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
			tips.setSuccess(true);
			PrdCardType type = storeData.getPrdCardTypeMap().get(inputForm.getId());
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, type.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.DelPrdCardType.getMark()));
		}
		return tips;
	}

	// 修改商品分类
	public OperateTips updPrdCardType(long storeId, PrdCardTypeUpdateForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.UpdPrdCardType.getMark() + "失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if (checkNameExists(inputForm.getName(), storeData)) {
			tips.setTips("次卡分类已存在");
			return tips;
		}

		if (StoreCardInfoBeanHelper.getInstance().updatePrdCardType(storeData, inputForm)) {
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, inputForm.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.UpdPrdCardType.getMark()));
		}
		return tips;
	}
	
	private boolean checkNameExists(String name, StoreCardInfo storeData) {
		Collection<PrdCardType> values = storeData.getPrdCardTypeMap().values();
		if (StringUtils.isNoneBlank(name) && CollectionUtils.isNotEmpty(values)) {
			for (PrdCardType type : values) {
				if(name.equals(type.getName()) && type.getEntityState() != EntityState.Deleted.ordinal()) {
					return true;
				}
			}
		}
		return false;
	}
}
