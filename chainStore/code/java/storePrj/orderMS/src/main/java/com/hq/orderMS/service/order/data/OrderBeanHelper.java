package com.hq.orderMS.service.order.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.zenmind.common.hotSwap.HotSwap;

public class OrderBeanHelper {

	public static OrderBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(OrderBeanHelper.class);
	}
	
	/**
	 * 随机生成订单号
	 */
	public String genOrderNumber(){
		return String.valueOf(SnowflakeIdWorker.getInstance().nextId());
	}
	
	/**
	 * 生成有业务规则意义的订单序号
	 * 订单类型1位+店铺4位+用户cuserId3位+时间信息4位+下单时间的Unix时间戳后8位[8位已经可以表示1天24小时的任何时刻]
	 * @param order
	 * @return
	 */
	public String genOrderNumber(Order order){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		String nowStr = date.getTime()+"";
		StringBuffer sb = new StringBuffer();
		//订单类型
		sb.append(order.getOrderType());
		//店铺ID
		String storeIdStr = StringUtils.substring(order.getStoreId()+"", -4);
		sb.append(StringUtils.leftPad(storeIdStr, 4, "0"));
		//cuerId
		String cuserIdStr = StringUtils.substring(order.getCuserId()+"", -3);
		sb.append(StringUtils.leftPad(cuserIdStr, 3, "0"));
		//月日
		sb.append(sdf.format(date));
		//随机时间戳
		sb.append(StringUtils.substring(nowStr, -8));
		return sb.toString();
	}
	
	public static void main(String[] args) {
//		System.out.println(OrderBeanHelper.getInstance().genOrderNumber());
		Order order = Order.newInstance();
		order.setStoreId(RandomUtils.nextLong(100L, 12000L));
		order.setCuserId(RandomUtils.nextLong(1L, 12000L));
		order.setOrderType(RandomUtils.nextInt(0, 2));
		System.out.println(OrderBeanHelper.getInstance().genOrderNumber(order));
	}

}
