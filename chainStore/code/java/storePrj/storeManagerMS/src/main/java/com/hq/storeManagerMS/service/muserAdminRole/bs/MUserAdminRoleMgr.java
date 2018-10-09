package com.hq.storeManagerMS.service.muserAdminRole.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleQueryApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRoleBeanHelper;
import com.hq.storeManagerMS.service.muserAdminRole.data.SysAdminRoleEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserAdminRoleMgr {

	public static MUserAdminRoleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MUserAdminRoleMgr.class);
	}
	
	public void init(){
		List<String> names = new ArrayList<String>();
		names.add(SysAdminRoleEnum.MNG_ADMIN.getMark());
		names.add(SysAdminRoleEnum.MNG_OPERATE.getMark());
		List<MUserAdminRole> adminRoles = findByNames(names);
		checkAdmin(adminRoles);
		checkOperate(adminRoles);
	}
	
	private void checkAdmin(List<MUserAdminRole> adminRoles){
		boolean flag = false;
		if(CollectionUtils.isNotEmpty(adminRoles)){
			for (MUserAdminRole mUserAdminRole : adminRoles) {
				if(mUserAdminRole.getName().equals(SysAdminRoleEnum.MNG_ADMIN.getMark())){
					flag = true;
					break;
				}
			}
		}
		if(!flag){
			MUserAdminRole target = MUserAdminRoleBeanHelper.getInstance().getAdminRole();
			addAndReturnId(target);
		}
	}
	
	private void checkOperate(List<MUserAdminRole> adminRoles){
		boolean flag = false;
		if(CollectionUtils.isNotEmpty(adminRoles)){
			for (MUserAdminRole mUserAdminRole : adminRoles) {
				if(mUserAdminRole.getName().equals(SysAdminRoleEnum.MNG_OPERATE.getMark())){
					flag = true;
					break;
				}
			}
		}
		if(!flag){
			MUserAdminRole target = MUserAdminRoleBeanHelper.getInstance().getOperateRole();
			addAndReturnId(target);
		}
	}
	
	public List<MUserAdminRole> findByNames(List<String> names){
		return MUserAdminRoleDataHolder.getInstance().findByNames(names);
	}

	public void addAndReturnId(MUserAdminRole target) {
		MUserAdminRoleDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void delete(MUserAdminRole target) {
		MUserAdminRoleDataHolder.getInstance().delete(target);
	}
	
	public void update(MUserAdminRole target) {
		MUserAdminRoleDataHolder.getInstance().update(target);
	}

	public MUserAdminRole get(long id) {
		return MUserAdminRoleDataHolder.getInstance().get(id);
	}

	public List<MUserAdminRole> findMUserAdminRoles(MUserAdminRoleQueryApiForm queryForm) {
		List<MUserAdminRole> list = MUserAdminRoleDataHolder.getInstance().findMUserAdminRoles(queryForm);
		return toROList(list);
	}
	
	public Map<Long, MUserAdminRole> getAllMUserAdminRoleMap() {
		MUserAdminRoleQueryApiForm queryForm = MUserAdminRoleQueryApiForm.newInstance();
		List<MUserAdminRole> list = MUserAdminRoleDataHolder.getInstance().findMUserAdminRoles(queryForm);
		return toMap(list);
	}

	private Map<Long, MUserAdminRole> toMap(List<MUserAdminRole> list){
		Map<Long, MUserAdminRole> map = new HashMap<Long, MUserAdminRole>();
		if(CollectionUtils.isEmpty(list)){
			for (MUserAdminRole role : list) {
				map.put(role.getId(), role);
			}
		}
		return map;
	}
	
	private List<MUserAdminRole> toROList(List<MUserAdminRole> list) {
		List<MUserAdminRole> targetList = new ArrayList<MUserAdminRole>();
		if(CollectionUtils.isEmpty(list)){
			for (MUserAdminRole role : list) {
				targetList.add(role);
			}
		}
		return targetList;
	}
}
