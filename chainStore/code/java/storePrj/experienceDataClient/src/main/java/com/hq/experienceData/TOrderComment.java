package com.hq.experienceData;

import java.util.ArrayList;
import java.util.List;

import com.hq.experienceData.service.DataConstants;

public class TOrderComment {
	private String content;
	private String imgPath;
	
	public static TOrderComment newInstance(String content, String imgPath){
		TOrderComment data = new TOrderComment();
		data.content = content;
		data.imgPath = imgPath;
		return data;
	}
	
	public static List<TOrderComment> buildTOrderCommentList() {
		List<TOrderComment> list = new ArrayList<TOrderComment>();
		list.add(newInstance("添加标签的格式", "面部护理1.jpg"));
		list.add(newInstance("新建土豆时，输入英文井字符后", "面部护理2.jpg"));
		list.add(newInstance("在任务列表点击标签可以", "面部护理3.jpg"));
		list.add(newInstance("标签与任务描述之间需要空一格", "面部护理4.jpg"));
		list.add(newInstance("需要使用英文字符", "面部护理5.jpg"));
		
		list.add(newInstance("应用服务器实例过多，可统计每个实例建立的连接数", "面部护理6.jpg"));
		list.add(newInstance("这种就是配置的问题，更改连接池参数。", "面部护理7.jpg"));
		list.add(newInstance("连接池配置不合理", "面部护理8.jpg"));
		list.add(newInstance("这几天一直在压测服务器的性能问题", "面部护理9.jpg"));
		list.add(newInstance("Kotlin程序开发入门精要 ", "全身芳疗.jpg"));
		return list.subList(0, DataConstants.OTHER_COUNT);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
