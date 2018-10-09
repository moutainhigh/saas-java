package com.hq.testClass.robot.orderComment;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.orderComment.apiData.OrderCommentQueryParams;
import com.hq.chainStore.service.orderComment.apiData.SaveBeauticianCommentForm;
import com.hq.chainStore.service.orderComment.bs.OrderCommentMgr;
import com.hq.chainStore.service.orderComment.data.OrderComment;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class OrderCommentRobot {
	
	private OrderCommentRobotData data;
	
	public static OrderCommentRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static OrderCommentRobot newInstance(int mark){
		OrderCommentRobot robot = new OrderCommentRobot();
		robot.data = OrderCommentRobotData.newInstance(mark);
		return robot;
	}
	
	public void saveBeauticianComment(long orderCommentId, long storeId){
		SaveBeauticianCommentForm saveForm = SaveBeauticianCommentForm.newInstance();
		FastBeanCopyer.getInstance().copy(data, saveForm);
		OrderCommentMgr.getInstance().saveBeauticianComment(orderCommentId, storeId, saveForm);
	}
	
	public OrderComment getOrderComment(long orderCommentId){
		return OrderCommentMgr.getInstance().getOrderComment(orderCommentId);
	}
	
	public List<OrderComment> findOrderCommentList(OrderCommentQueryParams params){
		return OrderCommentMgr.getInstance().findOrderCommentList(params);
	}
	
	public OrderCommentRobotData getData() {
		return data;
	}

	public void setData(OrderCommentRobotData data) {
		this.data = data;
	}	
	
	public long getId() {
		return this.data.getOrderId();
	}
	
}
