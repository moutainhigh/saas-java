package com.hq.storeMS.service.leaguerRecord.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "leaguerRecord")
public class LeaguerRecord {
	@Id
	private long id;

	@IndexField
	private long storeId;

	// 客户ID
	private String leaguerId;
	// 标题
	private String title;
	// 内容
	private String content;
	// 图片
	private List<String> imgPaths = new ArrayList<String>();
	// 关联订单
	private RelateOrder relateOrder;
	// 关联项目
	private RelateProduct relateProduct;
	// 关联流程
	private long workFlowDataId;

	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;

	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static LeaguerRecord newInstance() {
		LeaguerRecord data = new LeaguerRecord();

		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public RelateOrder getRelateOrder() {
		return relateOrder;
	}

	public void setRelateOrder(RelateOrder relateOrder) {
		this.relateOrder = relateOrder;
	}

	public RelateProduct getRelateProduct() {
		return relateProduct;
	}

	public void setRelateProduct(RelateProduct relateProduct) {
		this.relateProduct = relateProduct;
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}

}
