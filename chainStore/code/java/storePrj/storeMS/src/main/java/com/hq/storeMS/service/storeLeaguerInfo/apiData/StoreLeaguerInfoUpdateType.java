package com.hq.storeMS.service.storeLeaguerInfo.apiData;

public enum StoreLeaguerInfoUpdateType {
	UpdateLeaguerInfo("修改客户信息"),
	AddLeaguerInfo("新增客户"),
	AddLeaguerInfoList("批量新增客户"),
	AddAttention("添加标星客户"),
	RemoveAttention("移除标星客户"),
	DelLeaguer("删除客户"),
	UpdateMemberCard("设置会员卡"),
	RechargeMemberCard("会员卡充值"),
	PurchaseProductCard("购买次卡"),
	ReduceProductCardCount("更新次卡使用次数"),
	AddListFromStore("从店铺批量新增客户"),
	AddListFromExcel("从Excel批量新增客户"),
	AddListOfLeaguerIds("通过id列表批量新增客户"),
	
	//客户标签管理
	AddLeaguerLabel("添加标签"),
	RemoveLeaguerLabel("删除标签"),
	UpdateLeaguerLabel("编辑标签"),
	AddLeaguerLabelList("批量添加标签"),
	
	SaveFollowUser("设置会员的负责人"),
	;
	
	private String mark;
	
	private StoreLeaguerInfoUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static StoreLeaguerInfoUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
