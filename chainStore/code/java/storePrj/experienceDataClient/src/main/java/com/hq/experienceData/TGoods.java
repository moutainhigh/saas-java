package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hq.chainStore.service.storeGoods.data.GoodsStateEnum;
import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;


public class TGoods {
	// 商品编号
	private String number;
	// 名称
	private String name;
	// 售价
	private float price;
	// 成本
	private float cost;
	// 状态 对应GoodsStateEnum
	private int state;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgNames = new ArrayList<String>();
	
	private String typeName;
	
	public TGoods(String name, float price, String imgPath) {
		this.number = UUID.randomUUID().toString();
		this.name = name;
		this.price = price;
		this.cost = RandomUtils.nextFloat(100f, this.price);
		this.descript = "描述-"+RandomUtils.nextInt(10, 99);
		this.state = GoodsStateEnum.Open.ordinal();
		this.imgNames.add(imgPath);
	}
	
	public TGoods(String typeName) {
		this.typeName = typeName;
	}

	public static List<TGoods> buildTGoodsList() {
		List<TGoods> list = new ArrayList<TGoods>();
		
		list.add(new TGoods("玻尿酸原液面膜", RandomUtils.nextFloat(100f, 1000f), "手部护理1.jpg"));
		list.add(new TGoods("高效保湿喷雾", RandomUtils.nextFloat(100f, 1000f), "面部护理1.jpg"));
		list.add(new TGoods("修复液", RandomUtils.nextFloat(100f, 1000f), "脱腋毛.jpg"));
		list.add(new TGoods("修复精华凝胶", RandomUtils.nextFloat(100f, 1000f), "青春定格.jpg"));
		list.add(new TGoods("隔离防晒霜", RandomUtils.nextFloat(100f, 1000f), "面部美白保温护理.jpg"));
		
		list.add(new TGoods("洁面膏", RandomUtils.nextFloat(100f, 1000f), "眼部玉石刮痧.jpg"));
		list.add(new TGoods("去皱嫩肤", RandomUtils.nextFloat(100f, 1000f), "背部排毒.jpg"));
		list.add(new TGoods("祛痘膏", RandomUtils.nextFloat(100f, 1000f), "全身芳疗.jpg"));
		list.add(new TGoods("保湿液", RandomUtils.nextFloat(100f, 1000f), "肾部保养.jpg"));
		list.add(new TGoods("水晶亮肤", RandomUtils.nextFloat(100f, 1000f), "水晶亮肤.jpg"));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}
	
	public static List<TGoods> buildRandomTGoodsList(int count) {
		List<TGoods> tmpList = buildTGoodsList();
		List<TGoods> list = new ArrayList<TGoods>();
		for (int i = 0; i < count; i++) {
			TGoods data = tmpList.get(RandomUtils.nextInt(0, tmpList.size()));
			data.setName(data.getName()+"-"+RandomUtils.nextLong(1000L, 9000L));
			list.add(data);
		}
		return list;
	}
	
	public static List<TGoods> buildTGoodsTypeList() {
		List<TGoods> list = new ArrayList<TGoods>();
		
		list.add(new TGoods("美容"));
		list.add(new TGoods("美体"));
		list.add(new TGoods("美白"));
		list.add(new TGoods("瘦身"));
		list.add(new TGoods("护肤"));
		
		list.add(new TGoods("祛斑"));
		list.add(new TGoods("护发"));
		list.add(new TGoods("瘦脸"));
		list.add(new TGoods("保湿"));
		list.add(new TGoods("亮肤"));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}
	
	public static List<TGoods> buildRandomTGoodsTypeList(int count) {
		List<TGoods> tmpList = buildTGoodsTypeList();
		List<TGoods> list = new ArrayList<TGoods>();
		for (int i = 0; i < count; i++) {
			TGoods data = tmpList.get(RandomUtils.nextInt(0, tmpList.size()));
			data.setName(data.getName()+"-"+RandomUtils.nextLong(1000L, 9000L));
			list.add(data);
		}
		return list;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<String> getImgNames() {
		return imgNames;
	}

	public void setImgNames(List<String> imgNames) {
		this.imgNames = imgNames;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
