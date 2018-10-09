package com.hq.storeMS.service.workFlowData.data;

import java.util.HashSet;
import java.util.Set;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class GoodsRecord {
	// goodsId_recordType
	private String id;
	// 商品ID
	private String goodsId;
	// 商品数量
	private int count;
	// 商品价格
	private float price;
	// 折扣
	private float discount;
	// 记录类型 RecordTypeEnum
	private int recordType;
	// 创建时间
	private long createTime;
	// 预存数量
	private int restCount;

	// 原价
	private float oldPrice;
	// 总价 = 原价 X 个数
	private float cost;
	// 应结 = 售价 X 个数
	private float pay;

	/********************** 遗留字段 **********************/
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();

	public static GoodsRecord newInstance() {
		GoodsRecord data = new GoodsRecord();
		data.createTime = System.currentTimeMillis();
		return data;
	}

	public BuyItem toBuyItem() {
		BuyItem item = BuyItem.newInstance(BuyTypeEnum.GOODS.ordinal(), this.goodsId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
	}

	public DonationItem toDonationItem() {
		DonationItem item = DonationItem.newInstance(BuyTypeEnum.GOODS.ordinal(), this.goodsId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}

}
