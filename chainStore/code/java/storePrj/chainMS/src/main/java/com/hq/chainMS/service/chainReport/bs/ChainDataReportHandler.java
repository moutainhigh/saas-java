package com.hq.chainMS.service.chainReport.bs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.dataReport.apiData.ChainReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeClient.service.dataReport.data.CardMapData;
import com.hq.storeClient.service.dataReport.data.CardStatisticsData;
import com.hq.storeClient.service.dataReport.data.FinanceReport;
import com.hq.storeClient.service.dataReport.data.MemberStatisticsData;
import com.hq.storeClient.service.dataReport.data.ProductStatisticsData;
import com.hq.storeClient.service.dataReport.data.ProductStatisticsItem;
import com.hq.storeClient.service.dataReport.data.TradingFlow;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

/**
 * @Description
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/24
 */
public class ChainDataReportHandler {


    public static ChainDataReportHandler getInstance() {
        return HotSwap.getInstance().getSingleton(ChainDataReportHandler.class);
    }


    public ReqResult<FinanceReport> getChainFinancialStatics(ChainReportQueryForm queryForm) {
        ReqResult<FinanceReport> result = ReqResult.newInstance(false, FinanceReport.class);
        try {
            List<FinanceReport> financeReportList = ChainDataReportHolder.getInstance().getChainFinancialStatics(queryForm);
            result.setTargetList(financeReportList);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.DataReport, "ChainDataReportHandler[getChainFinancialStatics]", reason, e);
        }
        return result;
    }


    public ReqResult<MemberStatisticsData> getChainMemberStatisticsData(ChainReportQueryForm queryForm) {
        ReqResult<MemberStatisticsData> result = ReqResult.newInstance(false, MemberStatisticsData.class);
        try {
            List<MemberStatisticsData> memberStatisticsDataList = ChainDataReportHolder.getInstance().getChainMemberStatisticsData(queryForm);
            MemberStatisticsData ret = null;
            if (null != memberStatisticsDataList && memberStatisticsDataList.size() > 0) {
                ret = memberStatisticsDataList.get(0);
                if (memberStatisticsDataList.size() > 1) {
                    for (int i = 1; i < memberStatisticsDataList.size(); i++) {
                        ret.add(memberStatisticsDataList.get(i));
                    }
                }
            } else {
                ret = MemberStatisticsData.newInstance();
            }
            result.setTarget(ret);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.DataReport, "ChainDataReportHandler[getChainMemberStatisticsData]", reason, e);
        }
        return result;
    }


    public ReqResult<CardStatisticsData> getChainCardStatisticsData(ChainReportQueryForm queryForm) {
        ReqResult<CardStatisticsData> result = ReqResult.newInstance(false, CardStatisticsData.class);
        try {
            List<CardStatisticsData> cardStatisticsDataList = ChainDataReportHolder.getInstance().getChainCardStatistics(queryForm);
            CardStatisticsData ret = new CardStatisticsData();
            ret.setUpdataTime(AppUtils.getDate(System.currentTimeMillis()));
            ret.setStoreId(queryForm.getStoreIds());
            List<CardMapData> cardMapDataList = new ArrayList<>();
            if (null != cardStatisticsDataList) {
                for (CardStatisticsData cardStatisticsData : cardStatisticsDataList) {
                    if (null != cardStatisticsData.getCardMapDataList())
                        cardMapDataList.addAll(cardStatisticsData.getCardMapDataList());
                }
            }
            cardMapDataList.sort(new Comparator<CardMapData>() {
                @Override
                public int compare(CardMapData o1, CardMapData o2) {
                    return o2.getNumber() - o1.getNumber();
                }

            });
            ret.setCardMapDataList(cardMapDataList);
            result.setTarget(ret);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.DataReport, "ChainDataReportHandler[getChainCardStatisticsData]", reason, e);
        }
        return result;
    }


    public ReqResult<ProductStatisticsData> getChainProductStatistics(ChainReportQueryForm queryForm) {
        ReqResult<ProductStatisticsData> result = ReqResult.newInstance(false, ProductStatisticsData.class);
        try {
            List<ProductStatisticsData> productStatisticsDataList = ChainDataReportHolder.getInstance().getChainProductStatistics(queryForm);
            ProductStatisticsData ret = null;
            if (null != productStatisticsDataList && productStatisticsDataList.size() > 0) {
                ret = productStatisticsDataList.get(0);
                if (productStatisticsDataList.size() > 1) {
                    for (int i = 1; i < productStatisticsDataList.size(); i++) {
                        ret.add(productStatisticsDataList.get(i));
                    }
                }
                //此处排序



            } else {
                ret = new ProductStatisticsData();
            }
            result.setTarget(ret);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.DataReport, "ChainDataReportHandler[getChainProductStatistics]", reason, e);
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getChainTradingFlowReport(TradingFlowReportQueryForm queryForm) {
        ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
        try {
            PageResp<TradingFlow> tradingFlowPageResp = ChainDataReportHolder.getInstance().getChainTradingFlowReport(queryForm);
            result.setTarget(tradingFlowPageResp);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
            result.setTips("服务暂不可用，请稍后尝试");
            MainLog.error(LogModule.DataReport, "ChainDataReportHandler[getChainTradingFlowReport]", reason, e);
        }
        return result;
    }


    @SynIgnoreField
    private static Comparator<ProductStatisticsItem> comparator = new Comparator<ProductStatisticsItem>() {
        @Override
        public int compare(ProductStatisticsItem o1, ProductStatisticsItem o2) {
            return o2.getSalesVolume() - o1.getSalesVolume();
        }
    };

}
