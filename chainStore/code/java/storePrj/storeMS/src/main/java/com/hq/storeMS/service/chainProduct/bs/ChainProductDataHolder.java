package com.hq.storeMS.service.chainProduct.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainClient.service.chainProduct.bs.ChainProductClientMgr;
import com.hq.chainClient.service.chainProduct.bs.ProductDetailClientMgr;
import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.chainProduct.data.ChainProductCacheDAO;
import com.hq.storeMS.service.chainProduct.data.ProductDetailCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainProductDataHolder implements IntfDataHolder{
	
	public static ChainProductDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainProductDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.ChainProduct;
	
	public ChainProduct get(long id) {
		ChainProduct data = ChainProductCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChainProductClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public ProductDetail getProductDetail(String productId, long chainId) {
		ProductDetail data = ProductDetailCacheDAO.getInstance().get(chainId, productId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ProductDetailClientMgr.getInstance().getProductDetail(chainId, productId);
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
			ChainProduct target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.ChainProduct, "ChainProductDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.ChainProduct, "ChainProductDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}
