package com.hq.storeMS.service.buser.apiData;

import com.zenmind.common.StringFormatUtil;

public class QueryVipUserForm {
	
	//对应会员编号
	private Long buserId;
	//对应会员姓名
	private String name;
	//对应会员手机号
	private Long phone;
	//对应会员类型
	private Integer vipType;
	//对应最小注册时间
	private Long minTime;
	//对应最大注册时间
	private Long maxTime;
	private Integer pageItemCount;
	private Integer pageNo;
	
	public static QueryVipUserForm newInstance(){
		QueryVipUserForm queryForm = new QueryVipUserForm();
		queryForm.buserId = 0L;
		queryForm.name = "";
		queryForm.phone = 0L;
		queryForm.vipType = -1;
		queryForm.minTime = 0L;
		queryForm.maxTime = 0L;
		
		return queryForm;
	}
	public String getListId(){
		return StringFormatUtil.format("{}_{}_{}_{}_{}_{}_{}_{}", buserId,name,phone,vipType,minTime,maxTime,pageItemCount,pageNo);
	}

	public long getBuserId() {
		return buserId;
	}

	public QueryVipUserForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public String getName() {
		return name;
	}

	public QueryVipUserForm setName(String name) {
		this.name = name;
		return this;
	}

	public long getPhone() {
		return phone;
	}

	public QueryVipUserForm setPhone(long phone) {
		this.phone = phone;
		return this;
	}

	public int getVipType() {
		return vipType;
	}

	public QueryVipUserForm setVipType(int vipType) {
		this.vipType = vipType;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public QueryVipUserForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public QueryVipUserForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public QueryVipUserForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public QueryVipUserForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

}
