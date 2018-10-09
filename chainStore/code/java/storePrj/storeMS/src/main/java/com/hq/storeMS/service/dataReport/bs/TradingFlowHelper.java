package com.hq.storeMS.service.dataReport.bs;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeMS.service.dataReport.data.TradeTypeEnum;
import com.hq.storeMS.service.dataReport.data.TradingFlow;
import com.hq.storeMS.service.orderNotes.bs.OrderNotesMgr;
import com.hq.storeMS.service.orderNotes.data.ChargeBackRecord;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TradingFlowHelper {

    public static PageResp<TradingFlow> getTradingFlowPageResp(TradingFlowReportQueryForm queryForm) {
        List<Order> orders = DataReportMgr.getInstance().findFianceOrderList(queryForm);
        List<TradingFlow> targetList = new ArrayList<>();
        buildTradingFlowList(queryForm, orders, targetList);
        sortTradingFlowList(targetList);
        return PageUtil.getInstance().buildPageResp(targetList, queryForm.getPageNo(), queryForm.getPageItemCount());
    }

    public static PageResp<TradingFlow> getChainTradingFlow(String storeIds, TradingFlowReportQueryForm queryForm) {
        String[] ids = storeIds.split(",");
        List<TradingFlow> targetList = new ArrayList<>();
        for (String id : ids) {
            queryForm.setStoreId(Long.valueOf(id));
            List<Order> orders = DataReportMgr.getInstance().findFianceOrderList(queryForm);
            buildTradingFlowList(queryForm, orders, targetList);
        }
        sortTradingFlowList(targetList);
        return PageUtil.getInstance().buildPageResp(targetList, queryForm.getPageNo(), queryForm.getPageItemCount());
//        return targetList;
    }


    private static void buildTradingFlowList(TradingFlowReportQueryForm queryForm, List<Order> orders, List<TradingFlow> targetList) {
        if (null != orders && orders.size() > 0) {
            StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(queryForm.getStoreId());
            Map<String, Leaguer> leaguerInfoMap = storeLeaguerInfo.getLeaguerInfoMap();
            List<Order> chargeBackOrderList = addReceiptTrade(queryForm, orders, targetList, leaguerInfoMap);
            if (null != chargeBackOrderList && chargeBackOrderList.size() > 0) {
                String ids = getOrderNotesIds(chargeBackOrderList);
                List<OrderNotes> orderNotesList = OrderNotesMgr.getInstance().findOrderByIds(queryForm.getStoreId(), ids);
                addRefundTrade(queryForm, orderNotesList, targetList, leaguerInfoMap, chargeBackOrderList);
            }
        }
    }

    /**
     * 获取退单记录 ids
     *
     * @param chargeBackOrderList
     * @return
     */
    private static String getOrderNotesIds(List<Order> chargeBackOrderList) {
        StringBuilder idsSb = null;
        for (Order order : chargeBackOrderList) {
            if (null == idsSb) {
                idsSb = new StringBuilder(String.valueOf(order.getId()));
            } else {
                idsSb.append(",").append(String.valueOf(order.getId()));
            }
        }
        return idsSb.toString();
    }

    private static void sortTradingFlowList(List<TradingFlow> targetList) {
        targetList.sort(new Comparator<TradingFlow>() {
            @Override
            public int compare(TradingFlow o1, TradingFlow o2) {
                if (o1.getPayTime() > o2.getPayTime()) {
                    return -1;
                } else if (o1.getPayTime() == o2.getPayTime()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    private static void addRefundTrade(TradingFlowReportQueryForm queryForm, List<OrderNotes> orderNotes, List<TradingFlow> targetList, Map<String, Leaguer> leaguerInfoMap, List<Order> chargebackOrderList) {
        if (queryForm.getTradeTypeMap().isEmpty() || queryForm.getTradeTypeMap().containsKey(TradeTypeEnum.REFUND.ordinal())) {
            if (null != orderNotes) {
                for (OrderNotes oN : orderNotes) {
                    for (Order order : chargebackOrderList) {
                        if (order.getId() == oN.getId()) {
                            List<ChargeBackRecord> chargeBackRecords = new ArrayList<>(oN.getChargeBackRecordMap().values());
                            for (ChargeBackRecord chargeBackRecord : chargeBackRecords) {
                                List<PayItem> payItems = chargeBackRecord.getPayItems();
                                addTradingFlow(targetList, leaguerInfoMap, order, payItems, TradeTypeEnum.REFUND.ordinal(), queryForm);
                            }
                            break;
                        }
                    }

                }
            }
        }
    }

    private static List<Order> addReceiptTrade(TradingFlowReportQueryForm queryForm, List<Order> orders, List<TradingFlow> targetList, Map<String, Leaguer> leaguerInfoMap) {
        List<Order> chargeBackOrderList = new ArrayList<>();
        for (Order order : orders) {
            if (queryForm.getTradeTypeMap().isEmpty() || queryForm.getTradeTypeMap().containsKey(TradeTypeEnum.RECEIPT.ordinal())) {
                List<PayItem> payItems = order.getPayItems();
                addTradingFlow(targetList, leaguerInfoMap, order, payItems, TradeTypeEnum.RECEIPT.ordinal(), queryForm);

            }
            if (queryForm.getTradeTypeMap().isEmpty() || queryForm.getTradeTypeMap().containsKey(TradeTypeEnum.REFUND.ordinal())) {
                if (order.getStatus() == OrderStatusEnum.CHARGEBACK_ALL.ordinal() || order.getStatus() == OrderStatusEnum.CHARGEBACK_PART.ordinal()) {
                    chargeBackOrderList.add(order);
                }
            }
        }
        return chargeBackOrderList;
    }


    private static void addTradingFlow(List<TradingFlow> targetList, Map<String, Leaguer> leaguerInfoMap, Order order, List<PayItem> payItems, int tradeType, TradingFlowReportQueryForm queryForm) {
        if (null != payItems && payItems.size() > 0) {
            for (PayItem payItem : payItems) {
                String leagueName = getLeagueName(leaguerInfoMap, order);
                String tradeNo = payItem.getTradeNo();
                String queryName = queryForm.getQueryName();
                if (null == tradeNo)
                    tradeNo = "";
                if (!StringUtils.isBlank(queryName)) {
                    if (!leagueName.equals(queryName) && !tradeNo.contains(queryName)) {
                        return;
                    }
                }
                Map<Integer, Integer> payTypeMap = queryForm.getPayTypeMap();
                if (!payTypeMap.isEmpty()) {
                    if (!payTypeMap.containsKey(payItem.getPayType())) {
                        return;
                    }
                }
                TradingFlow tradingFlow = TradingFlow.newInstance(String.valueOf(order.getId()), payItem, tradeType, leagueName);
                targetList.add(tradingFlow);
            }
        }
    }


    private static String getLeagueName(Map<String, Leaguer> leaguerInfoMap, Order order) {
        String leagueName = "";
        Leaguer leaguer = leaguerInfoMap.get(order.getLeaguerId());
        if (null != leaguer) {
            leagueName = leaguer.getName();
        }
        return leagueName;
    }
}
