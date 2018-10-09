package com.hq.chainStore.pressTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.common.JsonUtilRecorder;
import com.hq.chainStore.pressTest.common.PressRecorder;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.asynExecutor.AsynExecutor;
import com.zenmind.common.asynExecutor.IntfAsynTask;
import com.zenmind.common.asynExecutor.IntfErrorHandler;
import com.zenmind.common.json.JsonUtil;

public class StoreJsonTest {
	private ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	private AsynExecutor<String> asynExecutor;
	
	private int index=0;
	
	public static StoreJsonTest newInstance(int i) throws InterruptedException{
		StoreJsonTest runner = new StoreJsonTest();
		runner.initExecutor();
		runner.init();
		runner.index = i;
		return runner; 
	}
	
	private String testJson2String(List<WorkFlowData> list){
		return JsonUtil.getInstance().toJson(list);
	}
	
	private List<WorkFlowData> testString2Obj(String json){
		return JsonUtil.getInstance().parseList(json, WorkFlowData.class);
	}
	
	private static WorkFlowData buildData(int random){
		String data="{\"_id\":"+random+",\"storeId\":"+random+",\"buserId\":"+random+",\"workFlowTypeId\":2,\"status\":0,\"leaguerInfo\":{\"leaguerId\":\"5298_32262\",\"leaguerName\":\"客户-6710\",\"cuserDocId\":0,\"cuserCareId\":0,\"followUserId\":5697)},\"prodRecordMap\":{\"3\":{\"prodId\":\"3\",\"status\":0,\"count\":4,\"price\":"+random+",\"discount\":0.0,\"buserIds\":[]},\"4\":{\"prodId\":\"4\",\"status\":0,\"count\":5,\"price\":1958.74072265625,\"discount\":0.0,\"buserIds\":[]},\"5\":{\"prodId\":\"5\",\"status\":0,\"count\":2,\"price\":1259.78540039063,\"discount\":0.0,\"buserIds\":[]},\"6\":{\"prodId\":\"6\",\"status\":0,\"count\":9,\"price\":1202.32116699219,\"discount\":0.0,\"buserIds\":[]},\"7\":{\"prodId\":\"7\",\"status\":0,\"count\":1,\"price\":1684.40026855469,\"discount\":0.0,\"buserIds\":[]},\"9\":{\"prodId\":\"9\",\"status\":0,\"count\":3,\"price\":1353.57019042969,\"discount\":0.0,\"buserIds\":[]}},\"decreasePrdCardRecordMap\":{},\"prdCardRecordMap\":{},\"goodsRecordMap\":{\"1\":{\"goodsId\":\"1\",\"count\":8,\"price\":1674.48559570313,\"discount\":0.0,\"buserIds\":[]},\"2\":{\"goodsId\":\"2\",\"count\":2,\"price\":1596.4326171875,\"discount\":0.0,\"buserIds\":[]},\"3\":{\"goodsId\":\"3\",\"count\":4,\"price\":1272.896484375,\"discount\":0.0,\"buserIds\":[]},\"4\":{\"goodsId\":\"4\",\"count\":5,\"price\":1356.98645019531,\"discount\":0.0,\"buserIds\":[]},\"5\":{\"goodsId\":\"5\",\"count\":4,\"price\":1169.01440429688,\"discount\":0.0,\"buserIds\":[]},\"6\":{\"goodsId\":\"6\",\"count\":7,\"price\":1405.75158691406,\"discount\":0.0,\"buserIds\":[]},\"7\":{\"goodsId\":\"7\",\"count\":9,\"price\":1530.57055664063,\"discount\":0.0,\"buserIds\":[]},\"8\":{\"goodsId\":\"8\",\"count\":2,\"price\":1434.39978027344,\"discount\":0.0,\"buserIds\":[]},\"9\":{\"goodsId\":\"9\",\"count\":6,\"price\":1870.61767578125,\"discount\":0.0,\"buserIds\":[]},\"10\":{\"goodsId\":\"10\",\"count\":6,\"price\":1683.8330078125,\"discount\":0.0,\"buserIds\":[]}},\"memCardInfo\":{\"cost\":1050.36645507813,\"largess\":104.513366699219,\"buserIds\":[],\"limitTime\":0,\"limitUnit\":0},\"bonusInfoMap\":{},\"createdTime\":1521800830686,\"lastUpdateTime\":1521801432744,\"ver\":64)}";
		return JsonUtil.getInstance().fromJson(data, WorkFlowData.class);
	}
	
	private void init(){
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					asynExecutor.addData("driveRun"+RandomUtils.nextInt(0, 65535));
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
			}
		}, 1, 1000, TimeUnit.MILLISECONDS);
	}
	
	private void initExecutor(){
		IntfAsynTask<String> asynTaskP = new IntfAsynTask<String>() {
			@Override
			public void doTask(String target) {
				List<WorkFlowData> list = new ArrayList<WorkFlowData>();
				for (int i = 0; i < 100; i++) {
					list.add(buildData(RandomUtils.nextInt(0, 10000) + (index*100000)));
				}
				
				long startTime = System.currentTimeMillis();
				String json = testJson2String(list);
				testString2Obj(json);
				long costInMs = System.currentTimeMillis() - startTime;
				JsonUtilRecorder.getInstance().add(costInMs);
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
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 500; i++) {
			try {
				StoreJsonTest.newInstance(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int count = 0;
		while (true) {
			Thread.sleep(1000);
			count++;
			System.out.println(JsonUtilRecorder.getInstance().getInfo());
			if(count == 10){
				JsonUtilRecorder.getInstance().reset();
			}
		}
	}
}
