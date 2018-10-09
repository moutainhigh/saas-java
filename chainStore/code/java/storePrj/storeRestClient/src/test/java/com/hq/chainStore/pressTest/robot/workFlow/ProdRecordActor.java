package com.hq.chainStore.pressTest.robot.workFlow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.robot.StoreProductInfoActor;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdInfoForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.bs.WorkFlowDataProdRecordMgr;
import com.hq.chainStore.service.workFlowData.data.ProdRecord;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ProdRecordActor {

	public static ProdRecordActor getInstance(){
		return HotSwap.getInstance().getSingleton(ProdRecordActor.class);
	}
	
	/**
	 * 添加项目
	 * @param storeId
	 */
	public void addProdRecord(long storeId){
		ProductInfo randomProduct = StoreProductInfoActor.getInstance().getRandomProduct(storeId);
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRandomWorkFlow(storeId);
		if(randomProduct != null && randomWorkFlow != null){
			ProdRecordAddForm inputForm = ProdRecordAddForm.newInstance();
			inputForm.setCount(RandomUtils.nextInt(0, 10));
			inputForm.setPrice(RandomUtils.nextFloat(100f, 1000f));
			inputForm.setProdId(String.valueOf(randomProduct.getId()));
			WorkFlowDataProdRecordMgr.getInstance().addProdRecord(randomWorkFlow.getId(), inputForm);
		}
	}
	
	/**
	 * 添加修改项目列表
	 * @param storeId
	 */
	public void upProdList(long storeId){
		List<ProductInfo> productList = StoreProductInfoActor.getInstance().getProductList(storeId);
		WorkFlowData randomWorkFlow = WorkFlowActor.getInstance().getRandomWorkFlow(storeId);
		if(!productList.isEmpty() && randomWorkFlow != null){
			ProdRecordUpdListForm updListForm = ProdRecordUpdListForm.newInstance();
			List<ProdRecordAddForm> inputForms = new ArrayList<ProdRecordAddForm>();
			for(ProductInfo product:productList){
				ProdRecordAddForm inputForm = ProdRecordAddForm.newInstance();
				inputForm.setCount(RandomUtils.nextInt(0, 10));
				inputForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
				inputForm.setProdId(String.valueOf(product.getId()));
				inputForms.add(inputForm);
			}
			updListForm.setProdRecordAddForms(inputForms);
			WorkFlowDataProdRecordMgr.getInstance().updProdRecordList(randomWorkFlow.getId(), updListForm);
		}
	}
	
	/**
	 * 修改项目数量、价格
	 * @param storeId
	 */
	public void updateProdItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<ProdRecord> prodRecordList = new ArrayList<ProdRecord>(workFlowData.getProdRecordMap().values());
			if(!prodRecordList.isEmpty()){
				ProdRecord prodRecord = prodRecordList.get(RandomUtils.nextInt(0,prodRecordList.size()));
				ProdRecordUpdInfoForm updInfoForm = ProdRecordUpdInfoForm.newInstance();
				FastBeanCopyer.getInstance().copy(prodRecord, updInfoForm);
				updInfoForm.setCount(updInfoForm.getCount()+1);
				updInfoForm.setPrice(RandomUtils.nextFloat(1000f, 2000f));
				WorkFlowDataProdRecordMgr.getInstance().updateProdRecordInfo(workFlowData.getId(), prodRecord.getProdId(), updInfoForm);
				break;
			}
		}
	}
	
	/**
	 * 删除项目
	 * @param storeId
	 */
	public void deleteProdItem(long storeId){
		List<WorkFlowData> workFlowList = WorkFlowActor.getInstance().getList(storeId);
		for(WorkFlowData workFlowData:workFlowList){
			List<ProdRecord> prodRecordList = new ArrayList<ProdRecord>(workFlowData.getProdRecordMap().values());
			if(!prodRecordList.isEmpty()){
				ProdRecord prodRecord = prodRecordList.get(RandomUtils.nextInt(0,prodRecordList.size()));
				WorkFlowDataProdRecordMgr.getInstance().deleteProdRecord(workFlowData.getId(), prodRecord.getProdId());
				break;
			}
		}
	}
	
}
