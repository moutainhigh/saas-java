package com.hq.chainStore.service.productCardDetail.data;

import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.common.dataDetial.bs.AbsDetailDataHolder;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ProductCardDetailDataHolder extends AbsDetailDataHolder<ProductCardDetail> {

	public static ProductCardDetailDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailDataHolder.class);
	}

	final private DataVersionEnum versionEnum = DataVersionEnum.ProductCardDetail;

	protected RestDao<ProductCardDetail> getDao() {
		return ProductCardDetailDAO.getInstance();
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
