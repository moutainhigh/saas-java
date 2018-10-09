package com.hq.chainMS.service.chainReport.apiData;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TradingFlowReportQueryForm extends DataReportQueryForm {
    private String queryName;
    private int pageNo;
    private int pageItemCount;
//    private List<Integer> payType = new ArrayList<>();//支付方式
//    private List<Integer> tradeType = new ArrayList<>();//交易类型

    private Map<Integer, Integer> tradeTypeMap = new HashMap<>();
    private Map<Integer, Integer> payTypeMap =  new HashMap<>();

    public static TradingFlowReportQueryForm newInstance() {
        TradingFlowReportQueryForm data = new TradingFlowReportQueryForm();
        data.setStoreId(0L);
        data.setMaxTime(0L);
        data.setMinTime(0L);
        data.setPageNo(1);
        data.setPageItemCount(10);
        return data;
    }


    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
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

    public void setPayType(String payType) {
        this.payTypeMap.clear();
        if (StringUtils.isBlank(payType)){
            return;
        }
        String[] types =  payType.split(",");
        for (String s : types){
            Integer i =  Integer.valueOf(s);
            this.payTypeMap.put(i,i);
        }
    }


    public void setTradeType(String tradeType) {
        this.tradeTypeMap.clear();
        if (StringUtils.isBlank(tradeType)){
            return;
        }
        String[] types =  tradeType.split(",");
        for (String s : types){
            Integer i =  Integer.valueOf(s);
            this.tradeTypeMap.put(i,i);
        }
    }

    public Map<Integer, Integer> getTradeTypeMap() {
        return tradeTypeMap;
    }

    public Map<Integer, Integer> getPayTypeMap() {
        return payTypeMap;
    }
}
