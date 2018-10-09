package com.hq.chainMS.service.sysDataInit.data;

public enum SysInitAreaCodeEnum {
	China("+86", "中国", "China"),
	America("+1", "美国", "America"),
	Canada("+1", "加拿大", "Canada"),
	Australia("+61", "澳大利亚", "Australia"),
	Hongkong("+852", "中国香港", "Hongkong"),
	Macao("+853", "中国澳门", "Macao"),
	Taiwan("+886", "中国台湾", "Taiwan"),
	NewZealand("+64", "新西兰", "NewZealand"),
	;

	private String areaCode;
	private String countryCh;
	private String countryEn;

	private SysInitAreaCodeEnum(String areaCodeP, String ch, String en) {
		this.areaCode = areaCodeP;
		this.countryCh = ch;
		this.countryEn = en;
	}

	public static SysInitAreaCodeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getCountryCh() {
		return countryCh;
	}

	public String getCountryEn() {
		return countryEn;
	}

}
