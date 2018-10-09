package com.hq.storeMS.service.buserDevice.bs;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.buser.bs.BUserDataHolder;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buserDevice.apiData.BindDeviceForm;
import com.hq.storeMS.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.storeMS.service.buserDevice.apiData.OperateHistoryQueryForm;
import com.hq.storeMS.service.buserDevice.apiData.UpdateBanding4SnCodeForm;
import com.hq.storeMS.service.buserDevice.data.BUserDevice;
import com.hq.storeMS.service.buserDevice.data.BUserDeviceDAO;
import com.hq.storeMS.service.buserDevice.data.BUserDeviceRestDAO;
import com.hq.storeMS.service.buserDevice.data.MClient;
import com.hq.storeMS.service.buserDevice.data.MClientBandingSystemEnum;
import com.hq.storeMS.service.buserDevice.data.MCtrlLockApiForm;
import com.hq.storeMS.service.buserDevice.data.OperateHistory;
import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

/** 
 * @ClassName: BUserDeviceDataHolder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月27日 上午11:29:12 
 *  
 */
public class BUserDeviceDataHolder {

	final private DataSynType synType = DataSynType.BUserDevice;

	
	public static BUserDeviceDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDeviceDataHolder.class);
	}
	
	public void addAndReturnId(BUserDevice target) {
		BUserDeviceDAO.getInstance().addAndReturnId(target);
	}

	public BUserDevice getByUser(long buserId){
		Criteria criteria = new Criteria();  
		criteria.and("buserId").is(buserId);
		Query query = new Query(criteria);
		BUserDevice buserDevice = BUserDeviceDAO.getInstance().findOne(query);
		return buserDevice;
	}
	
	public void update(BUserDevice target){
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BUserDeviceDAO.getInstance().updpate(target);
	}
	
	public BUserDevice get(long id){
		BUserDevice buserDevice = BUserDeviceDAO.getInstance().get(id);
		return buserDevice;
	}
	
	public List<MClient> findByIds(String ids){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<MClient> data = BUserDeviceRestDAO.getInstance().findByIds(ids);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public MClient getClient(long id){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient mclient = BUserDeviceRestDAO.getInstance().getClient(id);
		AppIdThreadLocal.getInstance().set(null);
		return mclient;
	}
	
	public MClient findByClientId(String clientId){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient mclient = BUserDeviceRestDAO.getInstance().findByClientId(clientId);
		AppIdThreadLocal.getInstance().set(null);
		return mclient;
	}
	
	public MClient findBySnCode(String snCode){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient mclient = BUserDeviceRestDAO.getInstance().findBySnCode(snCode);
		AppIdThreadLocal.getInstance().set(null);
		return mclient;
	}
	
	public List<IotKeyValue> getClientParam(long id){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<IotKeyValue> params = BUserDeviceRestDAO.getInstance().getClientParam(id);
		AppIdThreadLocal.getInstance().set(null);
		return params;
	}
	
	public List<OperateHistory> getOperateHistory(OperateHistoryQueryForm params){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<OperateHistory> list = BUserDeviceRestDAO.getInstance().getOperateHistory(params);
		AppIdThreadLocal.getInstance().set(null);
		return list;
	}
	
	public MClient lockDevice(long id, MCtrlLockApiForm lockForm){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient mclient = BUserDeviceRestDAO.getInstance().lockClient(id,lockForm);
		AppIdThreadLocal.getInstance().set(null);
		return mclient;
	}
	
	public MClient bindDevice(BindDeviceForm bindForm){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		
		UpdateBanding4SnCodeForm updateForm = buildUpdateBandingForm4Bind(bindForm);
		MClient mclient = BUserDeviceRestDAO.getInstance().bindDevice(updateForm);
		
		AppIdThreadLocal.getInstance().set(null);
		return mclient;
	}
	
	public MClient unbindDevice(BindDeviceForm bindForm){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		
		UpdateBanding4SnCodeForm updateForm = buildUpdateBandingForm4Unbind(bindForm);
		MClient mclient = BUserDeviceRestDAO.getInstance().bindDevice(updateForm);
		
		AppIdThreadLocal.getInstance().set(null);
		return mclient;
	}
	
	private UpdateBanding4SnCodeForm buildUpdateBandingForm4Bind(BindDeviceForm bindForm) {
		UpdateBanding4SnCodeForm updateForm = UpdateBanding4SnCodeForm.newInstance();
		updateForm.setSnCode(bindForm.getSnCode());
		BUser buser = BUserDataHolder.getInstance().get(bindForm.getBuserId());
		updateForm.setBandingAccount(buser.getPhone()+"");//iot存电话号码
		updateForm.setBandingSystem(MClientBandingSystemEnum.ZMT.ordinal());
		return updateForm;
	}
	
	private UpdateBanding4SnCodeForm buildUpdateBandingForm4Unbind(BindDeviceForm bindForm) {
		UpdateBanding4SnCodeForm updateForm = UpdateBanding4SnCodeForm.newInstance();
		updateForm.setSnCode(bindForm.getSnCode());
		updateForm.setBandingAccount("");
		updateForm.setBandingSystem(MClientBandingSystemEnum.None.ordinal());
		return updateForm;
	}
	
	public MClient sendClientParam(MCtrlSendParamApiForm sendParamForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient data = BUserDeviceRestDAO.getInstance().sendClientParam(sendParamForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber(id )){
			BUserDevice target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.BUserDevice, "BUserDeviceDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.BUserDevice, "BUserDeviceDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
	
}
