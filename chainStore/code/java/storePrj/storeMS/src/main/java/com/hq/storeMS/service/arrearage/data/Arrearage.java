package com.hq.storeMS.service.arrearage.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "arrearage")
public class Arrearage {
	@Id
	private long id;

	@IndexField
	private long storeId;

	private String leaguerId;
	// 客户名称
	private String leaguerName;
	// 客户手机号
	private long leaguerPhone;

	@IndexField(groupName="orderId")
	private long orderId;
	private String orderNumber;

	// 状态 ArrearageStatusEnum
	private int status;

	// 剩余欠款金额 为0的时候 清账
	private float balanceDue;
	// 订单欠款总金额
	private float balanceTotal;

	// 还款记录
	private List<PaymentHistory> paymentHistories = new ArrayList<PaymentHistory>();
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;
	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static Arrearage newInstance() {
		Arrearage arrearage = new Arrearage();

		long curTime = System.currentTimeMillis();

		arrearage.status = ArrearageStatusEnum.BALANCE_DUE.ordinal();
		arrearage.createdTime = curTime;
		arrearage.lastUpdateTime = curTime;

		return arrearage;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
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

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public void setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(float balanceDue) {
		this.balanceDue = balanceDue;
	}

	public float getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(float balanceTotal) {
		this.balanceTotal = balanceTotal;
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

	public long getLeaguerPhone() {
		return leaguerPhone;
	}

	public void setLeaguerPhone(long leaguerPhone) {
		this.leaguerPhone = leaguerPhone;
	}

	public List<PaymentHistory> getPaymentHistories() {
		return paymentHistories;
	}

	public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
		this.paymentHistories = paymentHistories;
	}

}
