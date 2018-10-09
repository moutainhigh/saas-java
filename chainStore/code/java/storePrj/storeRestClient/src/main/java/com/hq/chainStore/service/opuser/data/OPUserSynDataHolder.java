package com.hq.chainStore.service.opuser.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OPUserSynDataHolder extends AbsDataSynDataHolder<OPUser> {

	public static OPUserSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OPUserSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.OPUser;

	protected Class<OPUser> getClazz() {
		return OPUser.class;
	}

	protected RestDao<OPUser> getDao() {
		return OPUserDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
