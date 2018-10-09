package com.hq.storeMS.service.workFlowType.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.data.SysFixCompEnum;
import com.hq.storeMS.service.workFlowType.data.WFCompEnum;
import com.hq.storeMS.service.workFlowType.data.WFCompInfo;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowTypeMgr {

	public static WorkFlowTypeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowTypeMgr.class);
	}
	
	/**
	 * 初始化工作流类型
	 */
	public void init(){
		saveBuyType();
		saveRechargeType();
	}
	
	//购买消费
	private void saveBuyType(){
		String typeName=SysFixCompEnum.BuyComp.getMark();
		WorkFlowType flowType = findOne(typeName);
		if(flowType == null){
			List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectCuserWFComp,1));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectFollowPersonWFComp,2));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectProductWFComp,3));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectPrdCardProdWFComp,4));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectGoodsWFComp,5));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectPrdCardWFComp,6));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.PayTheBillWFComp,7));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.OrderWFComp,8));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.BuyBonusWFComp,9));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectPackagePrjWFComp,10));
			
			WorkFlowType target = WorkFlowType.newInstance();
			target.setWfCompName(typeName);
			target.setWfCompInfos(wfCompInfos);
			addAndReturnId(target);
		}
	}
	
	//会员充值
	private void saveRechargeType(){
		String typeName=SysFixCompEnum.MemCardComp.getMark();
		WorkFlowType flowType = findOne(typeName);
		if(flowType == null){
			List<WFCompInfo> wfCompInfos = new ArrayList<WFCompInfo>();
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectCuserWFComp,1));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.SelectFollowPersonWFComp,2));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.RechargeMemCardWFComp,3));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.PayTheBillWFComp,4));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.OrderWFComp,5));
			wfCompInfos.add(WFCompInfo.newInstance(WFCompEnum.RechargeBonusWFComp,6));
			
			WorkFlowType target = WorkFlowType.newInstance();
			target.setWfCompName(typeName);
			target.setWfCompInfos(wfCompInfos);
			addAndReturnId(target);
		}
	}
	
	public void addAndReturnId(WorkFlowType target) {
		WorkFlowTypeDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void updateWorkFlowType(WorkFlowType target) {
		WorkFlowTypeDataHolder.getInstance().update(target);
	}
	
	public WorkFlowType get(long id){
		return WorkFlowTypeDataHolder.getInstance().get(id);
	}
	
	public WorkFlowType findOne(String typeName) {
		return WorkFlowTypeDataHolder.getInstance().findOne(typeName);
	}
	
	public List<WorkFlowType> findByCond(QueryWorkFlowTypeForm queryForm) {
		return WorkFlowTypeDataHolder.getInstance().findByCond(queryForm);
	}
	
	public Map<Long, WorkFlowType> findWorkFlowTypeMap(QueryWorkFlowTypeForm queryForm) {
		Map<Long, WorkFlowType> map = new HashMap<Long, WorkFlowType>();
		List<WorkFlowType> list = WorkFlowTypeDataHolder.getInstance().findByCond(queryForm);
		if(CollectionUtils.isNotEmpty(list)) {
			for (WorkFlowType workFlowType : list) {
				map.put(workFlowType.getId(), workFlowType);
			}
		}
		return map;
	}
}
