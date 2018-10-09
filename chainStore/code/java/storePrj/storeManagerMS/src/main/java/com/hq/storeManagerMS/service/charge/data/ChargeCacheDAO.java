package com.hq.storeManagerMS.service.charge.data;

import java.util.List;

import com.hq.storeManagerMS.service.charge.apiData.ChargeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeCacheDAO {

	public static ChargeCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeCacheDAO.class);
	}

	final private String groupName = "charge";

	public void saveList(ChargeQueryForm queryForm, List<Charge> list) {
		ChargeRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<Charge> getList(ChargeQueryForm queryForm) {
		return ChargeRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	public void save(Charge target) {
		ChargeRedisDAO.getInstance().save(target);
	}
	
	public Charge get(long id) {
		return ChargeRedisDAO.getInstance().get(id);
	}

	public void deleteCharge(Charge target) {
		ChargeRedisDAO.getInstance().delete(target.getId());
		ChargeRedisDAO.getInstance().deleteList(groupName);
	}
}
