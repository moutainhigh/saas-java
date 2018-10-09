package com.hq.chainStore.service.schedule.apidata;

/**
 * 
 * ClassName: ScheduleQueryParams <br/>
 * Function: TODO 待办事项查询参数，方便在各层传递参数，不用作api接口的参数. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * 
 * @author helen
 * @version
 * @since JDK 1.8
 */

public class ScheduleQueryParams {

	private Long storeId;
	private Long beauticianId;
	private String leaguerId;
	private Integer statu;// 状态

	private Integer pageItemCount;
	private Integer pageNo;

	public static ScheduleQueryParams newInstance() {
		ScheduleQueryParams params = new ScheduleQueryParams();

		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(Long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

	public Integer getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(Integer pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}
