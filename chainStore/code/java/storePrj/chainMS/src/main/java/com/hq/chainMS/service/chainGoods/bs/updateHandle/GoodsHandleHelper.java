package com.hq.chainMS.service.chainGoods.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateForm;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsHandleHelper {

	public static GoodsHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsHandleHelper.class);
	}

	private Map<ChainGoodsUpdateType, IGoodsHandle> handleMapper = new HashMap<ChainGoodsUpdateType, IGoodsHandle>();

	public GoodsHandleHelper() {
		handleMapper.put(ChainGoodsUpdateType.AddGoods, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsHandle.getInstance().addGoods(chainId, formInfo.getGoodsAddForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.RemoveGoods, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsHandle.getInstance().removeGoods(chainId, formInfo.getGoodsRemoveForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.UpdateGoods, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsHandle.getInstance().updateGoods(chainId, formInfo.getGoodsUpdateForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.UpdateGoodsState, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsHandle.getInstance().updateGoodsState(chainId, formInfo.getGoodsUpdateStateForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.BatchUpdateGoodsState, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return BatchGoodsHandle.getInstance().batchUpdateGoodsState(chainId, formInfo.getGoodsBatchUpdateStateForm());
			}
		});
		
		handleMapper.put(ChainGoodsUpdateType.AddGoodsType, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsTypeHandle.getInstance().addGoodsType(chainId, formInfo.getGoodsTypeAddForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.RemoveGoodsType, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsTypeHandle.getInstance().removeGoodsType(chainId, formInfo.getGoodsTypeRemoveForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.UpdateGoodsType, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsTypeHandle.getInstance().updateGoodsType(chainId, formInfo.getGoodsTypeUpdateForm());
			}
		});
		
		handleMapper.put(ChainGoodsUpdateType.AllotStore, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return GoodsHandle.getInstance().allotStore(chainId, formInfo.getGoodsAllotForm());
			}
		});
		handleMapper.put(ChainGoodsUpdateType.BatchAllotStore, new IGoodsHandle(){
			@Override
			public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
				return BatchGoodsHandle.getInstance().batchAllotStore(chainId, formInfo.getGoodsBatchAllotForm());
			}
		});
		
	}

	public OperateTips update(long chainId, ChainGoodsUpdateForm formInfo) {
		ChainGoodsUpdateType updateType = formInfo.getChainGoodsUpdateType();
		IGoodsHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(chainId, formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
