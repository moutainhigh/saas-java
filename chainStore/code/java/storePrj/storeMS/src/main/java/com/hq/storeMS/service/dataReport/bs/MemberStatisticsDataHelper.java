package com.hq.storeMS.service.dataReport.bs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.dataReport.data.DayData;
import com.hq.storeMS.service.dataReport.data.LeaguerOriginData;
import com.hq.storeMS.service.dataReport.data.MemberConsumptionData;
import com.hq.storeMS.service.dataReport.data.MemberStatisticsData;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailDataHolder;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerOriginConfig;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.OutsiderEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;

/**
 * @Description 会员统计业务逻辑类
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/6
 */
public class MemberStatisticsDataHelper {

    public static MemberStatisticsData buildMemberStatisticsData(long storeId, long startTime, long endTime) {
        MemberStatisticsData memberStatisticsData = MemberStatisticsData.newInstance(storeId);
        //来源统计数据模型
        Map<Integer, LeaguerOriginData> leaguerOriginDataMap = getOriginDataMap(storeId);
        //店铺会员总数量
        StoreLeaguerInfo storeLeaguerInfo = StoreLeaguerInfoMgr.getInstance().get(storeId);
        Map<String, Leaguer> leaguerInfoMap = storeLeaguerInfo.getLeaguerInfoMap();

        Map<String, MemberConsumptionData> memberConsumptionDataMap = new HashMap<>();
        int memberCount = 0;
        for (Leaguer leaguer : leaguerInfoMap.values()) {
            if (leaguer.getEntityState() == EntityState.Normal.ordinal()) {
                memberCount++;
                //新增一个会员（消费）记录
                MemberConsumptionData memberConsumptionData = MemberConsumptionData.newInstance(leaguer.getId(), leaguer.getName());
                memberConsumptionDataMap.put(leaguer.getId(), memberConsumptionData);
            }
        }
        //设置店铺所有会员人数
        memberStatisticsData.setTotalMembershipCount(memberCount);

        //新增会员数--指定时间内新增的会员
        List<LeaguerDetail> leaguerDetailList = getLeaguerDetailsByTimeRange(storeId, startTime, endTime);
        //设置所有新增的会员数
        setTotalMembershipCount(memberStatisticsData, leaguerDetailList);
        //统计来源
        countOriginData(leaguerOriginDataMap, leaguerDetailList);

        DataReportQueryForm queryForm = buildDataReportQueryForm(storeId, startTime, endTime);
        //初始化每日数据统计模型
        HashMap<String, DayData> dayDataMap = FinanceReportHelper.getDayDataMapByTimeRange(queryForm);
        HashMap<String, DayData> newMemberCostDataMap = AppUtils.clone(dayDataMap);
        HashMap<String, DayData> newMemberAddDataMap = AppUtils.clone(dayDataMap);
        //统计新增会员频次
        for (LeaguerDetail leaguerDetail : leaguerDetailList) {
            String dayStr = AppUtils.timeStamp2Str(leaguerDetail.getCreatedTime());
            DayData dayData = newMemberAddDataMap.get(dayStr);
            if (null == dayData) {
                dayData = DayData.newInstance(leaguerDetail.getCreatedTime());
                newMemberAddDataMap.put(dayStr, dayData);
            }
            dayData.addCount(1);
        }


        //会员消费统计
        memberConsumtionStatistic(storeId, memberStatisticsData, memberConsumptionDataMap, queryForm, dayDataMap, leaguerDetailList, newMemberCostDataMap);

        //来源统计
        List<LeaguerOriginData> leaguerOriginDatas = new ArrayList<>(leaguerOriginDataMap.values());
        memberStatisticsData.setLeaguerOriginDatas(leaguerOriginDatas);
        //转换消费统计为List 并按消费金额由大到小排序
        List<MemberConsumptionData> memberConsumptionDataList = getMemberConsumptionDataList(memberConsumptionDataMap);
        List<DayData> dayDataList = new ArrayList<>(dayDataMap.values());
        List<DayData> newMemberCostDataList = new ArrayList<>(newMemberCostDataMap.values());
        List<DayData> newMemberAddDataList = new ArrayList<>(newMemberAddDataMap.values());
        FinanceReportHelper.sortDayDataList(dayDataList);
        FinanceReportHelper.sortDayDataList(newMemberCostDataList);
        FinanceReportHelper.sortDayDataList(newMemberAddDataList);
        memberStatisticsData.setConsumptionList(memberConsumptionDataList);
        memberStatisticsData.setDayDataList(dayDataList);
        memberStatisticsData.setNewMemberAddDataList(newMemberAddDataList);
        memberStatisticsData.setNewMemberCostDataList(newMemberCostDataList);

        return memberStatisticsData;
    }

