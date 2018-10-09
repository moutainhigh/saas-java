package com.hq.storeMS.service.buserDevice.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buserDevice.apiData.AddStoreDeviceData;
import com.hq.storeMS.service.buserDevice.apiData.BindBUserDeviceData;
import com.hq.storeMS.service.buserDevice.data.BUserDevice;
import com.hq.storeMS.service.buserDevice.data.DeviceInfo;
import com.hq.storeMS.service.buserDevice.data.MClient;
import com.hq.storeMS.service.buserDevice.data.vo.DeviceDetail;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.zenmind.common.beanCopy.FastBeanCopyer;

/**
 * @ClassName: BUserDeviceBeanHelper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年1月30日 下午3:30:02
 * 
 */
public class BUserDeviceBeanHelper {

	public static BUserDeviceBeanHelper getInstance() {
		return new BUserDeviceBeanHelper();
	}

	public void bindBUserDevice(BindBUserDeviceData bindBUserDeviceData) {
		long buserId = bindBUserDeviceData.getBuserId();
		BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(
				buserId);
		
		if (buserDevice == null) {
			addBUserDevice(bindBUserDeviceData);
		} else {
			updateBUserDevice(buserDevice, bindBUserDeviceData);
		}

	}

	//新增
	public void addBUserDevice(BindBUserDeviceData bindBUserDeviceData) {
		BUserDevice buserDevice = BUserDevice.newInstance();
		buserDevice.setBuserId(bindBUserDeviceData.getBuserId());
		
		DeviceInfo deviceInfo = buildDeviceInfo(buserDevice,bindBUserDeviceData);
		buildBUserDevice(buserDevice,deviceInfo,bindBUserDeviceData);
		
		BUserDeviceMgr.getInstance().addAndReturnId(buserDevice);
		
		//BUser 
		setBUserDevice(bindBUserDeviceData.getBuserId());
	}
	
	//修改
	public void updateBUserDevice(BUserDevice buserDevice,
				BindBUserDeviceData bindBUserDeviceData) {

		DeviceInfo deviceInfo = buildDeviceInfo(buserDevice,bindBUserDeviceData);
		buildBUserDevice(buserDevice,deviceInfo,bindBUserDeviceData);
			
		BUserDeviceMgr.getInstance().update(buserDevice);
	}
	
	
	private DeviceInfo buildDeviceInfo(BUserDevice buserDevice,BindBUserDeviceData bindBUserDeviceData){
		DeviceInfo deviceInfo = DeviceInfo.newInstance();
		deviceInfo.setId(buserDevice.getIndex() + 1);
		deviceInfo.setBuserId(bindBUserDeviceData.getBuserId());
		deviceInfo.setIotRecordId(bindBUserDeviceData.getIotRecordId());
		return deviceInfo;
	}
	
	private void buildBUserDevice(BUserDevice buserDevice,DeviceInfo deviceInfo,BindBUserDeviceData bindBUserDeviceData){
		buserDevice.getDeviceList().add(deviceInfo);
		buserDevice.setIndex(deviceInfo.getId());
		buserDevice.setSalesman(bindBUserDeviceData.getSalesman());
		buserDevice.setSalesmanPhone(bindBUserDeviceData.getSalesmanPhone());
	}
	
