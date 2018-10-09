package com.hq.storeMS.service.chainDataSyn.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.Product;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataStatusEnum;
import com.hq.storeMS.service.chainDataSyn.data.ProductSyn;
import com.hq.storeMS.service.chainProduct.bs.ChainProductDataHolder;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoDataHolder;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductSynMgr {

	public static ProductSynMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductSynMgr.class);
	}

	public PageResp<ProductSyn> findChainProduct(ChainDataQueryForm queryForm) {
		List<ProductSyn> list = findList(queryForm);
		List<ProductSyn> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<ProductSyn> findList(ChainDataQueryForm queryForm) {
		long chainId = queryForm.getChainId();
		long storeId = queryForm.getStoreId();
		List<ProductSyn> result = new ArrayList<ProductSyn>();
		ChainProduct chainData = ChainProductDataHolder.getInstance().get(chainId);
		StoreProductInfo storeData = StoreProductInfoDataHolder.getInstance().get(storeId);

		Collection<Product> array = chainData.getProductInfoMap().values();
		for (Product tmp : array) {
			// 过滤掉 已删除 或者 已下架的
//			if (tmp.getEntityState() == EntityState.Deleted.ordinal()
//					|| tmp.getState() == ProductStateEnum.Close.ordinal()) {
//				continue;
//			}
			// 分配到店
			if (!tmp.getApplyStoreIds().contains(storeId)) {
				continue;
			}
			
			ProductSyn data = ProductSyn.newInstanceByProduct(tmp, storeId, chainId);
			if (storeData.takeProductInfoById(tmp.getId()) == null) {
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else if (storeData.takeProductInfoById(tmp.getId()).getEntityState() == EntityState.Deleted.ordinal()){
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else {
				data.setSynStatus(ChainDataStatusEnum.HAVE.ordinal());
			}
			result.add(data);
		}
		return result;
	}

	private List<ProductSyn> filterRecord(ChainDataQueryForm queryForm, List<ProductSyn> list) {
		List<ProductSyn> result = new ArrayList<ProductSyn>();
		for (ProductSyn record : list) {
			if (isRightRecord(queryForm, record)) {
				result.add(record);
			}
		}
		Collections.sort(result, new Comparator<ProductSyn>() {
			@Override
			public int compare(ProductSyn o1, ProductSyn o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return result;
	}

	private boolean isRightRecord(ChainDataQueryForm queryForm, ProductSyn record) {
		if (!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}

		if (!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		
		if (!checkSynStatus(queryForm.getSynStatus(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkNumberOrName(String numberOrName, ProductSyn record) {
		if (StringUtils.isBlank(numberOrName)) {
			return true;
		}
		if (record.getName() != null && record.getName().contains(numberOrName)) {
			return true;
		}
		if (record.getNumber() != null && record.getNumber().contains(numberOrName)) {
			return true;
		}
		return false;
	}

	private boolean checkTypeId(String typeId, ProductSyn record) {
		if (StringUtils.isBlank(typeId)) {
			return true;
		}
		if (typeId.equals(record.getTypeId())) {
			return true;
		}
		return false;
	}
	
	private boolean checkSynStatus(int synStatus, ProductSyn record) {
		if (synStatus == -1) {
			return true;
		}
		if (synStatus == record.getSynStatus()) {
			return true;
		}
		return false;
	}
}
