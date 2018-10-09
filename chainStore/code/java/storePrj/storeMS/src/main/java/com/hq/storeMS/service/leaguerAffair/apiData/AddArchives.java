package com.hq.storeMS.service.leaguerAffair.apiData;

import com.hq.storeMS.service.leaguerAffair.data.Archives;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AddArchives {
	private long id;
	private String content;

	public static AddArchives newInstance() {
		return new AddArchives();
	}
	
	public Archives toArchives(){
		Archives data = Archives.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
