package com.hq.chainMS.service.common;

import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 转换分页实体bean
 * @author Administrator
 */
@SynClass
public class PageResp<T> {
	private int pageNo; // 当前页数
	private long totalCount; // 总记录数
	private int totalPage; // 总页数
	private int pageItemCount; // 每页显示的记录数
	private List<T> list; // 每页显示数据记录的集合
	
	public static <T> PageResp<T> newInstance(){
		PageResp<T> pageResp = new PageResp<T>();
		return pageResp;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
