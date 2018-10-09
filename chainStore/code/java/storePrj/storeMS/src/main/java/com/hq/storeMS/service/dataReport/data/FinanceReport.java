package com.hq.storeMS.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务统计报表
 */
@SynClass
public class FinanceReport {


    public static FinanceReport newInstance(long storeId,long minTime, long maxTime) {
        FinanceReport financeReport = new FinanceReport();
        financeReport.setStoreId(storeId);
        financeReport.setMinTime(minTime);
        financeReport.setMaxTime(maxTime);
        return financeReport;
    }

    private long storeId;

    /**
     * 时间范围
     */
    private long minTime;//开始时间
    private long maxTime;//结束时间

    private double operatingIncome;//营业收入
    private double operatingExpenses;//营业支出
    private double cardSalesAmount;//售卡金额
    //    private double productCardAmount;//耗卡金额 //此处需求改动 直接显示 会员卡支付金额 + 划卡次数
    private double chargeBackAmount;//退单金额

    private double rechargeAmount;//充值金额
    private double donationAmount;//充值赠送金额
    private double memberCardPayAmount;//会员卡支付金额

    private int saleProductCardCount;//次卡售出数量
    private int delimitCardCount;//划卡次数

    private List<PayItemDetail> payItemDetails = new ArrayList<>();//营业收入（支付排序）
    private List<DayData> dayDataList = new ArrayList<>();//时间段内的每日数据统计

    /**
     * 增加划卡次数
     * @param delimitCardCount
     */
    public void adddelimitCardCount(int delimitCardCount) {
        this.delimitCardCount += delimitCardCount;
    }

    /**
     * 增加次卡出售数量
     * @param saleProductCardCount
     */
    public void addSaleProductCardCount(int saleProductCardCount) {
        this.saleProductCardCount += saleProductCardCount;
    }

    /**
     * 增加会员卡支付金额
     * @param memberCardPayAmount
     */
    public void addmemberCardPayAmount(double memberCardPayAmount) {
        this.memberCardPayAmount += memberCardPayAmount;
    }

    /**
     * 增加会员卡赠送金额
     * @param donationAmount
     */
    public void addDonationAmount(double donationAmount) {
        this.donationAmount += donationAmount;
    }

    /**
     * 增加充值金额
     *
     * @param rechargeAmount
     */
    public void addRechargeAmount(double rechargeAmount) {
        this.rechargeAmount += rechargeAmount;
    }

    /**
     * 增加退单金额
     *
     * @param chargeBackAmount
     */
    public void addChargeBackAmount(double chargeBackAmount) {
        this.chargeBackAmount += chargeBackAmount;
    }


    /**
     * 增加会员卡销售金额
     *
     * @param cardSalesAmount
     */
    public void addCardSalesAmount(double cardSalesAmount) {
        this.cardSalesAmount += cardSalesAmount;
    }

    /**
     * 增加营业支出
     *
     * @param operatingExpenses
     */
    public void addOperatingExpenses(double operatingExpenses) {
        this.operatingExpenses += operatingExpenses;
    }

    /**
     * 增加营业收入
     *
     * @param operatingIncome
     */
    public void addOperatingIncome(double operatingIncome) {
        this.operatingIncome += operatingIncome;
    }


    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public double getOperatingIncome() {
        return operatingIncome;
    }

    public void setOperatingIncome(double operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    public double getOperatingExpenses() {
        return operatingExpenses;
    }

    public void setOperatingExpenses(double operatingExpenses) {
        this.operatingExpenses = operatingExpenses;
    }

    public double getCardSalesAmount() {
        return cardSalesAmount;
    }

    public void setCardSalesAmount(double cardSalesAmount) {
        this.cardSalesAmount = cardSalesAmount;
    }

    public double getChargeBackAmount() {
        return chargeBackAmount;
    }

    public void setChargeBackAmount(double chargeBackAmount) {
        this.chargeBackAmount = chargeBackAmount;
    }

    public double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }

    public double getMemberCardPayAmount() {
        return memberCardPayAmount;
    }

    public void setMemberCardPayAmount(double memberCardPayAmount) {
        this.memberCardPayAmount = memberCardPayAmount;
    }

    public int getSaleProductCardCount() {
        return saleProductCardCount;
    }

    public void setSaleProductCardCount(int saleProductCardCount) {
        this.saleProductCardCount = saleProductCardCount;
    }

    public int getDelimitCardCount() {
        return delimitCardCount;
    }

    public void setDelimitCardCount(int delimitCardCount) {
        this.delimitCardCount = delimitCardCount;
    }

    public List<PayItemDetail> getPayItemDetails() {
        return payItemDetails;
    }

    public void setPayItemDetails(List<PayItemDetail> payItemDetails) {
        this.payItemDetails = payItemDetails;
    }

    public List<DayData> getDayDataList() {
        return dayDataList;
    }

    public void setDayDataList(List<DayData> dayDataList) {
        this.dayDataList = dayDataList;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }
}
