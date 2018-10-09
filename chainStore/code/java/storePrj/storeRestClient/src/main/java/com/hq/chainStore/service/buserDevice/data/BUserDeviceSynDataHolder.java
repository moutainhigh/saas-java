package com.hq.chainStore.service.buserDevice.data;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

/** 
 * @ClassName: BUserDeviceDataHolder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 上午11:29:12 
 *  
 */
public class BUserDeviceSynDataHolder extends AbsDataSynDataHolder<BUserDevice>{

	final private DataSynType synType = DataSynType.BUserDevice;

	public static BUserDeviceSynDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDeviceSynDataHolder.class);
	}

	public DataSynType getSynType() {
		return synType;
	}

	protected Class<BUserDevice> getClazz() {
		return BUserDevice.class;
	}

	protected RestDao<BUserDevice> getDao() {
		return BUserDeviceDAO.getInstance();
	}


	
}
