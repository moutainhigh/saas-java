package com.hq.storeMS.service.sellItem.bs;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productDetail.bs.ProductDetailMgr;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.sellItem.apiData.SellItemIdForm;
import com.hq.storeMS.service.sellItem.apiData.SellItemQueryForm;
import com.hq.storeMS.service.sellItem.data.SellItem;
import com.hq.storeMS.service.sellItem.data.SellItemTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SellItemMgr {

	public static SellItemMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SellItemMgr.class);
	}
	
	public List<SellItem> getSellItemList(SellItemQueryForm queryForm) {
		List<SellItem> list = new ArrayList<SellItem>();
		List<SellItemIdForm> sellItemIds = queryForm.getSellItemIds();
		long storeId = queryForm.getStoreId();
		for (SellItemIdForm sellItemIdForm : sellItemIds) {
			SellItem target = getSellItemByIdForm(storeId, sellItemIdForm);
			if(target!=null) {
				list.add(target);
			}
		}
		return list;
	}
	
	public SellItem getSellItemByIdForm(long storeId, SellItemIdForm sellItemIdForm) {
		SellItem item = null;
		String id = sellItemIdForm.getId();
		SellItemTypeEnum sellItemTypeEnum = sellItemIdForm.getSellItemTypeEnum();
		switch (sellItemTypeEnum) {
		case GOODS:
			GoodsDetail goodsDetail = GoodsDetailMgr.getInstance().get(storeId, id);
			item = SellItem.newInstanceByGoodsDetail(goodsDetail);
			break;
		case PACKAGE:
			PackageProjectDetail packageProjectDetail = PackageProjectDetailMgr.getInstance().get(storeId, id);
			item = SellItem.newInstanceByPackageProjectDetail(packageProjectDetail);
			break;
		case PRDCARD:
			ProductCardDetail productCardDetail = ProductCardDetailMgr.getInstance().get(storeId, id);
			item = SellItem.newInstanceByProductCardDetail(productCardDetail);
			break;
		case PRODUCT:
			ProductDetail productDetail = ProductDetailMgr.getInstance().get(storeId, id);
			item = SellItem.newInstanceByProductDetail(productDetail);
			break;
		default:
			break;
		}
		return item;
	}
}
