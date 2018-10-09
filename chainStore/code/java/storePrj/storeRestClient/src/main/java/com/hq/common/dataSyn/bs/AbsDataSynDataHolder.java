package com.hq.common.dataSyn.bs;

import com.hq.common.StringUtils4Client;
import com.hq.common.dataSyn.info.DataSynItem;
import com.hq.common.dataSyn.info.DataSynType;
import com.hq.common.dataSyn.info.DataSynVer;
import com.zenmind.dao.rest.RestDao;

public abstract class AbsDataSynDataHolder<T extends IntfSynData> {

	public T getData(String ownerId, String targetId) {
		T data = null;
		if (StringUtils4Client.isBlank(ownerId)) {
			data = getDao().get(targetId);
		} else {
			data = DataSynCacheMgr.getInstance().getData(ownerId, getSynType(), targetId, getClazz());
			if (data == null) {
				data = getDao().get(targetId);
				if (data != null) {
					String idStr = data.targetId().toString();
					DataSynVer synVer = DataSynVer.newInstance(getSynType(), idStr, data.targetVer());
					DataSynCacheMgr.getInstance().putData(ownerId, data, synVer);
				}
			}
		}
		return data;
	}

	public void synData(String ownerId, DataSynItem dataSynItem) {
		if (StringUtils4Client.isNotBlank(ownerId)) {
			DataSynCacheMgr.getInstance().synData(ownerId, dataSynItem, getClazz());
		}
	}

	// java 泛型擦除，要运行时传进来
	protected abstract Class<T> getClazz();

	protected abstract RestDao<T> getDao();

	public abstract DataSynType getSynType();

}
