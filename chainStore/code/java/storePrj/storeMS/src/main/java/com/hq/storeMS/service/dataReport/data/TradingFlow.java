package com.hq.storeMS.service.dataReport.data;


import com.hq.orderRestClient.service.order.data.PayItem;

/**
 * 交易流水报表
 */
public class TradingFlow {

    private String orderId;//订单ID
    private long payTime;//支付时间
    private String tradeNo;//交易流水号
    private String leagueName;//客户
    private int tradeType;
    private double amount;//支付金额
    private int payType;//支付方式

    public static TradingFlow newInstance(String orderId, PayItem payItem, int tradeType, String leagueName) {
        TradingFlow tradingFlow = new TradingFlow();
        tradingFlow.setOrderId(orderId);
        tradingFlow.setAmount(payItem.getCost());
        tradingFlow.setPayType(payItem.getPayType());
        tradingFlow.setPayTime(payItem.getCreatedTime());
        tradingFlow.setTradeNo(payItem.getTradeNo());
        tradingFlow.setTradeType(tradeType);
        tradingFlow.setLeagueName(leagueName);

        return tradingFlow;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
