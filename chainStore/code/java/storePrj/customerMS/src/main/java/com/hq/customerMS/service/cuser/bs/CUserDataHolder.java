package com.hq.customerMS.service.cuser.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.customerMS.service.cuser.apiData.CUserQueryForm;
import com.hq.customerMS.service.cuser.data.CUser;
import com.hq.customerMS.service.cuser.data.CUserCacheDAO;
import com.hq.customerMS.service.cuser.data.CUserDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserDataHolder {

	public static CUserDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(CUserDataHolder.class);
	}

	public void addAndReturnId(CUser target) {
		CUserDAO.getInstance().addAndReturnId(target);
		CUserCacheDAO.getInstance().delete(target);
	}

	public void updpate(CUser target) {
		target.incrVer();
		CUserDAO.getInstance().updpate(target);
		CUserCacheDAO.getInstance().delete(target);
	}

	public void delete(CUser target) {
		CUserDAO.getInstance().delete(target.getId());
		CUserCacheDAO.getInstance().delete(target);
	}

	public CUser get(long id) {
		CUser target = CUserCacheDAO.getInstance().get(id);
		if (target == null) {
			target = CUserDAO.getInstance().get(id);
			if (target != null) {
				CUserCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}

	public List<CUser> findByCond(CUserQueryForm queryForm) {
		List<CUser> list = CUserCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)) {
			list = CUserDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)) {
				CUserCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}

	public CUser findByPhone(long phone) {
		String key = String.valueOf(phone);
		CUser data = CUserCacheDAO.getInstance().getOne(key);
		if(data == null) {
			CUserQueryForm queryForm = CUserQueryForm.newInstance();
			queryForm.setPhone(phone);
			data = CUserDAO.getInstance().findOne(queryForm);
			if(data!=null) {
				CUserCacheDAO.getInstance().saveOne(key, data);
			}
		}
		return data;
	}
	
	public CUser findByWxUnionId(String wxUnionId) {
		CUser data = CUserCacheDAO.getInstance().getOne(wxUnionId);
		if(data == null) {
			CUserQueryForm queryForm = CUserQueryForm.newInstance();
			queryForm.setWxUnionId(wxUnionId);
			data = CUserDAO.getInstance().findOne(queryForm);
			if(data!=null) {
				CUserCacheDAO.getInstance().saveOne(wxUnionId, data);
			}
		}
		return data;
	}
	
	public CUser findByPriAccountNum(String priAccountNum) {
		CUser data = CUserCacheDAO.getInstance().getOne(priAccountNum);
		if(data == null) {
			CUserQueryForm queryForm = CUserQueryForm.newInstance();
			queryForm.setPriAccountNum(priAccountNum);
			data = CUserDAO.getInstance().findOne(queryForm);
			if(data!=null) {
				CUserCacheDAO.getInstance().saveOne(priAccountNum, data);
			}
		}
		return data;
	}
	
	//升级priAccountNum为空的数据
	public List<CUser> findPriAccountNumNotExists() {
		return CUserDAO.getInstance().findPriAccountNumNotExists();
	}
}
