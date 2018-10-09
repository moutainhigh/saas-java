package com.hq.testClass.robot.euser.robot;


/** 
 * @ClassName: EUserRobotData 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月20日 下午2:18:12 
 *  
 */
public class EUserRobotData {

	private int mark;
	private long id;
	private String name;
	private long phone;
	private int count;// 体验次数
	private long createTime;
	private long lastUpdateTime;
	private int ver;
	
	public static EUserRobotData newInstance(int mark) {
		EUserRobotData robotData = new EUserRobotData();
		robotData.phone = 18300000000L + mark;
		robotData.name = "体验账号-" + mark;
		robotData.count = mark+1;
		return robotData;
	}
	
	
	
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getVer() {
		return ver;
	}
	public void setVer(int ver) {
		this.ver = ver;
	}
	
	
}
