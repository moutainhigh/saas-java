package com.hq.storeMS.service.storeGoods.bs.update;

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
import com.hq.storeMS.service.excel.data.ExcelGoods;
import com.hq.storeMS.service.storeGoods.apiData.GoodsAddForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.GoodsStateEnum;
import com.hq.storeMS.service.storeGoods.data.GoodsTmp;
import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchStGoodsMgr {
	public static BatchStGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BatchStGoodsMgr.class);
	}

	public OperateTips addListFromExcel(long storeId, List<ExcelGoods> inputData) {
		List<GoodsAddForm> addFormList = new ArrayList<GoodsAddForm>();
		for (int index = 0; index < inputData.size(); index++) {
			addFormList.add(toAddGoodsFromExcel(inputData.get(index), storeId, index + 1));
		}
		return importListData(storeId, addFormList);
	}

	public OperateTips addListFromStore(long storeId, List<GoodsTmp> inputData) {
		List<GoodsAddForm> addFormList = new ArrayList<GoodsAddForm>();
		for (int index = 0; index < inputData.size(); index++) {
			addFormList.add(toAddGoodsFromStore(inputData.get(index), storeId, index+1));
		}
		return importListData(storeId, addFormList);
	}

	// 只要不抛出异常，则都算批量导入成功。
	private OperateTips importListData(long storeId, List<GoodsAddForm> addFormList) {
		OperateTips tips = OperateTips.newInstance(false, "批量导入商品失败");
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		
		StoreGoods storeData = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		long idIndex = storeData.getGoodsIdIndex();
		for (GoodsAddForm goodsAddForm : addFormList) {
			OperateTips optips = OperateTips.newInstance(false);
			try {
				idIndex++;
				goodsAddForm.setIndex(idIndex);
				optips = StGoodsMgr.getInstance().addGoods(storeId, goodsAddForm);
			} catch (Exception e) {
				idIndex--;
				MainLog.error(LogModule.StoreGoods, "BatchGoodsHandle[importListData]", "importListData failure", e);
			}
			if (!optips.isSuccess()) {
				tipsInfo.add(goodsAddForm.getName()+"["+goodsAddForm.getNumber()+"]");
				flag = true;
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下商品导入失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}

	// 批量更新商品状态
	public OperateTips batchUpdateGoodsState(long storeId, GoodsBatchUpdateStateForm batchUpdateStateForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.BatchUpdateGoodsState.getDescript() + "失败");
		Map<String, Goods> goodsMap = StoreGoodsMgr.getInstance().getByStoreId(storeId).getGoodsMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<GoodsUpdateStateForm> goodsUpdateStateFormList = batchUpdateStateForm.toGoodsUpdateStateFormList();
		for (GoodsUpdateStateForm goodsUpdateStateForm : goodsUpdateStateFormList) {
			OperateTips optips = StGoodsMgr.getInstance().updateGoodsState(storeId, goodsUpdateStateForm);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(goodsMap.get(goodsUpdateStateForm.getGoodsId()).getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下商品更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	private GoodsAddForm toAddGoodsFromExcel(ExcelGoods source, long storeId, int index) {
		GoodsAddForm target = GoodsAddForm.newInstance();
		BeanUtils.copyProperties(source, target, "typeId");
		fillInExtraInfo(target, storeId, index, source.getTypeId());
		return target;
	}
	
	public  GoodsAddForm toAddGoodsFromStore(GoodsTmp source, long storeId, int index) {
		GoodsAddForm target = GoodsAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(source, target);
		fillInExtraInfo(target, storeId, index, source.getTypeName());
		return target;
	}
	
	private void fillInExtraInfo(GoodsAddForm target, long storeId, int index, String typeName) {
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		target.setIndex(storeGoods.getGoodsIdIndex() + index);
		target.setState(GoodsStateEnum.Close.ordinal());
		//编号为null 自动生成
		if (StringUtils.isBlank(target.getNumber())) {
			target.setNumber(genNumber(target.getIndex()));
		}
		// 分类
		target.setTypeId(getTypeId(typeName, storeGoods));
	}
	
	private String genNumber(long index) {
		long tmpNum = 10000000L + index;
		StringBuilder sb = new StringBuilder(tmpNum+"");
		sb.replace(0, 1, ServerConstants.GOODS_SUFFIX);
		return sb.toString();
	}
	
	private String getTypeId(String typeName, StoreGoods storeGoods) {
		String typeId = ServerConstants.ZERO;
		if (StringUtils.isNotBlank(typeName)) {
			long storeId = storeGoods.getStoreId();
			Map<String, GoodsType> tmpMap = storeGoods.takeGoodsTypeMapWithTypeNameKey();
			if (tmpMap.containsKey(typeName)) {
				GoodsType goodsType = tmpMap.get(typeName);
				typeId = goodsType.getId();
				if(goodsType.getEntityState()==EntityState.Deleted.ordinal()){
					typeId = addTypeAndReturnId(storeGoods, storeId, typeName);
				}
				
			} else {
				typeId = addTypeAndReturnId(storeGoods, storeId, typeName);
			}
		}
		return typeId;
	}
	
	private String addTypeAndReturnId(StoreGoods storeGoods,long storeId,String typeName){
		String typeId = ServerConstants.ZERO;
		GoodsTypeAddForm inputData = GoodsTypeAddForm.newInstance();
		inputData.setIndex(storeGoods.getGoodsTypeIdIndex() + 1);
		inputData.setName(typeName);
		OperateTips tips = StGoodsTypeMgr.getInstance().addGoodsType(storeId, inputData);
		if (tips.isSuccess()) {
			typeId = inputData.getIndex()+"";
		}
		return typeId;
	}
}
