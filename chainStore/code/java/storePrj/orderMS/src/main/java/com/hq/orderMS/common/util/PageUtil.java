package com.hq.orderMS.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderMS.common.constants.ServerConstants;
import com.hq.orderMS.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;

public class PageUtil {
	
	public static PageUtil getInstance(){
		return HotSwap.getInstance().getSingleton(PageUtil.class);
	}
	
	public <T> List<T> getPageItemList(List<T> list, int pageNo, int pageItemCount){
		if(CollectionUtils.isEmpty(list)){
			return new ArrayList<T>();
		}
		if(pageNo < 1){
			pageNo = 1;
		}
		if(pageItemCount < 1){
			pageItemCount = ServerConstants.PAGE_ITEM_COUNT;
		}
		int fromIndex = (pageNo-1)*pageItemCount;
		int toIndex = pageNo * pageItemCount > list.size() ? list.size() : pageNo * pageItemCount;
		
		if(fromIndex >= toIndex) {
			return new ArrayList<T>();
		}
		
		return list.subList(fromIndex, toIndex);
	}
	
	public int getTotalPage(int totalCount, int pageItemCount) {
		if(pageItemCount < 1){
			pageItemCount = ServerConstants.PAGE_ITEM_COUNT;
		}
		int totalPage = 1;
		if (totalCount % pageItemCount == 0) {
			totalPage = totalCount / pageItemCount;
		} else {
			totalPage = totalCount / pageItemCount + 1;
		}
		return totalPage;
	}
	
	public <T> PageResp<T> buildPageResp(List<T> list, int pageNo, int pageItemCount){
		PageResp<T> pageResp = PageResp.newInstance();
		pageResp.setPageNo(pageNo);
		pageResp.setPageItemCount(pageItemCount);
		pageResp.setTotalCount(list.size());
		pageResp.setList(getPageItemList(list, pageNo, pageItemCount));
		pageResp.setTotalPage(getTotalPage(list.size(), pageItemCount));
		return pageResp;
	}
	
}
