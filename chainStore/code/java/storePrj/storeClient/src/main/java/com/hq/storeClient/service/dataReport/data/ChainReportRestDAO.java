package com.hq.storeClient.service.dataReport.data;

import java.util.List;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.common.utils.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

/**
 * @Description 数据报表DAO
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/3
 */
public class ChainReportRestDAO extends RestDao<ChainReport> {
	public static ChainReportRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainReportRestDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	/**
	 * 获取指定店铺ID的财务统计
	 *
	 * @param reqMap
	 * @return
	 */
	public List<FinanceReport> getFinanceReportList(ReqMap reqMap) {
		final String actionName = "getFinanceReportList";
		RestResp restResp = super.rawGetReq(actionName, reqMap, 0, 1);
		return JsonUtil.getInstance().parseList(restResp.gettListJson(), FinanceReport.class);
	}

	/**
	 * 获取指定店铺ID的交易流水
	 *
	 * @param reqMap
	 * @return
	 */
	public PageResp<TradingFlow> getChainTradingFlowReport(ReqMap reqMap) {
		final String actionName = "getChainTradingFlowReport";
		RestResp restResp = super.rawGetReq(actionName, reqMap, 0, 1);
		return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), TradingFlow.class);
	}

	/**
	 * 获取指定店铺ID的会员统计
	 *
	 * @param reqMap
	 * @return
	 */
	public List<MemberStatisticsData> getChainMemberStatisticsData(ReqMap reqMap) {
		final String actionName = "getChainMemberStatisticsData";
		RestResp restResp = super.rawGetReq(actionName, reqMap, 0, 1);
		return JsonUtil.getInstance().parseList(restResp.gettListJson(), MemberStatisticsData.class);
	}

	/**
	 * 获取指定店铺ID的产品统计
	 *
	 * @param reqMap
	 * @return
	 */
	public List<ProductStatisticsData> getChainProductStatistics(ReqMap reqMap) {
		final String actionName = "getChainProductStatistics";
		RestResp restResp = super.rawGetReq(actionName, reqMap, 0, 1);
		return JsonUtil.getInstance().parseList(restResp.gettListJson(), ProductStatisticsData.class);
	}

	/**
	 * 获取指定店铺ID的次卡统计
	 *
	 * @param reqMap
	 * @return
	 */
	public List<CardStatisticsData> getChainCardStatisticsData(ReqMap reqMap) {
		final String actionName = "getChainCardStatisticsData";
		RestResp restResp = super.rawGetReq(actionName, reqMap, 0, 1);
		return JsonUtil.getInstance().parseList(restResp.gettListJson(), CardStatisticsData.class);
	}

}
