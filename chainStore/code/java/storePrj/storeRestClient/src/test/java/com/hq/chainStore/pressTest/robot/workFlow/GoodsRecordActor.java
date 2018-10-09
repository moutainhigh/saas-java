package com.hq.chainStore.pressTest.robot.workFlow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.robot.StoreGoodsActor;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataGoodsRecordMgr;
import com.hq.chainStore.service.workFlowData.data.GoodsRecord;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsRecordActor {

	public static GoodsRecordActor getInstance(){
		return HotSwap.getInstance().getSingleton(GoodsRecordActor.class);
	}
	
	/**
	 * 添加、修改商品列表
	 * @param storeId
	 */
	public void upGoodsRecordList(long storeId){
		List<Goods> goodsList = StoreGoodsActor.getInstance().getGoodsList(storeId);
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRandomWorkFlow(storeId);
		if(!goodsList.isEmpty() && randomWorkFlow != null){
			List<GoodsRecordAddForm> inputForms = new ArrayList<GoodsRecordAddForm>();
			for(Goods goods:goodsList){
				GoodsRecordAddForm addForm = GoodsRecordAddForm.newInstance();
				addForm.setGoodsId(String.valueOf(goods.getId()));
				addForm.setCount(RandomUtils.nextInt(0, 10));
				addForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
				inputForms.add(addForm);
			}
			GoodsRecordUpdListForm updateForm = GoodsRecordUpdListForm.newInstance();
			updateForm.setGoodsRecordAddForms(inputForms);
			WorkFlowDataGoodsRecordMgr.getInstance().updGoodsRecordList(randomWorkFlow.getId(), updateForm);
		}
	}
	
	/**
	 * 修改商品数量、价格
	 * @param storeId
	 */
	public void updateGoodsItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<GoodsRecord> goodsRecordList = new ArrayList<GoodsRecord>(workFlowData.getGoodsRecordMap().values());
			if(!goodsRecordList.isEmpty()){
				GoodsRecord goodsRecord = goodsRecordList.get(RandomUtils.nextInt(0,goodsRecordList.size()));
				GoodsRecordUpdateForm updateForm = GoodsRecordUpdateForm.newInstance();
				updateForm.setGoodsId(goodsRecord.getGoodsId());
				updateForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
				updateForm.setCount(RandomUtils.nextInt(0, 10));
				WorkFlowDataGoodsRecordMgr.getInstance().updateGoodsRecord(workFlowData.getId(), goodsRecord.getGoodsId(), updateForm);
				break;
			}
		}
	}
	
	/**
	 * 删除商品
	 * @param storeId
	 */
	public void deleteGoodsItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<GoodsRecord> goodsRecordList = new ArrayList<GoodsRecord>(workFlowData.getGoodsRecordMap().values());
			if(!goodsRecordList.isEmpty()){
				GoodsRecord goodsRecord = goodsRecordList.get(RandomUtils.nextInt(0,goodsRecordList.size()));
				WorkFlowDataGoodsRecordMgr.getInstance().deleteGoodsRecord(workFlowData.getId(), goodsRecord.getGoodsId());
				break;
			}
		}
	}
	
}
