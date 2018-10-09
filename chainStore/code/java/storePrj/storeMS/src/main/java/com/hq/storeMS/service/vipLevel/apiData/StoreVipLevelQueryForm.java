package com.hq.storeMS.service.vipLevel.apiData;

import com.hq.storeManagerRestClient.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevelStateEnum;

public class StoreVipLevelQueryForm {
	private int pageItemCount;
	private int pageNo;
	// 当前登录者的vipLevelId
	private long vipType;

	public static StoreVipLevelQueryForm newInstance() {
		return new StoreVipLevelQueryForm();
	}

	public QueryVipLevelForm toQueryVipLevelForm(VipLevel vipLevel) {
		QueryVipLevelForm form = QueryVipLevelForm.newInstance();
		form.setPageItemCount(pageItemCount).setPageNo(pageNo).setTypeId(vipLevel.getTypeId())
				.setState(VipLevelStateEnum.OPEN.ordinal());
		return form;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public StoreVipLevelQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public StoreVipLevelQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public long getVipType() {
		return vipType;
	}

	public StoreVipLevelQueryForm setVipType(long vipType) {
		this.vipType = vipType;
		return this;
	}

}
