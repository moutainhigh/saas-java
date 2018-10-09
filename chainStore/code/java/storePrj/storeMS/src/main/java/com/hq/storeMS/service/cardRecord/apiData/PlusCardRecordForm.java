package com.hq.storeMS.service.cardRecord.apiData;

import java.util.List;

public class PlusCardRecordForm {
	private List<String> cardIds;// 卡片ID
	private String leaguerId;// 客户
	private Long productId;//项目ID  耗卡使用统计 需要

	public static PlusCardRecordForm newInstance() {
		return new PlusCardRecordForm();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public List<String> getCardIds() {
		return cardIds;
	}

	public void setCardIds(List<String> cardIds) {
		this.cardIds = cardIds;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
