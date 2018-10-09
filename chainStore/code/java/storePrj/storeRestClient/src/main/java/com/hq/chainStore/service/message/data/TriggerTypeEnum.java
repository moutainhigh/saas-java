package com.hq.chainStore.service.message.data;

public enum TriggerTypeEnum {
	NEW_MY_CLERK("店员管理", "你有一个新的员工申请加入，来自用户：{}，请注意查看。"), 
	APPLY_CLERK_SUCCESS("店员审核通过", "你的审核已通过"),
	APPLY_CLERK_FAILURE("店员审核不通过", "你的审核未通过"),
	NEW_MY_APPOINTMENT("我的预约", "你有一个新的预约，来自用户：{}，请注意查看。"), 
	NEW_MY_WORKFLOW("店务管理", "你有一个新的店务，来自用户：{}，请注意查看。"),
	
	PASS_APPLY_CHAIN("加入总部", "您申请加入{}已通过，请注意查看。"),
	CHAIN_ALLOT_CLERK("总部分配员工", "{}分派新员工:{}申请加入{}，请注意查看。"),
	CHAIN_PRODUCT_UPDATE("总部产品更新", "{}产品:{}有更新信息，请注意查看。"),
	CHAIN_PRODUCT_UP("总部产品上架", "{}产品:{}新上架信息，请注意查看。"),
	
	;

	private String mark;
	private String tips;

	private TriggerTypeEnum(String markP, String tipsP) {
		this.mark = markP;
		this.tips = tipsP;
	}

	public String getMark() {
		return mark;
	}

	public String getTips() {
		return tips;
	}
	
	public static TriggerTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
