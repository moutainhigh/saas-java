package com.hq.storeClient.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PayItemDetail {

    // 支付方式 PayTypeEnum
    private int payType;
    // 金额
    private double cost;

    private String payMethod;//支付方式

    private PayItemDetail() {
    }

    public static PayItemDetail newInstance(int payType, String payMethod) {
        PayItemDetail payItemDetail = new PayItemDetail();
        payItemDetail.payType = payType;
        payItemDetail.payMethod = payMethod;
        return payItemDetail;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void addCost(double cost) {
        this.cost += cost;
    }
}
