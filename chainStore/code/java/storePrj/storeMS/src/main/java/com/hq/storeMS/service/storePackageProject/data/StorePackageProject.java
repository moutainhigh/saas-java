package com.hq.storeMS.service.storePackageProject.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.common.DataOriginEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storePackageProject")
public class StorePackageProject {
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
		StorePackageProject data = new StorePackageProject();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public static StorePackageProject newInstance(long storeId) {
		StorePackageProject data = newInstance();
		data.id = storeId;
		data.storeId = storeId;
		PackageProjectType type = PackageProjectType.newInstance();
		type.setId(SysInitTypeEnum.UnClassify.getId());
		type.setName(SysInitTypeEnum.UnClassify.getName());
		data.packageProjectTypeMap.put(type.getId(), type);
		return data;
	}
	
	public PackageProject takePackageProjectById(String id) {
		return packageProjectMap.get(id);
	}
	
	public PackageProjectType takePackageProjectTypeById(String id) {
		return packageProjectTypeMap.get(id);
	}
	
	public boolean packagePrjIsFromChain(String packageProjectId) {
		PackageProject data = packageProjectMap.get(packageProjectId);
		if(data != null && data.getOrigin() == DataOriginEnum.CHAIN.ordinal()) {
			return true;
		}
		return false;
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

	/**
	 * 获取正常状态 已上架的PackageProject
	 *
	 * @return
	 */
	public Map<String, PackageProject> takeNormalPackageProjectMap() {
		Map<String, PackageProject> tempPackageProjectMap = new HashMap<>(packageProjectMap);
		Set<String> set = tempPackageProjectMap.keySet();
		Iterator<String> it = set.iterator();
		List<String> listKey = new ArrayList<String>();
		while (it.hasNext()) {
			String str = it.next();
			PackageProject packageProject = tempPackageProjectMap.get(str);
			if (packageProject != null && packageProject.getState() != PackageStateEnum.Open.ordinal() && packageProject.getEntityState() != EntityState.Normal.ordinal()) {
				listKey.add(str);
			}
		}
		for (String key : listKey) {
			tempPackageProjectMap.remove(key);
		}
		return tempPackageProjectMap;
	}
	
	
}
