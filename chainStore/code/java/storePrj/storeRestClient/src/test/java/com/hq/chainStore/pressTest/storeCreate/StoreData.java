package com.hq.chainStore.pressTest.storeCreate;

import com.hq.chainStore.pressTest.storeActor.BossActor;

public class StoreData {

	private BossActor bossActor;
	
	public static StoreData newInstance(BossActor bossActor){
		StoreData target = new StoreData();
		target.bossActor = bossActor;
		return target;
	}

	public BossActor getBossActor() {
		return bossActor;
	}

	public void setBossActor(BossActor bossActor) {
		this.bossActor = bossActor;
	}
	
}
