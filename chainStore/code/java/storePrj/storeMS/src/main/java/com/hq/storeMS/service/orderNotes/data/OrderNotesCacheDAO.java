package com.hq.storeMS.service.orderNotes.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderNotesCacheDAO {

	public static OrderNotesCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderNotesCacheDAO.class);
	}

	final private String suffix = "orderNotes";

	public void save(OrderNotes target) {
		OrderNotesRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public OrderNotes get(long storeId, Object id) {
		return OrderNotesRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(OrderNotes target) {
		OrderNotesRedisDAO.getInstance().delete(target.getId());
		OrderNotesRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	public List<OrderNotes> getList(DataReportQueryForm queryForm) {
		return OrderNotesRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public void saveList(DataReportQueryForm queryForm, List<OrderNotes> list) {
		OrderNotesRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<OrderNotes> getListByIds(long storeId, String ids) {
		return OrderNotesRedisDAO.getInstance().getList(getGroupName(storeId), AppUtils.joinByUnderline(suffix, ids));
	}
	
	public void saveIdsList(long storeId, String ids, List<OrderNotes> list) {
		OrderNotesRedisDAO.getInstance().saveList(list, getGroupName(storeId), AppUtils.joinByUnderline(suffix, ids));
	}

	private String getGroupName(Object targetId) {
		return AppUtils.joinByUnderline(suffix, targetId);
	}
}
