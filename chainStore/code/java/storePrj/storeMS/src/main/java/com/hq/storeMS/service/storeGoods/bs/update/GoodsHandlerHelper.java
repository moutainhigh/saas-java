package com.hq.storeMS.service.storeGoods.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateForm;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsHandlerHelper {

	public static GoodsHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsHandlerHelper.class);
	}

	private Map<StoreGoodsUpdateType, IGoodsHandler> handleMapper = new HashMap<StoreGoodsUpdateType, IGoodsHandler>();

	public GoodsHandlerHelper() {
		handleMapper.put(StoreGoodsUpdateType.AddGoods, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsMgr.getInstance().addGoods(storeId, formInfo.getGoodsAddForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.AddListFromExcel, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchStGoodsMgr.getInstance().addListFromExcel(storeId, formInfo.getAddListFromExcel());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.AddListFromStore, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchStGoodsMgr.getInstance().addListFromStore(storeId, formInfo.getAddListFromStore());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.RemoveGoods, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsMgr.getInstance().removeGoods(storeId, formInfo.getGoodsRemoveForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.UpdateGoods, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsMgr.getInstance().updateGoods(storeId, formInfo.getGoodsUpdateForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.UpdateGoodsState, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsMgr.getInstance().updateGoodsState(storeId, formInfo.getGoodsUpdateStateForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.BatchUpdateGoodsState, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return BatchStGoodsMgr.getInstance().batchUpdateGoodsState(storeId, formInfo.getGoodsBatchUpdateStateForm());
			}
		});
		
		handleMapper.put(StoreGoodsUpdateType.AddGoodsType, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsTypeMgr.getInstance().addGoodsType(storeId, formInfo.getGoodsTypeAddForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.RemoveGoodsType, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsTypeMgr.getInstance().removeGoodsType(storeId, formInfo.getGoodsTypeRemoveForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.UpdateGoodsType, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsTypeMgr.getInstance().updateGoodsType(storeId, formInfo.getGoodsTypeUpdateForm());
			}
		});
		
		handleMapper.put(StoreGoodsUpdateType.AddGoodsToTop, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsTopMgr.getInstance().addToTop(storeId, formInfo.getGoodsAddToTopForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.CancelGoodsFromTop, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.GOODS_ADMIN);
				return StGoodsTopMgr.getInstance().cancelTop(storeId, formInfo.getGoodsCancelTopForm());
			}
		});
		
		handleMapper.put(StoreGoodsUpdateType.BatchCancelChainGoods, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChGoodsMgr.getInstance().batchCancelChainGoods(storeId, formInfo.getGoodsBatchCancelForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.BatchPullGoodsFromChain, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChGoodsMgr.getInstance().batchAddChainGoods(storeId, formInfo.getGoodsBatchPullForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.CancelChainGoods, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChGoodsMgr.getInstance().cancelChainGoods(storeId, formInfo.getGoodsCancelForm());
			}
		});
		handleMapper.put(StoreGoodsUpdateType.PullGoodsFromChain, new IGoodsHandler(){
			@Override
			public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChGoodsMgr.getInstance().addChainGoods(storeId, formInfo.getGoodsPullForm());
			}
		});
	}

	public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo) {
		StoreGoodsUpdateType updateType = formInfo.getStoreGoodsUpdateType();
		IGoodsHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(storeId, formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}


}
