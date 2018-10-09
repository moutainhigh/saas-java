package com.hq.storeMS.service.dataReport.data;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.hq.storeMS.service.dataReport.bs.DataReportMgr;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectMgr;
import com.hq.storeMS.service.storePackageProject.data.PackageProject;
import com.hq.storeMS.service.storePackageProject.data.PackageProjectType;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;

import java.util.*;

/**
 * @Description 产品统计
 * @Creator geefox
 * @E-mail firstblh@163.com
 * @Date 2018/8/7
 */
public class ProductStatisticsHelper {

    private static Comparator<ProductStatisticsItem> comparator = new Comparator<ProductStatisticsItem>() {
        @Override
        public int compare(ProductStatisticsItem o1, ProductStatisticsItem o2) {
            return o2.getSalesVolume() - o1.getSalesVolume();
        }
    };

    public static List<ProductStatisticsData> buildChainProductStatisticsData(String storeIds, long minTime, long maxTime) {
        String[] storeIdArray = storeIds.split(",");
        List<ProductStatisticsData> retList = new ArrayList<>();
        for (String storeId : storeIdArray) {
            ProductStatisticsData productStatisticsData = buildProductStatisticsData(Long.valueOf(storeId), minTime, maxTime);
            retList.add(productStatisticsData);
        }
        return retList;
    }


    public static ProductStatisticsData buildProductStatisticsData(long storeId, long minTime, long maxTime) {
        //获取所有的项目
        StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
        Map<String, ProductInfo> productInfoMap = storeProductInfo.takeNormalProductInfoMap();
        Map<String, ProductType> productTypeMap = storeProductInfo.getProductTypeMap();
        //获取所有的商品
        StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
        Map<String, Goods> goodsMap = storeGoods.takeNormalGoods();
        Map<String, GoodsType> goodsTypeMap = storeGoods.getGoodsTypeMap();
        //获取所有的次卡
        StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
        Map<String, ProductCard> productCardMap = storeCardInfo.takeNormalProductCardMap();
        Map<String, PrdCardType> prdCardTypeMap = storeCardInfo.getPrdCardTypeMap();
        //获取所有的套餐
        StorePackageProject storePackageProject = StorePackageProjectMgr.getInstance().getByStoreId(storeId);
        Map<String, PackageProject> packageProjectMap = storePackageProject.takeNormalPackageProjectMap();
        Map<String, PackageProjectType> packageProjectTypeMap = storePackageProject.getPackageProjectTypeMap();
        //组建报表
        ProductStatisticsData productStatisticsData = ProductStatisticsData.newInstance(storeId);
        //1 所有产品数
        int totalProductCount = productInfoMap.size() + productCardMap.size() + goodsMap.size() + packageProjectMap.size();

        //查询指定时间内的订单
        DataReportQueryForm dataReportQueryForm = DataReportQueryForm.newInstance();
        dataReportQueryForm.setStoreId(storeId);
        dataReportQueryForm.setMinTime(minTime);
        dataReportQueryForm.setMaxTime(maxTime);
        List<Order> orderList = DataReportMgr.getInstance().findOrderList(dataReportQueryForm);

        //统计数据
//        Map<String, ProductStatisticsItem> allStatisticsItemMap = new HashMap<>();//全部
        Map<String, ProductStatisticsItem> productStatisticsItemMap = new HashMap<>();//项目
        Map<String, ProductStatisticsItem> goodsStatisticsItemMap = new HashMap<>();//商品
        Map<String, ProductStatisticsItem> productCardStatisticsItemMap = new HashMap<>();//次卡
        Map<String, ProductStatisticsItem> packageStatisticsItemMap = new HashMap<>();//套餐

        if (null != orderList) {
            for (Order order : orderList) {
                List<BuyItem> buyItems = order.getBuyItems();
                for (BuyItem buyItem : buyItems) {
                    BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyItem.getBuyType());

                    switch (buyTypeEnum) {
                        case PRODUCT:
                            ProductInfo productInfo = productInfoMap.get(buyItem.getPgId());
                            if (null != productInfo) {
                                String typeName = getProductTypeName(productTypeMap, productInfo);
                                groupBuyItems(productStatisticsItemMap, buyItem, productInfo.getName(), typeName);
                            }
                            break;
                        case GOODS:
                            Goods goods = goodsMap.get(buyItem.getPgId());
                            if (null != goods) {
                                String typeName = getGoodsTypeName(goodsTypeMap, goods);
                                groupBuyItems(goodsStatisticsItemMap, buyItem, goods.getName(), typeName);
                            }

                            break;
                        case PRDCARD:
                            ProductCard productCard = productCardMap.get(buyItem.getPgId());
                            if (null != productCard) {
                                String typeName = getPrdCardTypeName(prdCardTypeMap, productCard);
                                groupBuyItems(productCardStatisticsItemMap, buyItem, productCard.getName(), typeName);
                            }
                            break;
                        case PACKAGE:
                            PackageProject packageProject = packageProjectMap.get(buyItem.getPgId());
                            if (null != packageProject) {
                                String typeName = getPackageTypeName(packageProjectTypeMap, packageProject);
                                groupBuyItems(packageStatisticsItemMap, buyItem, packageProject.getName(), typeName);
                            }

                            break;
                        default:
                            break;
                    }
                }
            }
        }
        //所有产品
        productStatisticsData.setTotalProductCount(totalProductCount);


