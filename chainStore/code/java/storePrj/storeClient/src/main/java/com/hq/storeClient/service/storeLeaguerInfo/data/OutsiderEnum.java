package com.hq.storeClient.service.storeLeaguerInfo.data;

import com.zenmind.common.StringFormatUtil;

public enum OutsiderEnum {
	Boy("{}_01", "散客男", 1, "img/logo/leaguer/ic_sanke_nan.png"), 
	Girl("{}_02", "散客女", 2, "img/logo/leaguer/ic_sanke_nv.png"),
	;

	private String id;
	private String name;
	private int sex;
	private String headImg;

	private OutsiderEnum(String idP, String nameP, int sexP, String headImgP) {
		this.name = nameP;
		this.id = idP;
		this.sex = sexP;
		this.headImg = headImgP;
	}

	public static OutsiderEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getId(long storeId) {
		return StringFormatUtil.format(id, storeId);
	}

	public String getName() {
		return name;
	}

	public int getSex() {
		return sex;
	}

	public String getHeadImg() {
		return headImg;
	}
}
