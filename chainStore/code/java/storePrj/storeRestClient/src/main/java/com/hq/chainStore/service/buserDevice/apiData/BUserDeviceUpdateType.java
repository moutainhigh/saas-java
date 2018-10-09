package com.hq.chainStore.service.buserDevice.apiData;


/** 
 * @ClassName: BUserDeviceUpdateType 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 下午5:25:53 
 *  
 */
public enum BUserDeviceUpdateType {

	AddStoreDevice("分配到店"), 
	;
	
	private String descript;
	
	private BUserDeviceUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public static BUserDeviceUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
