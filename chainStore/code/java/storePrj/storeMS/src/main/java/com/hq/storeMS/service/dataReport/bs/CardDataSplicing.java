package com.hq.storeMS.service.dataReport.bs;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.dataReport.data.CardMapData;
import com.hq.storeMS.service.dataReport.data.CardStatisticsData;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerCardEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerPrdCardItem;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

import java.text.SimpleDateFormat;
import java.util.*;

public class CardDataSplicing {

    public static CardDataSplicing getInstance() {
        return HotSwap.getInstance().getSingleton(CardDataSplicing.class);
    }

    public CardStatisticsData getCardStatisticsData(String storeId) {

        CardStatisticsData cardStatisticsData = new CardStatisticsData(storeId);

        StoreCardInfo storeCardInfo = HelperClass.getInstance().getStoreCardInfo(storeId);
        List<LeaguerDetail> leaguerDetailList = FindLeaguerDeteilList.getInstance().getLeaguerDetailListByStoreId(storeId);
        Map<String, ProductCard> productCardMap = storeCardInfo.getProductCardMap();//获取到店铺所有的次卡
        Map<String, CardMapData> cardMapDataMap = new HashMap<>();

        for (LeaguerDetail leaguerDetail : leaguerDetailList) {
            Collection<LeaguerProductCard> leaguerProductCardCollections = leaguerDetail.getLeaguerProductCardMap().values();
            for (LeaguerProductCard leaguerProductCard : leaguerProductCardCollections) {
                if (leaguerProductCard.getState() == LeaguerCardEnum.NOTUSE.ordinal() || leaguerProductCard.getState() == LeaguerCardEnum.USING.ordinal()) {
                    String cardId = leaguerProductCard.getCardId();
                    if (cardId == null)
                        continue;
                    ProductCard productCard = productCardMap.get(cardId);
                    if (null != productCard) {
                        CardMapData cardMapData = cardMapDataMap.get(cardId);
                        if (null == cardMapData) {
                            cardMapData = CardMapData.newInstance(cardId, productCard.getName());
                            cardMapDataMap.put(cardId, cardMapData);
                        }
                        cardMapData.increaseCount();
                        for (LeaguerPrdCardItem leaguerPrdCardItem : leaguerProductCard.getLeaguerPrdCardItems()) {
                            if (leaguerPrdCardItem.getRestCount() != -1) {
                                cardMapData.addNumberOfConsumption(leaguerPrdCardItem.getRestCount());
                            } else {
                                cardMapData.setNumberOfConsumption(-1);
                            }
                        }
                    }
                }
            }
        }
        List<CardMapData> cardMapDataList = new ArrayList<>(cardMapDataMap.values());
        cardMapDataList.sort(new Comparator<CardMapData>() {
            @Override
            public int compare(CardMapData o1, CardMapData o2) {
                return o2.getNumber() - o1.getNumber();
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataTime = AppUtils.timeStamp2Str(currentTimeMillis, simpleDateFormat);
        cardStatisticsData.setUpdataTime(dataTime);
        cardStatisticsData.setCardMapDataList(cardMapDataList);
        return cardStatisticsData;
    }

}
