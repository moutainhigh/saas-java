package com.hq.storeMS.service.dataReport.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeMS.service.dataReport.bs.DataReportHandler;
import com.hq.storeMS.service.dataReport.data.CardStatisticsData;
import com.hq.storeMS.service.dataReport.data.FinanceReport;
import com.hq.storeMS.service.dataReport.data.MemberStatisticsData;
import com.hq.storeMS.service.dataReport.data.ProductStatisticsData;

/**
 * @Description 连锁店报表
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/4
 */

@RestController
@RequestMapping(value = "/chainReport")
public class ChainReportAPI {


    /**
     * 根据指定店铺ID列表 获取这些店铺的财务统计
     *
     * @param storeIds 店铺ID,用逗号隔开
     * @param maxTime  结束时间
     * @param minTime  开始时间
     * @return
     */
    @RequestMapping(value = "/getFinanceReportList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<FinanceReport>> getFinanceReportList(@RequestParam(value = "storeIds") String storeIds,
                                                                        @RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
                                                                        @RequestParam(value = "minTime", defaultValue = "0") long minTime) {
        
        ReqResult<FinanceReport> reqResult = DataReportHandler.getInstance().getFinanceReportList(storeIds, minTime, maxTime);
        return reqResult.buildRespEntity();
    }

    /**
     * 连锁店交易流水
     *
     * @param storeIds
     * @param maxTime
     * @param minTime
     * @param pageNo
     * @param pageItemCount
     * @param payType
     * @param tradeType
     * @param queryName
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
        queryForm.setMaxTime(maxTime);
        queryForm.setMinTime(minTime);
        queryForm.setPageNo(pageNo);
        queryForm.setPageItemCount(pageItemCount);
        queryForm.setPayType(payType);
        queryForm.setTradeType(tradeType);
        queryForm.setQueryName(queryName);
        ReqResult<PageResp> reqResult = DataReportHandler.getInstance().getChainTradingFlowReport(storeIds, queryForm);
        ResponseEntity<RestResp<PageResp>> respEntity = reqResult.buildJsonRespEntity();
        return respEntity;
    }


    /**
     * 连锁店会员统计的接口
     */
    @RequestMapping(value = "/getChainMemberStatisticsData", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<MemberStatisticsData>> getChainMemberStatisticsData(
            @RequestParam(value = "minTime", defaultValue = "0") long minTime,
            @RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
            @RequestParam(value = "storeIds") String storeIds) {
        ReqResult<MemberStatisticsData> result = DataReportHandler.getInstance().getChainMemberStatisticsData(minTime,
                maxTime, storeIds);
        ResponseEntity<RestResp<MemberStatisticsData>> respEntity = result.buildRespEntity();
        return respEntity;
    }

    /**
     * 连锁店产品统计
     *
     * @param storeIds
     * @param maxTime
     * @param minTime
     * @return
     */
    @RequestMapping(value = "/getChainProductStatistics", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<ProductStatisticsData>> getChainProductStatistics(@RequestParam(value = "storeIds") String storeIds,
                                                                                     @RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
                                                                                     @RequestParam(value = "minTime", defaultValue = "0") long minTime) {
        ReqResult<ProductStatisticsData> result = DataReportHandler.getInstance().getChainProductStatistics(storeIds, minTime, maxTime);
        ResponseEntity<RestResp<ProductStatisticsData>> respEntity = result.buildRespEntity();
        return respEntity;
    }


    /**
     * 连锁店次卡统计
     */
    @RequestMapping(value = "/getChainCardStatisticsData", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResp<CardStatisticsData>> getChainCardStatisticsData(@RequestParam(value = "storeIds") String storeIds) {
        ReqResult<CardStatisticsData> result = DataReportHandler.getInstance().getChainCardStatisticsData(storeIds);
        ResponseEntity<RestResp<CardStatisticsData>> respEntity = result.buildRespEntity();
        return respEntity;
    }


}
