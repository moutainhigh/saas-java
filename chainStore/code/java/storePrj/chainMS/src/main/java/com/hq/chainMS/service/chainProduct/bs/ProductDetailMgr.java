package com.hq.chainMS.service.chainProduct.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainMS.service.chainProduct.data.ProductDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.chainMS.service.detailDataVersion.data.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailMgr {

	public static ProductDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailMgr.class);
	}
	
	final private DataVersionEnum dataVersionEnum = DataVersionEnum.ProductDetail;
	
	public void addWithId(ProductDetail target) {
		ProductDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public void update(ProductDetail target) {
		ProductDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public void delete(ProductDetail target) {
		ProductDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public ProductDetail get(long chainId, String id) {
		return ProductDetailDataHolder.getInstance().get(chainId, id);
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
		if(!checkState(queryForm.getStateArray(), record)) {
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
	
	private boolean checkState(Set<Integer> stateArray, ProductDetail record){
		if(CollectionUtils.isEmpty(stateArray)) {
			return true;
		}
		
		if(stateArray.contains(record.getState())) {
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