    /**
     * 转换消费统计为List 并按消费金额由大到小排序
     *
     * @param memberConsumptionDataMap
     * @return
     */
    private static List<MemberConsumptionData> getMemberConsumptionDataList(Map<String, MemberConsumptionData> memberConsumptionDataMap) {
        List<MemberConsumptionData> memberConsumptionDataList = new ArrayList<>(memberConsumptionDataMap.values());
        memberConsumptionDataList.sort(new Comparator<MemberConsumptionData>() {
            @Override
            public int compare(MemberConsumptionData o1, MemberConsumptionData o2) {
                if (o1.getTotalCost() > o2.getTotalCost()) {
                    return -1;
                } else if (o1.getTotalCost() == o2.getTotalCost()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return memberConsumptionDataList;
    }

    private static void memberConsumtionStatistic(long storeId,
                                                  MemberStatisticsData memberStatisticsData,
                                                  Map<String, MemberConsumptionData> memberConsumptionDataMap,
                                                  DataReportQueryForm queryForm,
                                                  Map<String, DayData> dayDataMap,
                                                  List<LeaguerDetail> leaguerDetailList,
                                                  Map<String, DayData> newMemberCostDataMap) {
        List<Order> orderList = DataReportMgr.getInstance().findOrderList(queryForm);
        if (null != orderList) {
            for (Order order : orderList) {
                //统计收入
                String dayStr = AppUtils.timeStamp2Str(order.getCreatedTime());
                DayData dayData = dayDataMap.get(dayStr);
                if (dayData == null) {
                    dayData = DayData.newInstance(order.getCreatedTime());
                    dayDataMap.put(dayStr, dayData);
                }
                if (OutsiderEnum.Boy.getId(storeId).equals(order.getLeaguerId()) || OutsiderEnum.Girl.getId(storeId).equals(order.getLeaguerId())) {//散客消费
                    memberStatisticsData.addOutSiderConsumptionTimes(1);
                } else {//增加会员消费记录
                    memberStatisticsData.addTotalConsumptionTimes(1);
                    MemberConsumptionData memberConsumptionData = memberConsumptionDataMap.get(order.getLeaguerId());
                    if (null != memberConsumptionData) {
                        memberConsumptionData.addTotalCost(order.getRealPay());
                    }
                    dayData.addCount(1);
                    for (LeaguerDetail leaguerDetail : leaguerDetailList) {
                        if (null != leaguerDetail.getId() && leaguerDetail.getId().equals(order.getLeaguerId())) {
                            DayData newMemberDayData = newMemberCostDataMap.get(dayStr);
                            if (newMemberDayData == null) {
                                newMemberDayData = DayData.newInstance(order.getCreatedTime());
                                newMemberCostDataMap.put(dayStr, newMemberDayData);
                            }
                            newMemberDayData.addCount(1);
                            newMemberDayData.addAmount(order.getRealPay());
                            break;
                        }
                    }
                }


            }
        }
    }


    private static DataReportQueryForm buildDataReportQueryForm(long storeId, long startTime, long endTime) {
        DataReportQueryForm queryForm = DataReportQueryForm.newInstance();
        queryForm.setMinTime(startTime);
        queryForm.setMaxTime(endTime);
        queryForm.setStoreId(storeId);
        return queryForm;
    }

    private static List<LeaguerDetail> getLeaguerDetailsByTimeRange(long storeId, long startTime, long endTime) {
        LeaguerDetailQueryForm leaguerDetailQueryForm = LeaguerDetailQueryForm.newInstance();
        leaguerDetailQueryForm.setStoreId(storeId);
        leaguerDetailQueryForm.setMinTime(startTime);
        leaguerDetailQueryForm.setMaxTime(endTime);
        return LeaguerDetailDataHolder.getInstance().findLeaguerDetailList(leaguerDetailQueryForm);
    }

    private static void setTotalMembershipCount(MemberStatisticsData memberStatisticsData, List<LeaguerDetail> leaguerDetailList) {
        if (null != leaguerDetailList) {
            memberStatisticsData.setNewMembershipCount(leaguerDetailList.size());
        }
    }

    private static void countOriginData(Map<Integer, LeaguerOriginData> leaguerOriginDataMap, List<LeaguerDetail> leaguerDetailList) {
        if (null != leaguerDetailList) {
            for (LeaguerDetail leaguerDetail : leaguerDetailList) {
                LeaguerOriginData leaguerOriginData = leaguerOriginDataMap.get(leaguerDetail.getOriginId());
                if (null != leaguerOriginData) {
                    leaguerOriginData.addCount(1);
                }
            }
        }
    }

    private static Map<Integer, LeaguerOriginData> getOriginDataMap(long storeId) {
        Map<Integer, LeaguerOriginConfig> leaguerOriginConfigMap = StoreConfigMgr.getInstance().getByStoreId(storeId).takeLeaguerOriginConfigMap();
        //来源统计Map
        Map<Integer, LeaguerOriginData> leaguerOriginDataMap = new HashMap<>();
        if (null != leaguerOriginConfigMap) {
            for (LeaguerOriginConfig leaguerOriginConfig : leaguerOriginConfigMap.values()) {
                LeaguerOriginData leaguerOriginData = LeaguerOriginData.newInstance(leaguerOriginConfig.getId(), leaguerOriginConfig.getOriginName());
                leaguerOriginDataMap.put(leaguerOriginData.getOriginId(), leaguerOriginData);
            }
        }
        return leaguerOriginDataMap;
    }
}
