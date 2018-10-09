package com.hq.chainMS.service.chainPackageProject.data;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.util.AppUtils;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PackageProjectType {
	private String id;
	private String name;
	private int entityState;
	private long createTime;
	private long lastUpdateTime;

	public static PackageProjectType newInstance() {
		PackageProjectType data = new PackageProjectType();
		long currentTimeMillis = System.currentTimeMillis();
		data.createTime = currentTimeMillis;
		data.lastUpdateTime = currentTimeMillis;
		return data;
	}
	
	public static String generateId(long chainId, long index) {
		return AppUtils.joinByUnderline(ServerConstants.CHAIN_PACKAGE_PROJECT_TYPE_ID_SUFFIX, chainId, index);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

}
