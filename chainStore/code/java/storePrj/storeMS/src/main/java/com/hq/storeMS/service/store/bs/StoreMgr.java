package com.hq.storeMS.service.store.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.qrcode.bs.QrCodeMgr;
import com.hq.storeMS.service.saas.apiData.OPStoreQueryApiForm;
import com.hq.storeMS.service.store.apiData.StoreQueryForm;
import com.hq.storeMS.service.store.apiData.Submit4CheckApiData;
import com.hq.storeMS.service.store.apiData.UpdateStoreInfoApiData;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.store.data.StoreState;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMgr {

	public static StoreMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMgr.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(Store target) {
		StoreDataHolder.getInstance().addAndReturnId(target);		
	}
	
	public boolean updateStoreInfoApiData(UpdateStoreInfoApiData inputData){
		boolean success = false;
		long storeId = inputData.getStoreId();
		Store store = get(storeId);
		
		if(store!=null){
			inputData.update(store);
			update(store);
			success = true;
		}
		return success;
		
	}
	public boolean submit4Check(Submit4CheckApiData inputData){
		boolean success = false;
		Store store = get(inputData.getStoreId());
		
		if(store!=null && store.getState() == StoreState.New.ordinal()){
			StoreState stateTmp = StoreState.Checking;
			store.setState(stateTmp.ordinal());
			update(store);
			success = true;
		}
		return success;
	}
	public boolean approveStore(long storeId, boolean approved){
		Store store = get(storeId);
		
		if(store!=null && store.getState() == StoreState.Checking.ordinal()){
			StoreState stateTmp = approved? StoreState.Open: StoreState.CheckFail;
			store.setState(stateTmp.ordinal());
			update(store);
		}
		return true;
	}

	public List<StoreRO> findByIdList(Collection<Long> storeIdSet,int pageItemCount,int pageNo) {
		List<Store> findList = StoreDataHolder.getInstance().findByIdList(storeIdSet,pageItemCount,pageNo);
		return toROList(findList);
	}
	
	public List<StoreRO> findByName(String name,int pageItemCount,int pageNo) {
		List<Store> findList = StoreDataHolder.getInstance().findByName(name,pageItemCount,pageNo);
		return toROList(findList);
	}
	
	public List<StoreRO> findStoreList(OPStoreQueryApiForm queryForm) {
		List<Store> findList = StoreDataHolder.getInstance().findStoreList(queryForm);
		return toROList(findList);
	}
	
	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(Store target) {
		StoreDataHolder.getInstance().update(target);
	}

	public StoreRO getReadOnly(long id) {
		Store store = getSimple(id);
		genQrcode(store);
		return store;
	}
	
	public Store get(long id){
		Store store = getSimple(id);
		genQrcode(store);
		return store;
	}
	
	public Store getSimple(long id) {
		return StoreDataHolder.getInstance().get(id);
	}
	
	public List<StoreRO> getPage(int pageItemCount, int pageNo) {
		List<Store> findList = StoreDataHolder.getInstance().findPage(pageItemCount, pageNo);
		List<StoreRO> targetList = toROList(findList);
		return targetList;
	}
	
	public PageResp<StoreRO> findStoreByCond(StoreQueryForm queryForm) {
		List<Store> list = StoreDataHolder.getInstance().findStoreByCond(queryForm);
		List<StoreRO> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<StoreRO> filterRecord(StoreQueryForm queryForm, List<Store> list){
		List<StoreRO> result = new ArrayList<StoreRO>();
		for (Store record : list) {
			if(isRightRecord(queryForm, record)){
				result.add(record);
			}
		}
		Collections.sort(result, new Comparator<StoreRO>() {
			@Override
			public int compare(StoreRO o1, StoreRO o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(StoreQueryForm queryForm, Store record){
		return true;
	}

	private List<StoreRO> toROList(List<Store> findList) {
		List<StoreRO> targetList = new ArrayList<StoreRO>();
		for (Store store : findList) {
			genQrcode(store);
			targetList.add(store);
		}
		return targetList;
	}
	
	public void genQrcode(Store store){
		if(store == null) {
			return ;
		}
		try {
			boolean flag = false;
			if(StringUtils.isBlank(store.getJoinStoreImg())){
				String joinStoreImg = QrCodeMgr.getInstance().genJoinStoreQrCode(store.getId());
				if(StringUtils.isNotBlank(joinStoreImg)){
					store.setJoinStoreImg(joinStoreImg);
					flag = true;
				}
			}
			if(StringUtils.isBlank(store.getAcodeImg())){
				String acodeImg = QrCodeMgr.getInstance().genWxACode(store.getId());
				if(StringUtils.isNotBlank(acodeImg)){
					store.setAcodeImg(acodeImg);
					flag = true;
				}
			}
			if(flag){
				StoreMgr.getInstance().update(store);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Store, "StoreMgr[genQrcode]", "生成二维码失败", e);
		}
	}
	
	/**
	 * 添加店铺成功之后，回调函数，用于初始化店铺的其他信息；如店铺的员工、会员、项目等等。
	 */
	public void initStoreCallBack(Store store){
		try {
			//补全storeClerkInfo信息
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(store.getId());
			//不存在才添加，否则不处理。
			if(storeClerkInfo == null){
				storeClerkInfo = StoreClerkInfo.newInstance(store.getId(), store.getBossId());
				StoreClerkInfoMgr.getInstance().addWithId(storeClerkInfo);
			}
			store.setClerkInfoId(storeClerkInfo.getId());
			StoreMgr.getInstance().update(store);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(store);
			MainLog.error(LogModule.Store, "StoreHandler[initStoreCallBack]", reason, e);
		}
	}
	
}
