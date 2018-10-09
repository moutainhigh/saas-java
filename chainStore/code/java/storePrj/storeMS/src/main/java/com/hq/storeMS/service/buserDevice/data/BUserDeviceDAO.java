package com.hq.storeMS.service.buserDevice.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

/** 
 * @ClassName: BUserDeviceDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 上午11:04:58 
 *  
 */
public class BUserDeviceDAO extends MongodbDao<BUserDevice> {
	
	public static BUserDeviceDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDeviceDAO.class);
	}
	
}
