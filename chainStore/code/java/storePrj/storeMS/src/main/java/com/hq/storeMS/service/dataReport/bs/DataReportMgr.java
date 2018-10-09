package com.hq.storeMS.service.dataReport.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.dataReport.data.CardStatisticsData;
import com.hq.storeMS.service.dataReport.data.DataReport;
import com.hq.storeMS.service.dataReport.data.DataReportBeanHelper;
import com.hq.storeMS.service.dataReport.data.MemberDataCount;
import com.hq.storeMS.service.dataReport.data.MemberStatisticsData;
import com.hq.storeMS.service.order.bs.OrderDataHolder;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.ProductData;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class DataReportMgr {

    public static DataReportMgr getInstance() {
        return HotSwap.getInstance().getSingleton(DataReportMgr.class);
    }

    public MemberDataCount getMemberDataCount(long storeId) {
        MemberDataCount memberDataCount = MemberDataCount.newInstance();
        OrderQueryForm params = OrderQueryForm.newInstance();
        params.setStoreId(storeId);
        params.addStatus(OrderStatusEnum.HAS_PAY.ordinal());
        OrderCount orderCount = OrderMgr.getInstance().getOrderCount(params);

        StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(storeId);
        long memberCount = 0L;
        List<Leaguer> leaguers = storeLeaguerInfo.takeLeaguerInfoList();
        for (Leaguer leaguer : leaguers) {
            if (leaguer.getEntityState() != EntityState.Deleted.ordinal()) {
                memberCount++;
            }
        }
        memberDataCount.setStoreId(storeId);
        memberDataCount.setOrderCount(orderCount.getCount());
        memberDataCount.setOrderCost(orderCount.getOrderCost());
        memberDataCount.setMemberCount(memberCount);
        return memberDataCount;
    }

    public List<DataReport> findDataReprotList(DataReportQueryForm queryForm) {
        List<Order> orderList = this.findOrderList(queryForm);
        return DataReportBeanHelper.getInstance().orderList2ReportList(orderList);
    }

    public List<Order> findOrderList(DataReportQueryForm queryForm) {
        OrderQueryForm params = OrderQueryForm.newInstance();
        params.setStoreId(queryForm.getStoreId());
        params.addStatus(OrderStatusEnum.HAS_PAY.ordinal());
        params.setMaxTime(queryForm.getMaxTime());
        params.setMinTime(queryForm.getMinTime());
        params.setPageItemCount(0);
        params.setPageNo(1);
        return OrderMgr.getInstance().findOrderList(params);
    }

    public List<Order> findFianceOrderList(DataReportQueryForm queryForm) {
        OrderQueryForm params = OrderQueryForm.newInstance();
        params.setStoreId(queryForm.getStoreId());
        params.addStatus(OrderStatusEnum.HAS_PAY.ordinal(),
                OrderStatusEnum.CHARGEBACK_ALL.ordinal(),
                OrderStatusEnum.CHARGEBACK_PART.ordinal());
        params.setMaxTime(queryForm.getMaxTime());
        params.setMinTime(queryForm.getMinTime());
        params.setOrderType(-1);
        params.setPageItemCount(0);
        params.setPageNo(1);
        return OrderMgr.getInstance().findOrderList(params);
    }

    public ProductData findProductDataWithUnsold(String storeId) {
        Long storeIdNumber = Long.valueOf(storeId);
        ProductData productData = new ProductData();
//		获取店铺的商品简版信息个数
        StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeIdNumber);
        Map<String, Goods> normalGoods = storeGoods.takeNormalGoods();

//		获取店铺项目简版信息个数
        StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeIdNumber);
        Map<String, ProductInfo> productInfoMap = storeProductInfo.takeNormalProductInfoMap();

//		获取店铺套餐简版信息个数
        StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeIdNumber);
        Map<String, PackageProject> packageProjectMap = storePackageProject.takeNormalPackageProjectMap();

//		获取店铺次卡简版信息个数
        StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeIdNumber);
        Map<String, ProductCard> productCardMap = storeCardInfo.takeNormalProductCardMap();
        int totalProductCount = normalGoods.size() + productCardMap.size() + packageProjectMap.size() + productCardMap.size();
        productData.setTotalProductCount(totalProductCount);

//		滞留参数
//		获取七天内的店铺支付成功的订单。
        long currentTimeMillis = System.currentTimeMillis();
        long dateTime = currentTimeMillis - 6 * 24 * 60 * 60 * 1000;
        OrderQueryForm params = OrderQueryForm.newInstance();
        params.setStoreId(storeIdNumber);
        params.setMinTime(dateTime);
        params.setMaxTime(currentTimeMillis);
        params.addStatus(OrderStatusEnum.HAS_PAY.ordinal());

        List<Order> orderList = OrderDataHolder.getInstance().findOrderList(params);

        for (Order order : orderList) {
            for (BuyItem buyItem : order.getBuyItems()) {
                BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyItem.getBuyType());
                switch (buyTypeEnum) {
                    case PRDCARD://次卡
                        ProductCard productCard = productCardMap.get(buyItem.getPgId());
                        if (null != productCard) {
                            totalProductCount -= 1;
                        }
                        break;
                    case PRODUCT://项目
                        ProductInfo productInfo = productInfoMap.get(buyItem.getPgId());
                        if (null != productInfo) {
                            totalProductCount -= 1;
                        }
                        break;
                    case GOODS://商品
                        Goods goods = normalGoods.get(buyItem.getPgId());
                        if (null != goods) {
                            totalProductCount -= 1;
                        }
                        break;
                    case PACKAGE://套餐
                        PackageProject packageProject = packageProjectMap.get(buyItem.getPgId());
                        if (null != packageProject) {
                            totalProductCount -= 1;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        productData.setUnsoldProductCount(totalProductCount);
        return productData;
    }


    /**
     * 会员统计页面
     */
    public MemberStatisticsData getMemberStatisticsData(long storeId, long startTime, long endTime) {
        MemberStatisticsData memberStatisticsData = MemberStatisticsDataHelper.buildMemberStatisticsData(storeId, startTime, endTime);
        return memberStatisticsData;
    }

    /**
     * 连锁店会员统计
     *
     * @param storeIds
     * @param startTime
     * @param endTime
     * @return
     */
    public List<MemberStatisticsData> getChainMemberStatisticsData(String storeIds, long startTime, long endTime) {
        String[] storeIdArray = storeIds.split(",");
        List<MemberStatisticsData> targetList = new ArrayList<>();
        for (String storeId : storeIdArray) {
            MemberStatisticsData memberStatisticsData = MemberStatisticsDataHelper.buildMemberStatisticsData(Long.valueOf(storeId), startTime, endTime);
            targetList.add(memberStatisticsData);
        }
        return targetList;
    }


    //	次卡统计页面
    public CardStatisticsData getCardStatisticsData(String storeId) {

        return CardDataSplicing.getInstance().getCardStatisticsData(storeId);

    }

    //	连锁店次卡统计页面
    public List<CardStatisticsData> getChainCardStatisticsData(String storeIds) {
        String[] storeIdArray = storeIds.split(",");
        List<CardStatisticsData> targetList = new ArrayList<>();
        for (String storeId : storeIdArray) {
            CardStatisticsData cardStatisticsData = CardDataSplicing.getInstance().getCardStatisticsData(storeId);
            targetList.add(cardStatisticsData);
        }
        return targetList;

    }
}
