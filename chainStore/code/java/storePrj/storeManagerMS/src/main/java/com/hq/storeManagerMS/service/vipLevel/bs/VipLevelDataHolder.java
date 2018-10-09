package com.hq.storeManagerMS.service.vipLevel.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevel;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevelCacheDAO;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevelDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class VipLevelDataHolder {

	public static VipLevelDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelDataHolder.class);
	}
	
	public void addAndReturnId(VipLevel target) {
		VipLevelDAO.getInstance().addAndReturnId(target);
		VipLevelCacheDAO.getInstance().delete(target);
	}
	
	//用于系统数据初始化
	public void addWithId(VipLevel target) {
		VipLevelDAO.getInstance().addWithId(target);
		VipLevelCacheDAO.getInstance().delete(target);
	}
	
	public VipLevel findOneByMaxId(long maxId) {
		return VipLevelDAO.getInstance().findOneByMaxId(maxId);
	}

	public VipLevel get(long id) {
		VipLevel target = VipLevelCacheDAO.getInstance().get(id);
		if(target == null){
			target = VipLevelDAO.getInstance().get(id);
			if(target != null){
				VipLevelCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}

	public List<VipLevel> findList(QueryVipLevelForm queryForm) {
		List<VipLevel> list = VipLevelCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = VipLevelDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				VipLevelCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}

	public void update(VipLevel target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		VipLevelDAO.getInstance().updpate(target);
		VipLevelCacheDAO.getInstance().delete(target);
	}

}
