package com.hq.chainStore.service.productDetail.data;

import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionSynDataHolder;
import com.hq.common.StringUtils4Client;
import com.hq.common.dataDetial.bs.AbsDetailDataHolder;
import com.hq.common.dataDetial.info.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ProductDetailDataHolder extends AbsDetailDataHolder<ProductDetail> {

	public static ProductDetailDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailDataHolder.class);
	}

	final private DataVersionEnum versionEnum = DataVersionEnum.ProductDetail;

	protected RestDao<ProductDetail> getDao() {
		return ProductDetailDAO.getInstance();
	}
	
	public ProductDetail getData(long buserId, String productId, long storeId) {
		return getData(String.valueOf(buserId), StringUtils4Client.format("{}_{}", storeId, productId), storeId);
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
