package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;


public class TProduct {
	private String name;
	private float price;
	private String imgPath;
	private String label;
	private String description;
	
	public TProduct(String name, float price, String imgPath, String label, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.imgPath = imgPath;
		this.label = label;
	}

	public static List<TProduct> buildTProductList() {
		List<TProduct> list = new ArrayList<TProduct>();
		
		list.add(new TProduct("手部护理", RandomUtils.nextInt(100, 600), "手部护理1.jpg","美容","1、延缓肌肤衰老2、强化手部肌肉弹性3、按摩内脏器官4、避免秋冬皮肤皲裂5、隐藏年龄"));
		list.add(new TProduct("面部护理", RandomUtils.nextInt(100, 600), "面部护理1.jpg","美容","1、面部清洁2、去角质3、蒸脸4、面部按摩"));
		list.add(new TProduct("脱腋毛", RandomUtils.nextInt(100, 600), "脱腋毛.jpg","整形","腋毛位于腋下，亦为女性第二性征表现之一，一般是不分男女都有的，人体的腋毛与阴毛相似，是肾上腺开始分泌雄激素的结果。腋毛出现的时间较阴毛晚1-2年，国外报道平均年龄为14-15岁。我国女性也大致在14-15岁后出现腋毛。激光脱毛是目前好的腋毛祛除方法，激光脱毛是依据选择性的光热动力学原理，通过合理调节激光波长、能量、脉宽，激光便能穿过皮肤表层到达毛发的根部毛囊，光能被吸收并转化为破坏毛囊组织的热能，从而使毛发失去再生能力，同时又不损伤周边皮肤组织，祛除过程基本无痛感。"));
		list.add(new TProduct("青春定格", RandomUtils.nextInt(100, 600), "青春定格.jpg","整形","高疗效、高保养、原生态、零添加护肤 毛孔疏通、修复皮肤、再生皮脂膜，轮廓修复，面部筋结疏通，嫩白补水。"));
		list.add(new TProduct("面部美白保温护理", RandomUtils.nextInt(100, 600), "面部美白保温护理.jpg","美白","调节面部肌肤滋润度，放松面部，促进血液循环，恢复皮肤自由基，使肌肤水润光泽，富有弹性，减少因面部肌肤缺水而导致的干纹、细纹的产生，令肌肤白皙细嫩，有效延缓衰老"));
		
		list.add(new TProduct("眼部玉石刮痧", RandomUtils.nextInt(100, 600), "眼部玉石刮痧.jpg","美白","快速修复眼周受损的胶原纤维,提升胶原蛋白再造能力，令眼周肌肤回复紧致 、明亮，提升眼部肌肤的弹性，有效预防和减淡皱纹,高效紧致眼袋,具有很好的抗皱、祛皱、淡化眼袋、黑眼圈、提升等效果。"));
		list.add(new TProduct("背部排毒、舒穴", RandomUtils.nextInt(100, 600), "背部排毒.jpg","护理","采有中国古时期的民间中医物理疗法，运用刮痧、滑罐的方式对人体背部进行一深一浅的排毒方法，将体表的风、寒、湿、热等毒素，通过毛孔自然张开排出体外，达到肌肤放松，体表健康，同时调节神经、内分泌以及免疫系统，从整体上协调人体各组织器官的功能，有效改善亚健康状态。"));
		list.add(new TProduct("全身芳疗", RandomUtils.nextInt(100, 600), "全身芳疗.jpg","护理","打通人体经络，使气血运行通畅，加速身体代谢，给身体供养，排出毒素，改善疲劳，增强人体免疫力。"));
		list.add(new TProduct("肾部保养", RandomUtils.nextInt(100, 600), "肾部保养.jpg","保养","可补肾益气，有效缓解肾虚腰痛。尿频、尿急、性欲衰退等症状"));
		list.add(new TProduct("水晶亮肤", RandomUtils.nextInt(100, 600), "水晶亮肤.jpg", "保养", "疗效、高保养、原生态、零添加护肤 毛孔疏通、修复皮肤、再生皮脂膜，嫩白补水。"));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}
	
	public static List<TProduct> buildRandomTProductList(int count) {
		List<TProduct> tmpList = buildTProductList();
		List<TProduct> list = new ArrayList<TProduct>();
		for (int i = 0; i < count; i++) {
			TProduct data = tmpList.get(RandomUtils.nextInt(0, tmpList.size()));
			data.setName(data.getName()+"-"+RandomUtils.nextLong(1000L, 9000L));
			list.add(data);
		}
		return list;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
