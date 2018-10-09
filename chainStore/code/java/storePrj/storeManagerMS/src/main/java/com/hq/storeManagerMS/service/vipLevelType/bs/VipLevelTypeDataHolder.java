package com.hq.storeManagerMS.service.vipLevelType.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelTypeCacheDAO;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelTypeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelTypeDataHolder {

	public static VipLevelTypeDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeDataHolder.class);
	}
	
	public void addAndReturnId(VipLevelType target) {
		VipLevelTypeDAO.getInstance().addAndReturnId(target);
		VipLevelTypeCacheDAO.getInstance().delete(target);
	}
	
	//用于系统数据初始化
	public void addWithId(VipLevelType target) {
		VipLevelTypeDAO.getInstance().addWithId(target);
		VipLevelTypeCacheDAO.getInstance().delete(target);
	}
	
	public VipLevelType findOneByMaxId(long maxId) {
		return VipLevelTypeDAO.getInstance().findOneByMaxId(maxId);
	}
	
	public VipLevelType get(long id) {
		VipLevelType target = VipLevelTypeCacheDAO.getInstance().get(id);
		if(target == null){
			target = VipLevelTypeDAO.getInstance().get(id);
			if(target != null){
				VipLevelTypeCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public void delete(long id) {
		VipLevelType target = VipLevelTypeCacheDAO.getInstance().get(id);
		if(target != null){
			VipLevelTypeDAO.getInstance().delete(id);
			VipLevelTypeCacheDAO.getInstance().delete(target);
		}	
	}

	public List<VipLevelType> findList(QueryVipLevelTypeForm queryForm) {
		List<VipLevelType> list = VipLevelTypeCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = VipLevelTypeDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				VipLevelTypeCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}

	public void update(VipLevelType target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		VipLevelTypeDAO.getInstance().updpate(target);
		VipLevelTypeCacheDAO.getInstance().delete(target);
	}

}