	//设置BUser buserDeviceId
	private void setBUserDevice(long  buserId){
		BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserId);
		if(buserDevice!=null){
			BUser buser = (BUser) BUserQueryMgr.getInstance().getSimple(buserId);
			buser.setBuserDeviceId(buserDevice.getId());
			BUserModifyMgr.getInstance().update(buser);
		}
	}
	
	
	
	public boolean addStoreDevice(long id,
			AddStoreDeviceData addStoreDeviceData) {
		boolean success = false;

		long buserId = addStoreDeviceData.getBuserId();
		long deviceId = id;
		long storeId = addStoreDeviceData.getStoreId();

		BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserId);
		if (buserDevice != null) {
			List<DeviceInfo> deviceInfoList = buserDevice.getDeviceList();

			if (deviceInfoList.size() > 0) {
				for (DeviceInfo deviceInfo : deviceInfoList) {
					if (deviceId == deviceInfo.getId()) {
						deviceInfo.setStoreId(storeId);
						break;
					}
				}
			}
			
			BUserDeviceMgr.getInstance().update(buserDevice);
			success = true;
		}

		return success;
	}

	public List<MClient> getList(BUserDevice buserDevice) {
		List<Long> idArr = new ArrayList<Long>();

		for (DeviceInfo deviceInfo : buserDevice.getDeviceList()) {
			idArr.add(deviceInfo.getIotRecordId());
		}
		String ids = StringUtils.join(idArr, ",");

		List<MClient> clientList = BUserDeviceMgr.getInstance().findByIds(ids);

		return clientList;
	}
	
	/**
	 * 返回组装后的仪器对象列表
	 * 两行FastBeanCopyer代码一定不可交换位置 ，涉及id的值
	 * */
	public List<DeviceDetail> getDeviceDetailList(BUserDevice buserDevice) {
		List<DeviceDetail> list = new ArrayList<DeviceDetail>();
		List<Long> iotRecordIds = getIotRecordIds(buserDevice);

		String ids = StringUtils.join(iotRecordIds,",");
		List<MClient> clientList = BUserDeviceMgr.getInstance().findByIds(ids);
		
		Map<Long,MClient> clientMap = buildClientMap(clientList);
		
		for (DeviceInfo deviceInfo : buserDevice.getDeviceList()) {
			DeviceDetail devcieDetail = DeviceDetail.newInstance();
			
			if(ArrayUtils.contains(clientMap.keySet().toArray(), deviceInfo.getIotRecordId())){
				MClient mclient = clientMap.get(deviceInfo.getIotRecordId());
				FastBeanCopyer.getInstance().copy(mclient, devcieDetail);
				FastBeanCopyer.getInstance().copy(deviceInfo, devcieDetail);
			}
			
			if(deviceInfo.getStoreId()!=0){
				StoreRO store = StoreMgr.getInstance().getReadOnly(deviceInfo.getStoreId());
				if(store != null) {
					devcieDetail.setStoreName(store.getName());
				}
			}
			
			list.add(devcieDetail);
		}
		return list;
	}
	
	private List<Long> getIotRecordIds(BUserDevice buserDevice){
		List<Long> iotRecordIds = new ArrayList<Long>();
		
		for (DeviceInfo deviceInfo : buserDevice.getDeviceList()) {
			iotRecordIds.add(deviceInfo.getIotRecordId());
		}
		
		return iotRecordIds;
	}
	
	private Map<Long,MClient> buildClientMap(List<MClient> clientList){
		Map<Long,MClient> clientMap = new HashMap<Long,MClient>();
		
		for(MClient mclient:clientList){
			clientMap.put(mclient.getId(), mclient);
		}
		
		return clientMap;
	}
	
	
//	public List<DeviceDetail> getDeviceDetailList1(BUserDevice buserDevice) {
//		List<DeviceDetail> list = new ArrayList<DeviceDetail>();
//		
//			for (DeviceInfo deviceInfo : buserDevice.getDeviceList()) {
//				DeviceDetail devcieDetail = DeviceDetail.newInstance();
//				MClient mclient = null;
//				
//				try{
//					mclient = BUserDeviceMgr.getInstance().getClient(deviceInfo.getIotRecordId());
//				}catch(Exception e){
//					if (e instanceof RestProxyException) {
//						RestProxyException errorTmp = (RestProxyException) e;
//						if(errorTmp.getStatusCode()==404){
//							mclient = MClient.newInstance();
//						}
//					}
//				}
//				
//				//mclient来自设备后台的仪器数据
//				FastBeanCopyer.getInstance().copy(mclient, devcieDetail);
//				//deviceInfo来自storeMs的仪器数据
//				FastBeanCopyer.getInstance().copy(deviceInfo, devcieDetail);
//				
//				if(deviceInfo.getStoreId()!=0){
//					StoreRO store = StoreMgr.getInstance().getReadOnly(deviceInfo.getStoreId());
//					devcieDetail.setStoreName(store.getName());
//				}
//				
//				list.add(devcieDetail);
//			}
//			
//		return list;
//	}
	

}
