package com.hq.storeClient.service.dataReport.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class CardMapData {

    private String cardId;
    private String name;
    private int number;
    private int numberOfConsumption;

    public static CardMapData newInstance(String cardId, String cardName) {
        CardMapData cardMapData = new CardMapData();
        cardMapData.setCardId(cardId);
        cardMapData.setName(cardName);
        cardMapData.setNumberOfConsumption(0);
        return cardMapData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfConsumption() {
        return numberOfConsumption;
    }

    public void setNumberOfConsumption(int numberOfConsumption) {
        this.numberOfConsumption = numberOfConsumption;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void increaseCount() {//增加张数
        this.number += 1;
    }

    public void addNumberOfConsumption(int count) {
        if (this.numberOfConsumption != -1) {
            this.numberOfConsumption += count;
        }
    }
}
