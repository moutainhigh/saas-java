package com.hq.chainClient.service.chainCard.apiData;

public enum ChainCardUpdateType {
	AddMembershipCard("新增会员卡"),
	DelMembershipCard("删除会员卡"),
	UpdMembershipCard("编辑会员卡"),
	UpdMemberCardState("修改会员卡状态"),
	BatchUpdMemberCardState("批量修改会员卡状态") ,
	
	AddProductCard("新增次卡"),
	DelProductCard("删除次卡"),
	UpdProductCard("编辑次卡"),
	UpdProductCardState("修改次卡状态"),
	BatchUpdProductCardState("批量修改次卡状态") ,
	
	AddPrdCardType("新增次卡分类"),
	DelPrdCardType("删除次卡分类"),
	UpdPrdCardType("编辑次卡分类"),

	MemberCardAllot("会员卡分配到店"),
	MemberCardBatchAllot("会员卡批量分配到店"),
	ProductCardAllot("次卡分配到店"),
	ProductCardBatchAllot("次卡批量分配到店"),
	;
	
	private String mark;
	
	private ChainCardUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static ChainCardUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