        List<ProductStatisticsItem> productSalesItemList = new ArrayList<>(productStatisticsItemMap.values());
        productSalesItemList.sort(comparator);
        productStatisticsData.setProductSalesList(productSalesItemList);

        List<ProductStatisticsItem> goodsSalesItemList = new ArrayList<>(goodsStatisticsItemMap.values());
        goodsSalesItemList.sort(comparator);
        productStatisticsData.setGoodsSalesList(goodsSalesItemList);

        List<ProductStatisticsItem> prductCardSalesItemList = new ArrayList<>(productCardStatisticsItemMap.values());
        prductCardSalesItemList.sort(comparator);
        productStatisticsData.setProductCardSalesList(prductCardSalesItemList);

        List<ProductStatisticsItem> packageSalesItemList = new ArrayList<>(packageStatisticsItemMap.values());
        packageSalesItemList.sort(comparator);
        productStatisticsData.setPackageSalesList(packageSalesItemList);

        //热销排行榜统计

        List<ProductStatisticsItem> allSalesList = new ArrayList<>();
        allSalesList.addAll(productSalesItemList);
        allSalesList.addAll(goodsSalesItemList);
        allSalesList.addAll(prductCardSalesItemList);
        allSalesList.addAll(packageSalesItemList);

        //2 滞销产品数
        int unsalableProductCount = totalProductCount - allSalesList.size();
        productStatisticsData.setUnsalableProductCount(unsalableProductCount);

        getUnsaledProductMap(productInfoMap.keySet(), productStatisticsItemMap.keySet());
        getUnsaledProductMap(goodsMap.keySet(), goodsStatisticsItemMap.keySet());
        getUnsaledProductMap(productCardMap.keySet(), productCardStatisticsItemMap.keySet());
        getUnsaledProductMap(packageProjectMap.keySet(), packageStatisticsItemMap.keySet());

        //滞销项目
        List<ProductStatisticsItem> unSalesProductInfoList = new ArrayList<>();
        Collection<ProductInfo> productInfoSet = productInfoMap.values();
        for (ProductInfo productInfo : productInfoSet) {
            String prdTypeName = getProductTypeName(productTypeMap, productInfo);
            ProductStatisticsItem productStatisticsItem = ProductStatisticsItem.newInstance(productInfo.getId(), productInfo.getName(), BuyTypeEnum.PRODUCT.ordinal(), prdTypeName);
            unSalesProductInfoList.add(productStatisticsItem);
        }
        //滞销商品
        List<ProductStatisticsItem> unSalesGoodsList = new ArrayList<>();
        Collection<Goods> goodsList = goodsMap.values();
        for (Goods goods : goodsList) {
            String goodsType = getGoodsTypeName(goodsTypeMap, goods);
            ProductStatisticsItem goodsStatisticsItem = ProductStatisticsItem.newInstance(goods.getId(), goods.getName(), BuyTypeEnum.GOODS.ordinal(), goodsType);
            unSalesGoodsList.add(goodsStatisticsItem);
        }
        //滞销套餐
        List<ProductStatisticsItem> unSalesPackageList = new ArrayList<>();
        Collection<PackageProject> packageProjects = packageProjectMap.values();
        for (PackageProject packageProject : packageProjects) {
            String packageType = getPackageTypeName(packageProjectTypeMap, packageProject);
            ProductStatisticsItem packageStatisticsItem = ProductStatisticsItem.newInstance(packageProject.getId(), packageProject.getName(), BuyTypeEnum.PACKAGE.ordinal(), packageType);
            unSalesPackageList.add(packageStatisticsItem);
        }
        //滞销次卡
        List<ProductStatisticsItem> unSalesProductCardList = new ArrayList<>();
        Collection<ProductCard> productCards = productCardMap.values();
        for (ProductCard productCard : productCards) {
            String cardType = getPrdCardTypeName(prdCardTypeMap, productCard);
            ProductStatisticsItem productCardStatisticsItem = ProductStatisticsItem.newInstance(productCard.getId(), productCard.getName(), BuyTypeEnum.PRDCARD.ordinal(), cardType);
            unSalesProductCardList.add(productCardStatisticsItem);
        }

