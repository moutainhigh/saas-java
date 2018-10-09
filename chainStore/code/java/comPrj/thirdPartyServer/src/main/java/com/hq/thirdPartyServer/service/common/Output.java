package com.hq.thirdPartyServer.service.common;

public class Output {
	private String outputLabel;
	private String outputMulti;
	private OutputValue outputValue;

	public static Output newInstance() {
		return new Output();
	}

	public String getOutputLabel() {
		return outputLabel;
	}

	public void setOutputLabel(String outputLabel) {
		this.outputLabel = outputLabel;
	}

	public String getOutputMulti() {
		return outputMulti;
	}

	public void setOutputMulti(String outputMulti) {
		this.outputMulti = outputMulti;
	}

	public OutputValue getOutputValue() {
		return outputValue;
	}

	public void setOutputValue(OutputValue outputValue) {
		this.outputValue = outputValue;
	}
}
