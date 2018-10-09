package com.hq.chainStore.service.euser.apiData;


/** 
 * @ClassName: EUserUpdateType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月19日 下午3:32:05 
 *  
 */
public enum EUserUpdateType {

	updateCount("修改体验用户体验次数"),
	;
	
	private String descript;
	
	private EUserUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public static EUserUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

}
