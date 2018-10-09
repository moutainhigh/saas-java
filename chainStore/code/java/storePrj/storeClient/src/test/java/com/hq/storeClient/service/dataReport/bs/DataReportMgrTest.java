package com.hq.storeClient.service.dataReport.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.storeClient.common.validate.ValidateThreadLocal;
import com.hq.storeClient.service.dataReport.apiData.ChainReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeClient.service.dataReport.apiData.TradingFlowReportQueryForm;
import com.hq.storeClient.service.dataReport.data.vo.SellStatisData;
import com.hq.storeClient.service.storeConfig.bs.StoreConfigClientMgr;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.hq.storeClient.testClass.TestEnv;
import com.zenmind.common.json.JsonUtil;

/**
 * @Description
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/9/3
 */
public class DataReportMgrTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestEnv.initTestEnv();
	}

	@Test
	public void testFindSellStatisData() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		long currentTimeMillis = System.currentTimeMillis();
		DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
		queryForm.setStoreId(16052L);
		queryForm.setMinTime(currentTimeMillis - 10 * 24L * 3600 * 1000);
		queryForm.setMaxTime(currentTimeMillis - 5 * 24L * 3600 * 1000);
		SellStatisData sellStatisData = DataReportMgr.getInstance().findSellStatisData(queryForm);
		System.out.println(JsonUtil.getInstance().toJson(sellStatisData));
		ValidateThreadLocal.getInstance().remove();
	}

	@Test
	public void testGetFinanceReport() {
		String storeIds = "16052,16085,16086";
		ChainReportQueryForm queryForm = ChainReportQueryForm.newInstance();
		queryForm.setStoreIds(storeIds);
		queryForm.setMinTime(1500052800000L);
		queryForm.setMaxTime(1535731199000L);

		TradingFlowReportQueryForm queryForm1 = TradingFlowReportQueryForm.newInstance();
		queryForm1.setStoreIds("16052,16085,16086");
		queryForm1.setMinTime(1500052800000L);
		queryForm1.setMaxTime(1535731199000L);
		// List<FinanceReport> financeReport=
		// DataReportMgr.getInstance().getFinanceReportList(queryForm);
		// List<CardStatisticsData> financeReport=
		// DataReportMgr.getInstance().getChainCardStatisticsData(queryForm);
		// List<MemberStatisticsData> financeReport=
		// DataReportMgr.getInstance().getChainMemberStatisticsData(queryForm);
		// List<ProductStatisticsData> financeReport=
		// DataReportMgr.getInstance().getChainProductStatistics(queryForm);
		// PageResp<TradingFlow> financeReport=
		// DataReportMgr.getInstance().getChainTradingFlowReport(queryForm1);
		List<StoreConfig> StoreConfigs = StoreConfigClientMgr.getInstance().getStoreConfigs(storeIds);
		System.out.println(JsonUtil.getInstance().toJson(StoreConfigs));
	}
}
