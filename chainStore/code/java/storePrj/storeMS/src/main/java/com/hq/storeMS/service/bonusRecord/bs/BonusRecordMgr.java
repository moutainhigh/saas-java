package com.hq.storeMS.service.bonusRecord.bs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordForm;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.hq.storeMS.service.bonusRecord.data.BonusBeanHelper;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.bonusRecord.data.BonusRecordGroup;
import com.hq.storeMS.service.bonusRecord.data.BonusRecordStatusEnum;
import com.hq.storeMS.service.bonusRecord.data.OrderBonus;
import com.hq.storeMS.service.buser.bs.StoreBUserMgr;
import com.hq.storeMS.service.buser.data.SimpleBUser;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusRecordMgr {

	public static BonusRecordMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordMgr.class);
	}

	private final DateFormat sdf = new SimpleDateFormat(ServerConstants.df_yy_mm);
	
	/**
	 * 获取单个订单的提成信息
	 * @param orderId
	 * @return
	 */
	public OrderBonus getOrderBonusByOrderId(long orderId, long storeId) {
		List<BonusRecord> list = BonusRecordDataHolder.getInstance().findBonusRecordList(BonusRecordQueryForm.newInstance(orderId, storeId));
		return BonusBeanHelper.getInstance().bonusRecords2OrderBonus(list);
	}
	
	/**
	 * 更新订单下面的提成状态
	 * @param orderId
	 * @param status
	 */
	public void updateBonusRecordStatusByOrderId(long orderId, int status) {
		BonusRecordQueryForm queryForm = BonusRecordQueryForm.newInstance();
		queryForm.setOrderId(orderId);
		queryForm.setStatus(BonusRecordStatusEnum.UNVALID.ordinal()+"");
		List<BonusRecord> list = findBonusRecordList(queryForm);
		for (BonusRecord bonusRecord : list) {
			bonusRecord.setStatus(status);
			updateBonusRecord(bonusRecord);
		}
	}
	
	/**
	 * 保存单个购买项的提成信息
	 * @param addForm
	 * @param order
	 */
	public void saveBonusRecord(BonusRecordForm addForm, Order order) {
		//同一个店铺/订单/购买品  如果存在，则先删除再新增
		long storeId = addForm.getStoreId();
		long orderId = addForm.getOrderId();
		String buyId = BonusInfo.genBonusId(addForm.getPrdCardPayType(), addForm.getBuyType(), addForm.getPgId(), addForm.getLeaguerPrdCardId());
		
		BonusRecordQueryForm queryForm = BonusRecordQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setOrderId(orderId);
		queryForm.setBuyId(buyId);
		List<BonusRecord> list = findBonusRecordList(queryForm);
		for (BonusRecord bonusRecord : list) {
			delete(bonusRecord);
		}
		
		List<BonusRecord> bonusRecords = BonusBeanHelper.getInstance().addForm2BonusRecords(addForm);
		if(CollectionUtils.isNotEmpty(bonusRecords)){
			additionalBUserName(bonusRecords, storeId);
			int status = BonusRecordStatusEnum.UNVALID.ordinal();
			OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(order.getStatus());
			if(orderStatusEnum == OrderStatusEnum.HAS_PAY){
				status = BonusRecordStatusEnum.VALID.ordinal();
			}
			for (BonusRecord bonusRecord : bonusRecords) {
				bonusRecord.setStatus(status);
				bonusRecord.setOrderNumber(order.getNumber());
				bonusRecord.setOrderTime(order.getCreatedTime());
				addAndReturnId(bonusRecord);
			}
		}
	}
	
	private void additionalBUserName(List<BonusRecord> list, long storeId){
		Map<Long, SimpleBUser> buserMap = StoreBUserMgr.getInstance().get(storeId).getBuserInfoMap();
		for (BonusRecord bonusRecord : list) {
			if(buserMap.get(bonusRecord.getBuserId()) != null) {
				bonusRecord.setBuserName(buserMap.get(bonusRecord.getBuserId()).getName());
			}
		}
	}
	
	public void addAndReturnId(BonusRecord target) {
		BonusRecordDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void delete(BonusRecord target) {
		BonusRecordDataHolder.getInstance().delete(target);
	}

	public void updateBonusRecord(BonusRecord target) {
		BonusRecordDataHolder.getInstance().update(target);
	}
	
	public BonusRecord get(long storeId, long id) {
		return BonusRecordDataHolder.getInstance().get(storeId, id);
	}
	
	public List<BonusRecord> findBonusRecordList(BonusRecordQueryForm queryForm) {
		List<BonusRecord> list = BonusRecordDataHolder.getInstance().findBonusRecordList(queryForm);
		List<BonusRecord> result = filterRecord(queryForm, list);
		return PageUtil.getInstance().getPageItemList(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public PageResp<BonusRecord> findBonusRecordPageInfo(BonusRecordQueryForm queryForm) {
		List<BonusRecord> list = BonusRecordDataHolder.getInstance().findBonusRecordList(queryForm);
		List<BonusRecord> result = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public PageResp<BonusRecordGroup> getBonusRecordGroupPageInfo(BonusRecordQueryForm queryForm) {
		List<BonusRecord> list = BonusRecordDataHolder.getInstance().findBonusRecordList(queryForm);
		List<BonusRecord> result = filterRecord(queryForm, list);
		List<BonusRecordGroup> bonusRecordGroups = buildBonusRecordGroupList(result, queryForm);
		return PageUtil.getInstance().buildPageResp(bonusRecordGroups, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<BonusRecordGroup> buildBonusRecordGroupList(List<BonusRecord> list, BonusRecordQueryForm queryForm){
		List<BonusRecordGroup> result = new ArrayList<BonusRecordGroup>();
		if(CollectionUtils.isEmpty(list)) {
			return result;
		}
		
		long storeId = queryForm.getStoreId();
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		Map<Long, ClerkInfo> clerkInfoMap = storeClerkInfo.getClerkInfoMap();
		Map<Integer, StoreAdminRole> roleMap = storeClerkInfo.getRoleMap();
		Map<Long, SimpleBUser> buserMap = StoreBUserMgr.getInstance().get(storeId).getBuserInfoMap();
		
		Map<String, BonusRecordGroup> map = new HashMap<String, BonusRecordGroup>();
		for (BonusRecord data : list) {
			String dateStr = AppUtils.timeStamp2Str(data.getOrderTime(), sdf);
			long buserId = data.getBuserId();
			BonusRecordGroup recordGroup = map.get(getBuserDateKey(dateStr, buserId));
			if(recordGroup == null) {
				recordGroup = buildBonusRecordGroup(buserMap.get(buserId), clerkInfoMap.get(buserId), roleMap, data);
				recordGroup.setDateStr(dateStr);
				recordGroup.setBuserId(buserId);
				map.put(getBuserDateKey(dateStr, buserId), recordGroup);
			}
			recordGroup.setAmountCost(recordGroup.getAmountCost() + data.getAmount());
			recordGroup.setBonusCost(recordGroup.getBonusCost() + data.getCost());
			
		}
		
		result.addAll(map.values());
		return result;
	}
	
	private String getBuserDateKey(String dateStr, long buserId) {
		return AppUtils.joinByUnderline(dateStr, buserId);
	}
	
	private BonusRecordGroup buildBonusRecordGroup(SimpleBUser buser, ClerkInfo clerkInfo, Map<Integer, StoreAdminRole> roleMap, BonusRecord data) {
		BonusRecordGroup recordGroup = BonusRecordGroup.newInstance();
		if(buser!=null) {
			recordGroup.setBuserHeadImg(buser.getHeadImg());
			recordGroup.setBuserName(buser.getName());
			recordGroup.setBuserPhone(buser.getPhone());
			if(clerkInfo!=null) {
				Set<Integer> roleSet = clerkInfo.getRoleSet();
				for (Integer roleIndex : roleSet) {
					recordGroup.addRoleName(roleMap.get(roleIndex).getName());
				}
			}
		}
		return recordGroup;
	}
	
	private List<BonusRecord> filterRecord(BonusRecordQueryForm queryForm, List<BonusRecord> list){
		List<BonusRecord> result = new ArrayList<BonusRecord>();
		if(CollectionUtils.isNotEmpty(list)){
			for (BonusRecord record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		
		Collections.sort(result, new Comparator<BonusRecord>() {
			@Override
			public int compare(BonusRecord o1, BonusRecord o2) {
				 return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(BonusRecordQueryForm queryForm, BonusRecord record){
		if(!checkBuyId(queryForm.getBuyId(), record)){
			return false;
		}

		if(!checkStatus(queryForm.getStatusSet(), record)){
			return false;
		}
		
		if(!checkBUserName(queryForm.getBuserName(), record)){
			return false;
		}
		
//		if(!checkOrderId(queryForm.getOrderId(), record)){
//			return false;
//		}
		
		return true;
	}
	
	private boolean checkBuyId(String buyId, BonusRecord record){
		if(StringUtils.isNoneBlank(buyId) && !StringUtils.equals(buyId, record.getBuyId())){
			return false;
		}
		return true;
	}
	
	private boolean checkStatus(Set<Integer> status, BonusRecord record){
		if(CollectionUtils.isNotEmpty(status) && !status.contains(record.getStatus())){
			return false;
		}
		return true;
	}
	
	private boolean checkBUserName(String buserName, BonusRecord record){
		if(StringUtils.isNoneBlank(buserName)){
			if(StringUtils.isBlank(record.getBuserName()) || !record.getBuserName().contains(buserName)){
				return false;
			}
		}
		return true;
	}
	
//	private boolean checkOrderId(long orderId, BonusRecord record){
//		if(orderId == 0){
//			return true;
//		}
//		if(orderId == record.getOrderId()){
//			return true;
//		}
//		return false;
//	}

}
