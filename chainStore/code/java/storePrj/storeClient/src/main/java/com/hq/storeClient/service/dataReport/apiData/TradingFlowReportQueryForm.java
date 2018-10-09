package com.hq.storeClient.service.dataReport.apiData;



import java.util.HashMap;
import java.util.Map;

public class TradingFlowReportQueryForm {

    private String queryName;
    private String storeIds;
    private long maxTime;
    private long minTime;

    private int pageNo = 1;
    private int pageItemCount = 10;
    private String payType;//支付方式
    private String tradeType;//交易类型

    private Map<Integer, Integer> tradeTypeMap = new HashMap<>();
    private Map<Integer, Integer> payTypeMap =  new HashMap<>();

    public static TradingFlowReportQueryForm newInstance() {
        TradingFlowReportQueryForm data = new TradingFlowReportQueryForm();
        data.setMaxTime(0L);
        data.setMinTime(0L);
        return data;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Map<Integer, Integer> getTradeTypeMap() {
        return tradeTypeMap;
    }

    public void setTradeTypeMap(Map<Integer, Integer> tradeTypeMap) {
        this.tradeTypeMap = tradeTypeMap;
    }

    public Map<Integer, Integer> getPayTypeMap() {
        return payTypeMap;
    }

    public void setPayTypeMap(Map<Integer, Integer> payTypeMap) {
        this.payTypeMap = payTypeMap;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageItemCount() {
        return pageItemCount;
    }

    public void setPageItemCount(int pageItemCount) {
        this.pageItemCount = pageItemCount;
    }
}
