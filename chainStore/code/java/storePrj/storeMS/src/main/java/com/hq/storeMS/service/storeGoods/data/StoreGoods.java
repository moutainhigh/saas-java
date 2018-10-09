package com.hq.storeMS.service.storeGoods.data;

import java.util.*;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.common.DataOriginEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "storeGoods")
public class StoreGoods {
    @Id
    private long id;
    private long storeId;
    //商品ID
    private long goodsIdIndex = 0L;
    //分类ID
    private long goodsTypeIdIndex = 0L;
    //商品集合
    private Map<String, Goods> goodsMap = new HashMap<String, Goods>();
    // 商品分类
    private Map<String, GoodsType> goodsTypeMap = new HashMap<String, GoodsType>();
    //数据拆分标识
    @SynIgnoreField
    private int splitMark;

    private long createdTime;
    private long lastUpdateTime;
    private long ver;

    public static StoreGoods newInstance(long storeId) {
        StoreGoods data = new StoreGoods();
        data.id = storeId;
        data.storeId = storeId;
        data.splitMark = SplitMarkEnum.FINISH.ordinal();

        GoodsType type = GoodsType.newInstance();
        type.setId(SysInitTypeEnum.UnClassify.getId());
        type.setName(SysInitTypeEnum.UnClassify.getName());
        data.goodsTypeMap.put(type.getId(), type);

        long curTime = System.currentTimeMillis();
        data.createdTime = curTime;
        data.lastUpdateTime = curTime;
        return data;
    }

    /**
     * 获取分类的map  {"分类名称":分类对象}
     *
     * @return
     */
    public Map<String, GoodsType> takeGoodsTypeMapWithTypeNameKey() {
        Map<String, GoodsType> result = new HashMap<String, GoodsType>();
        Collection<GoodsType> values = this.goodsTypeMap.values();
        for (GoodsType type : values) {
            result.put(type.getName(), type);
        }
        return result;
    }

    public Goods takeGoodsById(String id) {
        return goodsMap.get(id);
    }

    public GoodsType takeGoodsTypeById(String id) {
        return goodsTypeMap.get(id);
    }

    public boolean goodsIsFromChain(String goodsId) {
        Goods data = goodsMap.get(goodsId);
        if (data != null && data.getOrigin() == DataOriginEnum.CHAIN.ordinal()) {
            return true;
        }
        return false;
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

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getGoodsIdIndex() {
        return goodsIdIndex;
    }

    public void setGoodsIdIndex(long goodsIdIndex) {
        this.goodsIdIndex = goodsIdIndex;
    }

    public long getGoodsTypeIdIndex() {
        return goodsTypeIdIndex;
    }

    public void setGoodsTypeIdIndex(long goodsTypeIdIndex) {
        this.goodsTypeIdIndex = goodsTypeIdIndex;
    }

    public Map<String, Goods> getGoodsMap() {
        return goodsMap;
    }

    public void setGoodsMap(Map<String, Goods> goodsMap) {
        this.goodsMap = goodsMap;
    }

    public Map<String, GoodsType> getGoodsTypeMap() {
        return goodsTypeMap;
    }

    public void setGoodsTypeMap(Map<String, GoodsType> goodsTypeMap) {
        this.goodsTypeMap = goodsTypeMap;
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

    public int getSplitMark() {
        return splitMark;
    }

    public void setSplitMark(int splitMark) {
        this.splitMark = splitMark;
    }


    /**
     * 获取正常状态 已上架的goods
     *
     * @return
     */
    public Map<String, Goods> takeNormalGoods() {
        Map<String, Goods> tempGoodsMap = new HashMap<>(goodsMap);
        Set<String> set = tempGoodsMap.keySet();
        Iterator<String> it = set.iterator();
        List<String> listKey = new ArrayList<String>();
        while (it.hasNext()) {
            String str = it.next();
            Goods goods = tempGoodsMap.get(str);
            if (goods != null && goods.getState() != GoodsStateEnum.Open.ordinal() && goods.getEntityState() != EntityState.Normal.ordinal()) {
                listKey.add(str);
            }
        }
        for (String key : listKey) {
            tempGoodsMap.remove(key);
        }
        return tempGoodsMap;
    }
}
