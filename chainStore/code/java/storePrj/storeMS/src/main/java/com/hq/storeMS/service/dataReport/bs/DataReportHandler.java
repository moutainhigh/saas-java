package com.hq.storeMS.service.dataReport.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeMS.service.dataReport.data.CardStatisticsData;
import com.hq.storeMS.service.dataReport.data.DataReport;
import com.hq.storeMS.service.dataReport.data.FinanceReport;
import com.hq.storeMS.service.dataReport.data.MemberDataCount;
import com.hq.storeMS.service.dataReport.data.MemberStatisticsData;
import com.hq.storeMS.service.dataReport.data.ProductStatisticsData;
import com.hq.storeMS.service.dataReport.data.ProductStatisticsHelper;
import com.hq.storeMS.service.dataReport.data.TradingFlow;
import com.hq.storeMS.service.dataReport.data.vo.SellStatisData;
import com.zenmind.common.hotSwap.HotSwap;

public class DataReportHandler {

    public static DataReportHandler getInstance() {
        return HotSwap.getInstance().getSingleton(DataReportHandler.class);
    }

    private final LogModule logModule = LogModule.DataReport;

    public ReqResult<MemberDataCount> getMemberDataCount(long storeId) {
        ReqResult<MemberDataCount> result = ReqResult.newInstance(false, MemberDataCount.class);
        try {
            MemberDataCount memberDataCount = DataReportMgr.getInstance().getMemberDataCount(storeId);
            if (memberDataCount != null) {
                result.setTarget(memberDataCount);
                result.setSuccess(true);
            } else {
                result.setStatus(RespStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            final String logId = "DataReportHandler[getMemberDataCount]";
            final String reason = LogHelper.getInstance().exceptionReason(storeId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<DataReport> findDataReprotList(DataReportQueryForm queryForm) {
        ReqResult<DataReport> result = ReqResult.newInstance(false, DataReport.class);
        try {
            List<DataReport> dataReports = DataReportMgr.getInstance().findDataReprotList(queryForm);
            result.setTargetList(dataReports);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "DataReportHandler[findDataReprotList]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<Order> findOrderList(DataReportQueryForm queryForm) {
        ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
        try {
            List<Order> orders = DataReportMgr.getInstance().findOrderList(queryForm);
            result.setTargetList(orders);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "DataReportHandler[findOrderList]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }


    // 销售统计
    public ReqResult<SellStatisData> findSellStatisData(long storeId, long minTime, long maxTime) {
    	ReqResult<SellStatisData> result = ReqResult.newInstance(false, SellStatisData.class);
    	try {
    		SellStatisData target = SellDataReportMgr.getInstance().findSellStatisData(storeId, minTime, maxTime);
    		result.setTarget(target);
    		result.setSuccess(true);
    	} catch (Exception e) {
    		final String logId = "DataReportHandler[findSellStatisData]";
    		final String reason = LogHelper.getInstance().exceptionReason(storeId, minTime, maxTime);
    		ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
    				.withReason(reason).withResult(result);
    		HandlerHelper.getInstance().handleException(exceptionInfo, e);
    	}
    	return result;
    }
    
    /**
     * 产品统计
     *
     * @param
     * @return
     */
    public ReqResult<ProductStatisticsData> findProductStatistics(long storeId, long minTime, long maxTime) {
        ReqResult<ProductStatisticsData> result = ReqResult.newInstance(false, ProductStatisticsData.class);
        try {
            ProductStatisticsData productStatisticsData = ProductStatisticsHelper.buildProductStatisticsData(storeId, minTime, maxTime);
            result.setTarget(productStatisticsData);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "DataReportHandler[findProductStatistics]";
            final String reason = LogHelper.getInstance().exceptionReason(storeId, minTime, maxTime);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }


    /**
     * 连锁店产品统计
     *
     * @param
     * @return
     */
    public ReqResult<ProductStatisticsData> getChainProductStatistics(String storeIds, long minTime, long maxTime) {
        ReqResult<ProductStatisticsData> result = ReqResult.newInstance(false, ProductStatisticsData.class);
        if (StringUtils.isBlank(storeIds)) {
            idIsNull(result);
            return result;
        }
        try {
            List<ProductStatisticsData> productStatisticsData = ProductStatisticsHelper.buildChainProductStatisticsData(storeIds, minTime, maxTime);
            result.setTargetList(productStatisticsData);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "DataReportHandler[getChainProductStatistics]";
            final String reason = LogHelper.getInstance().exceptionReason(storeIds, minTime, maxTime);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

//
//    public ReqResult<ProductData> findProductNumberWithUnsold(String storeId) {
//        ReqResult<ProductData> result = ReqResult.newInstance(false, ProductData.class);
//        try {
//            ProductData productData = DataReportMgr.getInstance().findProductDataWithUnsold(storeId);
//            if (productData != null) {
//                result.setTarget(productData);
//                result.setSuccess(true);
//            }
//        } catch (Exception e) {
//            final String logId = "DataReportHandler[findProductNumberWithUnsold]";
//            final String reason = LogHelper.getInstance().exceptionReason(storeId);
//            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
//                    .withLogModule(logModule).withLogId(logId)
//                    .withReason(reason).withResult(result);
//            HandlerHelper.getInstance().handleException(exceptionInfo, e);
//        }
//        return result;
//    }


    public ReqResult<CardStatisticsData> findCardData(String storeId) {
        ReqResult<CardStatisticsData> result = ReqResult.newInstance(false, CardStatisticsData.class);
        try {
            CardStatisticsData cardStatisticsData = DataReportMgr.getInstance().getCardStatisticsData(storeId);
            if (cardStatisticsData != null) {
                result.setTarget(cardStatisticsData);
                result.setSuccess(true);
            }
        } catch (Exception e) {
            final String logId = "DataReportHandler[findCardData]";
            final String reason = LogHelper.getInstance().exceptionReason(storeId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
                    .withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    /**
     * 连锁店次卡统计
     *
     * @param storeIds
     * @return
     */
    public ReqResult<CardStatisticsData> getChainCardStatisticsData(String storeIds) {
        ReqResult<CardStatisticsData> result = ReqResult.newInstance(false, CardStatisticsData.class);
        if (StringUtils.isBlank(storeIds)) {
            idIsNull(result);
            return result;
        }
        try {
            List<CardStatisticsData> cardStatisticsDataList = DataReportMgr.getInstance().getChainCardStatisticsData(storeIds);
            if (cardStatisticsDataList != null) {
                result.setTargetList(cardStatisticsDataList);
                result.setStatus(RespStatus.OK);
                result.setSuccess(true);
            }
        } catch (Exception e) {
            final String logId = "DataReportHandler[getChainCardStatisticsData]";
            final String reason = LogHelper.getInstance().exceptionReason(storeIds);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
                    .withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }


    // 会员统计
    public ReqResult<MemberStatisticsData> findMenberStatisticsData(long startTime, long endTime, long storeId) {
        ReqResult<MemberStatisticsData> result = ReqResult.newInstance(false, MemberStatisticsData.class);

        try {

            MemberStatisticsData memberStatisticsData = DataReportMgr.getInstance().getMemberStatisticsData(storeId,
                    startTime, endTime);
            if (memberStatisticsData != null) {
                result.setTarget(memberStatisticsData);
                result.setSuccess(true);
            }
        } catch (Exception e) {
            final String logId = "DataReportHandler[findMenberStatisticsData]";
            final String reason = LogHelper.getInstance().exceptionReason(storeId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
                    .withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;

    }

    /**
     * 连锁店会员统计
     */
    public ReqResult<MemberStatisticsData> getChainMemberStatisticsData(long startTime, long endTime, String storeIds) {
        ReqResult<MemberStatisticsData> result = ReqResult.newInstance(false, MemberStatisticsData.class);
        if (StringUtils.isBlank(storeIds)) {
            idIsNull(result);
            return result;
        }
        try {
            List<MemberStatisticsData> memberStatisticsDatas = DataReportMgr.getInstance().getChainMemberStatisticsData(storeIds,
                    startTime, endTime);
            if (memberStatisticsDatas != null) {
                result.setTargetList(memberStatisticsDatas);
                result.setSuccess(true);
                result.setStatus(RespStatus.OK);
            }
        } catch (Exception e) {
            final String logId = "DataReportHandler[findMenberStatisticsData]";
            final String reason = LogHelper.getInstance().exceptionReason(storeIds);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
                    .withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;

    }

    /**
     * 获取财务统计 待优化
     *
     * @param queryForm
     * @return
     */
    public ReqResult<FinanceReport> getFinanceReport(DataReportQueryForm queryForm) {
        ReqResult<FinanceReport> result = ReqResult.newInstance(false, FinanceReport.class);
        try {
            FinanceReport financeReport = FinanceReportHelper.buildFinanceReport(queryForm);
            result.setTarget(financeReport);
            result.setStatus(RespStatus.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "DataReportHandler[getFinanceReport]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }

        return result;
    }

    /**
     * 获取财务统计列表
     *
     * @param
     * @return
     */
    public ReqResult<FinanceReport> getFinanceReportList(String storeIds, long minTime, long maxTime) {
        ReqResult<FinanceReport> result = ReqResult.newInstance(false, FinanceReport.class);
        if (StringUtils.isBlank(storeIds)) {
            idIsNull(result);
        } else {
            try {
                String[] ids = storeIds.split(",");
                List<FinanceReport> ret = new ArrayList<>();
                for (String id : ids) {
                    DataReportQueryForm queryForm = buildQueryForm(minTime, maxTime, id);
                    FinanceReport financeReport = FinanceReportHelper.buildFinanceReport(queryForm);
                    ret.add(financeReport);
                }
                result.setTargetList(ret);
                result.setStatus(RespStatus.OK);
                result.setSuccess(true);
            } catch (Exception e) {
                final String logId = "DataReportHandler[getFinanceReportList]";
                final String reason = LogHelper.getInstance().exceptionReason(storeIds, minTime, maxTime);
                ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                        .withReason(reason).withResult(result);
                HandlerHelper.getInstance().handleException(exceptionInfo, e);
            }
        }


        return result;
    }

    private DataReportQueryForm buildQueryForm(long minTime, long maxTime, String storeId) {
        DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
        queryForm.setStoreId(Long.valueOf(storeId));
        queryForm.setMaxTime(maxTime);
        queryForm.setMinTime(minTime);
        return queryForm;
    }

    /**
     * 获取交易流水
     *
     * @param queryForm
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ReqResult<PageResp> getTradingFlowReport(TradingFlowReportQueryForm queryForm) {
        ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
        try {
            PageResp<TradingFlow> pageResp = TradingFlowHelper.getTradingFlowPageResp(queryForm);
            result.setSuccess(true);
            result.setStatus(RespStatus.OK);
            result.setTarget(pageResp);

        } catch (Exception e) {
            final String logId = "DataReportHandler[getTradingFlowReport]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                    .withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }

        return result;
    }

    /**
     * 获取交易流水
     *
     * @param queryForm
     * @return
     */
    @SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getChainTradingFlowReport(String storeIds, TradingFlowReportQueryForm queryForm) {
        ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
        if (StringUtils.isBlank(storeIds)) {
            idIsNull(result);
        } else {
            try {
                PageResp<TradingFlow> pageResp  = TradingFlowHelper.getChainTradingFlow(storeIds, queryForm);
                result.setSuccess(true);
                result.setStatus(RespStatus.OK);
                result.setTarget(pageResp);
            } catch (Exception e) {
                final String logId = "DataReportHandler[getChainTradingFlowReport]";
                final String reason = LogHelper.getInstance().exceptionReason(storeIds, queryForm);
                ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
                        .withReason(reason).withResult(result);
                HandlerHelper.getInstance().handleException(exceptionInfo, e);
            }
        }
        return result;
    }


    @SuppressWarnings("rawtypes")
	private void idIsNull(ReqResult result) {
        result.setTips("店铺Id不能为空");
        result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
    }
}
