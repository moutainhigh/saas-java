package com.hq.storeMS.service.chainDataSyn.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.ProductCard;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.chainCard.bs.ChainCardDataHolder;
import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataStatusEnum;
import com.hq.storeMS.service.chainDataSyn.data.ProductCardSyn;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoDataHolder;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardSynMgr {

	public static ProductCardSynMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardSynMgr.class);
	}

	public PageResp<ProductCardSyn> findChainProductCard(ChainDataQueryForm queryForm) {
		List<ProductCardSyn> list = findList(queryForm);
		List<ProductCardSyn> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<ProductCardSyn> findList(ChainDataQueryForm queryForm) {
		long chainId = queryForm.getChainId();
		long storeId = queryForm.getStoreId();
		List<ProductCardSyn> result = new ArrayList<ProductCardSyn>();
		ChainCard chainData = ChainCardDataHolder.getInstance().get(chainId);
		StoreCardInfo storeData = StoreCardInfoDataHolder.getInstance().get(storeId);

		Collection<ProductCard> array = chainData.getProductCardMap().values();
		for (ProductCard tmp : array) {
			// 过滤掉 已删除 或者 已下架的
//			if (tmp.getEntityState() == EntityState.Deleted.ordinal()
//					|| tmp.getStatus() == CardStatusEnum.CLOSE.ordinal()) {
//				continue;
//			}
			// 分配到店
			if (!tmp.getApplyStoreIds().contains(storeId)) {
				continue;
			}
			ProductCardSyn data = ProductCardSyn.newInstanceByProductCard(tmp, storeId, chainId);
			if (storeData.takeProductCardById(tmp.getId()) == null) {
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else if (storeData.takeProductCardById(tmp.getId()).getEntityState() == EntityState.Deleted.ordinal()){
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else {
				data.setSynStatus(ChainDataStatusEnum.HAVE.ordinal());
			}
			result.add(data);
		}
		return result;
	}

	private List<ProductCardSyn> filterRecord(ChainDataQueryForm queryForm, List<ProductCardSyn> list) {
		List<ProductCardSyn> result = new ArrayList<ProductCardSyn>();
		for (ProductCardSyn record : list) {
			if (isRightRecord(queryForm, record)) {
				result.add(record);
			}
		}
		Collections.sort(result, new Comparator<ProductCardSyn>() {
			@Override
			public int compare(ProductCardSyn o1, ProductCardSyn o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return result;
	}

	private boolean isRightRecord(ChainDataQueryForm queryForm, ProductCardSyn record) {
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

	private boolean checkNumberOrName(String numberOrName, ProductCardSyn record) {
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

	private boolean checkTypeId(String typeId, ProductCardSyn record) {
		if (StringUtils.isBlank(typeId)) {
			return true;
		}
		if (typeId.equals(record.getTypeId())) {
			return true;
		}
		return false;
	}
	
	private boolean checkSynStatus(int synStatus, ProductCardSyn record) {
		if (synStatus == -1) {
			return true;
		}
		if (synStatus == record.getSynStatus()) {
			return true;
		}
		return false;
	}
}
