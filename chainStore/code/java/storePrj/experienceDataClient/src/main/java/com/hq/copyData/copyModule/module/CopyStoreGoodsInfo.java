package com.hq.copyData.copyModule.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hq.chainStore.service.common.EntityState;
import com.hq.chainStore.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.chainStore.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.chainStore.service.goodsDetail.data.GoodsDetail;
import com.hq.chainStore.service.storeGoods.apiData.GoodsAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.chainStore.service.storeGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainStore.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.chainStore.service.storeGoods.data.GoodsType;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;

public class CopyStoreGoodsInfo extends AbstractCopyModule {

	public static CopyStoreGoodsInfo newInstance() {
		CopyStoreGoodsInfo data = new CopyStoreGoodsInfo();
		return data;
	}

	public void copy() {
		// 数据准备
		AccessTokenMgr.getInstance().setOpIdTL(getSourceBossId());
		List<GoodsType> types = getGoodsTypes();
		List<GoodsDetail> goods = getGoodsDetails();
		AccessTokenMgr.getInstance().removeOpIdTL();

		// 数据拷贝
		AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(getTargetStoreId());
		addGoodsTypes(storeGoods, types);
		addGoodsDetails(storeGoods, goods);
		AccessTokenMgr.getInstance().removeOpIdTL();

		System.out.println("copy store goods info finish");
	}

	private List<GoodsType> getGoodsTypes() {
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(getSourceStoreId());
		storeGoods.getGoodsTypeMap().remove("0");
		List<GoodsType> types = new ArrayList<GoodsType>(storeGoods.getGoodsTypeMap().values());
		Collections.sort(types, new Comparator<GoodsType>() {
			@Override
			public int compare(GoodsType o1, GoodsType o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return types;
	}

	private List<GoodsDetail> getGoodsDetails() {
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setStoreId(getSourceStoreId());
		return GoodsDetailMgr.getInstance().getGoodsDetailPageInfo(queryForm).getList();
	}

	private void addGoodsTypes(StoreGoods storeGoods, List<GoodsType> types) {
		long goodsTypeIdIndex = storeGoods.getGoodsTypeIdIndex();
		for (GoodsType goodsType : types) {
			goodsTypeIdIndex++;
			GoodsTypeAddForm dataForm = GoodsTypeAddForm.newInstance();
			dataForm.setIndex(goodsTypeIdIndex);
			dataForm.setName(goodsType.getName());
			StoreGoodsMgr.getInstance().addGoodsType(getTargetStoreId(), dataForm);

			if (goodsType.getEntityState() == EntityState.Deleted.ordinal()) {
				GoodsTypeRemoveForm removeForm = GoodsTypeRemoveForm.newInstance();
				removeForm.setGoodsTypeId(goodsType.getId());
				StoreGoodsMgr.getInstance().removeGoodsType(getTargetStoreId(), removeForm);
			}
		}
	}

	private void addGoodsDetails(StoreGoods storeGoods, List<GoodsDetail> goods) {
		long goodsIdIndex = storeGoods.getGoodsIdIndex();
		for (GoodsDetail info : goods) {
			if (info.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			goodsIdIndex++;
			GoodsAddForm dataForm = GoodsAddForm.newInstance();
			BeanUtils.copyProperties(info, dataForm, "id");
			dataForm.setIndex(goodsIdIndex);
			StoreGoodsMgr.getInstance().addGoods(getTargetStoreId(), dataForm);
		}
	}
}
