package com.hq.storeClient.service.common;

public enum GenderEnum {
	EMPTY(""), 
	MALE("男"), 
	FEMALE("女"),
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
