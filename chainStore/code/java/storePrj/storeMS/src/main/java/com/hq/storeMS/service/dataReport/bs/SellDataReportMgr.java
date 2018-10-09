package com.hq.storeMS.service.dataReport.bs;

import java.util.List;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.storeMS.service.dataReport.data.vo.SellStatisData;
import com.hq.storeMS.service.dataReport.data.vo.SellStatisDataBuilder;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class SellDataReportMgr {

	public static SellDataReportMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SellDataReportMgr.class);
	}

	public SellStatisData findSellStatisData(long storeId, long minTime, long maxTime) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMaxPayTime(maxTime).setMinPayTime(minTime).setStoreId(storeId).setOrderType(-1);
		params.addStatus(OrderStatusEnum.HAS_PAY.ordinal(),
                OrderStatusEnum.CHARGEBACK_ALL.ordinal(),
                OrderStatusEnum.CHARGEBACK_PART.ordinal());
		List<Order> orders = OrderMgr.getInstance().findOrderList(params);

		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);

		SellStatisDataBuilder builder = SellStatisDataBuilder.newInstance();
		builder.setQueryForm(params).setOrders(orders).setStoreCardInfo(storeCardInfo).setStoreGoods(storeGoods)
				.setStorePackageProject(storePackageProject).setStoreProductInfo(storeProductInfo);

		return builder.build();
	}
}
