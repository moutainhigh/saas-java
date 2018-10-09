package com.hq.storeManagerMS.service.mngDevice.data.buserDevice;

import com.zenmind.dataSyn.annotation.SynClass;

/** 
 * @ClassName: DeviceDetail 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年2月2日 下午1:41:32 
 *  
 */
@SynClass
public class DeviceDetail {

	private long id;
		
	private long iotRecordId;//设备后台记录ID;
		
	private long buserId;
	
	private long storeId;
	
	private String storeName;
	
	//设备id
	private String clientId;
	//设备系列号
	private String mseriesId;
	//设备系列名称(预留，不存值)
	private String mseriesName;
	//设备型号id
	private String mtypeId;
	//设备型号名称(预留，不存值)
	private String mtypeName;
	//SN
	private String snCode;
	//IMSI码
	private String imsiCode;
	//密钥
	private String secretKey;
	
	//控制状态 MClientCtrlStateEnum
	private int ctrlState;

	//绑定系统 MClientBandingSystemEnum
	private int bandingSystem;
	
	//绑定账户
	private String bandingAccount;
	
	//状态 MClientStatusEnum
	private int status;
	//绑定时间
	private long bandingTime;

	
	public static DeviceDetail newInstance(){
		return new DeviceDetail();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIotRecordId() {
		return iotRecordId;
	}

	public void setIotRecordId(long iotRecordId) {
		this.iotRecordId = iotRecordId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getCtrlState() {
		return ctrlState;
	}

	public void setCtrlState(int ctrlState) {
		this.ctrlState = ctrlState;
	}

	public long getBandingTime() {
		return bandingTime;
	}

	public void setBandingTime(long bandingTime) {
		this.bandingTime = bandingTime;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMseriesId() {
		return mseriesId;
	}

	public void setMseriesId(String mseriesId) {
		this.mseriesId = mseriesId;
	}

	public String getMseriesName() {
		return mseriesName;
	}

	public void setMseriesName(String mseriesName) {
		this.mseriesName = mseriesName;
	}

	public String getMtypeId() {
		return mtypeId;
	}

	public void setMtypeId(String mtypeId) {
		this.mtypeId = mtypeId;
	}

	public String getMtypeName() {
		return mtypeName;
	}

	public void setMtypeName(String mtypeName) {
		this.mtypeName = mtypeName;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String getImsiCode() {
		return imsiCode;
	}

	public void setImsiCode(String imsiCode) {
		this.imsiCode = imsiCode;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getBandingSystem() {
		return bandingSystem;
	}

	public void setBandingSystem(int bandingSystem) {
		this.bandingSystem = bandingSystem;
	}

	public String getBandingAccount() {
		return bandingAccount;
	}

	public void setBandingAccount(String bandingAccount) {
		this.bandingAccount = bandingAccount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
