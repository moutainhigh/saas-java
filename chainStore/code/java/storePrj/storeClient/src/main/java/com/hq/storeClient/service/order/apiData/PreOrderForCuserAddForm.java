package com.hq.storeClient.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

public class PreOrderForCuserAddForm {
	// 客户ID
	private String leaguerId;
	// 客户会员卡ID
	private String memberCardId;
	// 应结金额
	private float cost;
	// 购买消费清单
	private List<BuyItemForCuser> buyItemForCusers = new ArrayList<BuyItemForCuser>();
	// 店铺ID
	private long storeId;
	// 创建者ID
	private long creatorId;

	// 用户动态Id
	private long dynamicId;

	// 快递信息
	// OrderTrackTypeEnum
	private int type;
	// 收货人
	private String receiver;
	// 收货人号码
	private String phone;
	// 收货地址
	private String address;

	public static PreOrderForCuserAddForm newInstance() {
		return new PreOrderForCuserAddForm();
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public List<BuyItemForCuser> getBuyItemForCusers() {
		return buyItemForCusers;
	}

	public void setBuyItemForCusers(List<BuyItemForCuser> buyItemForCusers) {
		this.buyItemForCusers = buyItemForCusers;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(long dynamicId) {
		this.dynamicId = dynamicId;
	}

}
