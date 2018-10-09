package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.storeMS.service.storeCardInfo.apiData.DelProductCardForm;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdProductCardForm;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfoBeanHelper;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardMgr {
	
	public static ProductCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardMgr.class);
	}
	
	//添加次卡类型
	public OperateTips addProductCard(long storeId, AddProductCardForm inputData){
		OperateTips tips = OperateTips.newInstance(false,StoreCardInfoUpdateType.AddProductCard.getMark()+"失败");
		if(StoreVipMgr.getInstance().isProductCardLimited(storeId)){
			tips.setTips("当前店铺次卡数量已达上限");
			return tips;
		}
		
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if(checkNumberExists4Add(inputData.getNumber(), storeCardInfo.getProductCardMap().values())){
			tips.setTips("次卡编号已存在");
			return tips;
		}
		
		if(StoreCardInfoBeanHelper.getInstance().addProductCard(storeCardInfo, inputData)){
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
			
			ProductCardDetail detail = inputData.toProductCardDetail(storeId);
			ProductCardDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.AddProductCard.getMark()));
		}
		return tips;
	}
	
	public OperateTips delProductCard(long storeId, DelProductCardForm inputData){
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.DelProductCard.getMark()+"失败");
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		
		if(StoreCardInfoBeanHelper.getInstance().delProductCard(storeCardInfo, inputData)){
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
			
			//将详情信息也更新
			ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(storeId, inputData.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			ProductCardDetailMgr.getInstance().update(detail);
			
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.DelProductCard.getMark()));
		}
		return tips;
	}
	
	public OperateTips updProductCard(long storeId, UpdProductCardForm inputData){
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.UpdProductCard.getMark()+"失败");
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		
		if(checkNumberExists4Update(inputData.getNumber(), inputData.getId(), storeCardInfo.getProductCardMap().values())){
			tips.setTips("次卡编号已存在");
			return tips;
		}
		
		if(StoreCardInfoBeanHelper.getInstance().updProductCard(storeCardInfo, inputData)){
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
		}
		
		//将详情信息也更新
		ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(storeId, inputData.getId());
		inputData.updateProductCardDetail(detail);
		ProductCardDetailMgr.getInstance().update(detail);
		tips.setSuccess(true);
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.UpdProductCard.getMark()));
		return tips;
	}
	
	public OperateTips updProductCardState(long storeId, UpdProductCardStateData inputData){
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.UpdProductCardState.getMark()+"失败");
		
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		if(StoreCardInfoBeanHelper.getInstance().updateProductCardState(storeCardInfo, inputData.getId(), inputData.getState())){
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeCardInfo);
			//将详情信息也更新
			ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(storeId, inputData.getId());
			detail.setStatus(inputData.getState());
			ProductCardDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreCardInfoUpdateType.UpdProductCardState.getMark()));
		}
		return tips;
	}
	
	private boolean checkNumberExists4Add(String number, Collection<ProductCard> productCards){
		return checkNumberExists(number, "", productCards);
	}
	
	private boolean checkNumberExists4Update(String number, String id, Collection<ProductCard> productCards){
		return checkNumberExists(number, id, productCards);
	}
	
	private boolean checkNumberExists(String number, String id, Collection<ProductCard> productCards){
		if(StringUtils.isBlank(number)) {
			return false;
		}
		if(CollectionUtils.isNotEmpty(productCards)){
			for (ProductCard info : productCards) {
				if(number.equals(info.getNumber()) && info.getEntityState() != EntityState.Deleted.ordinal() && !info.getId().equals(id)){
					return true;
				}
			}
		}
		return false;
	}
}
