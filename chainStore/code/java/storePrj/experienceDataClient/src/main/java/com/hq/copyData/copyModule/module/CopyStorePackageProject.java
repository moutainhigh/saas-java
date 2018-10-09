package com.hq.copyData.copyModule.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hq.chainStore.service.common.EntityState;
import com.hq.chainStore.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.chainStore.service.packageProjectDetail.bs.PackageProjectDetailMgr;
import com.hq.chainStore.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeRemoveForm;
import com.hq.chainStore.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.chainStore.service.storePackageProject.data.PackageProjectType;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProject;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CopyStorePackageProject extends AbstractCopyModule {

	public static CopyStorePackageProject newInstance() {
		CopyStorePackageProject data = new CopyStorePackageProject();
		return data;
	}

	public void copy() {
		// 数据准备
		AccessTokenMgr.getInstance().setOpIdTL(getSourceBossId());
		List<PackageProjectType> types = getPackageProjectTypes();
		List<PackageProjectDetail> list = getPackageProjectDetails();
		AccessTokenMgr.getInstance().removeOpIdTL();

		// 数据拷贝
		AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(getTargetStoreId());
		addPackageProjectTypes(storePackageProject, types);
		addPackageProjectDetails(storePackageProject, list);
		AccessTokenMgr.getInstance().removeOpIdTL();

		System.out.println("copy store packageProject finish");
	}

	private List<PackageProjectType> getPackageProjectTypes() {
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(getSourceStoreId());
		storePackageProject.getPackageProjectTypeMap().remove("0");
		List<PackageProjectType> types = new ArrayList<PackageProjectType>(storePackageProject.getPackageProjectTypeMap().values());
		Collections.sort(types, new Comparator<PackageProjectType>() {
			@Override
			public int compare(PackageProjectType o1, PackageProjectType o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return types;
	}

	private List<PackageProjectDetail> getPackageProjectDetails() {
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setStoreId(getSourceStoreId());
		return PackageProjectDetailMgr.getInstance().getPackageProjectDetailPageInfo(queryForm).getList();
	}

	private void addPackageProjectTypes(StorePackageProject storePackageProject, List<PackageProjectType> types) {
		long packageProjectTypeIndex = storePackageProject.getPackageProjectTypeIndex();
		for (PackageProjectType type : types) {
			packageProjectTypeIndex++;
			PackageProjectTypeAddForm inputForm = PackageProjectTypeAddForm.newInstance();
			inputForm.setIndex(packageProjectTypeIndex);
			inputForm.setName(type.getName());
			StorePackageProjectMgr.getInstance().addPackageProjectType(getTargetStoreId(), inputForm);
			if (type.getEntityState() == EntityState.Deleted.ordinal()) {
				PackageProjectTypeRemoveForm removeForm = PackageProjectTypeRemoveForm.newInstance();
				removeForm.setId(type.getId());
				StorePackageProjectMgr.getInstance().removePackageProjectType(getTargetStoreId(), removeForm);
			}
		}
	}

	private void addPackageProjectDetails(StorePackageProject storePackageProject, List<PackageProjectDetail> list) {
		long packageProjectIndex = storePackageProject.getPackageProjectIndex();
		for (PackageProjectDetail data : list) {
			if (data.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			packageProjectIndex++;
			PackageProjectAddForm inputForm = PackageProjectAddForm.newInstance();
			FastBeanCopyer.getInstance().copy(data, inputForm);
			inputForm.setIndex(packageProjectIndex);
			StorePackageProjectMgr.getInstance().addPackageProject(getTargetStoreId(), inputForm);
		}
	}
}
