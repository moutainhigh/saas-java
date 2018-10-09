package com.hq.chainStore.service.appointment.data;

import com.zenmind.dao.rest.ReqMap;

/**
 * 
 * ClassName: AppointmentQueryParams <br/>
 * Function: TODO 预约查询参数，仅仅是方便在各层传递参数，不能用作api接口的参数. <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class AppointmentQueryParams {
	private long minTime;
	private long maxTime;
	private long storeId;
	private long cuserId;
	private String leaguerId;
	private String leaguerName;
	private String status;// 状态，如果需要查询多个，请用,号分割
	private int sort;// 默认是升序
	private long buserId;// 服务人员ID
	private int origin;// 渠道 AppointFromEnums

	private int pageItemCount;
	private int pageNo;

	/** =========================遗留的字段========================= **/
	private Long beauticianId;

	public static AppointmentQueryParams newInstance() {
		AppointmentQueryParams params = new AppointmentQueryParams();
		params.minTime = 0;
		params.maxTime = 0;
		params.storeId = 0;
		params.cuserId = 0;
		params.leaguerId = "";
		params.leaguerName = "";
		params.status = "";
		params.sort = -1;

		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("cuserId", cuserId)
				.add("buserId", buserId).add("leaguerId", leaguerId).add("status", status).add("sort", sort)
				.add("leaguerName", leaguerName).add("origin", origin);
		return reqMap;
	}

	public long getMinTime() {
		return minTime;
	}

	public AppointmentQueryParams setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public AppointmentQueryParams setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public AppointmentQueryParams setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getCuserId() {
		return cuserId;
	}

	public AppointmentQueryParams setCuserId(long cuserId) {
		this.cuserId = cuserId;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public AppointmentQueryParams setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public AppointmentQueryParams setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public AppointmentQueryParams setStatus(String status) {
		this.status = status;
		return this;
	}

	public int getSort() {
		return sort;
	}

	public AppointmentQueryParams setSort(int sort) {
		this.sort = sort;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public AppointmentQueryParams setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public AppointmentQueryParams setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Long getBeauticianId() {
		return beauticianId;
	}

	public AppointmentQueryParams setBeauticianId(Long beauticianId) {
		this.beauticianId = beauticianId;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public AppointmentQueryParams setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public int getOrigin() {
		return origin;
	}

	public AppointmentQueryParams setOrigin(int origin) {
		this.origin = origin;
		return this;
	}

}
