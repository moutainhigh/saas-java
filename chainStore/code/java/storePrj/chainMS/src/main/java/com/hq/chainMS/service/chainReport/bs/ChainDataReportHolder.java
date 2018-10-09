package com.hq.chainMS.service.chainReport.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.dataReport.apiData.ChainReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeClient.service.dataReport.bs.DataReportMgr;
import com.hq.storeClient.service.dataReport.data.*;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.List;

/**
 * @Description
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/6
 */
public class ChainDataReportHolder {
    public static ChainDataReportHolder getInstance() {
        return HotSwap.getInstance().getSingleton(ChainDataReportHolder.class);
    }

    public List<FinanceReport> getChainFinancialStatics(ChainReportQueryForm queryForm) {
        AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
        List<FinanceReport> financeReportList = DataReportMgr.getInstance().getFinanceReportList(queryForm);
        AppIdThreadLocal.getInstance().set(null);
        return financeReportList;
    }

    public List<MemberStatisticsData> getChainMemberStatisticsData(ChainReportQueryForm queryForm) {
        AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
        List<MemberStatisticsData> cardStatisticsData = DataReportMgr.getInstance().getChainMemberStatisticsData(queryForm);
        AppIdThreadLocal.getInstance().set(null);
        return cardStatisticsData;
    }

    public List<CardStatisticsData> getChainCardStatistics(ChainReportQueryForm queryForm) {
        AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
        List<CardStatisticsData> cardStatisticsData = DataReportMgr.getInstance().getChainCardStatisticsData(queryForm);
        AppIdThreadLocal.getInstance().set(null);
        return cardStatisticsData;
    }


    public List<ProductStatisticsData> getChainProductStatistics(ChainReportQueryForm queryForm) {
        AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
        List<ProductStatisticsData> productStatisticsDataList = DataReportMgr.getInstance().getChainProductStatistics(queryForm);
        AppIdThreadLocal.getInstance().set(null);
        return productStatisticsDataList;
    }

    public PageResp<TradingFlow> getChainTradingFlowReport(TradingFlowReportQueryForm queryForm) {
        AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
        PageResp<TradingFlow> tradingFlowPageResp =  DataReportMgr.getInstance().getChainTradingFlowReport(queryForm);
        AppIdThreadLocal.getInstance().set(null);
        return tradingFlowPageResp;
    }
}
