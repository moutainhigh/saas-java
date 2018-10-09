package com.hq.chainStore.service.buserDevice.bs;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.buserDevice.apiData.AddStoreDeviceData;
import com.hq.chainStore.service.buserDevice.apiData.BindDeviceForm;
import com.hq.chainStore.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.chainStore.service.buserDevice.data.BUserDevice;
import com.hq.chainStore.service.buserDevice.data.MClient;
import com.hq.chainStore.service.buserDevice.data.MCtrlLockApiForm;
import com.hq.chainStore.service.buserDevice.data.OperateHistory;
import com.hq.chainStore.service.buserDevice.data.vo.DeviceDetail;
import com.hq.chainStore.service.buserDevice.data.vo.IotKeyValue;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.dao.rest.RestResp;

/** 
 * @ClassName: BUserDeviceMgr 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月29日 上午11:22:54 
 *  
 */
public class BUserDeviceMgrTest {
	
	private static Boss boss;
	private static long buserId;
	private static long id;
	
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		buserId = 12L;
		id = 17;
	}

	/********BUserDevice*********/
	
	@Test
	public void testGetByBUser() {
				
		BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserId);
		
		System.out.println("buserDevice  "+buserDevice.toString());
		
	}
	
	@Test
	public void testGet() {
		
		BUserDevice buserDevice = BUserDeviceMgr.getInstance().get(id);
		System.out.println("buserDevice  "+buserDevice.toString());
	}
	
	@Test
	public void testBindBUserDevcie() {
		
		BindDeviceForm bindForm = BindDeviceForm.newInstance();
		bindForm.setBuserId(buserId);
		bindForm.setSnCode("BD1704003451");//id = 5
		RestResp restResp = BUserDeviceMgr.getInstance().bindDevice(bindForm);
		System.out.println(" code  "+restResp.getCode());
	}
	
	@Test
	public void testAddStoreDevcie() {
		BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserId);
		System.out.println(" before  "+buserDevice.toString());

		long id = 2;
		
		AddStoreDeviceData addStoreDeviceData = AddStoreDeviceData.newInstance();
		addStoreDeviceData.setBuserId(buserId);
		addStoreDeviceData.setStoreId(15);	
		BUserDeviceMgr.getInstance().addStoreDevcie(id,addStoreDeviceData);
			
		
		BUserDevice buserDevice2 = BUserDeviceMgr.getInstance().getByUser(buserId);
		System.out.println(" after  "+buserDevice2.toString());
	}
	
	

	
	@Test
	public void testLock() {
		long iotRecordId = 1;
		MClient mclient =  MClientMgr.getInstance().getMClient(iotRecordId);
		System.out.println("BEFOR  "+mclient.getCtrlState());
		
		
		MCtrlLockApiForm mctrlLockApiForm = MCtrlLockApiForm.newInstance();
		mctrlLockApiForm.setLock(true);
		mctrlLockApiForm.setSupplier(false);
		RestResp restResp = BUserDeviceMgr.getInstance().lock(iotRecordId, mctrlLockApiForm);
		System.out.println("code  "+restResp.getCode());
			
		MClient mclient2 =  MClientMgr.getInstance().getMClient(iotRecordId);
		System.out.println("AFTER  "+mclient2.getCtrlState());
	}
	
	@Test
	public void testUnLock() {
		long iotRecordId = 1;
		MClient mclient =  MClientMgr.getInstance().getMClient(iotRecordId);
		System.out.println("BEFOR  "+mclient.getCtrlState());
		
		
		MCtrlLockApiForm mctrlLockApiForm = MCtrlLockApiForm.newInstance();
		mctrlLockApiForm.setLock(false);
		mctrlLockApiForm.setSupplier(false);
		RestResp restResp = BUserDeviceMgr.getInstance().lock(iotRecordId, mctrlLockApiForm);
		System.out.println("code  "+restResp.getCode());
			
		MClient mclient2 =  MClientMgr.getInstance().getMClient(iotRecordId);
		System.out.println("AFTER  "+mclient2.getCtrlState());
	}
	
	/********DeviceDetail*********/
	
	@Test
	public void testGetList() {
		
		List<DeviceDetail> list = DeviceDetailMgr.getInstance().getList(buserId);
		
		System.out.println(list.size());
			
	}
	
	
	
	
	/********MClient*********/
	
	@Test
	public void testGetMClient() {
		long iotRecordId = 1;
		
		MClient mclient =  MClientMgr.getInstance().getMClient(iotRecordId);
		
		System.out.println("mclient  "+mclient.toString());
			
	}
	
	@Test
	public void testGetByMClientId() {
		
		String clientId = "D8B04CB31723";
		
		MClient mclient = MClientMgr.getInstance().findByClientId(clientId);
		
		System.out.println("mclient  "+mclient.toString());
			
	}
	
	@Test
	public void testSendMClientParam() {
		
		String clientId = "D8B04CB31721";
		IotKeyValue obj1 = IotKeyValue.getInstance();
		obj1.setKey("1");
		obj1.setValue("1");
		IotKeyValue obj2 = IotKeyValue.getInstance();
		obj1.setKey("2");
		obj1.setValue("1");
		IotKeyValue obj3 = IotKeyValue.getInstance();
		obj1.setKey("3");
		obj1.setValue("2");
		
		List<IotKeyValue> originParams = new ArrayList<IotKeyValue>();
		originParams.add(obj1);
		originParams.add(obj2);
		originParams.add(obj3);

		MCtrlSendParamApiForm sendParamForm = MCtrlSendParamApiForm.newInstance();
		sendParamForm.setClientId(clientId);
		sendParamForm.setOriginParams(originParams);
		MClient mclient = MClientMgr.getInstance().sendClientParam(sendParamForm);
		
		System.out.println("mclient  "+mclient.toString());
			
	}
	
	/********IotKeyValue*********/
	@Test
	public void testGetParams() {
		
		long iotRecordId = 30;
		
		List<IotKeyValue> params = DeviceParamMgr.getInstance().getParams(iotRecordId);
		
		System.out.println("params  "+params.toString());
			
	}
	
	/********OperateHistory*********/
	@Test
	public void testGetOperateHistory() {
		
		String  clientId = "D8B04CB3160D";
		String  createdDate = "2018-02";
		List<OperateHistory> oh = DeviceOperateHistoryMgr.getInstance().getOperateHistory(clientId, createdDate);
		
		System.out.println("OperateHistory: "+oh.size());
			
	}

}