        List<ProductStatisticsItem> allUnSalesList = new ArrayList<>();
        allUnSalesList.addAll(unSalesProductInfoList);
        allUnSalesList.addAll(unSalesGoodsList);
        allUnSalesList.addAll(unSalesPackageList);
        allUnSalesList.addAll(unSalesProductCardList);

        productStatisticsData.setUnsalableList(allUnSalesList);
        productStatisticsData.setUnSalesProductInfoList(unSalesProductInfoList);
        productStatisticsData.setUnSalesGoodsList(unSalesGoodsList);
        productStatisticsData.setUnSalesPackageList(unSalesPackageList);
        productStatisticsData.setUnSalesProductCardList(unSalesProductCardList);

        allSalesList.sort(comparator);
        productStatisticsData.setHotSalesList(allSalesList);//设置热销产品统计结果
        return productStatisticsData;
    }

    /**
     * 获取套餐分类名
     *
     * @param packageProjectTypeMap
     * @param packageProject
     * @return
     */
    private static String getPackageTypeName(Map<String, PackageProjectType> packageProjectTypeMap, PackageProject packageProject) {
        PackageProjectType packageProjectType = packageProjectTypeMap.get(packageProject.getTypeId());
        return null == packageProjectType ? "-" : packageProjectType.getName();
    }

    /**
     * 获取次卡分类名
     *
     * @param prdCardTypeMap
     * @param productCard
     * @return
     */
    private static String getPrdCardTypeName(Map<String, PrdCardType> prdCardTypeMap, ProductCard productCard) {
        PrdCardType prdCardType = prdCardTypeMap.get(productCard.getTypeId());
        return null == prdCardType ? "-" : prdCardType.getName();
    }

    /**
     * 获取商品分类名
     *
     * @param goodsTypeMap
     * @param goods
     * @return
     */
    private static String getGoodsTypeName(Map<String, GoodsType> goodsTypeMap, Goods goods) {
        GoodsType goodsType = goodsTypeMap.get(goods.getTypeId());
        return null == goodsType ? "-" : goodsType.getName();
    }

    /**
     * 获取项目分类名
     *
     * @param productTypeMap
     * @param productInfo
     * @return
     */
    private static String getProductTypeName(Map<String, ProductType> productTypeMap, ProductInfo productInfo) {
        ProductType productType = productTypeMap.get(productInfo.getTypeId());
        return null == productType ? "-" : productType.getName();
    }

    /**
     * 移除已出售的产品
     *
     * @param sourceKeySet
     * @param saleskeySet
     */
    private static void getUnsaledProductMap(Set<String> sourceKeySet, Set<String> saleskeySet) {
        for (String keyStr : saleskeySet) {
            sourceKeySet.remove(keyStr);
        }
    }

    private static void groupBuyItems(Map<String, ProductStatisticsItem> statisticsItemMap, BuyItem buyItem, String pgName, String typeName) {

        ProductStatisticsItem goodsStatisticsItem = statisticsItemMap.get(buyItem.getPgId());
        if (null == goodsStatisticsItem) {
            goodsStatisticsItem = ProductStatisticsItem.newInstance(buyItem, pgName, typeName);
            statisticsItemMap.put(buyItem.getPgId(), goodsStatisticsItem);
        }
        goodsStatisticsItem.addSalesVolume(buyItem.getCount());

    }


}
