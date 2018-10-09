package com.hq.storeMS.service.footprint.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.footprint.apiData.FootprintQueryForm;
import com.hq.storeMS.service.footprint.data.Footprint;
import com.zenmind.common.hotSwap.HotSwap;

public class FootprintQueryMgr {

	public static FootprintQueryMgr getInstance() {
		return HotSwap.getInstance().getSingleton(FootprintQueryMgr.class);
	}

	public Footprint get(long id) {
		return FootprintDataHolder.getInstance().get(id);
	}
	
	public PageResp<Footprint> findPage(FootprintQueryForm queryForm) {
		List<Footprint> tmpList = FootprintDataHolder.getInstance().findByCond(queryForm);
		List<Footprint> list = filterRecord(queryForm, tmpList);
		
		Collections.sort(list, new Comparator<Footprint>() {
			@Override
			public int compare(Footprint o1, Footprint o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return PageUtil.getInstance().buildPageResp(list, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<Footprint> filterRecord(FootprintQueryForm queryForm, List<Footprint> list) {
		List<Footprint> result = new ArrayList<Footprint>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Footprint record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		return result;
	}

	private boolean isRightRecord(FootprintQueryForm queryForm, Footprint record) {
		if (!checkDynamicType(queryForm.getDynamicType(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkDynamicType(Set<Integer> dynamicType, Footprint record) {
		if (CollectionUtils.isEmpty(dynamicType)) {
			return true;
		}
		if (dynamicType.contains(record.getDynamicType())) {
			return true;
		}
		return false;
	}
}
