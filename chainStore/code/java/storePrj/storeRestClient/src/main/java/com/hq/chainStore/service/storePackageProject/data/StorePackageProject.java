package com.hq.chainStore.service.storePackageProject.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "storePackageProject")
public class StorePackageProject implements IntfSynData{
	@Id
	private long id;
	private long storeId;

	private long packageProjectIndex = 0;
	private Map<String, PackageProject> packageProjectMap = new HashMap<String, PackageProject>();
	
	private long packageProjectTypeIndex = 0;
	private Map<String, PackageProjectType> packageProjectTypeMap = new HashMap<String, PackageProjectType>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StorePackageProject newInstance() {
		StorePackageProject storePackageProject = new StorePackageProject();
		return storePackageProject;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	public long getPackageProjectIndex() {
		return packageProjectIndex;
	}

	public void setPackageProjectIndex(long packageProjectIndex) {
		this.packageProjectIndex = packageProjectIndex;
	}

	public Map<String, PackageProject> getPackageProjectMap() {
		return packageProjectMap;
	}

	public void setPackageProjectMap(Map<String, PackageProject> packageProjectMap) {
		this.packageProjectMap = packageProjectMap;
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

	public long getPackageProjectTypeIndex() {
		return packageProjectTypeIndex;
	}

	public void setPackageProjectTypeIndex(long packageProjectTypeIndex) {
		this.packageProjectTypeIndex = packageProjectTypeIndex;
	}

	public Map<String, PackageProjectType> getPackageProjectTypeMap() {
		return packageProjectTypeMap;
	}

	public void setPackageProjectTypeMap(Map<String, PackageProjectType> packageProjectTypeMap) {
		this.packageProjectTypeMap = packageProjectTypeMap;
	}
}
