package com.hq.storeMS.service.daySnapshot.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.arrearage.bs.ArrearageMgr;
import com.hq.storeMS.service.arrearage.data.PaymentHistory;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.hq.storeMS.service.daySnapshot.data.DaySnapshot;
import com.hq.storeMS.service.daySnapshot.data.PreDaySnapshotData;
import com.hq.storeMS.service.daySnapshot.data.PreDaySnapshotDataBuilder;
import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.storeMS.service.incomePay.bs.IncomePayDataHolder;
import com.hq.storeMS.service.incomePay.data.IncomePay;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.orderNotes.bs.OrderNotesMgr;
import com.hq.storeMS.service.orderNotes.data.ChargeBackRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class DaySnapshotMgr {

	public static DaySnapshotMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DaySnapshotMgr.class);
	}

	public void addAndReturnId(DaySnapshot target) {
		DaySnapshotDataHolder.getInstance().addAndReturnId(target);
	}

	public void update(DaySnapshot target) {
		DaySnapshotDataHolder.getInstance().updpate(target);
	}

	public void delete(DaySnapshot target) {
		DaySnapshotDataHolder.getInstance().delete(target);
	}

	public DaySnapshot get(long id) {
		return DaySnapshotDataHolder.getInstance().get(id);
	}

	public PreDaySnapshotData getPreDaySnapshotData(long minTime, long maxTime, long storeId) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId);
		List<Order> orders = OrderMgr.getInstance().findOrderList(params);

		IncomePayQueryForm queryForm = IncomePayQueryForm.newInstance();
		queryForm.setMaxIncomePayTime(maxTime).setMinIncomePayTime(minTime).setStoreId(storeId);
		List<IncomePay> incomePays = IncomePayDataHolder.getInstance().findByCond(queryForm);

		// 退款 订单里面有退款金额
		List<ChargeBackRecord> chargeBackRecords = OrderNotesMgr.getInstance().findChargeBackRecords(storeId, minTime, maxTime);
		List<PaymentHistory> paymentHistorys = ArrearageMgr.getInstance().findPaymentHistorys(storeId, minTime, maxTime);

		PreDaySnapshotDataBuilder builder = PreDaySnapshotDataBuilder.newInstance();
		builder.withIncomePays(incomePays).withOrders(orders).withPaymentHistorys(paymentHistorys)
				.withChargeBackRecords(chargeBackRecords);

		return builder.build();
	}

	public PageResp<DaySnapshot> findPage(DaySnapshotQueryForm queryForm) {
		List<DaySnapshot> list = DaySnapshotDataHolder.getInstance().findList(queryForm);
		List<DaySnapshot> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<DaySnapshot> filterRecord(DaySnapshotQueryForm queryForm, List<DaySnapshot> list) {
		List<DaySnapshot> result = new ArrayList<DaySnapshot>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (DaySnapshot record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<DaySnapshot>() {
			@Override
			public int compare(DaySnapshot o1, DaySnapshot o2) {
				return Long.compare(o2.getStartTime(), o1.getStartTime());
			}
		});
		return result;
	}

	private boolean isRightRecord(DaySnapshotQueryForm queryForm, DaySnapshot record) {
		if (!checkName(queryForm.getName(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkName(String name, DaySnapshot record) {
		if (StringUtils.isBlank(name)) {
			return true;
		}
		if (record.getBuserName() != null && record.getBuserName().contains(name)) {
			return true;
		}
		return false;
	}

}
