package com.hq.orderMS.service.order.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

//筛选订单助手类
public class OrderQueryFilterHelper {

	public static OrderQueryFilterHelper getInstance() {
		return HotSwap.getInstance().getSingleton(OrderQueryFilterHelper.class);
	}
	
	public List<Order> filterRecord(OrderQueryForm queryForm, List<Order> list){
		List<Order> result = new ArrayList<Order>();
		if(CollectionUtils.isNotEmpty(list)){
			for (Order record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		return result;
	}
	
	private boolean isRightRecord(OrderQueryForm queryForm, Order record){
		if(!checkNumberOrName(queryForm.getNumberOrName(), record)){
			return false;
		}
		if(!checkOrderType(queryForm.getOrderType(), record)){
			return false;
		}
		if(!checkStatus(queryForm.getStatus(), record)){
			return false;
		}
		if(!checkLeaguerId(queryForm.getLeaguerId(), record)){
			return false;
		}
		if(!checkCUserId(queryForm.getCuserId(), record)){
			return false;
		}
		if(!checkBUserId(queryForm.getBuserId(), record)){
			return false;
		}
		if(!checkOrigin(queryForm.getOrigin(), record)){
			return false;
		}
		return true;
	}
	
	private boolean checkNumberOrName(String numberOrName, Order record){
		if(StringUtils.isBlank(numberOrName)){
			return true;
		}
		if(record.getName()!=null && record.getName().contains(numberOrName)){
			return true;
		}
		if(record.getNumber()!=null && record.getNumber().contains(numberOrName)){
			return true;
		}
		return false;
	}
	
	private boolean checkOrderType(int orderType, Order record){
		if(orderType == -1){
			return true;
		}
		if(orderType == record.getOrderType()){
			return true;
		}
		return false;
	}
	
	private boolean checkStatus(Set<Integer> statusSet, Order record){
		if(CollectionUtils.isEmpty(statusSet)){
			return true;
		}
		if(statusSet.contains(record.getStatus())){
			return true;
		}
		return false;
	}
	
	private boolean checkLeaguerId(String leaguerId, Order record){
		if(StringUtils.isBlank(leaguerId)){
			return true;
		}
		if(leaguerId.equals(record.getLeaguerId())){
			return true;
		}
		return false;
	}
	
	private boolean checkCUserId(long cuserId, Order record){
		if(cuserId == 0){
			return true;
		}
		if(cuserId==record.getCuserId()){
			return true;
		}
		return false;
	}
	
	private boolean checkBUserId(long buserId, Order record){
		if(buserId == 0){
			return true;
		}
		if(buserId==record.getCreatorId()){
			return true;
		}
		return false;
	}
	
	private boolean checkOrigin(Set<Integer> originSet, Order record){
		if(CollectionUtils.isEmpty(originSet)){
			return true;
		}
		if(originSet.contains(record.getOrigin())){
			return true;
		}
		return false;
	}
}
