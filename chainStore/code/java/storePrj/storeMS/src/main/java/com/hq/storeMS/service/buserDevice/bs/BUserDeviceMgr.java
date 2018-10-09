package com.hq.storeMS.service.buserDevice.bs;

import java.util.List;

import com.hq.storeMS.service.buserDevice.apiData.BindDeviceForm;
import com.hq.storeMS.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.storeMS.service.buserDevice.apiData.OperateHistoryQueryForm;
import com.hq.storeMS.service.buserDevice.data.BUserDevice;
import com.hq.storeMS.service.buserDevice.data.MClient;
import com.hq.storeMS.service.buserDevice.data.MCtrlLockApiForm;
import com.hq.storeMS.service.buserDevice.data.OperateHistory;
import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;
import com.zenmind.common.hotSwap.HotSwap;

/** 
 * @ClassName: BUserDeviceMgr 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 上午11:29:02 
 *  
 */
public class BUserDeviceMgr {

	public static BUserDeviceMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDeviceMgr.class);
	}
	
	public void addAndReturnId(BUserDevice target) {
		BUserDeviceDataHolder.getInstance().addAndReturnId(target);
	}
	
	public BUserDevice get(long id) {
		BUserDevice buserDevice = BUserDeviceDataHolder.getInstance().get(id);
		return buserDevice;
	}
	
	public List<MClient> findByIds(String ids){
		return BUserDeviceDataHolder.getInstance().findByIds(ids);
	}
	
	public MClient getClient(long id){
		return BUserDeviceDataHolder.getInstance().getClient(id);
	}
	
	public MClient findByClientId(String clientId){
		return BUserDeviceDataHolder.getInstance().findByClientId(clientId);
	}
	
	public MClient findBySnCode(String snCode){
		return BUserDeviceDataHolder.getInstance().findBySnCode(snCode);
	}
	
	public List<IotKeyValue> getClientParam(long id){
		return BUserDeviceDataHolder.getInstance().getClientParam(id);
	}
	
	public List<OperateHistory> getOperateHistory(OperateHistoryQueryForm params){
		return BUserDeviceDataHolder.getInstance().getOperateHistory(params);
	}
	
	public MClient lockDevice(long id, MCtrlLockApiForm lockForm){
		return BUserDeviceDataHolder.getInstance().lockDevice(id,lockForm);
	}
	
	public MClient bindDevice(BindDeviceForm bindForm){
		MClient mClient = BUserDeviceDataHolder.getInstance().bindDevice(bindForm);
		return mClient;
	}
	
	public MClient unbindDevice(BindDeviceForm bindForm){
		MClient mClient = BUserDeviceDataHolder.getInstance().unbindDevice(bindForm);
		return mClient;
	}
	
	public BUserDevice getByUser(long buserId) {
		BUserDevice buserDevice = BUserDeviceDataHolder.getInstance().getByUser(buserId);
		return buserDevice;
	}
	
	public void update(BUserDevice target){
		BUserDeviceDataHolder.getInstance().update(target);
	}
	
	public MClient sendClientParam(MCtrlSendParamApiForm sendParamForm) {
		return BUserDeviceDataHolder.getInstance().sendClientParam(sendParamForm);
	}

}
