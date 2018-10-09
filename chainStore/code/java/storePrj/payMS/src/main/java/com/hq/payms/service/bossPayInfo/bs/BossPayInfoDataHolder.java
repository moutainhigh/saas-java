package com.hq.payms.service.bossPayInfo.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoQueryForm;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.BossPayInfoCount;
import com.hq.payms.service.bossPayInfo.data.BossPayInfoDAO;
import com.hq.payms.service.bossPayInfo.data.BossPayInfoRedisDAO;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayClientPoolMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class BossPayInfoDataHolder {

	public static BossPayInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BossPayInfoDataHolder.class);
	}
	
	final private String groupName="bosspayinfo";
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(BossPayInfo target) {
		BossPayInfoDAO.getInstance().addAndReturnId(target);
		BossPayInfoRedisDAO.getInstance().deleteList(groupName);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void updpate(BossPayInfo target) {
		ZmAlipayClientPoolMgr.getInstance().remove(target.getAlipayAppId());
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BossPayInfoDAO.getInstance().updpate(target);
		BossPayInfoRedisDAO.getInstance().deleteList(groupName);
		BossPayInfoRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(long id) {
		BossPayInfoDAO.getInstance().delete(id);
		BossPayInfoRedisDAO.getInstance().deleteList(groupName);
		BossPayInfoRedisDAO.getInstance().delete(id);
	}

	public BossPayInfo get(long id) {
		BossPayInfo target = BossPayInfoRedisDAO.getInstance().get(id);
		if(target == null){
			target = BossPayInfoDAO.getInstance().get(id);
			if(target != null){
				BossPayInfoRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<BossPayInfo> findList(BossPayInfoQueryForm params) {
		String listId = params.getListId();
		List<BossPayInfo> list = BossPayInfoRedisDAO.getInstance().getList(groupName, listId);
		if(CollectionUtils.isEmpty(list)){
		    list = BossPayInfoDAO.getInstance().findList(params);
			if(CollectionUtils.isEmpty(list)){
				BossPayInfoRedisDAO.getInstance().saveList(list, groupName, listId);
			}
		}
		return list;
	}	
	
	public BossPayInfoCount getCount(BossPayInfoQueryForm params) {
		return BossPayInfoDAO.getInstance().getCount(params);
	}
	
	public BossPayInfoCount getCount4ExactQuery(BossPayInfoQueryForm params) {
		return BossPayInfoDAO.getInstance().getCount4ExactQuery(params);
	}


	public BossPayInfo findByStoreId(long storeId) {
		String groupId = String.valueOf(storeId);
		BossPayInfo target = BossPayInfoRedisDAO.getInstance().findByOne(groupName, groupId);
		if(target == null){
			target = BossPayInfoDAO.getInstance().findByStoreId(storeId);
			if(target != null){
				BossPayInfoRedisDAO.getInstance().saveOne(groupName, groupId, target);
			}
		}
		return target;
	}
	
	
}
