package com.hq.chainStore.service.storeCardInfo.apiData;

public enum StoreCardInfoUpdateType {
	AddMembershipCard("新增会员卡"),
	DelMembershipCard("删除会员卡"),
	UpdMembershipCard("编辑会员卡"),
	UpdMemberCardState("修改会员卡状态"),
	BatchUpdMemberCardState("批量修改会员卡状态") ,// 批量上下架
	
	AddProductCard("新增次卡"),//次卡
	DelProductCard("删除次卡"),
	UpdProductCard("编辑次卡"),
	UpdProductCardState("修改次卡状态"),
	BatchUpdProductCardState("批量修改次卡状态") ,// 批量上下架
	
	AddDiscountCard("新增优惠卷"),//已过时
	DelDiscountCard("删除优惠卷"),//已过时
	UpdDiscountCard("编辑优惠卷"),//已过时
	
	AddMembershipCardList("批量新增会员卡"),
	
	AddPrdCardType("新增次卡分类"),
	DelPrdCardType("删除次卡分类"),
	UpdPrdCardType("编辑次卡分类"),
	
	/*************************连锁店数据同步**************************************/
	PullCardFromChain("拉取连锁店次卡"),
	CancelChainCard("取消获取连锁店次卡"),
	BatchPullCardFromChain("批量拉取连锁店次卡"),
	BatchCancelChainCard("批量取消连锁店次卡"),
	
	PullMemberCardFromChain("拉取连锁店会员卡"),
	CancelChainMemberCard("取消获取连锁店会员卡"),
	BatchPullMemberCardFromChain("批量拉取连锁店会员卡"),
	BatchCancelChainMemberCard("批量取消连锁店会员卡"),
	/*************************连锁店数据同步**************************************/
	
	AddPrdCardTop("次卡置顶"),
	CancelPrdCardTop("取消置顶"),
	;
	
	private String mark;
	
	private StoreCardInfoUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static StoreCardInfoUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
