package com.hq.storeClient.common.exception;

public enum AppNameEnum {
	None("none"), 
	ChainMS("ChainMS"),
	StoreMngMS("StoreMngMS"),
	CustomerMS("CustomerMS"),
	;
	
	
	private String name;

	private AppNameEnum(String name) {
		this.name = name;
	}

	public static AppNameEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getName() {
		return name;
	}
}
