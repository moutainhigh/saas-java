package com.hq.thirdPartyServer.service.common;

import java.util.ArrayList;
import java.util.List;


public class RequestEntity {
	private List<Input> inputs = new ArrayList<Input>();

	public static RequestEntity newInstance(Input input) {
		RequestEntity data = new RequestEntity();
		data.inputs.add(input);
		return data;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

}
