package com.hq.storeMS.service.productDetail.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailMgr {

	public static ProductDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailMgr.class);
	}
	
	final private DataVersionEnum dataVersionEnum = DataVersionEnum.ProductDetail;
	
	public void addWithId(ProductDetail target) {
		addImgDefaultPaths(target);
		ProductDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	//填充默认图片   在入口做检验  查询出口不需要再次检查
	private void addImgDefaultPaths(ProductDetail target){
		try {
			if(CollectionUtils.isEmpty(target.getImgPathList())){
				target.getImgPathList().add(ServerConstants.PRODUCT_DEFAULT_PATH);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.ProductDetail, "ProductDetailMgr[addImgDefaultPaths]", "", e);
		}
	}

	public void update(ProductDetail target) {
		ProductDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public void delete(ProductDetail target) {
		ProductDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public ProductDetail get(long storeId, String productId) {
		ProductDetail detail = ProductDetailDataHolder.getInstance().get(storeId, productId);
		if(detail != null) {
			return detail;
		}
		if(storeId == 0) {
			return null;
		}
		return ProductDetailDataHolder.getInstance().get(storeId, AppUtils.joinByUnderline(storeId, productId));
	}
	
	public PageResp<ProductDetail> getProductDetailPageInfo(ProductDetailQueryForm queryForm) {
		List<ProductDetail> list = ProductDetailDataHolder.getInstance().findProductDetailList(queryForm);
		List<ProductDetail> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<ProductDetail> filterRecord(ProductDetailQueryForm queryForm, List<ProductDetail> list){
		List<ProductDetail> result = new ArrayList<ProductDetail>();
		if(CollectionUtils.isNotEmpty(list)){
			for (ProductDetail record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<ProductDetail>() {
			@Override
			public int compare(ProductDetail o1, ProductDetail o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(ProductDetailQueryForm queryForm, ProductDetail record){
		if(!checkState(queryForm.getState(), record)) {
			return false;
		}
		
		if(!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		
		if(!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkState(int state, ProductDetail record){
		if(state == -1) {
			return true;
		}
		
		if(record.getState() == state) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkTypeId(String typeId, ProductDetail record){
		if(StringUtils.isBlank(typeId)) {
			return true;
		}
		
		if(typeId.equals(record.getTypeId())) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkNumberOrName(String numberOrName, ProductDetail record){
		if(StringUtils.isBlank(numberOrName)) {
			return true;
		}
		
		String name = record.getName();
		if(name !=null && name.contains(numberOrName)) {
			return true;
		}
		
		String number = record.getNumber();
		if(number!=null && number.contains(numberOrName)) {
			return true;
		}
		
		return false;
	}
	
	
}
