package com.hq.customerRestClient.service.cuser.apiData;

public class CUserQueryForm {
	/********************** 模糊条件 **********************/
	// 手机号或名称
	private String phoneOrName;

	/********************** 精确条件 **********************/
	// 手机号码
	private long phone;
	// 用户名称
	private String name;
	// 微信用户统一标识
	private String wxUnionId;
	// 系统生成的内部账号
	private String priAccountNum;

	private int pageItemCount;
	private int pageNo;

	public static CUserQueryForm newInstance() {
		CUserQueryForm data = new CUserQueryForm();
		data.pageNo = 1;
		return data;
	}

	public String getPhoneOrName() {
		return phoneOrName;
	}

	public void setPhoneOrName(String phoneOrName) {
		this.phoneOrName = phoneOrName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getWxUnionId() {
		return wxUnionId;
	}

	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}

	public String getPriAccountNum() {
		return priAccountNum;
	}

	public void setPriAccountNum(String priAccountNum) {
		this.priAccountNum = priAccountNum;
	}

}
