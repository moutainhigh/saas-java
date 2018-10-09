package com.hq.storeMS.service.storeLeaguerInfo.apiData;


public class LeaguerDelApiForm {
	// 生成策略 storeId + cuserId每个店铺的会员都是C端的用户 所以storeId 与 cuserId必定构成唯一ID。
	private String id;

	public static LeaguerDelApiForm newInstance() {
		LeaguerDelApiForm data = new LeaguerDelApiForm();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
