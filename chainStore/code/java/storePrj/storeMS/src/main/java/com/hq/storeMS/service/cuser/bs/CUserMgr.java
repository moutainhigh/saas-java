package com.hq.storeMS.service.cuser.bs;

import com.hq.customerRestClient.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerRestClient.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestProxyException;

public class CUserMgr {

	public static CUserMgr getInstance() {
		return HotSwap.getInstance().getSingleton(CUserMgr.class);
	}

	public void updateFromMs(CuserUpdate4Ms inputForm) {
		CUserDataHolder.getInstance().updateFromMs(inputForm);
	}

	public CUser get(long id) {
		try {
			return CUserDataHolder.getInstance().get(id);
		} catch (Exception e) {
			String tips = id + "";
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				tips = tips + "-" + errorTmp.getTips();
			}
			MainLog.error(LogModule.CUser, "CUserMgr[get]", tips);

		}
		return null;
	}

	public CUser findByPhone(long phone) {
		try {
			return CUserDataHolder.getInstance().findByPhone(phone);
		} catch (Exception e) {
			String tips = phone + "";
			if (e instanceof RestProxyException) {
				RestProxyException errorTmp = (RestProxyException) e;
				tips = tips + "-" + errorTmp.getTips();
			}
			MainLog.error(LogModule.CUser, "CUserMgr[findByPhone]", tips);
		}
		return null;
	}

	public CUser addFromMs(CuserAdd4Ms inputForm) {
		return CUserDataHolder.getInstance().addFromMs(inputForm);
	}

}
