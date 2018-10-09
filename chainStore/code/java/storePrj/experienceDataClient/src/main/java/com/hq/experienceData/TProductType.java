package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

/** 
 * @ClassName: TProductType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月23日 上午9:05:22 
 *  
 */
public class TProductType {

	
	private String name;
	
	
	public TProductType(String name) {
		this.name = name;

	}
	
	public static List<TProductType> buildTProductTypeList() {
		List<TProductType> list = new ArrayList<TProductType>();
		list.add(new TProductType("脱毛"));
		list.add(new TProductType("美白"));
		list.add(new TProductType("保湿"));
		list.add(new TProductType("祛皱紧致"));
		list.add(new TProductType("排毒养颜"));
		list.add(new TProductType("嫩肤"));
		list.add(new TProductType("面部护理"));
		list.add(new TProductType("手部护理"));
		list.add(new TProductType("保湿"));
		list.add(new TProductType("手部护理"));
		list.add(new TProductType("保湿"));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}
	
	public static List<TProductType> buildRandomTProductTypeList(int count) {
		List<TProductType> tmpList = buildTProductTypeList();
		List<TProductType> list = new ArrayList<TProductType>();
		for (int i = 0; i < count; i++) {
			TProductType data = tmpList.get(RandomUtils.nextInt(0, tmpList.size()));
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

	
}
