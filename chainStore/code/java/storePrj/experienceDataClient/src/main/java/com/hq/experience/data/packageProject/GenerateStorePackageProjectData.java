package com.hq.experience.data.packageProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hq.BaseGenerate;
import com.hq.StoreClientMgr;
import com.hq.chainStore.service.packageProjectDetail.data.PackageItem;
import com.hq.chainStore.service.packageProjectDetail.data.PackageItemEnum;
import com.hq.chainStore.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.chainStore.service.storeGoods.data.Goods;
import com.hq.chainStore.service.storeGoods.data.StoreGoods;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectAddForm;
import com.hq.chainStore.service.storePackageProject.apiData.PackageProjectTypeAddForm;
import com.hq.chainStore.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.chainStore.service.storePackageProject.data.PackageProjectType;
import com.hq.chainStore.service.storePackageProject.data.StorePackageProject;
import com.hq.chainStore.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.experienceData.TPackagePrj;
import com.hq.experienceData.TPackagePrjType;
import com.hq.experienceData.service.RandomUtils;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class GenerateStorePackageProjectData extends BaseGenerate{
	
	public static void main(String[] args) {
		long phone = 13660623953L;
		String service = "http://192.168.40.220/storems/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), service);
		new GenerateStorePackageProjectData().genData(phone);
	}
	
	public GenerateStorePackageProjectData(){
		super();
	}
	
	@Override
	public void genData(long phone){
		try {
			initEnv(phone);
			for (Long id : storeIds) {
				this.storeId = id;
				genStorePackageProjectData();
			}
//			this.storeId = 16055L;
//			genStorePackageProjectData();
			System.out.println("Generate Material Data success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void genStorePackageProjectData() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreProductInfo storeProduct = StoreProductInfoMgr.getInstance().findSimpleStoreInfo(storeId);
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().findSimpleStoreInfo(storeId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		List<TPackagePrjType> types = TPackagePrjType.buildTPackagePrjTypes();
		for (TPackagePrjType type : types) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(storeId);
			PackageProjectTypeAddForm inputForm = PackageProjectTypeAddForm.newInstance();
			inputForm.setName(type.getName());
			inputForm.setIndex(storePackageProject.getPackageProjectTypeIndex()+1);
			StorePackageProjectMgr.getInstance().addPackageProjectType(storeId, inputForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
		
		List<TPackagePrj> packagePrjs = TPackagePrj.buildTPackagePrjs();
		for (TPackagePrj tPackagePrj : packagePrjs) {
			AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
			StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getStorePackageProject(storeId);
			
			Collection<PackageProjectType> packageProjectTypes = storePackageProject.getPackageProjectTypeMap().values();
			
			PackageProjectAddForm inputForm = PackageProjectAddForm.newInstance();
			FastBeanCopyer.getInstance().copy(tPackagePrj, inputForm);
			long index = storePackageProject.getPackageProjectIndex()+1;
			inputForm.setIndex(index);
			inputForm.setNumber("P000000"+inputForm.getIndex());
			inputForm.setPackageItems(getPackageItems(storeProduct, storeGoods));
			inputForm.setTypeId(getTypeId(tPackagePrj.getTypeName(), packageProjectTypes));
			StorePackageProjectMgr.getInstance().addPackageProject(storeId, inputForm);
			AccessTokenMgr.getInstance().removeOpIdTL();
		}
	}
	
	private String getTypeId(String typeName, Collection<PackageProjectType> types) {
		for (PackageProjectType packageProjectType : types) {
			if(packageProjectType.getName().equals(typeName)) {
				return packageProjectType.getId();
			}
		}
		return "0";
	}
	
	private List<PackageItem> getPackageItems(StoreProductInfo storeProduct, StoreGoods storeGoods){
		List<PackageItem> packageItems = new ArrayList<PackageItem>();
		packageItems.add(getRandomProductItem(storeProduct));
		packageItems.add(getRandomGoodsItem(storeGoods));
		return packageItems;
	}
	
	private PackageItem getRandomProductItem(StoreProductInfo storeProduct) {
		List<ProductInfo> values = new ArrayList<ProductInfo>(storeProduct.getProductInfoMap().values());
		ProductInfo productInfo = values.get(RandomUtils.nextInt(0, values.size()));
		
		PackageItem item = PackageItem.newInstance();
		item.setCount(RandomUtils.nextInt(1, 5));
		item.setDiscountPrice(RandomUtils.nextFloat(100f, 500f));
		item.setItemType(PackageItemEnum.PRODUCT.ordinal());
		item.setPgId(productInfo.getId());
		return item;
	}
	
	private PackageItem getRandomGoodsItem(StoreGoods storeGoods) {
		List<Goods> values = new ArrayList<Goods>(storeGoods.getGoodsMap().values());
		Goods goods = values.get(RandomUtils.nextInt(0, values.size()));
		
		PackageItem item = PackageItem.newInstance();
		item.setCount(RandomUtils.nextInt(1, 5));
		item.setDiscountPrice(RandomUtils.nextFloat(100f, 500f));
		item.setItemType(PackageItemEnum.PRODUCT.ordinal());
		item.setPgId(goods.getId());
		return item;
	}
}
