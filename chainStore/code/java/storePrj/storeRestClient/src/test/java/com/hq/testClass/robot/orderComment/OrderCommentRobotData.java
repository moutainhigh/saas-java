package com.hq.testClass.robot.orderComment;

import java.util.ArrayList;
import java.util.List;

public class OrderCommentRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;
	
	private long orderId;
	private long storeId;
	private long beauticianId;
	private long productId;
	private long cuserId;
	private String cuserName;
	private float serviceScore;
	private float effectScore;
	private String comment;

	private String content;
	private List<String> imgPaths = new ArrayList<String>();

	public static OrderCommentRobotData newInstance(int mark) {
		OrderCommentRobotData data = new OrderCommentRobotData();
		data.mark = mark;
		data.imgPaths.add("img/buser/12325/2017_07_12_10_825d1e76-9ec3-4c60-8795-156ef741a306.jpg");
		data.content = "content-"+mark;
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getImgPaths() {
		return imgPaths;
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

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

}
