package com.hq.chainStore.service.buser.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;

public class BUserDAO extends RestDao<BUser> {

	public static BUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<BUser> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	/**
	 * 根据ID查询用户列表详情
	 * @param reqMap
	 * @return
	 */
	public List<BUser> findByMultitId(ReqMap reqMap) {
		final String findPath = "findByMultitId";
		List<BUser> buserList = findWithReqParam(findPath, reqMap, 10000, 1);
		return buserList;
	}
	
	/**
	 * 根据ID查询用户列表详情
	 * @param reqMap
	 * @return
	 */
	public List<BUser> findByMultitId(ReqMap reqMap,int pageItemCount, int pageNo) {
		final String findPath = "findByMultitId";
		List<BUser> buserList = findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
		return buserList;
	}
	
	public static void main(String[] args) {
//		RestTemplateMgr.getInstance().init();
//		List<BUser> list = BUserDAO.getInstance().list(10, 0);
//		System.out.println(list.size());
		
		new ClassInfo(BUser.class);
		
	}
	
}
