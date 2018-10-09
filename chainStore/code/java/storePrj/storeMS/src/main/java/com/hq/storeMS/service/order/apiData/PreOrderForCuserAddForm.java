package com.hq.storeMS.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.apiData.OrderAddApiForm;
import com.hq.orderRestClient.service.order.data.OrderOriginEnum;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.zenmind.common.beanCopy.FastBeanCopyer;

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

	public OrderAddApiForm toOrderAddApiForm(String leaguerName) {
		OrderAddApiForm data = OrderAddApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setOrderType(OrderTypeEnum.PURCHASE.ordinal());
		data.setName(leaguerName);
		for (BuyItemForCuser buyItemForCuser : buyItemForCusers) {
			data.getBuyItems().add(buyItemForCuser.toBuyItem());
		}
		data.setOrigin(OrderOriginEnum.CUSTOMER.ordinal());
		return data;
	}

	public OrderTrack toOrderTrack(long orderId) {
		OrderTrack data = OrderTrack.newInstance(storeId, orderId);
		data.setAddress(address);
		data.setType(type);
		data.setReceiver(receiver);
		data.setPhone(phone);
		return data;
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
