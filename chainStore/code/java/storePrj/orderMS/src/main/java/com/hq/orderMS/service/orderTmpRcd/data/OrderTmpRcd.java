package com.hq.orderMS.service.orderTmpRcd.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.orderMS.common.util.AppUtils;
import com.hq.orderMS.service.order.data.Order;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 订单-临时表
 * 
 * @author kevin
 *
 */
@SynClass
@Table(name = "orderTmpRcd")
public class OrderTmpRcd {
	@Id
	// storeId_orderId
	private String id;
	// 店铺ID
	private long storeId;
	// 订单ID
	private long orderId;
	// 订单创建时间
	@IndexField
	private long createTime;
	// 订单来源 OrderOriginEnum
	@IndexField
	private int origin;

	public static OrderTmpRcd newInstance() {
		OrderTmpRcd data = new OrderTmpRcd();
		return data;
	}

	public static OrderTmpRcd newInstance(Order order) {
		OrderTmpRcd data = newInstance();
		data.orderId = order.getId();
		data.storeId = order.getStoreId();
		data.createTime = order.getCreatedTime();
		data.id = AppUtils.joinByUnderline(order.getStoreId(), order.getId());
		data.origin = order.getOrigin();
		return data;
	}
	
	public static String generateId(long storeId, long orderId) {
		return AppUtils.joinByUnderline(storeId, orderId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

}
