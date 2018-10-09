package com.hq.chainClient.service.chainClerk.bs;

import com.hq.chainClient.service.chainClerk.apiData.AdminRoleAddForm;
import com.hq.chainClient.service.chainClerk.apiData.AdminRoleRemoveForm;
import com.hq.chainClient.service.chainClerk.apiData.AdminRoleUpdateForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainAllotStoreForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainBatchAllotStoreForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkAddForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkReomveForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkUpdateForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkUpdateInfoForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkUpdateType;
import com.hq.chainClient.service.chainClerk.apiData.ClerkRoleSaveForm;
import com.hq.chainClient.service.chainClerk.data.ChainClerk;
import com.hq.chainClient.service.chainClerk.data.ChainClerkDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainClerkClientMgr {

	public static ChainClerkClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkClientMgr.class);
	}

	public ChainClerk get(long chainId) {
		return ChainClerkDAO.getInstance().get(chainId);
	}

	/******************************** 岗位操作 ********************************/
	// 添加岗位信息
	public void addAdminRole(long chainId, AdminRoleAddForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.AddAdminRole.ordinal());
		updateForm.setAdminRoleAddForm(inputForm);
		update(chainId, updateForm);
	}

	// 更新岗位信息
	public void updateAdminRole(long chainId, AdminRoleUpdateForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.UpdateAdminRole.ordinal());
		updateForm.setAdminRoleUpdateForm(inputForm);
		update(chainId, updateForm);
	}

	// 删除岗位信息
	public void removeAdminRole(long chainId, AdminRoleRemoveForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.RemoveAdminRole.ordinal());
		updateForm.setAdminRoleRemoveForm(inputForm);
		update(chainId, updateForm);
	}

	/******************************** 员工操作 ********************************/
	// 添加连锁店员工
	public void addClerk(long chainId, ChainClerkAddForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.AddClerk.ordinal());
		updateForm.setChainClerkAddForm(inputForm);
		update(chainId, updateForm);
	}

	// 删除连锁店员工
	public void reomveClerk(long chainId, ChainClerkReomveForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.ReomveClerk.ordinal());
		updateForm.setChainClerkReomveForm(inputForm);
		update(chainId, updateForm);
	}
	
	// 删除修改
	public void updateClerk(long chainId, ChainClerkUpdateInfoForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.UpdateClerk.ordinal());
		updateForm.setChainClerkUpdateInfoForm(inputForm);
		update(chainId, updateForm);
	}

	/******************************** 员工岗位操作 ********************************/
	// 保存员工的岗位信息
	public void saveRoleSet2Clerk(long chainId, ClerkRoleSaveForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.SaveRoleSet2Clerk.ordinal());
		updateForm.setClerkRoleSaveForm(inputForm);
		update(chainId, updateForm);
	}
	
	/******************************** 员工分配操作 ********************************/
	// 分配
	public void allotStore(long chainId, ChainAllotStoreForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.AllotStore.ordinal());
		updateForm.setChainAllotStoreForm(inputForm);
		update(chainId, updateForm);
	}
	
	// 批量分配
	public void batchAllotStore(long chainId, ChainBatchAllotStoreForm inputForm) {
		ChainClerkUpdateForm updateForm = ChainClerkUpdateForm.newInstance();
		updateForm.setUpdateType(ChainClerkUpdateType.BatchAllotStore.ordinal());
		updateForm.setChainBatchAllotStoreForm(inputForm);
		update(chainId, updateForm);
	}

	private void update(long chainId, ChainClerkUpdateForm updateForm) {
		ChainClerkDAO.getInstance().update(chainId, updateForm);
	}
}
