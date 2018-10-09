package com.hq.storeMS.service.leaguerAffair.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class Archives {
	private long id;

	private String content;
	
	private long creatorId;
	private String creatorName;
	
	private long createTime;

	public static Archives newInstance() {
		Archives data = new Archives();
		data.createTime = System.currentTimeMillis();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

}
