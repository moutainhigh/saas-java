package com.hq.storeMS.service.dynamic.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hq.storeMS.service.dynamic.data.DynamicStatusEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class DynamicQueryFormForCuser {
	private long maxTime;
	private long minTime;
	private Set<Long> buserIds = new HashSet<Long>();

	// 文案内容
	private String content;

	private int pageItemCount;
	private int pageNo;

	public static DynamicQueryFormForCuser newInstance() {
		DynamicQueryFormForCuser data = new DynamicQueryFormForCuser();
		return data;
	}
	
	public List<DynamicQueryForm> toDynamicQueryForms(){
		List<DynamicQueryForm> list = new ArrayList<DynamicQueryForm>();
		for (Long buserId : buserIds) {
			DynamicQueryForm form = DynamicQueryForm.newInstance();
			FastBeanCopyer.getInstance().copy(this, form);
			form.setBuserId(buserId).addStatus(DynamicStatusEnum.Release.ordinal());
			list.add(form);
		}
		return list;
	}

	public DynamicQueryFormForCuser addBuserId(long... array) {
		for (long i : array) {
			buserIds.add(i);
		}
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public DynamicQueryFormForCuser setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public DynamicQueryFormForCuser setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public DynamicQueryFormForCuser setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
		return this;
	}

	public String getContent() {
		return content;
	}

	public DynamicQueryFormForCuser setContent(String content) {
		this.content = content;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public DynamicQueryFormForCuser setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public DynamicQueryFormForCuser setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
