package com.hq.chainStore.pressTest;

import com.hq.chainStore.pressTest.common.PressRecorder;

public class StorePool {

	
	public static void run(int storeCount) throws InterruptedException{
		for (int i = 0; i < storeCount; i++) {
			int clerkCount = 20;
			int cuserCount = 10;
			int productCount = 10;
			StoreRunner.newInstance(clerkCount, cuserCount, productCount);
		}
		while(true){
			String pressInfo = PressRecorder.getInfo();
			System.out.println(pressInfo);
			Thread.sleep(5000);
		}
	}
	
	
	
}
