package com.hq.storeMS.service.storeCardInfo.bs.update;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.ProductCardItem;
import com.hq.chainClient.service.chainCard.data.ProductCardItemEnum;
import com.hq.storeMS.service.chainCard.bs.ChainCardMgr;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataSynBeanHelper;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.storeCardInfo.apiData.CardBatchCancelForm;
import com.hq.storeMS.service.storeCardInfo.apiData.CardBatchPullForm;
import com.hq.storeMS.service.storeCardInfo.apiData.CardCancelForm;
import com.hq.storeMS.service.storeCardInfo.apiData.CardPullForm;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.apiData.GoodsBatchPullForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsPullForm;
import com.hq.storeMS.service.storeGoods.bs.update.ChGoodsMgr;
import com.hq.storeMS.service.storePackageProject.apiData.PackageBatchPullForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackagePullForm;
import com.hq.storeMS.service.storePackageProject.bs.update.ChPackageProjectMgr;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductBatchPullForm;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductPullForm;
import com.hq.storeMS.service.storeProductInfo.bs.update.ChProductMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainProductCardMgr {
	public static ChainProductCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductCardMgr.class);
	}

	public OperateTips batchCancelChainCard(long storeId, CardBatchCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.BatchCancelChainCard.getMark() + "失败");
		List<CardCancelForm> cancelForms = inputForm.getCancelForms();
		for (CardCancelForm form : cancelForms) {
			cancelChainCard(storeId, form);
		}
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips batchAddChainCard(long storeId, CardBatchPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.BatchPullCardFromChain.getMark() + "失败");
		List<CardPullForm> forms = inputForm.getPullForms();
		for (CardPullForm form : forms) {
			addChainCard(storeId, form);
		}
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips cancelChainCard(long storeId, CardCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.CancelChainCard.getMark() + "失败");
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		ProductCard productCard = storeData.getProductCardMap().get(inputForm.getId());
		if(productCard!=null) {
			productCard.setEntityState(EntityState.Deleted.ordinal());
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		}
		
		ProductCardDetail sdetail = ProductCardDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail!=null) {
			sdetail.setEntityState(EntityState.Deleted.ordinal());
			ProductCardDetailMgr.getInstance().update(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips addChainCard(long storeId, CardPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StoreCardInfoUpdateType.PullCardFromChain.getMark() + "失败");
		com.hq.chainClient.service.chainCard.data.ProductCardDetail cdetail = ChainCardMgr.getInstance().getProductCardDetail(inputForm.getId(), inputForm.getChainId());
		OperateTips synContent = this.synContent(cdetail, inputForm.getChainId(), storeId);
		if(!synContent.isSuccess()){
			tips.setSuccess(false);
			tips.setTips(synContent.getTips());
			return tips;
		}
		
		StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		ChainCard chainData = ChainCardMgr.getInstance().getChainCard(inputForm.getChainId());
		ChainDataSynBeanHelper.getInstance().synStoreProductCard(storeData, chainData, inputForm.getId());
		
		boolean updFlag = true;
		ProductCardDetail sdetail = ProductCardDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail == null) {
			sdetail = ProductCardDetail.newInstance();
			updFlag = false;
		}
		ChainDataSynBeanHelper.getInstance().synProductCardDetail(storeId, sdetail, cdetail);
		
		StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		if(updFlag) {
			ProductCardDetailMgr.getInstance().update(sdetail);
		}else {
			ProductCardDetailMgr.getInstance().addWithId(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	private OperateTips synContent(com.hq.chainClient.service.chainCard.data.ProductCardDetail cdetail,long chainId,long storeId){
		OperateTips tips = OperateTips.newInstance(true);
		List<ProductCardItem> productCardItems = cdetail.getProductCardItems();
		List<ProductPullForm> productPullForms = new ArrayList<ProductPullForm>();
		List<PackagePullForm> packagePullForms = new ArrayList<PackagePullForm>();
		List<GoodsPullForm> goodsPullForms = new ArrayList<GoodsPullForm>();
		for (ProductCardItem productCardItem : productCardItems) {
			ProductCardItemEnum productCardItemEnum = ProductCardItemEnum.valueOf(productCardItem.getItemType());
			switch (productCardItemEnum) {
			case PRODUCT:
				ProductPullForm productPullForm = ProductPullForm.newInstance();
				productPullForm.setChainId(chainId);
				productPullForm.setId(productCardItem.getPgId());
				productPullForms.add(productPullForm);
				break;
			case GOODS:
				GoodsPullForm goodsPullForm = GoodsPullForm.newInstance();
				goodsPullForm.setChainId(chainId);
				goodsPullForm.setId(productCardItem.getPgId());
				goodsPullForms.add(goodsPullForm);
				break;
			case PACKAGE:
				PackagePullForm packagePullForm = PackagePullForm.newInstance();
				packagePullForm.setChainId(chainId);
				packagePullForm.setId(productCardItem.getPgId());
				packagePullForms.add(packagePullForm);
				break;

			default:
				break;
			}
		}
		if(CollectionUtils.isNotEmpty(productPullForms)){
			ProductBatchPullForm productBatchPullForm = ProductBatchPullForm.newInstance();
			productBatchPullForm.setPullForms(productPullForms);
			OperateTips batchAddProduct = ChProductMgr.getInstance().batchAddChainProduct(storeId, productBatchPullForm);
			if(!batchAddProduct.isSuccess()){
				tips.setSuccess(false);
				tips.setTips("同步次卡内项目失败");
				return tips;
			}
		}
		
		if(CollectionUtils.isNotEmpty(goodsPullForms)){
			GoodsBatchPullForm goodsBatchPullForm = GoodsBatchPullForm.newInstance();
			goodsBatchPullForm.setGoodsPullForms(goodsPullForms);
			OperateTips batchAddGoods = ChGoodsMgr.getInstance().batchAddChainGoods(storeId, goodsBatchPullForm);
			if(!batchAddGoods.isSuccess()){
				tips.setSuccess(false);
				tips.setTips("同步次卡内商品失败");
				return tips;
			}
		}
		
		if(CollectionUtils.isNotEmpty(packagePullForms)){
			PackageBatchPullForm packageBatchPullForm = PackageBatchPullForm.newInstance();
			packageBatchPullForm.setPullForms(packagePullForms);
			OperateTips batchAddPackage = ChPackageProjectMgr.getInstance().batchAddChainPackage(storeId, packageBatchPullForm);
			if(!batchAddPackage.isSuccess()){
				tips.setSuccess(false);
				tips.setTips("同步次卡内套餐失败");
				return tips;
			}
		}
		return tips;
	}
}
