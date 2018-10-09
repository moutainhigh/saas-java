package com.hq.thirdPartyServer.service.common;

import java.util.ArrayList;
import java.util.List;


public class ResponesEntity {
	private List<Output> outputs = new ArrayList<Output>();

	public static ResponesEntity newInstance() {
		return new ResponesEntity();
	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Output> outputs) {
		this.outputs = outputs;
	}
}
