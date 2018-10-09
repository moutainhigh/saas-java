package com.hq.storeMS.service.chainGoods.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainClient.service.chainGoods.bs.ChainGoodsClientMgr;
import com.hq.chainClient.service.chainGoods.bs.GoodsDetailClientMgr;
import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.chainGoods.data.ChainGoodsCacheDAO;
import com.hq.storeMS.service.chainGoods.data.GoodsDetailCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainGoodsDataHolder implements IntfDataHolder{
	
	public static ChainGoodsDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainGoodsDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainGoods;
	
	public ChainGoods get(long id) {
		ChainGoods data = ChainGoodsCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChainGoodsClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public GoodsDetail getGoodsDetail(String goodsId, long chainId) {
		GoodsDetail data = GoodsDetailCacheDAO.getInstance().get(chainId, goodsId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = GoodsDetailClientMgr.getInstance().getGoodsDetail(chainId, goodsId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber(id)){
			ChainGoods target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ChainGoods, "ChainGoodsDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ChainGoods, "ChainGoodsDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}
