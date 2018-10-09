package com.hq.chainStore.service.saas.bs;

import java.util.List;

import com.hq.chainStore.service.opuser.data.adminRole.OPAdminRole;
import com.hq.chainStore.service.saas.apiData.OPAdminRoleAddApiForm;
import com.hq.chainStore.service.saas.apiData.OPStoreQueryApiForm;
import com.hq.chainStore.service.saas.apiData.OPStoreUpdateApiForm;
import com.hq.chainStore.service.saas.apiData.OPStoreUpdateStateApiData;
import com.hq.chainStore.service.saas.apiData.OPStoreUpdateType;
import com.hq.chainStore.service.saas.data.OPAdminRoleDAO;
import com.hq.chainStore.service.saas.data.OPStore;
import com.hq.chainStore.service.saas.data.OPStoreDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class OPStoreMgr {

	public static OPStoreMgr getInstance(){
		return HotSwap.getInstance().getSingleton(OPStoreMgr.class);
	}

	public OPAdminRole add(OPAdminRoleAddApiForm formInfo) {

		OPAdminRole returnObj = OPAdminRoleDAO.getInstance().add(formInfo);
		return returnObj;
	}


	public void approveStore(long storeId, boolean approved) {
		OPStoreUpdateApiForm updateForm = OPStoreUpdateApiForm.newInstance();
		OPStoreUpdateStateApiData inputData = OPStoreUpdateStateApiData.newInstance();
		inputData.setId(storeId);
		inputData.setApproved(approved);
		updateForm.setUpdateData(inputData );
		updateForm.setUpdateTypeEnum(OPStoreUpdateType.updateState);
		
		update(storeId, updateForm);
	}
	
	private void update(long storeId, OPStoreUpdateApiForm updateForm) {
		OPStoreDAO.getInstance().update(storeId, updateForm);
	}
	
	public OPStore get(long id) {
		OPStore opStore = OPStoreDAO.getInstance().get(id);
		return opStore;
	}
	
	public List<OPStore> findByName(String name,int pageItemCount,int pageNo) {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance().add("name", name);
		List<OPStore> storeList = OPStoreDAO.getInstance().findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
		return storeList;
	}
	
	public List<OPStore> findStoreList(OPStoreQueryApiForm queryForm) {
		final String findPath = "findStoreList";
		ReqMap reqMap = ReqMap.newInstance().add("name", queryForm.getName())
											.add("area", queryForm.getArea())
											.add("address", queryForm.getAddress())
											.add("state", queryForm.getState())
											.add("company", queryForm.getCompany())
											.add("companyArea", queryForm.getCompanyArea())
											.add("companyAddress", queryForm.getCompanyAddress());
		List<OPStore> storeList = OPStoreDAO.getInstance().findWithReqParam(findPath, reqMap, queryForm.getPageItemCount(), queryForm.getPageNo());
		return storeList;
	}
	
}
