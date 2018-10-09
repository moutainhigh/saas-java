package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;


public class TPackProduct {
	private String name;
	private float salePrice;
	private String descript;
	
	public TPackProduct(String name, float salePrice, String descript) {
		this.name = name;
		this.salePrice = salePrice;
		this.descript = descript;
	}

	public static List<TPackProduct> buildTPackProductList() {
		List<TPackProduct> list = new ArrayList<TPackProduct>();
		
		list.add(new TPackProduct("手部护理套餐", RandomUtils.nextFloat(1000f, 2000f), "1、延缓肌肤衰老2、强化手部肌肉弹性3、按摩内脏器官4、避免秋冬皮肤皲裂5、隐藏年龄"));
		list.add(new TPackProduct("面部护理套餐", RandomUtils.nextFloat(1000f, 2000f), "1、面部清洁2、去角质3、蒸脸4、面部按摩"));
		list.add(new TPackProduct("脱毛套餐", RandomUtils.nextFloat(1000f, 2000f), "脱毛线。。。。"));
		list.add(new TPackProduct("美白套餐", RandomUtils.nextFloat(1000f, 2000f), "高疗效、高保养、原生态、零添加护肤 毛孔疏通、修复皮肤、再生皮脂膜，轮廓修复，面部筋结疏通，嫩白补水。"));
		list.add(new TPackProduct("护发套餐", RandomUtils.nextFloat(1000f, 2000f), "调节面部肌肤滋润度，放松面部，促进血液循环，恢复皮肤自由基，使肌肤水润光泽，富有弹性，减少因面部肌肤缺水而导致的干纹、细纹的产生，令肌肤白皙细嫩，有效延缓衰老"));
		
		list.add(new TPackProduct("护肤套餐", RandomUtils.nextFloat(1000f, 2000f), "快速修复眼周受损的胶原纤维,提升胶原蛋白再造能力，令眼周肌肤回复紧致 、明亮，提升眼部肌肤的弹性，有效预防和减淡皱纹,高效紧致眼袋,具有很好的抗皱、祛皱、淡化眼袋、黑眼圈、提升等效果。"));
		list.add(new TPackProduct("激光美容套餐", RandomUtils.nextFloat(1000f, 2000f), "激光美白"));
		list.add(new TPackProduct("套餐一", RandomUtils.nextFloat(1000f, 2000f), "打通人体经络，使气血运行通畅，加速身体代谢，给身体供养，排出毒素，改善疲劳，增强人体免疫力。"));
		list.add(new TPackProduct("套餐二", RandomUtils.nextFloat(1000f, 2000f), "可补肾益气，有效缓解肾虚腰痛。尿频、尿急、性欲衰退等症状"));
		list.add(new TPackProduct("套餐三", RandomUtils.nextFloat(1000f, 2000f), "疗效、高保养、原生态、零添加护肤 毛孔疏通、修复皮肤、再生皮脂膜，嫩白补水。"));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}


}
