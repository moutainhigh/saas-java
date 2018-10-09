package com.hq.storeMS.service.buserDevice.data;

import java.util.List;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.storeMS.service.buserDevice.apiData.OperateHistoryQueryForm;
import com.hq.storeMS.service.buserDevice.apiData.UpdateBanding4SnCodeForm;
import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

/** 
 * @ClassName: BUserDeviceRestDAO 
 * @Description: 调用设备后台接口
 * @author helen 
 * @date 2018年1月27日 上午11:04:58 
 *  
 */
public class BUserDeviceRestDAO extends RestDao<BUserDevice> {
	
	public static BUserDeviceRestDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDeviceRestDAO.class);
	}

	private final String table = "mclient";
	private final String ctrlName = "mctrl";
	
	
	/**************************mctrl*******************************/
	public List<IotKeyValue> getClientParam(long id){
		String findPath = id+"/param";
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), ctrlName,findPath);	
			return (List<IotKeyValue>) RestProxy.getInstance().list(uri, IotKeyValue.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = ctrlName;
			throw(RestDaoException.newInstance("RestDao.list()","", tableName, e));
		}
	}
	
	public MClient sendClientParam(MCtrlSendParamApiForm sendParamForm){
		String findPath = "sendParam";
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), ctrlName,findPath);	
			RestResp restResp = RestProxy.getInstance().rawReq(uri,sendParamForm);
			MClient mclient = JsonUtil.getInstance().fromJson(restResp.gettJson(), MClient.class);
			return mclient;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = ctrlName;
			throw(RestDaoException.newInstance("RestDao.add()","", tableName, e));
		}
	}
	
	public List<OperateHistory> getOperateHistory(OperateHistoryQueryForm params){
		String findPath = "/operateHistory";
		ReqMap reqMap = new ReqMap();
		reqMap.add("clientId", params.getClientId());
		reqMap.add("createdDate", params.getCreatedDate());
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), ctrlName,findPath,reqParam);
			return (List<OperateHistory>) RestProxy.getInstance().list(uri, OperateHistory.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = ctrlName;
			throw(RestDaoException.newInstance("RestDao.list()","", tableName, e));
		}
	}
	
	public MClient lockClient(long id,MCtrlLockApiForm lockForm) {
		String path = id+"/lock";
		MClient target  = null;
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), ctrlName,path);
			target = (MClient)RestProxy.getInstance().update(uri, lockForm,MClient.class);
			
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = ctrlName;
			throw(RestDaoException.newInstance("RestDao.update()", tableName, id+"", e));
		}
		return target;
	}
	
	/************************mclient********************************/
	
	public List<MClient> findByIds(String ids){
		String findPath = "findByIds";
		ReqMap reqMap = new ReqMap();
		reqMap.add("ids", ids);

		try {
			final String uriPattern = "{}/{}/{}?{}";

			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), table,findPath,reqParam);
			
			return (List<MClient>) RestProxy.getInstance().list(uri, MClient.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			String tableName = table;
			throw(RestDaoException.newInstance("RestDao.list()","", tableName, e));
		}
	}
	
	public MClient findByClientId(String clientId) {
		String findPath = "findByClientId";
		ReqMap reqMap = new ReqMap();
		reqMap.add("clientId", clientId);
		MClient target  = null;
		
		try {
			//http://{sesrviceId}/{table}/{uriPath}/{param}
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), table,findPath,reqParam);
			target = (MClient)RestProxy.getInstance().get(uri, MClient.class);
			
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			String tableName = table;
			throw(RestDaoException.newInstance("RestDao.get()", tableName, findPath, e));
		}
		return target;
	}
	
	public MClient findBySnCode(String snCode) {
		String findPath = "findBySnCode";
		ReqMap reqMap = new ReqMap();
		reqMap.add("snCode", snCode);
		MClient target  = null;
		
		try {
			//http://{sesrviceId}/{table}/{uriPath}/{param}
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), table,findPath,reqParam);
			target = (MClient)RestProxy.getInstance().get(uri, MClient.class);
			
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			String tableName = table;
			throw(RestDaoException.newInstance("RestDao.get()", tableName, findPath, e));
		}
		return target;
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
			throw(RestDaoException.newInstance("RestDao.get()", tableName, id+"", e));
		}
		return target;
	}
	

	
	public MClient bindDevice(UpdateBanding4SnCodeForm updateForm){
		String path = "banding";
		MClient target = null;
		try {
			final String uriPattern = "{}/{}/{}";
		
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			target = RestProxy.getInstance().update(uri, updateForm,MClient.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			String tableName = table;
			throw(RestDaoException.newInstance("RestDao.update()", tableName, path, e));
		}
		return target;
	}
	
	

	@Override
	public String getService() {
		return StoreMSCfgMgr.getProp().getIotHost();
	}

	
}
