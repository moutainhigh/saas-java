package com.hq.storeMS.service.buser.apiData;

public enum LoginCodeEnum {
	Success("登录成功"),
	UnKonw("未知原因"),
	JsCodeEmpty("jscode为空"),
	UnionIdEmpty("unionId为空"),
	UserNotExists("用户不存在"),
	;
	
	private String descript;
	
	private LoginCodeEnum(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static LoginCodeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
