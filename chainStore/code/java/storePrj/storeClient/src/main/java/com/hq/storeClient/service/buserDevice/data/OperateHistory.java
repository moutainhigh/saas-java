package com.hq.storeClient.service.buserDevice.data;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 设备操作历史
 * 
 * @Description:
 * @author: wujunwei
 * @date: 2018年2月23日
 * @version: v1.0
 * @since: JDK 1.8
 */
@SynClass
@Table(name = "buserDevice") // 此注解无意义，只是为了拼接客户端请求路径
public class OperateHistory {
	@Id
	private long id;
	// 设备ID
	private String clientId;

	// 操作详情
	private List<OperateDetail> operateList = new ArrayList<OperateDetail>();

	// 创建日期(作为查询和更新的条件，方便操作)
	private String createdDate;

	// 创建时间
	private long createdTime;
	// 最后修改时间
	private long lastUpdateTime;
	// 版本
	private int ver;

	public static OperateHistory newInstance() {
		OperateHistory operateHistory = new OperateHistory();
		long curTime = System.currentTimeMillis();
		operateHistory.createdTime = curTime;
		operateHistory.lastUpdateTime = curTime;
		return operateHistory;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public List<OperateDetail> getOperateList() {
		return operateList;
	}

	public void setOperateList(List<OperateDetail> operateList) {
		this.operateList = operateList;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
