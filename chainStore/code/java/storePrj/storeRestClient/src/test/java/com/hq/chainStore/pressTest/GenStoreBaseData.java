package com.hq.chainStore.pressTest;

import com.hq.chainStore.pressTest.common.PressRecorder;
import com.hq.chainStore.pressTest.storeCreate.StoreCreator;

public class GenStoreBaseData {
	
	public static void main(String[] args) throws InterruptedException {
		StoreCreator.initEnv();
		for (int i = 0; i < 500; i++) {
			StoreCreator.create(100, 10, 10, 10);
		}
		System.out.println(PressRecorder.getInfo());
	}
}
