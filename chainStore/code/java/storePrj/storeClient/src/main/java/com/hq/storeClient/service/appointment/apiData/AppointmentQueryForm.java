package com.hq.storeClient.service.appointment.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class AppointmentQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	private long cuserId;
	private String leaguerId;
	private String leaguerName;
	private Set<Integer> status = new HashSet<Integer>();
	private int sort;// 默认是升序
	private long buserId;// 服务人员ID
	private int origin;// 渠道 AppointFromEnums

	private int pageItemCount;
	private int pageNo;

	public static AppointmentQueryForm newInstance() {
		return new AppointmentQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("cuserId", cuserId)
				.add("leaguerId", leaguerId).add("leaguerName", leaguerName)
				.add("status", StringUtils4Client.join(status, ",")).add("sort", sort).add("buserId", buserId)
				.add("origin", origin);
		return reqMap;
	}

	public long getMinTime() {
		return minTime;
	}

	public AppointmentQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public AppointmentQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public AppointmentQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getCuserId() {
		return cuserId;
	}

	public AppointmentQueryForm setCuserId(long cuserId) {
		this.cuserId = cuserId;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public AppointmentQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public int getSort() {
		return sort;
	}

	public AppointmentQueryForm setSort(int sort) {
		this.sort = sort;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public AppointmentQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public AppointmentQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getLeaguerName() {
		return leaguerName;
	}

	public AppointmentQueryForm setLeaguerName(String leaguerName) {
		this.leaguerName = leaguerName;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public AppointmentQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public int getOrigin() {
		return origin;
	}

	public AppointmentQueryForm setOrigin(int origin) {
		this.origin = origin;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public AppointmentQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}
}
