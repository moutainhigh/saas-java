package com.hq.storeMS.service.workFlowData.apiData;

public class WorkFlowDataUpdateInfoForm {
	private long workFlowTypeId;
	// 客户备注信息
	private String leaguerInfoComment;
	// 项目备注信息
	private String productComment;
	// 划卡备注信息
	private String decreasePrdInCardComment;
	// 次卡备注信息
	private String prdInCardComment;
	// 商品备注信息
	private String goodsComment;
	//会员充值备注信息
	private String memCardComment;
	// 订单备注信息
	private String orderInfoComment;
	// 提成备注信息
	private String bonusInfoComment;

	public static WorkFlowDataUpdateInfoForm newInstance() {
		return new WorkFlowDataUpdateInfoForm();
	}

	public long getWorkFlowTypeId() {
		return workFlowTypeId;
	}

	public void setWorkFlowTypeId(long workFlowTypeId) {
		this.workFlowTypeId = workFlowTypeId;
	}

	public String getLeaguerInfoComment() {
		return leaguerInfoComment;
	}

	public void setLeaguerInfoComment(String leaguerInfoComment) {
		this.leaguerInfoComment = leaguerInfoComment;
	}

	public String getProductComment() {
		return productComment;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

	public String getDecreasePrdInCardComment() {
		return decreasePrdInCardComment;
	}

	public void setDecreasePrdInCardComment(String decreasePrdInCardComment) {
		this.decreasePrdInCardComment = decreasePrdInCardComment;
	}

	public String getPrdInCardComment() {
		return prdInCardComment;
	}

	public void setPrdInCardComment(String prdInCardComment) {
		this.prdInCardComment = prdInCardComment;
	}

	public String getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(String goodsComment) {
		this.goodsComment = goodsComment;
	}

	public String getMemCardComment() {
		return memCardComment;
	}

	public void setMemCardComment(String memCardComment) {
		this.memCardComment = memCardComment;
	}

	public String getOrderInfoComment() {
		return orderInfoComment;
	}

	public void setOrderInfoComment(String orderInfoComment) {
		this.orderInfoComment = orderInfoComment;
	}

	public String getBonusInfoComment() {
		return bonusInfoComment;
	}

	public void setBonusInfoComment(String bonusInfoComment) {
		this.bonusInfoComment = bonusInfoComment;
	}
}
