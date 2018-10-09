package com.hq.chainMS.service.chainClerk.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateType;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkInfoHandleHelper {

	public static ClerkInfoHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkInfoHandleHelper.class);
	}

	private Map<ChainClerkUpdateType, IClerkInfoHandle> handleMapper = new HashMap<ChainClerkUpdateType, IClerkInfoHandle>();

	public ClerkInfoHandleHelper() {
		handleMapper.put(ChainClerkUpdateType.AddAdminRole, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return AdminRoleHandle.getInstance().addAdminRole(chainId, inputForm.getAdminRoleAddForm());
			}
		});
		handleMapper.put(ChainClerkUpdateType.UpdateAdminRole, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return AdminRoleHandle.getInstance().updateAdminRole(chainId, inputForm.getAdminRoleUpdateForm());
			}
		});
		handleMapper.put(ChainClerkUpdateType.RemoveAdminRole, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return AdminRoleHandle.getInstance().removeAdminRole(chainId, inputForm.getAdminRoleRemoveForm());
			}
		});
		
		handleMapper.put(ChainClerkUpdateType.AddClerk, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return ClerkInfoHandle.getInstance().addClerk(chainId, inputForm.getChainClerkAddForm());
			}
		});
		handleMapper.put(ChainClerkUpdateType.UpdateClerk, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return ClerkInfoHandle.getInstance().updateClerk(chainId, inputForm.getChainClerkUpdateInfoForm());
			}
		});
		handleMapper.put(ChainClerkUpdateType.ReomveClerk, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return ClerkInfoHandle.getInstance().reomveClerk(chainId, inputForm.getChainClerkReomveForm());
			}
		});
		
		handleMapper.put(ChainClerkUpdateType.SaveRoleSet2Clerk, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return ClerkInfoHandle.getInstance().addRoleSet2Clerk(chainId, inputForm.getClerkRoleSaveForm());
			}
		});
		
		handleMapper.put(ChainClerkUpdateType.AllotStore, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return ClerkAllotHandle.getInstance().allotStore(chainId, inputForm.getChainAllotStoreForm());
			}
		});
		handleMapper.put(ChainClerkUpdateType.BatchAllotStore, new IClerkInfoHandle(){
			@Override
			public OperateTips update(long chainId, ChainClerkUpdateForm inputForm){
				return ClerkAllotHandle.getInstance().batchAllotStore(chainId, inputForm.getChainBatchAllotStoreForm());
			}
		});
	}

	public OperateTips update(long chainId, ChainClerkUpdateForm inputForm) {
		ChainClerkUpdateType updateType = inputForm.getChainClerkUpdateType();
		IClerkInfoHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(chainId, inputForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
