package com.hq.chainClient.testClass.robot.chain;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chain.apiData.ChainAddForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChainRobotData {
	// 名称
	private String name;
	// 店铺描述
	private String descript;
	// 区域
	private String area;
	// 地址
	private String address;

	public static ChainRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		ChainRobotData data = new ChainRobotData();
		data.name="智美连锁店"+random;
		data.descript="智美连锁店"+random;
		data.area="广州天河";
		data.address="华观路";
		return data;
	}
	
	public ChainAddForm toChainAddForm() {
		ChainAddForm data = ChainAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
