package com.hq.chainStore.service.chainPackageProject.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "chainPackageProject")
public class ChainPackageProject implements IntfSynData {
	@Id
	private long id;
	private long chainId;

	private long packageProjectIndex = 0;
	private Map<String, PackageProject> packageProjectMap = new HashMap<String, PackageProject>();
	
	private long packageProjectTypeIndex = 0;
	private Map<String, PackageProjectType> packageProjectTypeMap = new HashMap<String, PackageProjectType>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static ChainPackageProject newInstance() {
		ChainPackageProject chainPackageProject = new ChainPackageProject();
		return chainPackageProject;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}
	
	public PackageProject takePackageProjectById(String id) {
		return packageProjectMap.get(id);
	}
	
	public PackageProjectType takePackageProjectTypeById(String id) {
		return packageProjectTypeMap.get(id);
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

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
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
