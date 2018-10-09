package com.hq.chainMS.service.chainCard.bs.updateHandle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.buserMessage.apiData.ProductMessageForm;
import com.hq.chainMS.service.buserMessage.bs.BUserMessageMgr;
import com.hq.chainMS.service.chainCard.apiData.AddProductCardForm;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateType;
import com.hq.chainMS.service.chainCard.apiData.DelProductCardForm;
import com.hq.chainMS.service.chainCard.apiData.ProductCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.UpdProductCardForm;
import com.hq.chainMS.service.chainCard.apiData.UpdProductCardStateData;
import com.hq.chainMS.service.chainCard.bs.ChainCardMgr;
import com.hq.chainMS.service.chainCard.bs.ProductCardDetailMgr;
import com.hq.chainMS.service.chainCard.data.CardStatusEnum;
import com.hq.chainMS.service.chainCard.data.ChainCard;
import com.hq.chainMS.service.chainCard.data.ChainCardBeanHelper;
import com.hq.chainMS.service.chainCard.data.ProductCard;
import com.hq.chainMS.service.chainCard.data.ProductCardDetail;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.storeChain.bs.StoreChainMgr;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.data.StoreChainItemType;
import com.hq.storeClient.service.storeChain.data.StoreChainStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardHandle {
	
	public static ProductCardHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardHandle.class);
	}
	
	//添加次卡
	public OperateTips addProductCard(long chainId, AddProductCardForm addProductCard){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainCardUpdateType.AddProductCard.getMark()+"失败");
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		
		if(checkNumberExists4Add(addProductCard.getNumber(), chainCard.getProductCardMap().values())){
			tips.setTips("次卡编号已存在");
			return tips;
		}
		
		if(ChainCardBeanHelper.getInstance().addProductCard(chainCard, addProductCard)){
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			ProductCardDetail detail = addProductCard.toProductCardDetail(chainId);
			ProductCardDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//删除次卡
	public OperateTips delProductCard(long chainId, DelProductCardForm delProductCard){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.DelProductCard.getMark()+"失败");
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		
		if(ChainCardBeanHelper.getInstance().delProductCard(chainCard, delProductCard)){
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			//将详情信息也更新
			ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(chainId, delProductCard.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			ProductCardDetailMgr.getInstance().update(detail);
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//更新次卡
	public OperateTips updProductCard(long chainId, UpdProductCardForm updProductCard){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.UpdProductCard.getMark()+"失败");
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		
		if(checkNumberExists4Update(updProductCard.getNumber(), updProductCard.getId(), chainCard.getProductCardMap().values())){
			tips.setTips("次卡编号已存在");
			return tips;
		}
		
		if(ChainCardBeanHelper.getInstance().updProductCard(chainCard, updProductCard)){
			ChainCardMgr.getInstance().updateChainCard(chainCard);
		}
		
		//将详情信息也更新
		ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(chainId, updProductCard.getId());
		updProductCard.updateProductCardDetail(detail);
		ProductCardDetailMgr.getInstance().update(detail);
		
		ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
		BUserMessageMgr.getInstance().updateChainProduct(chainId, form);
		tips.setSuccess(true);
		return tips;
	}
	
	//更新次卡状态
	public OperateTips updProductCardState(long chainId, UpdProductCardStateData inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainCardUpdateType.UpdProductCardState.getMark()+"失败");
		
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		if(ChainCardBeanHelper.getInstance().updateProductCardState(chainCard, inputForm)){
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			//将详情信息也更新
			ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setStatus(inputForm.getState());
			ProductCardDetailMgr.getInstance().update(detail);
			
			if(inputForm.getState() == CardStatusEnum.CLOSE.ordinal()) {//下架操作
				updateStoreDataClose(chainId, detail.getApplyStoreIds(), inputForm.getId());
			}else {
				ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
				BUserMessageMgr.getInstance().openChainProduct(chainId, form);
			}	
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private void updateStoreDataClose(long chainId, Set<Long> applyStoreIds, String id) {
		try {
			List<StoreChainUpdateStatusForm> updateStatusForms = new ArrayList<StoreChainUpdateStatusForm>();
			for (Long storeId : applyStoreIds) {
				StoreChainUpdateStatusForm form = StoreChainUpdateStatusForm.newInstance();
				form.setStatus(StoreChainStatus.Close.ordinal());
				form.setId(id);
				form.setItemType(StoreChainItemType.ProductCard.ordinal());
				form.setStoreId(storeId);
				updateStatusForms.add(form);
			}
			StoreChainMgr.getInstance().batchUpdateState(String.valueOf(chainId), updateStatusForms);
		} catch (Exception e) {
			MainLog.error(LogModule.ChainCard, "ProductCardHandle[updateStoreDataClose]", "", e);
		}
	}
	
	//分配次卡
	public OperateTips allotProductCard(long chainId, ProductCardAllotForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false,ChainCardUpdateType.ProductCardAllot.getMark()+"失败");
		
		ChainCard chainCard = ChainCardMgr.getInstance().get(chainId);
		inputForm.getApplyStoreIds().remove(null);
		if(ChainCardBeanHelper.getInstance().allotProductCard(chainCard, inputForm)){
			ChainCardMgr.getInstance().updateChainCard(chainCard);
			
			//将详情信息也更新
			ProductCardDetail detail = ProductCardDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setApplyStoreIds(inputForm.getApplyStoreIds());
			ProductCardDetailMgr.getInstance().update(detail);
			
			tips.setSuccess(true);
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
