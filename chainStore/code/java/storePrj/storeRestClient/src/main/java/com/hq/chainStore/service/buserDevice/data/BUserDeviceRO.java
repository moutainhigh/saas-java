package com.hq.chainStore.service.buserDevice.data;

import java.util.List;

/** 
 * @ClassName: BUserDeviceData 
 * @Description: 用户绑定仪器 实体
 * @author helen 
 * @date 2018年1月27日 上午9:55:42 
 *  
 */

public interface BUserDeviceRO{

	
	public long getId();
	
	public long getBuserId();

	public List<DeviceInfo> getDeviceList();
	
	public long getCreateTime() ;

	public void setCreateTime(long createTime);

	public Integer getVer();
	
	
	
}
