package com.hq.storeMS.service.storeProductInfo.bs.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.excel.data.ExcelProduct;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfoState;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfoTmp;
import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 项目批量操作
 * @author kevin
 *
 */
public class BatchStProductMgr {
	public static BatchStProductMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BatchStProductMgr.class);
	}

	// 从excel批量添加项目
	public OperateTips addListFormExcel(List<ExcelProduct> inputData, long storeId) {
		List<AddProductInfoData> productInfoDataList = new ArrayList<AddProductInfoData>();
		for (int index = 0; index < inputData.size(); index++) {
			productInfoDataList.add(toAddDataFromExcel(inputData.get(index), storeId, index + 1));
		}
		return importListData(productInfoDataList, storeId);
	}

	//从店铺批量添加项目
	public OperateTips addListFormStore(List<ProductInfoTmp> inputData, long storeId) {
		List<AddProductInfoData> productInfoDataList = new ArrayList<AddProductInfoData>();
		for (int index = 0; index < inputData.size(); index++) {
			productInfoDataList.add(toAddDataFromStore(inputData.get(index), storeId, index + 1 ));
		}
		return importListData(productInfoDataList, storeId);
	}
	
	// 批量修改项目状态
	public OperateTips batchUpdateProductState(BatchUpdateProductStateData inputData) {
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.BatchUpdateProductState.getDescript() + "失败");

		Map<String, ProductInfo> dataMap = StoreProductInfoMgr.getInstance().getByStoreId(storeId).getProductInfoMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<UpdateProductStateData> updateList = inputData.toUpdateProductStateDataList();
		for (UpdateProductStateData updateStateForm : updateList) {
			OperateTips optips = StProductMgr.getInstance().updateProductState(updateStateForm);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(dataMap.get(updateStateForm.getId()).getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下项目更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	// 只要不抛出异常，则都算批量导入成功。
	private OperateTips importListData(List<AddProductInfoData> productInfoDataList, long storeId){
		OperateTips tips = OperateTips.newInstance(false, "批量导入项目失败");
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		
		StoreProductInfo storeData = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		int idIndex = storeData.getProductIdIndex();
		for (AddProductInfoData addProductInfoData : productInfoDataList) {
			OperateTips optips = OperateTips.newInstance(false);
			try {
				idIndex++;
				addProductInfoData.setIndex(idIndex);
				optips = StProductMgr.getInstance().addProductInfo(addProductInfoData);
			} catch (Exception e) {
				idIndex--;
				MainLog.error(LogModule.StoreProductInfo, "BatchProductHandle[importListData]", "importListData failure", e);
			}
			if (!optips.isSuccess()) {
				tipsInfo.add(addProductInfoData.getName()+"["+addProductInfoData.getNumber()+"]");
				flag = true;
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下项目导入失败（名称[编号]）：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	public AddProductInfoData toAddDataFromStore(ProductInfoTmp source, long storeId,int index) {
		AddProductInfoData target = AddProductInfoData.newInstance();
		FastBeanCopyer.getInstance().copy(source, target);
		fillInExtraInfo(target, storeId, index, source.getTypeName());
		return target;
	}

	private AddProductInfoData toAddDataFromExcel(ExcelProduct source, long storeId, int index) {
		AddProductInfoData target = AddProductInfoData.newInstance();
		BeanUtils.copyProperties(source, target, "typeId");
		fillInExtraInfo(target, storeId, index, source.getTypeId());
		return target;
	}
	
	private void fillInExtraInfo(AddProductInfoData target, long storeId, int index, String typeName) {
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		target.setIndex(storeProductInfo.getProductIdIndex() + index);
		target.setStoreId(storeId);
		target.setState(ProductInfoState.Close.ordinal());
		// 编号为null 自动生成
		if (StringUtils.isBlank(target.getNumber())) {
			target.setNumber(genNumber(target.getIndex()));
		}
		// 分类
		target.setTypeId(getTypeId(typeName, storeProductInfo));
	}
	
	private String genNumber(long index) {
		long tmpNum = 10000000L + index;
		StringBuilder sb = new StringBuilder(tmpNum+"");
		sb.replace(0, 1, ServerConstants.PRODUCT_SUFFIX);
		return sb.toString();
	}
	
	private String getTypeId(String typeName, StoreProductInfo storeProductInfo) {
		String typeId = ServerConstants.ZERO;
		if (StringUtils.isNotBlank(typeName)) {
			long storeId = storeProductInfo.getStoreId();
			Map<String, ProductType> tmpMap = storeProductInfo.takeProductTypeMapWithTypeNameKey();
			if (tmpMap.containsKey(typeName)) {
				ProductType productType = tmpMap.get(typeName);
				typeId = productType.getId();
				if(productType.getEntityState()== EntityState.Deleted.ordinal()){
					typeId = addTypeAndReturnId(storeProductInfo, storeId, typeName);
				}
				
			} else {
				typeId = addTypeAndReturnId(storeProductInfo, storeId, typeName);
			}
		}
		return typeId;
	}
	
	private String addTypeAndReturnId(StoreProductInfo storeProductInfo,long storeId,String typeName){
		String typeId = ServerConstants.ZERO;
		AddProductTypeData inputData = AddProductTypeData.newInstance();
		inputData.setIndex(storeProductInfo.getProductTypeIdIndex() + 1);
		inputData.setStoreId(storeId);
		inputData.setName(typeName);
		OperateTips tips = StProductTypeMgr.getInstance().addProductType(inputData);
		if (tips.isSuccess()) {
			typeId = inputData.getIndex()+"";
		}
		return typeId;
	}
	
}
