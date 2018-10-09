package com.hq.storeMS.service.storeGoods.data;


public class ProductData {
    private int totalProductCount;
    private int unsoldProductCount;

    public static ProductData newInstance() {
        ProductData productData = new ProductData();
        return productData;
    }

    public int getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(int totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public int getUnsoldProductCount() {
        return unsoldProductCount;
    }

    public void setUnsoldProductCount(int unsoldProductCount) {
        this.unsoldProductCount = unsoldProductCount;
    }

    public void addTotalProductCount(int count) {
        this.totalProductCount += count;
    }
}
