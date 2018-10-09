package com.hq.thirdPartyServer.service.sms.data;

public enum AreaCodeEnum {
	China("+86", "中国"),
	Hongkong("+852", "香港"),
	Macao("+853", "澳门"),
	Taiwan("+886", "台湾"),
	;

	private String state;
	private String code;

	private AreaCodeEnum(String code, String state) {
		this.state = state;
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public String getCode() {
		return code;
	}

	public static AreaCodeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
