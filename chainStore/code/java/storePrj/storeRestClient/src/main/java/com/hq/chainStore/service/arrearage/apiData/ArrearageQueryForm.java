package com.hq.chainStore.service.arrearage.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.common.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

/**
 * 
 * ClassName: ArrearageQueryParams <br/>
 * Function: TODO 预约查询参数，仅仅是方便在各层传递参数，不能用作api接口的参数. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class ArrearageQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;
	
	private String leaguerId;
	private String leaguerNameOrPhone;
	private Set<Integer> statusSet = new HashSet<Integer>();
	private int pageItemCount;
	private int pageNo;
	
	public static ArrearageQueryForm newInstance() {
		return new ArrearageQueryForm();
	}
	
	public ReqMap toReqMap(){
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId).add("leaguerId", leaguerId)
			.add("leaguerNameOrPhone", leaguerNameOrPhone).add("status", StringUtils4Client.join(statusSet, ","));
		return reqMap;
	}

	public long getMinTime() {
		return minTime;
	}

	public ArrearageQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public ArrearageQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public ArrearageQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public ArrearageQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ArrearageQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ArrearageQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getLeaguerNameOrPhone() {
		return leaguerNameOrPhone;
	}

	public ArrearageQueryForm setLeaguerNameOrPhone(String leaguerNameOrPhone) {
		this.leaguerNameOrPhone = leaguerNameOrPhone;
		return this;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public ArrearageQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
		return this;
	}
	
	public void addStatus(Integer status){
		this.statusSet.add(status);
	}
	
}
