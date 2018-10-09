package com.hq.chainMS.service.chainGoods.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.hq.chainMS.service.chainGoods.data.GoodsDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.chainMS.service.detailDataVersion.data.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailMgr {

	public static GoodsDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailMgr.class);
	}

	final private DataVersionEnum dataVersionEnum = DataVersionEnum.GoodsDetail;

	public void addWithId(GoodsDetail target) {
		GoodsDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}

	public void update(GoodsDetail target) {
		GoodsDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}

	public void delete(GoodsDetail target) {
		GoodsDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}

	public GoodsDetail get(long chainId, String id) {
		return GoodsDetailDataHolder.getInstance().get(chainId, id);
	}

	public PageResp<GoodsDetail> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		List<GoodsDetail> list = GoodsDetailDataHolder.getInstance().findGoodsDetailList(queryForm);
		List<GoodsDetail> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<GoodsDetail> filterRecord(GoodsDetailQueryForm queryForm, List<GoodsDetail> list) {
		List<GoodsDetail> result = new ArrayList<GoodsDetail>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (GoodsDetail record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<GoodsDetail>() {
			@Override
			public int compare(GoodsDetail o1, GoodsDetail o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}

	private boolean isRightRecord(GoodsDetailQueryForm queryForm, GoodsDetail record) {
		if (!checkState(queryForm.getStateArray(), record)) {
			return false;
		}

		if (!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}

		if (!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}

		return true;
	}

	private boolean checkState(Set<Integer> stateArray, GoodsDetail record) {
		if (CollectionUtils.isEmpty(stateArray)) {
			return true;
		}

		if (stateArray.contains(record.getState())) {
			return true;
		}

		return false;
	}

	private boolean checkTypeId(String typeId, GoodsDetail record) {
		if (StringUtils.isBlank(typeId)) {
			return true;
		}

		if (typeId.equals(record.getTypeId())) {
			return true;
		}

		return false;
	}

	private boolean checkNumberOrName(String numberOrName, GoodsDetail record) {
		if (StringUtils.isBlank(numberOrName)) {
			return true;
		}

		String name = record.getName();
		if (name != null && name.contains(numberOrName)) {
			return true;
		}

		String number = record.getNumber();
		if (number != null && number.contains(numberOrName)) {
			return true;
		}

		return false;
	}

}
