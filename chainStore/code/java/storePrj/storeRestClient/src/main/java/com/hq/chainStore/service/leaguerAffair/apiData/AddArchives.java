package com.hq.chainStore.service.leaguerAffair.apiData;


public class AddArchives {
	private long id;
	private String content;

	public static AddArchives newInstance() {
		return new AddArchives();
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
