package com.hq.storeMS.service.chainDataSyn.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.Goods;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataStatusEnum;
import com.hq.storeMS.service.chainDataSyn.data.GoodsSyn;
import com.hq.storeMS.service.chainGoods.bs.ChainGoodsDataHolder;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsDataHolder;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsSynMgr {

	public static GoodsSynMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsSynMgr.class);
	}

	public PageResp<GoodsSyn> findChainGoods(ChainDataQueryForm queryForm) {
		List<GoodsSyn> list = findList(queryForm);
		List<GoodsSyn> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<GoodsSyn> findList(ChainDataQueryForm queryForm) {
		long chainId = queryForm.getChainId();
		long storeId = queryForm.getStoreId();
		List<GoodsSyn> result = new ArrayList<GoodsSyn>();
		ChainGoods chainData = ChainGoodsDataHolder.getInstance().get(chainId);
		StoreGoods storeData = StoreGoodsDataHolder.getInstance().get(storeId);

		Collection<Goods> array = chainData.getGoodsMap().values();
		for (Goods tmp : array) {
			// 过滤掉 已删除 或者 已下架的
//			if (tmp.getEntityState() == EntityState.Deleted.ordinal()
//					|| tmp.getState() == GoodsStateEnum.Close.ordinal()) {
//				continue;
//			}
			// 分配到店
			if (!tmp.getApplyStoreIds().contains(storeId)) {
				continue;
			}
			GoodsSyn data = GoodsSyn.newInstanceByGoods(tmp, storeId, chainId);
			if (storeData.takeGoodsById(tmp.getId()) == null) {
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else if (storeData.takeGoodsById(tmp.getId()).getEntityState() == EntityState.Deleted.ordinal()){
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else {
				data.setSynStatus(ChainDataStatusEnum.HAVE.ordinal());
			}
			result.add(data);
		}
		return result;
	}

	private List<GoodsSyn> filterRecord(ChainDataQueryForm queryForm, List<GoodsSyn> list) {
		List<GoodsSyn> result = new ArrayList<GoodsSyn>();
		for (GoodsSyn record : list) {
			if (isRightRecord(queryForm, record)) {
				result.add(record);
			}
		}
		Collections.sort(result, new Comparator<GoodsSyn>() {
			@Override
			public int compare(GoodsSyn o1, GoodsSyn o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return result;
	}

	private boolean isRightRecord(ChainDataQueryForm queryForm, GoodsSyn record) {
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

	private boolean checkNumberOrName(String numberOrName, GoodsSyn record) {
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

	private boolean checkTypeId(String typeId, GoodsSyn record) {
		if (StringUtils.isBlank(typeId)) {
			return true;
		}
		if (typeId.equals(record.getTypeId())) {
			return true;
		}
		return false;
	}
	
	private boolean checkSynStatus(int synStatus, GoodsSyn record) {
		if (synStatus == -1) {
			return true;
		}
		if (synStatus == record.getSynStatus()) {
			return true;
		}
		return false;
	}
}
