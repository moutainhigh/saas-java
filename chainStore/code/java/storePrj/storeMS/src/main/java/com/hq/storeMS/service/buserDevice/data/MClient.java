package com.hq.storeMS.service.buserDevice.data;

import javax.persistence.Id;


import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

/**
 * @Description: 设备  
 * @author: wujunwei 
 * @date: 2018年1月11日  
 * @version: v1.0
 */
@SynClass
public class MClient {

	@Id
	private long id;
	//设备id
	private String clientId;
	//设备系列号
	private String mseriesId;
	//设备系列名称
	private String mseriesName;
	//设备型号id
	private String mtypeId;
	//设备型号名称
	private String mtypeName;
	//设备型号图片
	private String mtypeImg;
	//SN
	private String snCode;
	//IMSI码
	private String imsiCode;
	//密钥
	private String secretKey;
	//绑定系统 MClientBandingSystemEnum
	private int bandingSystem;
	//绑定账户
	private String bandingAccount;
	//是否激活(绑定就是激活) MClientIsActivatedEnum
	private int isActivated;
	//定位信息
	private String location;
	//定位经纬度
	private String locationGps;
	//定位地图url
	private String locationUrl;
	
	//联网状态 MClientStatusEnum
	private int status;
	//控制状态 MClientCtrlStateEnum
	private int ctrlState;
	//锁定状态 MClientLockStateEnum
	private int lockState;
	/*
	 * 锁定信号量
	 * 当发送锁定指令时，先用lockSignal来标记是否厂家锁定；当设备回传已锁定信息时，再根据此标记来更新lockState 
	 * MClientLockStateEnum
	 */
	@SynIgnoreField
	private int lockSignal; 
	
	//绑定时间
	private long bandingTime;
	//创建时间
	private long createdTime;
	//最后修改时间
	private long lastUpdateTime;
	//版本
	private int ver;

	public static MClient newInstance() {
		MClient mClient = new MClient();
		mClient.status = MClientStatusEnum.Offline.ordinal();
		mClient.ctrlState = MClientCtrlStateEnum.Poweroff.ordinal();
		mClient.lockState = MClientLockStateEnum.unLocked.ordinal();
		mClient.lockSignal = MClientLockStateEnum.unLocked.ordinal();
		mClient.bandingSystem = MClientBandingSystemEnum.None.ordinal();  
		mClient.isActivated = MClientIsActivatedEnum.False.ordinal();
		long curTime = System.currentTimeMillis();
		mClient.createdTime = curTime;
		mClient.lastUpdateTime = curTime;
		return mClient;
	}
	
	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getMtypeId() {
		return mtypeId;
	}

	public void setMtypeId(String mtypeId) {
		this.mtypeId = mtypeId;
	}
	
	public String getMseriesName() {
		return mseriesName;
	}

	public void setMseriesName(String mseriesName) {
		this.mseriesName = mseriesName;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
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

	public String getLocationGps() {
		return locationGps;
	}

	public void setLocationGps(String locationGps) {
		this.locationGps = locationGps;
	}

	public String getLocationUrl() {
		return locationUrl;
	}

	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}

	public int getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(int isActivated) {
		this.isActivated = isActivated;
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

	public String getMtypeImg() {
		return mtypeImg;
	}

	public void setMtypeImg(String mtypeImg) {
		this.mtypeImg = mtypeImg;
	}

	public int getLockState() {
		return lockState;
	}

	public void setLockState(int lockState) {
		this.lockState = lockState;
	}

	public int getLockSignal() {
		return lockSignal;
	}

	public void setLockSignal(int lockSignal) {
		this.lockSignal = lockSignal;
	}
	
}