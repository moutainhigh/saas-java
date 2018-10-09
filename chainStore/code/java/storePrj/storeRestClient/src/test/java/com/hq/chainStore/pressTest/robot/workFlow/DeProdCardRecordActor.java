package com.hq.chainStore.pressTest.robot.workFlow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.robot.StoreCardInfoActor;
import com.hq.chainStore.service.storeCardInfo.data.PrdInCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCard;
import com.hq.chainStore.service.storeCardInfo.data.ProductCardTypeEnum;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardAddForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.DecreasePrdCardUpdateForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataDecreasePrdCardRecordMgr;
import com.hq.chainStore.service.workFlowData.data.DecreasePrdCardRecord;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class DeProdCardRecordActor {

	public static DeProdCardRecordActor getInstance(){
		return HotSwap.getInstance().getSingleton(DeProdCardRecordActor.class);
	}
	
	/**
	 * 添加修改划卡项目列表
	 * @param storeId
	 */
	public void upDeProdList(long storeId){
//		ProductInfo randomProduct = StoreProductInfoActor.getInstance().getRandomProduct(storeId);
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRandomWorkFlow(storeId);
		if(randomWorkFlow != null){
			List<ProductCard> productCardList = StoreCardInfoActor.getInstance().getProductCardList(storeId);
			for(ProductCard productCard:productCardList){
				if(productCard.getType() == ProductCardTypeEnum.LIMIT_PRDANDTIME.ordinal()){//限项目限次数
					ArrayList<PrdInCard> prdInCardList = new ArrayList<PrdInCard>(productCard.getProductMap().values());
					if(prdInCardList.size() > 0){
						PrdInCard prdInCard = prdInCardList.get(0);
						DecreasePrdCardUpdListForm upListForm = DecreasePrdCardUpdListForm.newInstance();
						List<DecreasePrdCardAddForm> inputForms = new ArrayList<DecreasePrdCardAddForm>();
						DecreasePrdCardAddForm addForm = DecreasePrdCardAddForm.newInstance();
						addForm.setCardTypeId("_prd_"+storeId+"_"+RandomUtils.nextInt(0, 10)+"");
						addForm.setStatus(RandomUtils.nextInt(0, 2));
						addForm.setCount(RandomUtils.nextInt(0, 10));
						addForm.setPrdId(prdInCard.getPrdId());
						inputForms.add(addForm);
						upListForm.setDecreasePrdCardAddForms(inputForms);
						WorkFlowDataDecreasePrdCardRecordMgr.getInstance().updDecreasePrdCardRecordList(randomWorkFlow.getId(), upListForm);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 修改划卡项目
	 * @param storeId
	 */
	public void updateDeProdItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<DecreasePrdCardRecord> deProdRecordList = new ArrayList<DecreasePrdCardRecord>(workFlowData.getDecreasePrdCardRecordMap().values());
			if(!deProdRecordList.isEmpty()){
				DecreasePrdCardRecord decreasePrdCardRecord = deProdRecordList.get(RandomUtils.nextInt(0,deProdRecordList.size()));
				DecreasePrdCardUpdateForm updateForm = DecreasePrdCardUpdateForm.newInstance();
				updateForm.setDecreasePrdCardId(String.valueOf(decreasePrdCardRecord.getCardTypeId()));
				updateForm.setCount(RandomUtils.nextInt(0, 10));
				WorkFlowDataDecreasePrdCardRecordMgr.getInstance().updateDecreasePrdCardRecord(workFlowData.getId(), decreasePrdCardRecord.getId(), updateForm);
				break;
			}
		}
	}
	
	/**
	 * 删除划卡项目
	 * @param storeId
	 */
	public void deleteDeProdItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<DecreasePrdCardRecord> deProdRecordList = new ArrayList<DecreasePrdCardRecord>(workFlowData.getDecreasePrdCardRecordMap().values());
			if(!deProdRecordList.isEmpty()){
				DecreasePrdCardRecord decreasePrdCardRecord = deProdRecordList.get(RandomUtils.nextInt(0,deProdRecordList.size()));
				WorkFlowDataDecreasePrdCardRecordMgr.getInstance().deleteDecreasePrdCardRecord(workFlowData.getId(), decreasePrdCardRecord.getId());
				break;
			}
		}
	}
	
}
