package com.hq.testClass.robot.clerkSalary.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

public class ClerkSalaryRobotData {

	private int mark;
	
	private String id;
	
	private long storeId;
	
	private long clerkId;
	
	//对应员工工资
	private float salary;
		
	//对应员工提成
	private float percentage;
	
	private String userName;
	
	/**
	 * 工资单数据
	 */
	//对应工资单日期
	private String date;
	//对应上班天数
	private int workdays; 
	//对应实际的基本工资
	private float realSalary;
	//对应基本工资备注
	private String salaryNotes;
	//对应订单总额
	private float orderAmount;
	//对应实际提成金额
	private float realPercentage;
	//对应提成备注
	private String percentageNotes;
	//对应其他金额
	private float otherIncome;
	//对应其他金额备注
	private String otherIncomeNotes;
	//对应工资合计
	private float totalWages;
	//对应实际工资总额
	private float realWages;
	//对应实际工资备注
	private String realWagesNotes;
	
	public static ClerkSalaryRobotData newInstance(int mark){
		ClerkSalaryRobotData robotData = new ClerkSalaryRobotData();
		robotData.salary = mark;
		robotData.userName = "小明";
		robotData.percentage = RandomUtils.nextFloat(0.2f, 0.8f);;
		robotData.workdays = RandomUtils.nextInt(1, 22);
		robotData.realSalary = RandomUtils.nextFloat(5000f, 10000f);
		robotData.salaryNotes = "基本工资备注"+mark;
		robotData.orderAmount = RandomUtils.nextFloat(10000f, 20000f);
		robotData.realPercentage = RandomUtils.nextFloat(1000f, 2000f);
		robotData.percentageNotes = "提成备注"+mark;
		robotData.otherIncome = RandomUtils.nextFloat(100f, 500f);
		robotData.otherIncomeNotes = "其他金额备注"+mark;
		robotData.totalWages = RandomUtils.nextFloat(10000f, 20000f);
		robotData.realWages = RandomUtils.nextFloat(8000f, 15000f);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String date = simpleDateFormat.format(new Date());
		robotData.date = date;
		return robotData;
	}
	
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getClerkId() {
		return clerkId;
	}

	public void setClerkId(long clerkId) {
		this.clerkId = clerkId;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getWorkdays() {
		return workdays;
	}

	public void setWorkdays(int workdays) {
		this.workdays = workdays;
	}

	public float getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(float realSalary) {
		this.realSalary = realSalary;
	}

	public String getSalaryNotes() {
		return salaryNotes;
	}

	public void setSalaryNotes(String salaryNotes) {
		this.salaryNotes = salaryNotes;
	}

	public float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public float getRealPercentage() {
		return realPercentage;
	}

	public void setRealPercentage(float realPercentage) {
		this.realPercentage = realPercentage;
	}

	public String getPercentageNotes() {
		return percentageNotes;
	}

	public void setPercentageNotes(String percentageNotes) {
		this.percentageNotes = percentageNotes;
	}

	public float getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(float otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getOtherIncomeNotes() {
		return otherIncomeNotes;
	}

	public void setOtherIncomeNotes(String otherIncomeNotes) {
		this.otherIncomeNotes = otherIncomeNotes;
	}

	public float getRealWages() {
		return realWages;
	}

	public void setRealWages(float realWages) {
		this.realWages = realWages;
	}

	public String getRealWagesNotes() {
		return realWagesNotes;
	}

	public void setRealWagesNotes(String realWagesNotes) {
		this.realWagesNotes = realWagesNotes;
	}

	public float getTotalWages() {
		return totalWages;
	}

	public void setTotalWages(float totalWages) {
		this.totalWages = totalWages;
	}
	
	
}
