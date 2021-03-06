package com.hq.storeMS.service.workFlowData.apiData.save;

import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardAddForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.storeMS.service.workFlowData.data.RecordTypeEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class DonateItemSaveForm {
	// 购买类型 BuyTypeEnum
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	// 原价
	private float oldPrice;
	// 数量
	private int count;

	public static DonateItemSaveForm newInstance() {
		return new DonateItemSaveForm();
	}
	
	public GoodsRecordAddForm toGoodsRecordAddForm() {
		GoodsRecordAddForm data = GoodsRecordAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setGoodsId(pgId);
		data.setRecordType(RecordTypeEnum.Donation.ordinal());
		return data;
	}
	
	public PackagePrjRecordAddForm toPackagePrjRecordAddForm() {
		PackagePrjRecordAddForm data = PackagePrjRecordAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setPackagePrjId(pgId);
		data.setRecordType(RecordTypeEnum.Donation.ordinal());
		return data;
	}
	
	public PrdCardAddForm toPrdCardAddForm() {
		PrdCardAddForm data = PrdCardAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setPrdCardTypeId(pgId);
		data.setRecordType(RecordTypeEnum.Donation.ordinal());
		return data;
	}
	
	public ProdRecordAddForm toProdRecordAddForm() {
		ProdRecordAddForm data = ProdRecordAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setProdId(pgId);
		data.setRecordType(RecordTypeEnum.Donation.ordinal());
		return data;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
