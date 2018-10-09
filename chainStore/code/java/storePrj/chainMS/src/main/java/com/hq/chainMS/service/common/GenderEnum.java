package com.hq.chainMS.service.common;

public enum GenderEnum {

	EMPTY(""),   //空
	MALE("男"),  //男
	FEMALE("女"),  //女
	;
	private String gender;
	
	private GenderEnum(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public static GenderEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
