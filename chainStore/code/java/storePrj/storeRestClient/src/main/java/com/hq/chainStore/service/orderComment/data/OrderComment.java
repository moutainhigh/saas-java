package com.hq.chainStore.service.orderComment.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "orderComment")
public class OrderComment implements IntfSynData {
	@Id
	private long id;
	private long orderId;
	private long storeId;
	private long beauticianId;
	private long productId;
	private long cuserId;
	private String cuserName;
	private float serviceScore;
	private float effectScore;
	private int status;
	
	private String comment;
	// 医美师 评论
	private BeauticianComment beauticianComment;

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static OrderComment newInstance() {
		OrderComment data = new OrderComment();
		return data;
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public String getCuserName() {
		return cuserName;
	}

	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}

	public float getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(float serviceScore) {
		this.serviceScore = serviceScore;
	}

	public float getEffectScore() {
		return effectScore;
	}

	public void setEffectScore(float effectScore) {
		this.effectScore = effectScore;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public BeauticianComment getBeauticianComment() {
		return beauticianComment;
	}

	public void setBeauticianComment(BeauticianComment beauticianComment) {
		this.beauticianComment = beauticianComment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderComment [id=" + id + ", orderId=" + orderId + ", storeId="
				+ storeId + ", beauticianId=" + beauticianId + ", productId="
				+ productId + ", cuserId=" + cuserId + ", cuserName="
				+ cuserName + ", serviceScore=" + serviceScore
				+ ", effectScore=" + effectScore + ", comment=" + comment
				+ ", createdTime=" + createdTime + ", lastUpdateTime="
				+ lastUpdateTime + ", ver=" + ver + "]";
	}

}
