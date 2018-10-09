package com.hq.storeMS.service.appointment.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

/**
 * 
 * ClassName: AppointmentQueryParams <br/>
 * Function: TODO 预约查询参数，仅仅是方便在各层传递参数，不能用作api接口的参数. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */
public class AppointmentQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	private long cuserId;
	private String leaguerId;
	private String leaguerName;
	private String status;// 状态，如果需要查询多个，请用,号分割
	private Set<Integer> statusSet = new HashSet<Integer>();
	private int sort;// 默认是升序
	private long buserId;// 服务人员ID
	private int origin;// 渠道 AppointFromEnums

	private int pageItemCount;
	private int pageNo;

	public static AppointmentQueryForm newInstance() {
		return new AppointmentQueryForm();
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
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

	public String getStatus() {
		return status;
	}

	public AppointmentQueryForm setStatus(String status) {
		this.status = status;
		if (StringUtils.isNoneBlank(status)) {
			String[] ss = status.split(",");
			for (String s : ss) {
				statusSet.add(Integer.valueOf(s));
			}
		}
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

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public int getOrigin() {
		return origin;
	}

	public AppointmentQueryForm setOrigin(int origin) {
		this.origin = origin;
		return this;
	}
}
