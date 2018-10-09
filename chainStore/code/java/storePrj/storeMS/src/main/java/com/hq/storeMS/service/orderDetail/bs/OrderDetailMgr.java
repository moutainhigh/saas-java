package com.hq.storeMS.service.orderDetail.bs;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.hq.storeMS.service.bonusRecord.bs.BonusRecordDataHolder;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.buser.bs.StoreBUserMgr;
import com.hq.storeMS.service.buser.data.StoreBUser;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailDataHolder;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.order.bs.OrderDataHolder;
import com.hq.storeMS.service.orderDetail.data.OrderDetail;
import com.hq.storeMS.service.orderDetail.data.OrderDetailBuilder;
import com.hq.storeMS.service.orderNotes.bs.OrderNotesDataHolder;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.orderTrack.bs.OrderTrackMgr;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderDetailMgr {

	public static OrderDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderDetailMgr.class);
	}

	public OrderDetail getOrderDetailByOrderId(long storeId, long orderId) {
		Order order = OrderDataHolder.getInstance().get(storeId, orderId);
		if(order == null) {
			return null;
		}
		LeaguerDetail leaguerDetail = LeaguerDetailDataHolder.getInstance().get(storeId, order.getLeaguerId());
		StoreBUser storeBUser = StoreBUserMgr.getInstance().get(storeId);
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
		OrderNotes orderNotes = OrderNotesDataHolder.getInstance().get(storeId, orderId);
		List<BonusRecord> bonusRecords = BonusRecordDataHolder.getInstance().findBonusRecordList(BonusRecordQueryForm.newInstance(orderId, storeId));
		OrderTrack orderTrack = OrderTrackMgr.getInstance().get(storeId, orderId);
		
		OrderDetailBuilder builder = OrderDetailBuilder.newInstance();
		builder.withOrder(order).withLeaguerDetail(leaguerDetail).withStoreBUserInfo(storeBUser)
			.withStoreCardInfo(storeCardInfo).withStoreGoods(storeGoods).withStoreProductInfo(storeProductInfo)
			.withStorePackageProject(storePackageProject).withOrderNotes(orderNotes)
			.withBonusRecords(bonusRecords).withOrderTrack(orderTrack);
		
		return builder.build();
	}

}
