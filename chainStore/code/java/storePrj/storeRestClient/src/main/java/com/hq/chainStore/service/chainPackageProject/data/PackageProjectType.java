package com.hq.chainStore.service.chainPackageProject.data;

public class PackageProjectType {
	private String id;
	private String name;
	private int entityState;
	private long createTime;
	private long lastUpdateTime;

	public static PackageProjectType newInstance() {
		PackageProjectType data = new PackageProjectType();
		return data;
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
