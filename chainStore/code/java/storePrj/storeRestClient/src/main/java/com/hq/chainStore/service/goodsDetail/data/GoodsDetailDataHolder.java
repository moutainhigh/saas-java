package com.hq.chainStore.service.goodsDetail.data;

import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.common.StringUtils4Client;
import com.hq.common.dataDetial.bs.AbsDetailDataHolder;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class GoodsDetailDataHolder extends AbsDetailDataHolder<GoodsDetail> {

	public static GoodsDetailDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailDataHolder.class);
	}

	final private DataVersionEnum versionEnum = DataVersionEnum.GoodsDetail;

	protected RestDao<GoodsDetail> getDao() {
		return GoodsDetailDAO.getInstance();
	}
	
	public GoodsDetail getData(long buserId, String goodsId, long storeId) {
		return getData(String.valueOf(buserId), StringUtils4Client.format("{}_{}", storeId, goodsId), storeId);
	}

	@Override
	protected long getDataVersion(String ownerId, long storeId) {
		DetailDataVersion data = DetailDataVersionSynDataHolder.getInstance().getData(ownerId, String.valueOf(storeId));
		Long ver = data.getDetailDataVerMap().get(versionEnum.ordinal());
		if(ver != null) {
			return ver;
		}
		return 0;
	}

	@Override
	public DataVersionEnum getDataVersionEnum() {
		return versionEnum;
	}
}
