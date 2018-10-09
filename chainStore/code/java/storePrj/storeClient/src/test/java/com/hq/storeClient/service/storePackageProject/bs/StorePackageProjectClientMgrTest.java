package com.hq.storeClient.service.storePackageProject.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.storePackageProject.apiData.PkgPrjAddTopForm;
import com.hq.storeClient.service.storePackageProject.apiData.StorePackageProjectUpdateForm;
import com.hq.storeClient.service.storePackageProject.apiData.StorePackageProjectUpdateType;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

public class StorePackageProjectClientMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testGet() {
		long storeId = 16052L;
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StorePackageProject storeData = StorePackageProjectClientMgr.getInstance().get(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeData));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testUpdate() {
		long storeId = 16052L;
//		String id = Robot.getInstance().getRandomPackageProject(storeId).getId();
		String id = "16052_1";
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		StorePackageProjectUpdateForm updateForm = StorePackageProjectUpdateForm.newInstance();
		
//		PackageProjectDetail detail = PackageProjectDetailClientMgr.getInstance().getPackageProjectDetail(storeId, id);
//		PackageProjectUpdateForm packageProjectUpdateForm = PackageProjectUpdateForm.newInstance();
//		FastBeanCopyer.getInstance().copy(detail, packageProjectUpdateForm);
//		packageProjectUpdateForm.setPromotionFlag(PromotionFlagEnum.Yes.ordinal());
//		packageProjectUpdateForm.setPromotionPrice(300f);
//		
//		updateForm.setStorePackageProjectUpdateType(StorePackageProjectUpdateType.UpdatePackageProject);
//		updateForm.setPackageProjectUpdateForm(packageProjectUpdateForm);
		
		PkgPrjAddTopForm pkgPrjAddTopForm = PkgPrjAddTopForm.newInstance();
		pkgPrjAddTopForm.setId(id);
		updateForm.setStorePackageProjectUpdateType(StorePackageProjectUpdateType.AddPackageProjectTop);
		updateForm.setPkgPrjAddTopForm(pkgPrjAddTopForm);
		
//		PkgPrjCancelTopForm pkgPrjCancelTopForm = PkgPrjCancelTopForm.newInstance();
//		pkgPrjCancelTopForm.setId(id);
//		updateForm.setStorePackageProjectUpdateType(StorePackageProjectUpdateType.CancelPackageProjectTop);
//		updateForm.setPkgPrjCancelTopForm(pkgPrjCancelTopForm);

		StorePackageProjectClientMgr.getInstance().update(storeId, updateForm);
		ValidateThreadLocal.getInstance().remove();
	}

}
