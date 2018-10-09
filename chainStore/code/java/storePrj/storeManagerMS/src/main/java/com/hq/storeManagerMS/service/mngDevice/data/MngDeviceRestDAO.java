package com.hq.storeManagerMS.service.mngDevice.data;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeClient.service.buser.data.BUserCount;
import com.hq.storeManagerMS.common.config.StoreMngMSCfgMgr;
import com.hq.storeManagerMS.service.mngDevice.apiData.MngDeviceQueryForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BUserQueryApiForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BindDeviceForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.mctrl.MCtrlLockApiForm;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserBindInfo;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserDevice;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.DeviceDetail;
import com.hq.storeManagerMS.service.mngDevice.data.mclient.MClient;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class MngDeviceRestDAO extends RestDao<DeviceDetail> {
	
	public static MngDeviceRestDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MngDeviceRestDAO.class);
	}
	//多个请求才能返回需要的结果
	private final String table = "deviceDetail";
	private final String buserTable = "buser";
	private final String buserDeviceTable = "buserDevice";
	private final String mclientTable = "mclient";
	private final String mngDeviceTable = "mngDevice";
	
	public List<DeviceDetail> findDeviceDetailList(MngDeviceQueryForm params){
		List<DeviceDetail> list = Collections.emptyList();
		try {
			String findPath = "getList" ;
			String bandingAccount = params.getBandingAccount();
			String snCode = params.getSnCode();
			String buserId = "";
			BUser buser = null;
			if(StringUtils.isNotBlank(bandingAccount)) {
				buser = getBUserByBandingAccount(bandingAccount);
			}else if(StringUtils.isNotBlank(snCode)){
				buser = getBUserBySnCode(snCode);
			}
			if(buser != null) {
				buserId = String.valueOf(buser.getId());
			}
			if(StringUtils.isBlank(buserId)) {
				return list;
			}
			final String uriPattern = "{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table,findPath,buserId);
			list = (List<DeviceDetail>) RestProxy.getInstance().list(uri, DeviceDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = table;
			throw(RestDaoException.newInstance("MngDeviceRestDAO.findDeviceDetailList()","", tableName, e));
		}
		return list;
	}
	
	private BUser getBUserByBandingAccount(String bandingAccount){
		BUser buser = null;
		String findPath = "findByPhone";
		ReqMap reqMap = new ReqMap();
		reqMap.add("phone", bandingAccount);
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), buserTable,findPath,reqParam);
			buser = (BUser) RestProxy.getInstance().get(uri, BUser.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MngDeviceRestDAO.getBUserByBandingAccount()","", buserTable, e));
		}
		return buser;
	}
	
	private BUser getBUserBySnCode(String snCode){
		MClient mclient = getMClientBySnCode(snCode);
		if(mclient == null || StringUtils.isBlank(mclient.getBandingAccount())) {
			return null;
		}
		BUser buser = getBUserByBandingAccount(mclient.getBandingAccount());
		return buser;
	}
	
	public MClient getMClientBySnCode(String snCode){
		String findPath = "findList";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("snCode", snCode);
		reqMap.add("pageItemCount", "1");
		reqMap.add("pageNo", "1");
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getIotService(), mclientTable,findPath,reqParam);
			List<MClient> list =  (List<MClient>) RestProxy.getInstance().list(uri, MClient.class);
			return (list != null && list.size() > 0) ?  list.get(0) : null;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MngDeviceRestDAO.getMClientBySnCode()","", mclientTable, e));
		}
	}
	
	
	public MClient getClient(long id) {
		MClient target  = null;
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table,id);
			target = (MClient)RestProxy.getInstance().get(uri, MClient.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = table;
			throw(RestDaoException.newInstance("MngDeviceRestDAO.getClient()", tableName, id+"", e));
		}
		return target;
	}
	
	public MClient lockClient(long id,MCtrlLockApiForm lockForm) {
		MClient target = null;
		try {
			String path = "lock/" + id;
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), buserDeviceTable,path);
			target = (MClient)RestProxy.getInstance().add(uri, lockForm,MClient.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MngDeviceRestDAO.lockClient()", buserDeviceTable, id+"", e));
		}
		return target;
	}
	
	/**
	 * 
	 * @param bindForm
	 * @param actionName 绑定:binding 解绑 undinding
	 * @return
	 */
	public BUserDevice bindDevice(BindDeviceForm bindForm, String actionName) {
		//String actionName = "binding";
		BUserDevice target = null;
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), buserDeviceTable,actionName);
			target = RestProxy.getInstance().add(uri,bindForm,BUserDevice.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MngDeviceRestDAO.bindDevice()", buserDeviceTable, "", e));
		}
		return target;
	}
	
	
	public List<BUserBindInfo> findBUserBindInfoList(BUserQueryApiForm params){
		try {
			String findPath = "findBUserBindInfoList" ;
			final String uriPattern = "{}/{}/{}?{}";
			ReqMap reqMap = ReqMap.newInstance();
			reqMap.add("buserPhone", params.getBuserPhone());
			reqMap.add("pageItemCount", params.getPageItemCount());
			reqMap.add("pageNo", params.getPageNo());
			String uri = StringFormatUtil.format(uriPattern, getService(), mngDeviceTable,findPath,reqMap.toReqParam());
			List<BUserBindInfo> list =  (List<BUserBindInfo>) RestProxy.getInstance().list(uri, BUserBindInfo.class);
			return list;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MngDeviceRestDAO.findBUserBindInfoList()","", mngDeviceTable, e));
		}
	}
	
	public BUserCount getBUserBindInfoCount(BUserQueryApiForm params){
		try {
			String findPath="getBUserBindInfoCount";
			final String uriPattern = "{}/{}/{}?{}";
			ReqMap reqMap = ReqMap.newInstance();
			reqMap.add("buserPhone", params.getBuserPhone());
			String uri = StringFormatUtil.format(uriPattern, getService(), mngDeviceTable, findPath, reqMap.toReqParam());
			BUserCount buserCount = RestProxy.getInstance().get(uri, BUserCount.class);
			return buserCount;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("MngDeviceRestDAO.getBUserBindInfoCount()", "", mngDeviceTable, e));
		}
	}
	

	@Override
	public String getService() {
		return StoreMngMSCfgMgr.getProp().getStoreMSHost();
	}
	
	public String getIotService() {
		return StoreMngMSCfgMgr.getProp().getIotMSHost();
	}



	
}
