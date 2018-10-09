package com.hq.storeMS.service.workFlowType.data;


public enum WFCompEnum {
	//选择客户组件、选择跟进人员、项目组件、次卡项目组件、商品组件、次卡组件、结算组件、充值组件、订单组件、设置提成组件、客户跟踪组件
	SelectCuserWFComp("选择客户组件","选择客户"),
	SelectFollowPersonWFComp("选择跟进人员组件","选择跟进人员"),
	SelectProductWFComp("选择项目组件","选择项目"),
	SelectPrdCardProdWFComp("选择次卡项目组件","选择次卡项目"),
	SelectGoodsWFComp("选择商品组件","选择商品"),
	SelectPrdCardWFComp("选择次卡组件","选择次卡"),
	RechargeMemCardWFComp("会员卡充值组件","会员卡充值"),
	PayTheBillWFComp("结算组件","待结算"),
	OrderWFComp("订单组件","确认付款"),
	BuyBonusWFComp("购买提成组件","设置提成"),
	RechargeBonusWFComp("充值提成组件","设置提成"),
	CuserCareWFComp("客户关怀组件","客户跟踪"),
	SelectPackagePrjWFComp("选择套餐组件","选择套餐"),
	;
	
	private String compName;
	
	private String mark;

	private WFCompEnum(String compName, String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public String getCompName() {
		return compName;
	}

	public static WFCompEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
