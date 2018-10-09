package com.hq.storeMS.service.spreadStatis.bs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeMS.service.spreadStatis.data.SpreadStatis;
import com.hq.storeMS.service.spreadStatis.data.vo.BuserSpreadStatis;
import com.hq.storeMS.service.spreadStatis.data.vo.DateSpreadStatis;
import com.hq.storeMS.service.spreadStatis.data.vo.StoreSpreadStatis;
import com.zenmind.common.hotSwap.HotSwap;

public class SpreadStatisQueryMgr {

	public static SpreadStatisQueryMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisQueryMgr.class);
	}

	public BuserSpreadStatis findBuserSpreadStatis(SpreadStatisQueryForm queryForm) {
		StoreSpreadStatis storeSpreadStatis = findStoreSpreadStatis(queryForm.getStoreId(), queryForm.getMaxTime(), queryForm.getMinTime());
		return storeSpreadStatis.takeBuserSpreadStatis(queryForm.getBuserId());
	}

	public StoreSpreadStatis findStoreSpreadStatis(long storeId, long maxTime, long minTime) {
		SpreadStatisQueryForm queryForm = SpreadStatisQueryForm.newInstance();
		queryForm.setStoreId(storeId).setMaxTime(maxTime).setMinTime(minTime);

		List<SpreadStatis> list = findByCond(queryForm);

		return buildStoreSpreadStatis(storeId, list);
	}

	private StoreSpreadStatis buildStoreSpreadStatis(long storeId, List<SpreadStatis> list) {
		StoreSpreadStatis result = StoreSpreadStatis.newInstance(storeId);
		if (CollectionUtils.isEmpty(list)) {
			return result;
		}

		SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy/MM/dd");

		for (SpreadStatis spreadStatis : list) {
			long buserId = spreadStatis.getBuserId();
			Map<Long, BuserSpreadStatis> buserSpreadStatisMap = result.getBuserSpreadStatisMap();
			BuserSpreadStatis buserSpreadStatis = buserSpreadStatisMap.get(buserId);
			if (buserSpreadStatis == null) {
				buserSpreadStatis = BuserSpreadStatis.newInstance(buserId);
				buserSpreadStatisMap.put(buserId, buserSpreadStatis);
			}

			String date = AppUtils.timeStamp2Str(spreadStatis.getCreatedTime(), SDF_DAY);
			long dateTime = AppUtils.dateStr2TimeStamp(date, SDF_DAY);
			Map<String, DateSpreadStatis> dateSpreadStatisMap = buserSpreadStatis.getDateSpreadStatisMap();
			DateSpreadStatis dateSpreadStatis = dateSpreadStatisMap.get(date);
			if (dateSpreadStatis == null) {
				dateSpreadStatis = DateSpreadStatis.newInstance(date, dateTime);
				dateSpreadStatisMap.put(date, dateSpreadStatis);
			}
			dateSpreadStatis.setCount(dateSpreadStatis.getCount() + 1);
		}
		return result;
	}

	public PageResp<SpreadStatis> findPage(SpreadStatisQueryForm queryForm) {
		List<SpreadStatis> list = findByCond(queryForm);
		return PageUtil.getInstance().buildPageResp(list, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<SpreadStatis> findByCond(SpreadStatisQueryForm queryForm) {
		List<SpreadStatis> list = SpreadStatisDataHolder.getInstance().findByCond(queryForm);
		return filterRecord(queryForm, list);
	}

	private List<SpreadStatis> filterRecord(SpreadStatisQueryForm queryForm, List<SpreadStatis> list) {
		List<SpreadStatis> result = new ArrayList<SpreadStatis>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (SpreadStatis record : list) {
				if (record == null)
					continue;
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		return result;
	}

	private boolean isRightRecord(SpreadStatisQueryForm queryForm, SpreadStatis record) {
		if (!checkDynamicId(queryForm.getDynamicId(), record)) {
			return false;
		}
		if (!checkBuserId(queryForm.getBuserId(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkDynamicId(long dynamicId, SpreadStatis record) {
		if (dynamicId == 0 || dynamicId == record.getDynamicId()) {
			return true;
		}
		return false;
	}

	private boolean checkBuserId(long buserId, SpreadStatis record) {
		if (buserId == 0 || buserId == record.getBuserId()) {
			return true;
		}
		return false;
	}

}
