package com.hq.chainMS.service.chainCard.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.data.ProductCardDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.chainMS.service.detailDataVersion.data.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailMgr {

	public static ProductCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailMgr.class);
	}
	
	private final DataVersionEnum dataVersionEnum = DataVersionEnum.ProductCardDetail;
	
	public void addWithId(ProductCardDetail target) {
		ProductCardDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}

	public void update(ProductCardDetail target) {
		ProductCardDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public void delete(ProductCardDetail target) {
		ProductCardDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public ProductCardDetail get(long chainId, String id) {
		return ProductCardDetailDataHolder.getInstance().get(chainId, id);
	}
	
	public List<ProductCardDetail> getListByChainId(long chainId) {
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setChainId(chainId);
		return ProductCardDetailDataHolder.getInstance().findProductCardDetailList(queryForm);
	}
	
	public PageResp<ProductCardDetail> getProductCardDetailPageInfo(ProductCardDetailQueryForm queryForm) {
		List<ProductCardDetail> list = ProductCardDetailDataHolder.getInstance().findProductCardDetailList(queryForm);
		List<ProductCardDetail> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<ProductCardDetail> filterRecord(ProductCardDetailQueryForm queryForm, List<ProductCardDetail> list){
		List<ProductCardDetail> result = new ArrayList<ProductCardDetail>();
		if(CollectionUtils.isNotEmpty(list)){
			for (ProductCardDetail record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		
		Collections.sort(result, new Comparator<ProductCardDetail>() {
			@Override
			public int compare(ProductCardDetail o1, ProductCardDetail o2) {
				 return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(ProductCardDetailQueryForm queryForm, ProductCardDetail record){
		if(!checkCardNameOrNumber(queryForm.getCardNameOrNumber(), record)) {
			return false;
		}
		
		if(!checkStatus(queryForm.getStatusSet(), record)) {
			return false;
		}
		
		if(!checkTypeId(queryForm.getTypeId(), record)) {
			return false;
		}
		return true;
	}
	
	private boolean checkCardNameOrNumber(String cardNameOrNumber, ProductCardDetail record) {
		if(StringUtils.isBlank(cardNameOrNumber)) {
			return true;
		}
		if(record.getName()!=null && record.getName().contains(cardNameOrNumber)) {
			return true;
		}
		if(record.getNumber()!=null && record.getNumber().contains(cardNameOrNumber)) {
			return true;
		}
		return false;
	}
	
	private boolean checkStatus(Set<Integer> statusSet, ProductCardDetail record) {
		if(CollectionUtils.isNotEmpty(statusSet) && !statusSet.contains(record.getStatus())) {
			return false;
		}
		return true;
	}
	
	private boolean checkTypeId(String typeId, ProductCardDetail record) {
		if(StringUtils.isNoneBlank(typeId) && !typeId.equals(record.getTypeId())) {
			return false;
		}
		return true;
	}
}
