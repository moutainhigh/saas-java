package com.hq.storeMS.service.saas.bs;

import java.util.List;
import java.util.Set;

import com.hq.storeMS.service.auth.opUser.OPUserAuthUtils;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminPermEnum;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminRole;
import com.hq.storeMS.service.saas.apiData.OPAdminRoleUpdateInfoApiData;
import com.hq.storeMS.service.saas.data.SaasData;
import com.hq.storeMS.service.saas.data.SaasDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SaasMgr {

	public static SaasMgr getInstance(){
		return HotSwap.getInstance().getSingleton(SaasMgr.class);
	}
	
	public SaasData init(){
		SaasData data = getData();
		if(data == null){
			data = SaasData.newInstance();
			int nextOPAdminRoleId = data.getNextOPAdminRoleId();
			OPAdminRole superRole = OPAdminRole.newInstance(nextOPAdminRoleId);
			superRole.addPerm(OPAdminPermEnum.OP_SUPER);
			
			data.addOPAdminRole(superRole );
			SaasDataDAO.getInstance().addWithId(data);
		}
		return data;
	}
	
	public void addOPAdminRole(OPAdminRole role){
		OPUserAuthUtils.getInstance().checkPermission(OPAdminPermEnum.OP_SUPER);
		SaasData data = getData();
		data.addOPAdminRole(role);
		SaasDataDAO.getInstance().updpate(data);
	}
	public boolean updateOPAdminRole(OPAdminRoleUpdateInfoApiData inputData){
		OPUserAuthUtils.getInstance().checkPermission(OPAdminPermEnum.OP_SUPER);
		OPAdminRole role = inputData.toRole();
		SaasData data = getData();
		data.updateOPAdminRole(role);
		SaasDataDAO.getInstance().updpate(data);
		return true;
	}
	
	public OPAdminRole getOPAdminRole(int roleId){
		SaasData data = getData();
		OPAdminRole role = data.getOPAdminRole(roleId);
		return role;
	}
	public List<OPAdminRole> listOPAdminRole(){
		SaasData data = getData();
		return  data.listOPAdminRole();
	}
	
	public SaasData getData(){
		return SaasDataDAO.getInstance().get();
	}
	
	public Set<OPAdminRole> getOPAdminRoleSet(Set<Integer> roleIdSet){
		SaasData data = getData();
		return data.getOPAdminRoleSet(roleIdSet);
	}
	
}
