package com.hq.chainStore.pressTest.storeActor;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.common.PressRecorder;
import com.hq.chainStore.pressTest.storeCreate.StoreCreator;
import com.hq.chainStore.pressTest.storeCreate.StoreData;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;

public class StoreActor {

	
	private AsynExecutor<String> asynExecutor;	
	
	private StoreData storeData;

	public static StoreActor newInstance(int clerkCount, int cuserCount,int productCount) throws InterruptedException{
		StoreActor sa = new StoreActor();
		sa.init(clerkCount, cuserCount, productCount);
		return sa;
	}
	
	public void init(int clerkCount, int cuserCount,int productCount) throws InterruptedException{
		//创建实体的间隔时间
		long interval = 10;
		storeData = StoreCreator.create(clerkCount,cuserCount,productCount,interval);
		initExecutor();	
	}
	
	public void initExecutor(){
		IntfAsynTask<String> asynTaskP = new IntfAsynTask<String>() {

			@Override
			public void doTask(String target) {
				doStoreAction();
			}
			
		};
		IntfErrorHandler<String> errorHandlerP = new IntfErrorHandler<String>() {

			@Override
			public void handle(String target, String msg, Throwable e) {
				PressRecorder.logError( msg, e);
				
			}
		};
		int queueSizeP = 65535;
		int threadCountP = 6;
		asynExecutor = AsynExecutor.newInstance(asynTaskP, errorHandlerP, queueSizeP , threadCountP);
		asynExecutor.init();
	}
	
	private void doStoreAction() {
		storeData.getBossActor().doAction();
//		List<ClerkActor> clerkListTmp = storeData.getClerkList();
//		int clerksize = clerkListTmp.size();
//		int random = RandomUtils.nextInt(0, clerksize);
//		ClerkActor clerkActor = clerkListTmp.get(random);
//		clerkActor.doAction();		
	}
	
	//调用该函数进行一次驱动操作
	public void doAct(){
		this.asynExecutor.addData("driveRun"+RandomUtils.nextInt(0, 65535));
	}
	
}
