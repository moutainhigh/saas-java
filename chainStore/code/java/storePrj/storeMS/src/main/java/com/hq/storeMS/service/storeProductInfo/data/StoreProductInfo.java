package com.hq.storeMS.service.storeProductInfo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainClient.service.chainProduct.data.ProductStateEnum;
import com.hq.storeMS.service.common.DataOriginEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "storeProductInfo")
public class StoreProductInfo {
    @Id
    private long id;
    private long storeId;

    private int productIdIndex = 0;
    private int productTypeIdIndex = 0;
    private Map<String, ProductInfo> productInfoMap = new HashMap<String, ProductInfo>();
    private Map<String, ProductType> productTypeMap = new HashMap<String, ProductType>();// 项目分类

    // 数据拆分标识
    @SynIgnoreField
    private int splitMark;
    private long createdTime;
    private long lastUpdateTime;
    private long ver;

    public static StoreProductInfo newInstance() {
        StoreProductInfo storeProductInfo = new StoreProductInfo();
        long curTime = System.currentTimeMillis();
        storeProductInfo.createdTime = curTime;
        storeProductInfo.lastUpdateTime = curTime;
        return storeProductInfo;
    }
    
    public static StoreProductInfo newInstance(long storeId) {
    	StoreProductInfo storeProductInfo = newInstance();
    	storeProductInfo.id = storeId;
    	storeProductInfo.storeId = storeId;
    	storeProductInfo.splitMark = SplitMarkEnum.FINISH.ordinal();
    	
    	ProductType type = ProductType.newInstance();
    	type.setId(SysInitTypeEnum.UnClassify.getId());
    	type.setName(SysInitTypeEnum.UnClassify.getName());
    	storeProductInfo.productTypeMap.put(type.getId(), type);
    	return storeProductInfo;
    }

    /**
     * 获取分类的map {"分类名称":分类对象}
     *
     * @return
     */
    public Map<String, ProductType> takeProductTypeMapWithTypeNameKey() {
        Map<String, ProductType> result = new HashMap<String, ProductType>();
        Collection<ProductType> values = this.productTypeMap.values();
        for (ProductType type : values) {
            result.put(type.getName(), type);
        }
        return result;
    }

    public ProductInfo takeProductInfoById(String id) {
        return productInfoMap.get(id);
    }

    public ProductType takeProductTypeById(String id) {
        return productTypeMap.get(id);
    }

    public boolean productIsFromChain(String prdId) {
        ProductInfo data = productInfoMap.get(prdId);
        if (data != null && data.getOrigin() == DataOriginEnum.CHAIN.ordinal()) {
            return true;
        }
        return false;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getVer() {
        return ver;
    }

    public void setVer(long ver) {
        this.ver = ver;
    }

    public void incrVer() {
        this.ver = ver + 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, ProductInfo> getProductInfoMap() {
        return productInfoMap;
    }

    public void setProductInfoMap(Map<String, ProductInfo> productInfoMap) {
        this.productInfoMap = productInfoMap;
    }

    public int getProductIdIndex() {
        return productIdIndex;
    }

    public void setProductIdIndex(int productIdIndex) {
        this.productIdIndex = productIdIndex;
    }

    public Map<String, ProductType> getProductTypeMap() {
        return productTypeMap;
    }

    public void setProductTypeMap(Map<String, ProductType> productTypeMap) {
        this.productTypeMap = productTypeMap;
    }

    public int getProductTypeIdIndex() {
        return productTypeIdIndex;
    }

    public void setProductTypeIdIndex(int productTypeIdIndex) {
        this.productTypeIdIndex = productTypeIdIndex;
    }

    public int getSplitMark() {
        return splitMark;
    }

    public void setSplitMark(int splitMark) {
        this.splitMark = splitMark;
    }


    /**
     * 获取正常状态 已上架的ProductInfo
     *
     * @return
     */
    public Map<String, ProductInfo> takeNormalProductInfoMap() {
        Map<String, ProductInfo> tempProductInfoMap = new HashMap<>(productInfoMap);
        Set<String> set = tempProductInfoMap.keySet();
        Iterator<String> it = set.iterator();
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String str = it.next();
            ProductInfo productInfo = tempProductInfoMap.get(str);
            if (productInfo != null && productInfo.getState() != ProductStateEnum.Open.ordinal() && productInfo.getEntityState() != EntityState.Normal.ordinal()) {
                listKey.add(str);
            }
        }
        for (String key : listKey) {
            tempProductInfoMap.remove(key);
        }
        return tempProductInfoMap;
    }

}
