package com.hq.storeMS.service.buserDevice.data;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;
import com.zenmind.dataSyn.annotation.SynClass;
/**
 * 每次开关机操作详情
 * @Description:  
 * @author: wujunwei 
 * @date: 2018年2月23日  
 * @version: v1.0  
 * @since: JDK 1.8
 */

@SynClass
public class OperateDetail {
	// 记录id
	private long id;
	// 开机时间
	private long poweronTime;
	// 关机时间
	private long poweroffTime;
	// 出光次数
	private int radiateCount;
	// 操作时长，单位ms
	private long operateTimeCount;
	// 设备参数的原始数据串
	private String buffer;
	// 设备参数一次解析后的KeyValues
	private List<IotKeyValue> originParams = new ArrayList<IotKeyValue>();
	// 设备参数二次解析后的keyValues
	private List<IotKeyValue> displayParams = new ArrayList<IotKeyValue>();

	public static OperateDetail newInstance() {
		return new OperateDetail();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPoweronTime() {
		return poweronTime;
	}

	public void setPoweronTime(long poweronTime) {
		this.poweronTime = poweronTime;
	}

	public long getPoweroffTime() {
		return poweroffTime;
	}

	public void setPoweroffTime(long poweroffTime) {
		this.poweroffTime = poweroffTime;
	}

	public int getRadiateCount() {
		return radiateCount;
	}

	public void setRadiateCount(int radiateCount) {
		this.radiateCount = radiateCount;
	}

	public long getOperateTimeCount() {
		return operateTimeCount;
	}

	public void setOperateTimeCount(long operateTimeCount) {
		this.operateTimeCount = operateTimeCount;
	}

	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	public List<IotKeyValue> getOriginParams() {
		return originParams;
	}

	public void setOriginParams(List<IotKeyValue> originParams) {
		this.originParams = originParams;
	}

	public List<IotKeyValue> getDisplayParams() {
		return displayParams;
	}

	public void setDisplayParams(List<IotKeyValue> displayParams) {
		this.displayParams = displayParams;
	}
}
