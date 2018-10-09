package com.hq.chainStore.pressTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hq.chainStore.pressTest.common.PressRecorder;
import com.hq.chainStore.pressTest.storeActor.StoreActor;
import com.hq.chainStore.pressTest.storeCreate.StoreCreator;

public class StoreRunner {

	public static StoreRunner newInstance(int clerkCount, int cuserCount,int productCount) throws InterruptedException{
		StoreRunner runner = new StoreRunner();
		runner.init(clerkCount, cuserCount, productCount);
		return runner; 
	}
	
	private ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	
	private void init(int clerkCount, int cuserCount,int productCount) throws InterruptedException{
		
		final StoreActor storeActor = StoreActor.newInstance(clerkCount, cuserCount, productCount);
		
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					storeActor.doAct();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
			}
		}, 1, 2000, TimeUnit.MILLISECONDS);
	}
	
	public static void main(String[] args) throws InterruptedException {
		StoreCreator.initEnv();
		StoreRunner.newInstance(100, 10, 10);
		while (true) {
			Thread.sleep(1000);
			System.out.println(PressRecorder.getInfo());
		}
	}
}
