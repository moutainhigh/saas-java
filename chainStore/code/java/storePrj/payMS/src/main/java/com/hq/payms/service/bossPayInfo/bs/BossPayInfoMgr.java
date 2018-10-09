package com.hq.payms.service.bossPayInfo.bs;


import java.util.List;

import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoQueryForm;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.BossPayInfoCount;
import com.zenmind.common.hotSwap.HotSwap;

public class BossPayInfoMgr {

	public static BossPayInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BossPayInfoMgr.class);
	}

	public BossPayInfo add(BossPayInfo target) {
		BossPayInfoDataHolder.getInstance().addAndReturnId(target);
		return target;
	}
	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(BossPayInfo target) {
		BossPayInfoDataHolder.getInstance().updpate(target);
	}
	
	public BossPayInfo get(long id) {
		return BossPayInfoDataHolder.getInstance().get(id);
	}
	
	public BossPayInfo findByStoreId(long storeId) {
		return BossPayInfoDataHolder.getInstance().findByStoreId(storeId);
	}
	
	public BossPayInfoCount getCount(BossPayInfoQueryForm params) {
		return BossPayInfoDataHolder.getInstance().getCount(params);
	}
	
	public BossPayInfoCount getCount4ExactQuery(BossPayInfoQueryForm params) {
		return BossPayInfoDataHolder.getInstance().getCount4ExactQuery(params);
	}
	
	public List<BossPayInfo> findList(BossPayInfoQueryForm params) {
		return BossPayInfoDataHolder.getInstance().findList(params);
	}

}
