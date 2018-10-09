package com.hq.storeMS.service.dataReport.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeMS.service.dataReport.bs.DataReportHandler;
import com.hq.storeMS.service.dataReport.data.CardStatisticsData;
import com.hq.storeMS.service.dataReport.data.DataReport;
import com.hq.storeMS.service.dataReport.data.FinanceReport;
import com.hq.storeMS.service.dataReport.data.MemberDataCount;
import com.hq.storeMS.service.dataReport.data.MemberStatisticsData;
import com.hq.storeMS.service.dataReport.data.ProductStatisticsData;
import com.hq.storeMS.service.dataReport.data.vo.SellStatisData;

/**
 *
 * ClassName: DataReportAPI <br/>
 * Function: 数据统计API<br/>
 *
 * @author kevin
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value = "/dataReport")
public class DataReportAPI {

	@RequestMapping(value = "/getMemberDataCount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MemberDataCount>> getMemberDataCount(@RequestParam("storeId") long storeId) {
		ReqResult<MemberDataCount> result = DataReportHandler.getInstance().getMemberDataCount(storeId);
		ResponseEntity<RestResp<MemberDataCount>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findDataReprotList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<DataReport>> findDataReprotList(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime) {
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		ReqResult<DataReport> result = DataReportHandler.getInstance().findDataReprotList(queryForm);
		ResponseEntity<RestResp<DataReport>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findOrderList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Order>> findOrderList(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime) {
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		ReqResult<Order> result = DataReportHandler.getInstance().findOrderList(queryForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	// 销售统计
	@RequestMapping(value = "/findSellStatisData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<SellStatisData>> findSellStatisData(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime) {
		ReqResult<SellStatisData> result = DataReportHandler.getInstance().findSellStatisData(storeId, minTime, maxTime);
		ResponseEntity<RestResp<SellStatisData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
    /**
     * 产品统计
     * @param storeId
     * @param maxTime
     * @param minTime
     * @return
     */
	@RequestMapping(value = "/findProductStatistics", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductStatisticsData>> findProductStatistics(@RequestParam(value = "storeId") long storeId,
																				 @RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
																				 @RequestParam(value = "minTime", defaultValue = "0") long minTime) {
		ReqResult<ProductStatisticsData> result = DataReportHandler.getInstance().findProductStatistics(storeId, minTime, maxTime);
		ResponseEntity<RestResp<ProductStatisticsData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

//	// 产品统计获取产品数量和滞销产品数量的接口
//	@RequestMapping(value = "/findProductData", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<RestResp<ProductData>> getProductData(@RequestParam(value = "storeId") String storeId) {
//		ReqResult<ProductData> result = DataReportHandler.getInstance().findProductNumberWithUnsold(storeId);
//		ResponseEntity<RestResp<ProductData>> respEntity = result.buildJsonRespEntity();
//		return respEntity;
//	}


	// 会员统计的接口
	@RequestMapping(value = "/findMenberStatisticsData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MemberStatisticsData>> getMenberStatisticsData(
			@RequestParam(value = "startTime",defaultValue = "0") long startTime,
			@RequestParam(value = "endTime",defaultValue = "0") long endTime,
			@RequestParam(value = "storeId") long storeId) {
		ReqResult<MemberStatisticsData> result = DataReportHandler.getInstance().findMenberStatisticsData(startTime,
				endTime, storeId);
		ResponseEntity<RestResp<MemberStatisticsData>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	// 次卡统计
	@RequestMapping(value = "/findCardStatisticsData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<CardStatisticsData>> getCardData(@RequestParam(value = "storeId") String storeId) {
		ReqResult<CardStatisticsData> result = DataReportHandler.getInstance().findCardData(storeId);
		ResponseEntity<RestResp<CardStatisticsData>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	/**
	 * 获取财务统计
	 * 
	 * @param storeId
	 *            店铺ID
	 * @param maxTime
	 *            结束时间
	 * @param minTime
	 *            开始时间
	 * @return
	 */
	@RequestMapping(value = "/getFinanceReport", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<FinanceReport>> getFinanceReport(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime) {
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		ReqResult<FinanceReport> reqResult = DataReportHandler.getInstance().getFinanceReport(queryForm);
		return reqResult.buildRespEntity();
	}


	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTradingFlowReport", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getTradingFlowReport(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageItemCount", defaultValue = "10") int pageItemCount,
			@RequestParam(value = "payType", defaultValue = "") String payType,
			@RequestParam(value = "tradeType", defaultValue = "") String tradeType,
			@RequestParam(value = "queryName", defaultValue = "") String queryName) {
		TradingFlowReportQueryForm queryForm = TradingFlowReportQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		queryForm.setMaxTime(maxTime);
		queryForm.setMinTime(minTime);
		queryForm.setPageNo(pageNo);
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPayType(payType);
		queryForm.setTradeType(tradeType);
		queryForm.setQueryName(queryName);
		ReqResult<PageResp> restResp = DataReportHandler.getInstance().getTradingFlowReport(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = restResp.buildJsonRespEntity();
		return respEntity;
	}

}
