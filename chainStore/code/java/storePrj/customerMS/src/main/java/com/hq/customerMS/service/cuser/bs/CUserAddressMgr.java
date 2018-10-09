package com.hq.customerMS.service.cuser.bs;

import com.hq.customerMS.service.cuser.apiData.CUserAddressAddForm;
import com.hq.customerMS.service.cuser.apiData.CUserAddressRemoveForm;
import com.hq.customerMS.service.cuser.apiData.CUserAddressUpdateForm;
import com.hq.customerMS.service.cuser.apiData.CUserChangeDefaultAddressForm;
import com.hq.customerMS.service.cuser.data.CUser;
import com.hq.customerMS.service.cuser.data.CUserBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class CUserAddressMgr {

	public static CUserAddressMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CUserAddressMgr.class);
	}

	public CUser addAddress(long cuserId, CUserAddressAddForm addAddressData) {
		CUser cuser = CUserQueryMgr.getInstance().get(cuserId);
		if (CUserBeanHelper.getInstance().addAddress(cuser, addAddressData)) {
			CUserDataHolder.getInstance().updpate(cuser);
			return cuser;
		} else {
			return null;
		}
	}
	
	public CUser updateAddress(long cuserId, CUserAddressUpdateForm updateAddressData) {
		CUser cuser = CUserQueryMgr.getInstance().get(cuserId);
		if (CUserBeanHelper.getInstance().updateAddress(cuser, updateAddressData)) {
			CUserDataHolder.getInstance().updpate(cuser);
			return cuser;
		} else {
			return null;
		}
	}
	
	public CUser removeAddress(long cuserId, CUserAddressRemoveForm removeAddressData) {
		CUser cuser = CUserQueryMgr.getInstance().get(cuserId);
		if (CUserBeanHelper.getInstance().removeAddress(cuser, removeAddressData)) {
			CUserDataHolder.getInstance().updpate(cuser);
			return cuser;
		} else {
			return null;
		}
	}
	
	public CUser changeDefaultAddress(long cuserId, CUserChangeDefaultAddressForm changeDefaultAddressForm) {
		CUser cuser = CUserQueryMgr.getInstance().get(cuserId);
		if (CUserBeanHelper.getInstance().changeDefaultAddress(cuser, changeDefaultAddressForm)) {
			CUserDataHolder.getInstance().updpate(cuser);
			return cuser;
		} else {
			return null;
		}
	}
}
