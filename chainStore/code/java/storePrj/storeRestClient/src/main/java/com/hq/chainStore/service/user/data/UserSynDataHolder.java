package com.hq.chainStore.service.user.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class UserSynDataHolder extends AbsDataSynDataHolder<User> {

	public static UserSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(UserSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.CUser;

	protected Class<User> getClazz() {
		return User.class;
	}

	protected RestDao<User> getDao() {
		return UserDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
