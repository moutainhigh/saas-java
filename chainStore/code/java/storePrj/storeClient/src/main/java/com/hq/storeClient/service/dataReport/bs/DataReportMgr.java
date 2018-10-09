package com.hq.storeClient.service.dataReport.bs;

import java.util.List;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.dataReport.apiData.ChainReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeClient.service.dataReport.data.CardStatisticsData;
import com.hq.storeClient.service.dataReport.data.ChainReportRestDAO;
import com.hq.storeClient.service.dataReport.data.DataReportRestDAO;
import com.hq.storeClient.service.dataReport.data.FinanceReport;
import com.hq.storeClient.service.dataReport.data.MemberStatisticsData;
import com.hq.storeClient.service.dataReport.data.ProductStatisticsData;
import com.hq.storeClient.service.dataReport.data.TradingFlow;
import com.hq.storeClient.service.dataReport.data.vo.SellStatisData;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class DataReportMgr {

	public static DataReportMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DataReportMgr.class);
	}

	// 获取销售统计报表
	public SellStatisData findSellStatisData(DataReportQueryForm queryForm) {
		final String findPath = "findSellStatisData";
		return DataReportRestDAO.getInstance().findSellStatisData(findPath, queryForm);
	}

	/**
	 * 获取指定店铺id的财务统计
	 *
	 * @param queryForm
	 * @return
	 */
	public List<FinanceReport> getFinanceReportList(ChainReportQueryForm queryForm) {
		ReqMap reqMap = getReqMap(queryForm);
		return ChainReportRestDAO.getInstance().getFinanceReportList(reqMap);
	}

	/**
	 * 获取指定店铺id的交易流水统计
	 *
	 * @param queryForm
	 * @return
	 */
	public PageResp<TradingFlow> getChainTradingFlowReport(TradingFlowReportQueryForm queryForm) {
		ReqMap reqMap = new ReqMap();
		reqMap.add("storeIds", queryForm.getStoreIds());
		reqMap.add("minTime", queryForm.getMinTime());
		reqMap.add("maxTime", queryForm.getMaxTime());
		reqMap.add("queryName", queryForm.getQueryName());
		reqMap.add("payType", queryForm.getPayType());
		reqMap.add("tradeType", queryForm.getTradeType());
		reqMap.add("pageNo", queryForm.getPageNo());
		reqMap.add("pageItemCount", queryForm.getPageItemCount());
		return ChainReportRestDAO.getInstance().getChainTradingFlowReport(reqMap);
	}

	/**
	 * 获取指定店铺id的会员统计
	 *
	 * @param queryForm
	 * @return
	 */
	public List<MemberStatisticsData> getChainMemberStatisticsData(ChainReportQueryForm queryForm) {
		ReqMap reqMap = getReqMap(queryForm);
		return ChainReportRestDAO.getInstance().getChainMemberStatisticsData(reqMap);
	}

	/**
	 * 获取指定店铺id的产品统计
	 *
	 * @param queryForm
	 * @return
	 */
	public List<ProductStatisticsData> getChainProductStatistics(ChainReportQueryForm queryForm) {
		ReqMap reqMap = getReqMap(queryForm);
		return ChainReportRestDAO.getInstance().getChainProductStatistics(reqMap);
	}

	/**
	 * 获取指定店铺id的次卡统计
	 *
	 * @param queryForm
	 * @return
	 */
	public List<CardStatisticsData> getChainCardStatisticsData(ChainReportQueryForm queryForm) {
		ReqMap reqMap = new ReqMap();
		reqMap.add("storeIds", queryForm.getStoreIds());
		return ChainReportRestDAO.getInstance().getChainCardStatisticsData(reqMap);
	}

	private ReqMap getReqMap(ChainReportQueryForm queryForm) {
		ReqMap reqMap = new ReqMap();
		reqMap.add("storeIds", queryForm.getStoreIds());
		reqMap.add("minTime", queryForm.getMinTime());
		reqMap.add("maxTime", queryForm.getMaxTime());
		return reqMap;
	}
}
