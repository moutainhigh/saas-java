package com.hq.storeMS.service.leaguerRecord.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecord;
import com.hq.storeMS.service.leaguerRecord.data.RelateOrder;
import com.hq.storeMS.service.leaguerRecord.data.RelateProduct;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerRecordUpdateForm {
	// 标题
	private String title;
	// 内容
	private String content;
	// 图片
	private List<String> imgPaths = new ArrayList<String>();

	// 关联的项目列表
	private List<String> prdIds = new ArrayList<String>();
	// 关联订单
	private long orderId;
	// 订单内容
	private String orderContent;

	public static LeaguerRecordUpdateForm newInstance() {
		return new LeaguerRecordUpdateForm();
	}

	public void updateLeaguerRecord(LeaguerRecord data) {
		FastBeanCopyer.getInstance().copy(this, data);
		data.setRelateProduct(RelateProduct.newInstance(prdIds));
		data.setRelateOrder(RelateOrder.newInstance(orderId, orderContent));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public List<String> getPrdIds() {
		return prdIds;
	}

	public void setPrdIds(List<String> prdIds) {
		this.prdIds = prdIds;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
}
