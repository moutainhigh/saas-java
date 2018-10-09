package com.hq.chainMS.service.sellProduct.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.chainCard.apiData.ProductCardAllotForm;
import com.hq.chainMS.service.chainCard.apiData.UpdProductCardStateData;
import com.hq.chainMS.service.chainCard.bs.ChainCardMgr;
import com.hq.chainMS.service.chainCard.bs.updateHandle.ProductCardHandle;
import com.hq.chainMS.service.chainGoods.apiData.GoodsAllotForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainMS.service.chainGoods.bs.ChainGoodsMgr;
import com.hq.chainMS.service.chainGoods.bs.updateHandle.GoodsHandle;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectAllotForm;
import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.chainMS.service.chainPackageProject.bs.ChainPackageProjectMgr;
import com.hq.chainMS.service.chainPackageProject.bs.updateHandle.PackageProjectHandle;
import com.hq.chainMS.service.chainProduct.apiData.ProductAllotForm;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductStateData;
import com.hq.chainMS.service.chainProduct.bs.ChainProductMgr;
import com.hq.chainMS.service.chainProduct.bs.updateHandle.ProductHandle;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.sellProduct.apiData.SellProductAllotForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductBatchAllotForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductBatchUpdateStateForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductQueryForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductUpdateStateForm;
import com.hq.chainMS.service.sellProduct.data.SellProduct;
import com.hq.chainMS.service.sellProduct.data.SellProductTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SellProductMgr {

	public static SellProductMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SellProductMgr.class);
	}
	
	public void batchUpdateSellProductState(long chainId, SellProductBatchUpdateStateForm inputForm) {
		List<SellProductUpdateStateForm> list = inputForm.getSellProductUpdateStateForms();
		for (SellProductUpdateStateForm form : list) {
			updateSellProductState(chainId, form);
		}
	}
	
	public void batchAllotSellProduct(long chainId, SellProductBatchAllotForm inputForm) {
		List<SellProductAllotForm> list = inputForm.getProductAllotForms();
		for (SellProductAllotForm form : list) {
			allotSellProduct(chainId, form);
		}
	}

	public void allotSellProduct(long chainId, SellProductAllotForm inputForm) {
		SellProductTypeEnum sellProductTypeEnum = inputForm.getSellProductTypeEnum();
		switch (sellProductTypeEnum) {
		case GOODS:
			GoodsAllotForm goodsAllotForm =  GoodsAllotForm.newInstance(inputForm.getSellAllotId().getId(), inputForm.getApplyStoreIds());
			GoodsHandle.getInstance().allotStore(chainId, goodsAllotForm);
			break;
		case PACKAGE:
			PackageProjectAllotForm packageProjectAllotForm =  PackageProjectAllotForm.newInstance(inputForm.getSellAllotId().getId(), inputForm.getApplyStoreIds());
			PackageProjectHandle.getInstance().allotStore(chainId, packageProjectAllotForm);
			break;
		case PRODUCT:
			ProductAllotForm productAllotForm =  ProductAllotForm.newInstance(inputForm.getSellAllotId().getId(), inputForm.getApplyStoreIds());
			ProductHandle.getInstance().allotStore(chainId, productAllotForm);
			break;
		case PRDCARD:
			ProductCardAllotForm productCardAllotForm =  ProductCardAllotForm.newInstance(inputForm.getSellAllotId().getId(), inputForm.getApplyStoreIds());
			ProductCardHandle.getInstance().allotProductCard(chainId, productCardAllotForm);
			break;
		default:
			break;
		}
	}
	
	public void updateSellProductState(long chainId, SellProductUpdateStateForm inputForm) {
		SellProductTypeEnum sellProductTypeEnum = inputForm.getSellProductTypeEnum();
		switch (sellProductTypeEnum) {
		case GOODS:
			GoodsUpdateStateForm goodsForm =  GoodsUpdateStateForm.newInstance(inputForm.getSellAllotId().getId(), inputForm.getState());
			GoodsHandle.getInstance().updateGoodsState(chainId, goodsForm);
			break;
		case PACKAGE:
			PackageProjectUpdateStateForm packageForm =  PackageProjectUpdateStateForm.newInstance(inputForm.getSellAllotId().getId(), inputForm.getState());
			PackageProjectHandle.getInstance().updPackageProjectState(chainId, packageForm);
			break;
		case PRODUCT:
			UpdateProductStateData productForm =  UpdateProductStateData.newInstance(inputForm.getSellAllotId().getId(), inputForm.getState());
			ProductHandle.getInstance().updateProductState(chainId, productForm);
			break;
		case PRDCARD:
			UpdProductCardStateData productCardForm =  UpdProductCardStateData.newInstance(inputForm.getSellAllotId().getId(), inputForm.getState());
			ProductCardHandle.getInstance().updProductCardState(chainId, productCardForm);
			break;
		default:
			break;
		}
	}
	
	public PageResp<SellProduct> getPageInfo(SellProductQueryForm queryForm) {
		List<SellProduct> list = getSellProducts(queryForm.getChainId());
		List<SellProduct> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<SellProduct> getSellProducts(long chainId){
		List<SellProduct> result = new ArrayList<SellProduct>();
		result.addAll(ChainGoodsMgr.getInstance().getSellProducts(chainId));
		result.addAll(ChainProductMgr.getInstance().getSellProducts(chainId));
		result.addAll(ChainPackageProjectMgr.getInstance().getSellProducts(chainId));
		result.addAll(ChainCardMgr.getInstance().getSellProducts(chainId));
		return result;
	}
	
	private List<SellProduct> filterRecord(SellProductQueryForm queryForm, List<SellProduct> list) {
		List<SellProduct> result = new ArrayList<SellProduct>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (SellProduct record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<SellProduct>() {
			@Override
			public int compare(SellProduct o1, SellProduct o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return result;
	}

	private boolean isRightRecord(SellProductQueryForm queryForm, SellProduct record) {
		if (!checkSellProductType(queryForm.getSellProductTypeArray(), record)) {
			return false;
		}
		if (!checkState(queryForm.getStateArray(), record)) {
			return false;
		}
		if (!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		if (!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}
		return true;
	}
	
	private boolean checkSellProductType(Set<Integer> sellProductTypeArray, SellProduct record) {
		if (CollectionUtils.isEmpty(sellProductTypeArray)) {
			return true;
		}
		
		if (sellProductTypeArray.contains(record.getSellProductType())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkState(Set<Integer> stateArray, SellProduct record) {
		if (CollectionUtils.isEmpty(stateArray)) {
			return true;
		}

		if (stateArray.contains(record.getState())) {
			return true;
		}

		return false;
	}
	
	private boolean checkTypeId(String typeId, SellProduct record) {
		if (StringUtils.isBlank(typeId)) {
			return true;
		}
		
		if (typeId.equals(record.getTypeId())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkNumberOrName(String numberOrName, SellProduct record) {
		if (StringUtils.isBlank(numberOrName)) {
			return true;
		}
		
		if (record.getName().contains(numberOrName)) {
			return true;
		}
		
		if (record.getNumber().contains(numberOrName)) {
			return true;
		}
		
		return false;
	}
}
