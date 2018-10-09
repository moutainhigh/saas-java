package com.hq.storeClient.service.wxMedia.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;


@SynClass
@Table(name = "wxMedia")
public class WxMedia {
	@Id
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
