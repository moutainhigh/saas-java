package com.hq.storeMS.service.orderTrack.bs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackQueryForm;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackUpdateStatusForm;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.hq.storeMS.service.orderTrack.data.OrderTrackStatusEnum;
import com.hq.storeMS.service.orderTrack.data.OrderTrackTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTrackMgr {

	public static OrderTrackMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTrackMgr.class);
	}

	public void addWithId(OrderTrack target) {
		OrderTrackDataHolder.getInstance().addWithId(target);
	}

	public void update(OrderTrack target) {
		OrderTrackDataHolder.getInstance().updpate(target);
	}

	public OrderTrack get(long storeId, long orderId) {
		OrderTrack data = OrderTrackDataHolder.getInstance().get(storeId, orderId);
		if (data == null) {// 不存在，则新增
			data = OrderTrack.newInstance(storeId, orderId);
			OrderTrackDataHolder.getInstance().addWithId(data);
		}
		return data;
	}

	public List<OrderTrack> findOrderTrackList(OrderTrackQueryForm queryForm) {
		List<OrderTrack> target = OrderTrackDataHolder.getInstance().findOrderTrackList(queryForm);
		return target;
	}
	
	public Map<Long, OrderTrack> getOrderTrackMap(OrderTrackQueryForm queryForm){
		Map<Long, OrderTrack> map = new HashMap<Long, OrderTrack>();
		List<OrderTrack> list = OrderTrackDataHolder.getInstance().findOrderTrackList(queryForm);
		for (OrderTrack orderTrack : list) {
			map.put(orderTrack.getId(), orderTrack);
		}
		return map;
	}
	
	public OperateTips updateStatus(long storeId, long orderId, OrderTrackUpdateStatusForm updateStatusForm) {
		OperateTips tips = OperateTips.newInstance(false);
		OrderTrack orderTrack = get(storeId, orderId);
		if(orderTrack == null) {
			tips.setTips("订单的物流信息不存在");
			return tips;
		}
		
		orderTrack.setStatus(updateStatusForm.getStatus());
		long nowTime = System.currentTimeMillis();
		
		OrderTrackStatusEnum orderTrackStatusEnum = updateStatusForm.getOrderTrackStatusEnum();
		switch (orderTrackStatusEnum) {
		case Finish:
			orderTrack.setConfirmTime(nowTime);;
			break;
		case Pay:
			if(orderTrack.getType() == OrderTrackTypeEnum.Prestore.ordinal()) {//到店自提  付款成功后，即已发货
				orderTrack.setStatus(OrderTrackStatusEnum.Send.ordinal());
				orderTrack.setDeliverTime(nowTime);
			}else {
				orderTrack.setPayTime(nowTime);
			}
			break;
		case Send:
			orderTrack.setCompany(updateStatusForm.getCompany());
			orderTrack.setCourierNum(updateStatusForm.getCourierNum());
			orderTrack.setDeliverTime(nowTime);
			break;
		case Cancel:
			orderTrack.setCancelTime(nowTime);
			break;
		default:
			break;
		}
		update(orderTrack);
		tips.setSuccess(true);
		return tips;
	}
}
