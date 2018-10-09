package com.hq.storeMS.service.buserDevice.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

/** 
 * @ClassName: BUserDeviceRedisDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 上午11:07:31 
 *  
 */
public class BUserDeviceRedisDAO extends RedisDao<BUserDevice>{
	
	public static BUserDeviceRedisDAO getInstance(){
		
		return HotSwap.getInstance().getSingleton(BUserDeviceRedisDAO.class);
	}

}
