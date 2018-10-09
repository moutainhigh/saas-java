package com.hq.testClass.robot.clerkSalary;

import java.util.List;

import com.hq.chainStore.service.clerkSalary.data.ClerkSalary;
import com.hq.testClass.robot.clerkSalary.robot.ClerkSalaryRobot;

public class TClerkSalary {

	private ClerkSalaryRobot robot; 
	
	public static TClerkSalary newInstance(ClerkSalaryRobot robot){
		TClerkSalary tClerkSalary = new TClerkSalary();
		tClerkSalary.robot = robot;
		return tClerkSalary;
	}
	
	public void addRecord(long storeId, long clerkId) {
		robot.addRecord(storeId, clerkId);
	}
	
	public void removeRecord(long id) {
		robot.removeRecord(id);
	}
	
	public void addPayroll(long storeId, long clerkId) {
		robot.addPayroll(storeId, clerkId);
	}
	
	public ClerkSalary getById(long storeId, long clerkId){
		return robot.getById(storeId, clerkId);
	}
	
	public List<ClerkSalary> findByStoreId(long storeId){
		return robot.findByStoreId(storeId);
	}
	
	public ClerkSalaryRobot getRobot() {
		return robot;
	}

	public void setRobot(ClerkSalaryRobot robot) {
		this.robot = robot;
	}

	public String getId(){
		return this.robot.getId();
	}
}
