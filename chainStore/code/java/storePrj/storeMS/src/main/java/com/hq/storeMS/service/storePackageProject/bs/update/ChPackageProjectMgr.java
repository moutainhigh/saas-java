package com.hq.storeMS.service.storePackageProject.bs.update;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageItem;
import com.hq.chainClient.service.chainPackageProject.data.PackageItemEnum;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataSynBeanHelper;
import com.hq.storeMS.service.chainPackageProject.bs.ChainPackageProjectMgr;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.storeGoods.apiData.GoodsBatchPullForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsPullForm;
import com.hq.storeMS.service.storeGoods.bs.update.ChGoodsMgr;
import com.hq.storeMS.service.storePackageProject.apiData.PackageBatchCancelForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageBatchPullForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackageCancelForm;
import com.hq.storeMS.service.storePackageProject.apiData.PackagePullForm;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductBatchPullForm;
import com.hq.storeMS.service.storeProductInfo.apiData.ProductPullForm;
import com.hq.storeMS.service.storeProductInfo.bs.update.ChProductMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ChPackageProjectMgr {
	public static ChPackageProjectMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChPackageProjectMgr.class);
	}

	public OperateTips batchCancelChainProduct(long storeId, PackageBatchCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.BatchCancelChainPackage.getMark() + "失败");
		List<PackageCancelForm> cancelForms = inputForm.getCancelForms();
		for (PackageCancelForm form : cancelForms) {
			cancelChainPackage(storeId, form);
		}
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips batchAddChainPackage(long storeId, PackageBatchPullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.BatchPullPackageFromChain.getMark() + "失败");
		List<PackagePullForm> forms = inputForm.getPullForms();
		for (PackagePullForm form : forms) {
			addChainPackage(storeId, form);
		}
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips cancelChainPackage(long storeId, PackageCancelForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.CancelChainPackage.getMark() + "失败");
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		PackageProject packageProject = storeData.getPackageProjectMap().get(inputForm.getId());
		if(packageProject!=null) {
			packageProject.setEntityState(EntityState.Deleted.ordinal());
			StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
		}
		PackageProjectDetail sdetail = PackageProjectDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail!=null) {
			sdetail.setEntityState(EntityState.Deleted.ordinal());
			PackageProjectDetailMgr.getInstance().update(sdetail);
		}
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips addChainPackage(long storeId, PackagePullForm inputForm) {
		OperateTips tips = OperateTips.newInstance(false, StorePackageProjectUpdateType.PullPackageFromChain.getMark() + "失败");
		com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail cdetail = ChainPackageProjectMgr.getInstance().getPackageProjectDetail(inputForm.getId(), inputForm.getChainId());
		OperateTips synContent = this.synContent(cdetail, inputForm.getChainId(), storeId);
		if(!synContent.isSuccess()){
			tips.setSuccess(false);
			tips.setTips(synContent.getTips());
			return tips;
		}
		StorePackageProject storeData = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		ChainPackageProject chainData = ChainPackageProjectMgr.getInstance().getChainPackageProject(inputForm.getChainId());
		ChainDataSynBeanHelper.getInstance().synStorePackageProject(storeData, chainData, inputForm.getId());
		
		boolean updFlag = true;
		PackageProjectDetail sdetail = PackageProjectDetailMgr.getInstance().get(storeId, inputForm.getId());
		if(sdetail == null) {
			sdetail = PackageProjectDetail.newInstance();
			updFlag = false;
		}
		ChainDataSynBeanHelper.getInstance().synPackageProjectDetail(storeId, sdetail, cdetail);
		
		StorePackageProjectMgr.getInstance().updateStorePackageProject(storeData);
		if(updFlag) {
			PackageProjectDetailMgr.getInstance().update(sdetail);
		}else {
			PackageProjectDetailMgr.getInstance().addWithId(sdetail);
		}
		
		tips.setSuccess(true);
		return tips;
	}
	
	private OperateTips synContent(com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail cdetail,long chainId,long storeId){
		OperateTips tips = OperateTips.newInstance(true);
		List<PackageItem> packageItems = cdetail.getPackageItems();
		List<ProductPullForm> productPullForms = new ArrayList<ProductPullForm>();
		List<GoodsPullForm> goodsPullForms = new ArrayList<GoodsPullForm>();
		for (PackageItem packageItem : packageItems) {
			PackageItemEnum packageItemEnum = PackageItemEnum.valueOf(packageItem.getItemType());
			switch (packageItemEnum) {
			case PRODUCT:
				ProductPullForm productPullForm = ProductPullForm.newInstance();
				productPullForm.setChainId(chainId);
				productPullForm.setId(packageItem.getPgId());
				productPullForms.add(productPullForm);
				break;
			case GOODS:
				GoodsPullForm goodsPullForm = GoodsPullForm.newInstance();
				goodsPullForm.setChainId(chainId);
				goodsPullForm.setId(packageItem.getPgId());
				goodsPullForms.add(goodsPullForm);
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
				tips.setTips("同步套餐内项目失败");
				return tips;
			}
		}
		
		if(CollectionUtils.isNotEmpty(goodsPullForms)){
			GoodsBatchPullForm goodsBatchPullForm = GoodsBatchPullForm.newInstance();
			goodsBatchPullForm.setGoodsPullForms(goodsPullForms);
			OperateTips batchAddGoods = ChGoodsMgr.getInstance().batchAddChainGoods(storeId, goodsBatchPullForm);
			if(!batchAddGoods.isSuccess()){
				tips.setSuccess(false);
				tips.setTips("同步套餐内商品失败");
				return tips;
			}
		}
		return tips;
	}
}
