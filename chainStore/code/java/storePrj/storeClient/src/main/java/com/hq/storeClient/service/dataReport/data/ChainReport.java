package com.hq.storeClient.service.dataReport.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "chainReport")
public class ChainReport {
	@Id
	private long id;

	public static ChainReport newInstance() {
		ChainReport data = new ChainReport();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
