package com.hq.storeClient.service.orderDetail.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.order.data.PayItem;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ChargeBackDetail {
	// 退单ID
	private int id;
	// 退单流水号
	private String number;
	// 创建时间
	private long createTime;
	// 操作人
	private long creatorId;
	private String creatorName;
	// 退款项的数量与金额
	private List<ChargeBackItemDetail> chargeBackItems = new ArrayList<ChargeBackItemDetail>();
	// 退款明细
	private List<PayItem> payItems = new ArrayList<PayItem>();
	// 退单备注信息
	private String remark;

	public static ChargeBackDetail newInstance() {
		ChargeBackDetail data = new ChargeBackDetail();
		return data;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<ChargeBackItemDetail> getChargeBackItems() {
		return chargeBackItems;
	}

	public void setChargeBackItems(List<ChargeBackItemDetail> chargeBackItems) {
		this.chargeBackItems = chargeBackItems;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}
}
