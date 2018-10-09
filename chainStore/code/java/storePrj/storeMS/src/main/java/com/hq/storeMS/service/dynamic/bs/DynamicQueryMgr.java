package com.hq.storeMS.service.dynamic.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryFormForCuser;
import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.zenmind.common.hotSwap.HotSwap;

public class DynamicQueryMgr {

	public static DynamicQueryMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicQueryMgr.class);
	}

	public Dynamic get(long id) {
		return DynamicDataHolder.getInstance().get(id);
	}
	
	public PageResp<Dynamic> findPageForCuser(DynamicQueryFormForCuser queryForm) {
		List<Dynamic> list = new ArrayList<Dynamic>();
		List<DynamicQueryForm> dynamicQueryForms = queryForm.toDynamicQueryForms();
		for (DynamicQueryForm dynamicQueryForm : dynamicQueryForms) {
			List<Dynamic> tmpList = DynamicDataHolder.getInstance().findByCond(dynamicQueryForm);
			list.addAll(filterRecord(dynamicQueryForm, tmpList));
		}
		
		Collections.sort(list, new Comparator<Dynamic>() {
			@Override
			public int compare(Dynamic o1, Dynamic o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return PageUtil.getInstance().buildPageResp(list, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public PageResp<Dynamic> findPage(DynamicQueryForm queryForm) {
		List<Dynamic> tmpList = DynamicDataHolder.getInstance().findByCond(queryForm);
		List<Dynamic> list = filterRecord(queryForm, tmpList);
		
		Collections.sort(list, new Comparator<Dynamic>() {
			@Override
			public int compare(Dynamic o1, Dynamic o2) {
				return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
			}
		});
		return PageUtil.getInstance().buildPageResp(list, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<Dynamic> filterRecord(DynamicQueryForm queryForm, List<Dynamic> list) {
		List<Dynamic> result = new ArrayList<Dynamic>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Dynamic record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		return result;
	}

	private boolean isRightRecord(DynamicQueryForm queryForm, Dynamic record) {
		if (!checkContent(queryForm.getContent(), record)) {
			return false;
		}
		if (!checkStoreId(queryForm.getStoreId(), record)) {
			return false;
		}
		if (!checkStatus(queryForm.getStatus(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkContent(String content, Dynamic record) {
		if (StringUtils.isBlank(content)) {
			return true;
		}
		String docContent = record.getDocContent();
		if (StringUtils.isNotBlank(docContent) && docContent.contains(content)) {
			return true;
		}
		return false;
	}
	
	private boolean checkStoreId(long storeId, Dynamic record) {
		if (storeId == 0) {
			return true;
		}
		if (storeId == record.getStoreId()) {
			return true;
		}
		return false;
	}
	
	private boolean checkStatus(Set<Integer> status, Dynamic record) {
		if (CollectionUtils.isEmpty(status)) {
			return true;
		}
		if (status.contains(record.getStatus())) {
			return true;
		}
		return false;
	}
}
