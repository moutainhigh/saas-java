package com.hq.storeMS.service.homePage.bs.mgr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeMS.service.appointment.bs.AppointmentMgr;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.homePage.apiData.QueryHomePageForm;
import com.hq.storeMS.service.homePage.data.HomePage;
import com.hq.storeMS.service.homePage.data.StatisticsData;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StatisticsDataMgr implements IHomePageMgr{
	public static StatisticsDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StatisticsDataMgr.class);
	}
	
	
	public void attachInfo(HomePage homePage, QueryHomePageForm queryForm){
		Date today = new Date();
		long storeId = queryForm.getStoreId();
		
		StatisticsData statisticsData = StatisticsData.newInstance();
		
		//预约信息
		AppointmentQueryForm appointmentQueryForm = AppointmentQueryForm.newInstance();
		appointmentQueryForm.setStoreId(storeId);
		appointmentQueryForm.setMinTime(getMinTime(today));
		appointmentQueryForm.setMaxTime(getMaxTime(today));
		List<Appointment> appoints = AppointmentMgr.getInstance().findAppointmentList(appointmentQueryForm);
		statisticsData.setAppointCount(appoints.size());
		
		//客户总数
		StoreLeaguerInfo storeLeaguer = StoreLeaguerInfoMgr.getInstance().get(storeId);
		Collection<Leaguer> values = storeLeaguer.getLeaguerInfoMap().values();
		int leaguerCount = 0;
		for (Leaguer leaguer : values) {
			if(leaguer.getEntityState() != EntityState.Deleted.ordinal()) {
				leaguerCount++;
			}
		}
		statisticsData.setLeaguerCount(leaguerCount);
		
		//本月销售总额
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMaxTime(getMaxTime(today));
		params.setMinTime(getMonthFirstDay());
		params.setStoreId(storeId);
		params.addStatus(OrderStatusEnum.HAS_PAY.ordinal());
		OrderCount orderCount = OrderMgr.getInstance().getOrderCount(params);
		statisticsData.setMonthOrderCount(orderCount.getOrderCost());
		
		homePage.setStatisticsData(statisticsData);
	}
	
	private long getMonthFirstDay() {
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return getMinTime(c.getTime());
	}
	
	private long getMinTime(Date today) {
		DateFormat df = new SimpleDateFormat(ServerConstants.df_short_nosplit);
		String format = df.format(today);
		long time = 0;
		try {
			time = DateUtils.parseDate(format, ServerConstants.df_short_nosplit).getTime();
		} catch (Exception e) {
			MainLog.error(LogModule.HomePage, "StatisticsDataMgr[getMinTime]", "Exception happens.", e);
		}
		return time;
	}
	
	private long getMaxTime(Date today) {
		Date tomorrow = DateUtils.addDays(today, 1);
		return getMinTime(tomorrow) - 1;
	}
	
	public static void main(String[] args) throws Exception {
		Date today = new Date();
		System.out.println(StatisticsDataMgr.getInstance().getMinTime(today));
		System.out.println(StatisticsDataMgr.getInstance().getMaxTime(today));
		System.out.println(StatisticsDataMgr.getInstance().getMonthFirstDay());
	}
}
