package com.hq.storeMS.service.buserRole.bs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.bs.BUserDataHolder;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.hq.storeMS.service.buserRole.apiData.BatchPermForm;
import com.hq.storeMS.service.buserRole.apiData.OperateEnum;
import com.hq.storeMS.service.buserRole.data.BuserRole;
import com.hq.storeMS.service.buserRole.data.VipContent;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.vipLevel.bs.VipLevelMgr;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleMgr {

	public static BuserRoleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleMgr.class);
	}
	
	public void init() {
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(BuserRoleEnum.BOSS.ordinal());
		queryForm.setRoleSet(roleSet);
		List<BUser> list = BUserDataHolder.getInstance().findListByCond(queryForm);
		for (BUser bUser : list) {
			updateByBUser(bUser);
		}
	}
	
	public BuserRole getBossRoleByStoreId(long storeId) {
		Store store = StoreMgr.getInstance().getSimple(storeId);
		if(store == null) {
			return null;
		}
		return getSimple(store.getBossId());
	}
	
	public void updateByBUser(BUser buser) {
		BuserRole buserRole = getSimple(buser.getId());
		if(buserRole.getVipContent().getVipType() == buser.getVipType()) {//会员类型不变
			return ;
		}
		
		VipLevel vipLevel = VipLevelMgr.getInstance().getRO(buser.getVipType());
		if(vipLevel != null) {
			buserRole.setPreVipContent(buserRole.getVipContent());
			VipContent vipContent = VipContent.newInstance();
			FastBeanCopyer.getInstance().copy(vipLevel.getVipContent(), vipContent);
			vipContent.setVipType(buser.getVipType());
			//重新设置会员等级的信息
			buserRole.setVipContent(vipContent);
			update(buserRole);
		}
	}
	
	public void update(BuserRole target) {
		BuserRoleDataHolder.getInstance().updpate(target);
	}
	
	public BuserRole getSimple(long buserId) {
		BuserRole data = BuserRoleDataHolder.getInstance().get(buserId);
		if(data == null){//不存在，则新增
			data = BuserRole.newInstance(buserId);
			BuserRoleDataHolder.getInstance().addWithId(data);
		}
		return data;
	}

	public void batchPerm(BatchPermForm inputForm) {
		OperateEnum operateEnum = inputForm.getOperateEnum();
		Set<Long> buserIds = inputForm.getBuserIds();
		for (Long buserId : buserIds) {
			BuserRole buserRole = getSimple(buserId);
			if(operateEnum == OperateEnum.Add) {
				buserRole.getVipContent().getPermSet().addAll(inputForm.getPerms());
			}else {
				buserRole.getVipContent().getPermSet().removeAll(inputForm.getPerms());
			}
			update(buserRole);
		}
	}
}
