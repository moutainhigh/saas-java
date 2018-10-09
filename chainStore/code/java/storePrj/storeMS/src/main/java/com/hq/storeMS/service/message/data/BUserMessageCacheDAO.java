package com.hq.storeMS.service.message.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.message.apiData.MessageQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageCacheDAO {

	public static BUserMessageCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageCacheDAO.class);
	}

	final private String suffix = "buserMessage";

	public void saveList(MessageQueryForm queryForm, List<BUserMessage> list) {
		BUserMessageRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getBuserId()), queryForm.getListId());
	}

	public List<BUserMessage> getList(MessageQueryForm queryForm) {
		return BUserMessageRedisDAO.getInstance().getList(getGroupName(queryForm.getBuserId()), queryForm.getListId());
	}
	
	public void save(BUserMessage target) {
		BUserMessageRedisDAO.getInstance().save(target);
	}
	
	public BUserMessage get(long id) {
		return BUserMessageRedisDAO.getInstance().get(id);
	}

	public void delete(BUserMessage target) {
		BUserMessageRedisDAO.getInstance().delete(target.getId());
		BUserMessageRedisDAO.getInstance().deleteList(getGroupName(target.getBuserId()));
	}

	private String getGroupName(Object buserId) {
		return AppUtils.joinByUnderline(suffix, buserId);
	}

}
