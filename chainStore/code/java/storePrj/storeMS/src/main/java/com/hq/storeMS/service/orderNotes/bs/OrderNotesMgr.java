package com.hq.storeMS.service.orderNotes.bs;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.orderNotes.apiData.OrderNotesAddForm;
import com.hq.storeMS.service.orderNotes.data.ChargeBackRecord;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderNotesMgr {

	public static OrderNotesMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderNotesMgr.class);
	}

	final private DataVersionEnum dataVersionEnum = DataVersionEnum.OrderNotes;

	public OrderNotes addOrderNotes(OrderNotesAddForm addForm) {
		OrderNotes target = addForm.toOrderNotes();
		addWithId(target);
		return target;
	}

	public void addWithId(OrderNotes target) {
		OrderNotesDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}

	public void update(OrderNotes target) {
		OrderNotesDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}

	public void delete(OrderNotes target) {
		OrderNotesDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}

	public OrderNotes get(long storeId, long orderId) {
		OrderNotes data = OrderNotesDataHolder.getInstance().get(storeId, orderId);
		if (data == null) {// 不存在，则新增
			data = OrderNotes.newInstance(storeId, orderId);
			OrderNotesDataHolder.getInstance().addWithId(data);
		}
		return data;
	}

	public List<OrderNotes> findOrderNotesList(DataReportQueryForm queryForm) {
		List<OrderNotes> target = OrderNotesDataHolder.getInstance().findOrderNotesList(queryForm);
		return target;
	}

	// 获取店铺时间段内的退款记录列表
	public List<ChargeBackRecord> findChargeBackRecords(long storeId, long minTime, long maxTime) {
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		List<OrderNotes> orderNotes = findOrderNotesList(queryForm);
		List<ChargeBackRecord> list = new ArrayList<ChargeBackRecord>();
		for (OrderNotes data : orderNotes) {
			list.addAll(data.getChargeBackRecordMap().values());
		}
		return filterRecord(list, minTime, maxTime);
	}

	private List<ChargeBackRecord> filterRecord(List<ChargeBackRecord> list, long minTime, long maxTime) {
		if(maxTime==0L) {
			maxTime = Long.MAX_VALUE;
		}
		List<ChargeBackRecord> result = new ArrayList<ChargeBackRecord>();
		for (ChargeBackRecord record : list) {
			if (record.getCreateTime() >= minTime && record.getCreateTime() <= maxTime) {
				result.add(record);
			}
		}
		return result;
	}

	public List<OrderNotes> findOrderByIds(long storeId, String ids) {
		List<OrderNotes> target = OrderNotesDataHolder.getInstance().findOrderByIds(storeId, ids);
		return target;
	}
}
