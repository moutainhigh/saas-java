package com.hq.chainMS.service.chainReport.api;

import com.hq.chainMS.service.chainReport.bs.ChainDataReportHandler;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.dataReport.apiData.ChainReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeClient.service.dataReport.data.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 数据报表
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/24
 */
@RestController()
@RequestMapping(value = "/dataReport")
public class ChainDataReportAPI {

    /**
     * 财务统计
     *
     * @param storeIds
     * @param maxTime
     * @param minTime
     * @return
     */
    @RequestMapping(value = "/getChainFinancialStatics", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<FinanceReport>> getChainFinancialStatics(@RequestParam(value = "storeIds") String storeIds,
                                                                            @RequestParam(value = "maxTime") long maxTime,
                                                                            @RequestParam(value = "minTime") long minTime
    ) {
        ChainReportQueryForm queryForm = getChainReportQueryForm(storeIds, maxTime, minTime);
        ReqResult<FinanceReport> result = ChainDataReportHandler.getInstance().getChainFinancialStatics(queryForm);
        ResponseEntity<RestResp<FinanceReport>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 会员统计
     *
     * @param storeIds
     * @return
     */
    @RequestMapping(value = "/getChainMemberStatisticsData", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<MemberStatisticsData>> getChainMemberStatisticsData(@RequestParam(value = "storeIds") String storeIds,
                                                                                       @RequestParam(value = "maxTime") long maxTime,
                                                                                       @RequestParam(value = "minTime") long minTime
    ) {
        ChainReportQueryForm queryForm = getChainReportQueryForm(storeIds, maxTime, minTime);
        ReqResult<MemberStatisticsData> result = ChainDataReportHandler.getInstance().getChainMemberStatisticsData(queryForm);
        ResponseEntity<RestResp<MemberStatisticsData>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 次卡统计
     *
     * @param storeIds
     * @return
     */
    @RequestMapping(value = "/getChainCardStatisticsData", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<CardStatisticsData>> getChainCardStatisticsData(@RequestParam(value = "storeIds") String storeIds
    ) {
        ChainReportQueryForm queryForm = getChainReportQueryForm(storeIds, 0, 0);
        ReqResult<CardStatisticsData> result = ChainDataReportHandler.getInstance().getChainCardStatisticsData(queryForm);
        ResponseEntity<RestResp<CardStatisticsData>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 产品统计
     *
     * @param storeIds
     * @return
     */
    @RequestMapping(value = "/getChainProductStatistics", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<ProductStatisticsData>> getChainCardStatisticsData(@RequestParam(value = "storeIds") String storeIds,
                                                                                      @RequestParam(value = "maxTime") long maxTime,
                                                                                      @RequestParam(value = "minTime") long minTime
    ) {
        ChainReportQueryForm queryForm = getChainReportQueryForm(storeIds, maxTime, minTime);
        ReqResult<ProductStatisticsData> result = ChainDataReportHandler.getInstance().getChainProductStatistics(queryForm);
        ResponseEntity<RestResp<ProductStatisticsData>> respEntity = result.buildRespEntity();
        return respEntity;
    }


    /**
     * 交易流水
     *
     * @param storeIds
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getChainTradingFlowReport", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<PageResp>> getChainTradingFlowReport(@RequestParam(value = "storeIds") String storeIds,
                                                                      @RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
                                                                      @RequestParam(value = "minTime", defaultValue = "0") long minTime,
                                                                      @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                      @RequestParam(value = "pageItemCount", defaultValue = "10") int pageItemCount,
                                                                      @RequestParam(value = "payType", defaultValue = "") String payType,
                                                                      @RequestParam(value = "tradeType", defaultValue = "") String tradeType,
                                                                      @RequestParam(value = "queryName", defaultValue = "") String queryName) {
        TradingFlowReportQueryForm queryForm = TradingFlowReportQueryForm.newInstance();
        queryForm.setStoreIds(storeIds);
        queryForm.setMaxTime(maxTime);
        queryForm.setMinTime(minTime);
        queryForm.setPayType(payType);
        queryForm.setPageNo(pageNo);
        queryForm.setPageItemCount(pageItemCount);
        queryForm.setTradeType(tradeType);
        queryForm.setQueryName(queryName);
        ReqResult<PageResp>  restResp = ChainDataReportHandler.getInstance().getChainTradingFlowReport(queryForm);
        ResponseEntity<RestResp<PageResp>> respEntity = restResp.buildJsonRespEntity();
        return respEntity;
    }


    private ChainReportQueryForm getChainReportQueryForm(String storeIds, long maxTime, long minTime) {
        ChainReportQueryForm queryForm = ChainReportQueryForm.newInstance();
        queryForm.setStoreIds(storeIds);
        queryForm.setMaxTime(maxTime);
        queryForm.setMinTime(minTime);
        return queryForm;
    }
}
