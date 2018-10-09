package com.hq.chainStore.service.buser.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.common.StringUtils4Client;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;

public class BUserSynDataHolder extends AbsDataSynDataHolder<BUser> {

	public static BUserSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BUserSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.BUser;

	protected Class<BUser> getClazz() {
		return BUser.class;
	}
	
	public BUser getData(Long ownerId,Long targetId){
		return super.getData(String.valueOf(ownerId), String.valueOf(targetId) );
	}

	protected RestDao<BUser> getDao() {
		return BUserDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	public List<BUser> findByMultitId(Set<Long> ids) {
		ReqMap reqMap = ReqMap.newInstance().add("idList", StringUtils4Client.join(ids, ","));
		return BUserDAO.getInstance().findByMultitId(reqMap);
	}
	
	public Map<Long, BUser> findBUserMap(Set<Long> ids) {
		Map<Long, BUser> buserMap = new HashMap<Long, BUser>();
		List<BUser> bUsers = findByMultitId(ids);
		for (BUser bUser : bUsers) {
			buserMap.put(bUser.getId(), bUser);
		}
		return buserMap;
	}

}
