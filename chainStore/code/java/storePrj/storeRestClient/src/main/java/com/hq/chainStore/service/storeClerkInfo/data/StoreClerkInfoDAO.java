package com.hq.chainStore.service.storeClerkInfo.data;

import java.util.List;

import com.hq.chainStore.service.common.RespStatus;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.storeClerkInfo.apiData.AddClerkInfoData;
import com.hq.chainStore.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class StoreClerkInfoDAO extends RestDao<StoreClerkInfo> {

	public static StoreClerkInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	
	@Deprecated
	@Override
	public List<StoreClerkInfo> list(int pageItemCount, int pageNo){
		//ms不提供该接口
		return null;
		
	}
	
	@Deprecated
	@Override
	public StoreClerkInfo add(Object target) {
		//ms不提供该接口
		return null;
	};
	
	@Deprecated
	@Override
	public void delete(Object id) {
		//ms不提供该接口
		//do nothing
	};
	
	
	public static void main(String[] args) {
//		RestTemplateMgr.getInstance().init();
//		List<StoreClerkInfo> list = StoreClerkInfoDAO.getInstance().list(10, 0);
//		System.out.println(list.size());
		
		new ClassInfo(StoreClerkInfo.class);
		
	}

	public void update(String storeClerkInfoId, StoreClerkInfoUpdateForm updateForm) {
		super.update(storeClerkInfoId, updateForm);
	}
	
	public boolean addClerk(long storeId, AddClerkInfoData inputData) {
		boolean b = false;
		final String uriPattern = "{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, getService(), "storeClerkInfo", storeId, "addClerk");
		RestResp resp = RestProxy.getInstance().rawReq(uri, inputData);
		if(resp.getCode() == RespStatus.OK.getCode()){
			b = true;
		}
		return b;
	}
	
}
