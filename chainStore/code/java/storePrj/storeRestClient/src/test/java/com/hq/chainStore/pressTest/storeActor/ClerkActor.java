package com.hq.chainStore.pressTest.storeActor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.pressTest.common.IntfPressAction;
import com.hq.chainStore.pressTest.common.PressRecorder;
import com.hq.testClass.robot.buser.Clerk;

public class ClerkActor {
	
//	private Clerk clerk;
	
	private Map<Integer,IntfPressAction> actionMap = new HashMap<Integer,IntfPressAction>();
	
	public ClerkActor(Clerk clerkP){
//		this.clerk = clerkP;	
		init();
	}
	
	public void doAction() {
		int size = actionMap.size();
		int nextAct = RandomUtils.nextInt(0,size);
		try {
			actionMap.get(nextAct).doAction();
//			PressRecorder.logReq("ClerkActor");
		} catch (Exception e) {
			PressRecorder.logError("ClerkActor", e);
		}
	}
	
	private void init(){
		//购买消费
		IntfPressAction buyFlow = new IntfPressAction() {			
			@Override
			public void doAction() {
				// TODO Auto-generated method stub				
			}
		};
		
		actionMap.put(0, buyFlow );	
		
		//会员充值
		IntfPressAction memCharge = new IntfPressAction() {			
			@Override
			public void doAction() {
				// TODO Auto-generated method stub				
			}
		};
		actionMap.put(1, memCharge );	
		
		//预约
		IntfPressAction appointment = new IntfPressAction() {			
			@Override
			public void doAction() {
				// TODO Auto-generated method stub				
			}
		};
		actionMap.put(2, appointment );	
		

	}	
	
}
