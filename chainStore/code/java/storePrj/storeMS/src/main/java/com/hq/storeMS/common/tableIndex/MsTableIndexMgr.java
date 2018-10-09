package com.hq.storeMS.common.tableIndex;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeMS.service.appVersion.data.AppVersion;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProduct;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.cardRecord.data.CardRecord;
import com.hq.storeMS.service.clerkSalary.data.ClerkSalary;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffair;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecord;
import com.hq.storeMS.service.materialRecords.data.MaterialRecords;
import com.hq.storeMS.service.opuser.data.OPUser;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.schedule.data.Schedule;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;
import com.hq.storeMS.service.specialData.data.SpecialData;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;

/**
 * 创建表的索引
 * @author kevin
 *
 */
public class MsTableIndexMgr {
	
	public static MsTableIndexMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MsTableIndexMgr.class);
	}
	
	private Set<Class<?>> tableIndexs = new HashSet<Class<?>>();
	
	//实例化的时候，将需要创建索引的表，加入tableIndexs
	public MsTableIndexMgr(){
		tableIndexs.add(Appointment.class);
		tableIndexs.add(AppVersion.class);
		tableIndexs.add(BeauticianProduct.class);
		tableIndexs.add(BUser.class);
		tableIndexs.add(CardRecord.class);
		tableIndexs.add(ClerkSalary.class);
		tableIndexs.add(LeaguerAffair.class);
		tableIndexs.add(MaterialRecords.class);
		tableIndexs.add(OPUser.class);
		tableIndexs.add(Schedule.class);
		tableIndexs.add(SpecialData.class);
		tableIndexs.add(Store.class);
		tableIndexs.add(StoreBeauticianInfo.class);
		tableIndexs.add(StoreCardInfo.class);
		tableIndexs.add(StoreClerkInfo.class);
		tableIndexs.add(StoreLeaguerInfo.class);
		tableIndexs.add(StoreMaterialInfo.class);
		tableIndexs.add(StoreProductInfo.class);
		tableIndexs.add(ServerConfig.class);
		tableIndexs.add(StoreGoods.class);
		tableIndexs.add(WorkFlowType.class);
		tableIndexs.add(WorkFlowData.class);
		tableIndexs.add(BonusRecord.class);
		tableIndexs.add(Arrearage.class);
		tableIndexs.add(LeaguerDetail.class);
		tableIndexs.add(GoodsDetail.class);
		tableIndexs.add(ProductDetail.class);
		tableIndexs.add(PackageProjectDetail.class);
		tableIndexs.add(LeaguerRecord.class);
	}

	public Set<ClassInfo> getClassInfos() {
		Set<ClassInfo> classInfos = new HashSet<ClassInfo>();
		for (Class<?> classP : tableIndexs) {
			classInfos.add(new ClassInfo(classP));
		}
		return classInfos;
	}
	
}
