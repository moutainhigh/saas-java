package com.hq.storeClient.service.storeBeauticianInfo.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BeauticianInfo {
	// B端用户ID
	private long buserId;
	// 状态：BeauticianState
	private int state;
	// 描述
	private String descript;
	// 创建时间
	private long createTime;
	// 评分
	private float score;
	// 订单数量
	private long orderCount;

	public static BeauticianInfo newInstance(long buserIdP) {
		BeauticianInfo info = new BeauticianInfo();
		return info;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

}
