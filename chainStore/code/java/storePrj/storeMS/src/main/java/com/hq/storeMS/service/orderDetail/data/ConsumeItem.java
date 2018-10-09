package com.hq.storeMS.service.orderDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ConsumeItem {
	private String pgId;
	private String pgName;
	private String typeName;
	private String defaultImg;
	private float price;

	public static ConsumeItem newInstance() {
		ConsumeItem data = new ConsumeItem();
		return data;
	}

	public static ConsumeItem newInstance(String pgIdP, String pgNameP, float priceP) {
		ConsumeItem data = new ConsumeItem();
		data.pgName = pgNameP;
		data.price = priceP;
		data.pgId = pgIdP;
		return data;
	}

	public String getPgId() {
		return pgId;
	}

	public ConsumeItem withPgId(String pgId) {
		this.pgId = pgId;
		return this;
	}

	public String getPgName() {
		return pgName;
	}

	public ConsumeItem withPgName(String pgName) {
		this.pgName = pgName;
		return this;
	}

	public float getPrice() {
		return price;
	}

	public ConsumeItem withPrice(float price) {
		this.price = price;
		return this;
	}

	public String getTypeName() {
		return typeName;
	}

	public ConsumeItem withTypeName(String typeName) {
		this.typeName = typeName;
		return this;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public ConsumeItem withDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
		return this;
	}

}
