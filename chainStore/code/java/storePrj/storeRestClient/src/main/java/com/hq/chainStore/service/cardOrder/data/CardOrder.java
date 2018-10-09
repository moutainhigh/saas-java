package com.hq.chainStore.service.cardOrder.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "cardOrder")
public class CardOrder implements IntfSynData {
	@Id
	private long id;
	private long storeId;

	//顾客信息
	private long cuserId;
	private String leaguerId;
	
	//卡片信息
	private String cardId;//卡片ID
	private float price;//价格
	private int cardType;//卡片类型
	
	//订单信息
	private int payType;
	private float cost;
	private int origin;// 来源 0:未知 1:B端商家 2:B端医美师 3:C端用户[普通消费者]
	private int status;// 订单的状态 0未知 1未支付 2已支付
	
	//创建者信息
	private long creatorId;// 创建者ID

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static CardOrder newInstance() {
		return new CardOrder();
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	@Override
	public String toString() {
		return "CardOrder [id=" + id + ", storeId=" + storeId + ", cuserId="
				+ cuserId + ", leaguerId=" + leaguerId + ", cardId=" + cardId
				+ ", price=" + price + ", cardType=" + cardType + ", payType="
				+ payType + ", cost=" + cost + ", origin=" + origin
				+ ", status=" + status + ", creatorId=" + creatorId
				+ ", createdTime=" + createdTime + ", lastUpdateTime="
				+ lastUpdateTime + ", ver=" + ver + "]";
	}

}
