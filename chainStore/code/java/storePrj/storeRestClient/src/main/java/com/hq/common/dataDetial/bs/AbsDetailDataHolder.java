package com.hq.common.dataDetial.bs;

import com.hq.common.StringUtils4Client;
import com.hq.common.dataDetial.DataDetailCacheMgr;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.zenmind.dao.rest.RestDao;

public abstract class AbsDetailDataHolder<T extends IntfDetailData> {

	public T getData(String ownerId, String targetId, long storeId) {
		T data = null;
		if (StringUtils4Client.isBlank(ownerId)) {
			data = getDao().get(storeId+"/"+targetId);
		} else {
			data = DataDetailCacheMgr.getInstance().getData(ownerId, getDataVersionEnum(), getDataVersion(ownerId, storeId), targetId);
			if (data == null) {
				data = getDao().get(storeId+"/"+targetId);
				if (data != null) {
					DataDetailCacheMgr.getInstance().putData(ownerId, getDataVersionEnum(), data, getDataVersion(ownerId, storeId), targetId);
				}
			}
		}
		return data;
	}
	
	//获取店铺相关域的详情版本信息
	protected abstract long getDataVersion(String ownerId, long storeId);

	//传入具体域的RestDao
	protected abstract RestDao<T> getDao();

	//获取详情数据的枚举
	public abstract DataVersionEnum getDataVersionEnum();

}
