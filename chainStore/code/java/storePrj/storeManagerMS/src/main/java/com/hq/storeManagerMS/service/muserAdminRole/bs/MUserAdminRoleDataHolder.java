package com.hq.storeManagerMS.service.muserAdminRole.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeManagerMS.common.datasyn.IntfDataHolder;
import com.hq.storeManagerMS.common.datasyn.info.DataSynItem;
import com.hq.storeManagerMS.common.datasyn.info.DataSynType;
import com.hq.storeManagerMS.common.datasyn.info.DataSynVer;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleQueryApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRoleCacheDAO;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRoleDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class MUserAdminRoleDataHolder implements IntfDataHolder{
	
	public static MUserAdminRoleDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MUserAdminRoleDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.MUserAdminRole;
	
	public void addAndReturnId(MUserAdminRole target) {
		MUserAdminRoleDAO.getInstance().addAndReturnId(target);
		MUserAdminRoleCacheDAO.getInstance().delete(target);
	}

	public void delete(MUserAdminRole target) {
		MUserAdminRoleDAO.getInstance().delete(target.getId());
		MUserAdminRoleCacheDAO.getInstance().delete(target);
	}
	
	public void update(MUserAdminRole target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		MUserAdminRoleDAO.getInstance().updpate(target);
		MUserAdminRoleCacheDAO.getInstance().delete(target);
	}

	public MUserAdminRole get(long id) {
		MUserAdminRole target = MUserAdminRoleCacheDAO.getInstance().get(id);
		if(target == null){
			target = MUserAdminRoleDAO.getInstance().get(id);
			if(target != null){
				MUserAdminRoleCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<MUserAdminRole> findMUserAdminRoles(MUserAdminRoleQueryApiForm queryForm) {
		List<MUserAdminRole> list = MUserAdminRoleCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = MUserAdminRoleDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				MUserAdminRoleCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	public List<MUserAdminRole> findByNames(List<String> names) {
		return MUserAdminRoleDAO.getInstance().findByNames(names);
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id)){
			long idL = Long.valueOf(id);
			MUserAdminRole target = this.get(idL);
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			}else{
				MainLog.info(LogModule.MUser, "MUserDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.MUser, "MUserDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}
