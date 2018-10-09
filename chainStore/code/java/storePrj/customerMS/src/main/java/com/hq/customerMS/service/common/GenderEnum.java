package com.hq.customerMS.service.common;

/**
 * 
 * ClassName: GenderEnum <br/>  
 * Function: TODO 性别枚举类. <br/>  
 *  
 * @author kevin 
 * @version   
 * @since JDK 1.6
 */
public enum GenderEnum {
	EMPTY(""),   //空
	Men("男"),  //男
	Women("女"),  //女
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
