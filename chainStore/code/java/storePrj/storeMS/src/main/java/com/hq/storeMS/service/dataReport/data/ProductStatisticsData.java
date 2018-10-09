package com.hq.storeMS.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 产品统计
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/7
 */
@SynClass
public class ProductStatisticsData {

    private long storeId;

    private int totalProductCount;//所有产品总数，不包含已下架的

    private int unsalableProductCount;//滞销产品总数，不包含已下架的

    private List<ProductStatisticsItem> hotSalesList = new ArrayList<>();//热销排行榜

    private List<ProductStatisticsItem> unsalableList = new ArrayList<>();//滞销排行榜

    private List<ProductStatisticsItem> productSalesList = new ArrayList<>();//项目销售列表

    private List<ProductStatisticsItem> goodsSalesList = new ArrayList<>();//商品销售列表

    private List<ProductStatisticsItem> packageSalesList = new ArrayList<>();//套餐销售列表

    private List<ProductStatisticsItem> productCardSalesList = new ArrayList<>();//次卡销售列表

    private List<ProductStatisticsItem> unSalesProductInfoList = new ArrayList<>();//滞销项目
    private List<ProductStatisticsItem> unSalesGoodsList = new ArrayList<>();//滞销商品
    private List<ProductStatisticsItem> unSalesPackageList = new ArrayList<>();//滞销套餐
    private List<ProductStatisticsItem> unSalesProductCardList = new ArrayList<>();//滞销次卡


    public static ProductStatisticsData newInstance(long storeId) {
        ProductStatisticsData target = new ProductStatisticsData();
        target.setStoreId(storeId);
        return target;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(int totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public int getUnsalableProductCount() {
        return unsalableProductCount;
    }

    public void setUnsalableProductCount(int unsalableProductCount) {
        this.unsalableProductCount = unsalableProductCount;
    }

    public List<ProductStatisticsItem> getHotSalesList() {
        return hotSalesList;
    }

    public void setHotSalesList(List<ProductStatisticsItem> hotSalesList) {
        this.hotSalesList = hotSalesList;
    }

    public List<ProductStatisticsItem> getUnsalableList() {
        return unsalableList;
    }

    public void setUnsalableList(List<ProductStatisticsItem> unsalableList) {
        this.unsalableList = unsalableList;
    }

    public List<ProductStatisticsItem> getProductSalesList() {
        return productSalesList;
    }

    public void setProductSalesList(List<ProductStatisticsItem> productSalesList) {
        this.productSalesList = productSalesList;
    }

    public List<ProductStatisticsItem> getGoodsSalesList() {
        return goodsSalesList;
    }

    public void setGoodsSalesList(List<ProductStatisticsItem> goodsSalesList) {
        this.goodsSalesList = goodsSalesList;
    }

    public List<ProductStatisticsItem> getPackageSalesList() {
        return packageSalesList;
    }

    public void setPackageSalesList(List<ProductStatisticsItem> packageSalesList) {
        this.packageSalesList = packageSalesList;
    }

    public List<ProductStatisticsItem> getProductCardSalesList() {
        return productCardSalesList;
    }

    public void setProductCardSalesList(List<ProductStatisticsItem> productCardSalesList) {
        this.productCardSalesList = productCardSalesList;
    }

    public List<ProductStatisticsItem> getUnSalesProductInfoList() {
        return unSalesProductInfoList;
    }

    public void setUnSalesProductInfoList(List<ProductStatisticsItem> unSalesProductInfoList) {
        this.unSalesProductInfoList = unSalesProductInfoList;
    }

    public List<ProductStatisticsItem> getUnSalesGoodsList() {
        return unSalesGoodsList;
    }

    public void setUnSalesGoodsList(List<ProductStatisticsItem> unSalesGoodsList) {
        this.unSalesGoodsList = unSalesGoodsList;
    }

    public List<ProductStatisticsItem> getUnSalesPackageList() {
        return unSalesPackageList;
    }

    public void setUnSalesPackageList(List<ProductStatisticsItem> unSalesPackageList) {
        this.unSalesPackageList = unSalesPackageList;
    }

    public List<ProductStatisticsItem> getUnSalesProductCardList() {
        return unSalesProductCardList;
    }

    public void setUnSalesProductCardList(List<ProductStatisticsItem> unSalesProductCardList) {
        this.unSalesProductCardList = unSalesProductCardList;
    }


}
