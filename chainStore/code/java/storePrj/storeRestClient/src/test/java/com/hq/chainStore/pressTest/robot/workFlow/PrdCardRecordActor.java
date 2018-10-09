package com.hq.chainStore.pressTest.robot.workFlow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.robot.StoreCardInfoActor;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardAddForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.PrdCardUpdateForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataPrdCardRecordMgr;
import com.hq.chainStore.service.workFlowData.data.PrdCardRecord;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class PrdCardRecordActor {

	public static PrdCardRecordActor getInstance(){
		return HotSwap.getInstance().getSingleton(PrdCardRecordActor.class);
	}
	
	/**
	 * 添加修改次卡列表
	 * @param storeId
	 */
	public void upPrdCardList(long storeId){
		ProductCard randomProductCard = StoreCardInfoActor.getInstance().getRandomProductCard(storeId);
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRandomWorkFlow(storeId);
		if(randomProductCard != null && randomWorkFlow != null){
			List<PrdCardAddForm> inputForms = new ArrayList<PrdCardAddForm>();
			PrdCardAddForm addForm = PrdCardAddForm.newInstance();
			addForm.setPrdCardTypeId("_prd_"+storeId+"_"+RandomUtils.nextInt(1, 11)+"");
			addForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
			addForm.setCount(RandomUtils.nextInt(0, 10));
			inputForms.add(addForm);
			PrdCardUpdListForm updateForm = PrdCardUpdListForm.newInstance();
			updateForm.setPrdCardAddForms(inputForms);
			WorkFlowDataPrdCardRecordMgr.getInstance().updPrdCardRecordList(randomWorkFlow.getId(), updateForm);
		}
	}
	
	/**
	 * 修改次卡数量、价格
	 * @param storeId
	 */
	public void updatePrdCardItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<PrdCardRecord> prdCardRecordList = new ArrayList<PrdCardRecord>(workFlowData.getPrdCardRecordMap().values());
			if(!prdCardRecordList.isEmpty()){
				PrdCardRecord prdCardRecord = prdCardRecordList.get(RandomUtils.nextInt(0,prdCardRecordList.size()));
				PrdCardUpdateForm updateForm = PrdCardUpdateForm.newInstance();
				updateForm.setPrdCardTypeId(prdCardRecord.getPrdCardTypeId());
				updateForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
				updateForm.setCount(RandomUtils.nextInt(0, 10));
				WorkFlowDataPrdCardRecordMgr.getInstance().updatePrdCardRecord(workFlowData.getId(), prdCardRecord.getPrdCardTypeId(), updateForm);
				break;
			}
		}
	}
	
	/**
	 * 删除次卡
	 * @param storeId
	 */
	public void deletePrdCardItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<PrdCardRecord> prdCardRecordList = new ArrayList<PrdCardRecord>(workFlowData.getPrdCardRecordMap().values());
			if(!prdCardRecordList.isEmpty()){
				PrdCardRecord prdCardRecord = prdCardRecordList.get(RandomUtils.nextInt(0,prdCardRecordList.size()));
				WorkFlowDataPrdCardRecordMgr.getInstance().deletePrdCardRecord(workFlowData.getId(), prdCardRecord.getPrdCardTypeId());
				break;
			}
		}
	}
	
}
