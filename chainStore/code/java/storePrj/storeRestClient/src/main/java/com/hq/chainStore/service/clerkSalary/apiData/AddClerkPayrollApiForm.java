package com.hq.chainStore.service.clerkSalary.apiData;

public class AddClerkPayrollApiForm {
	
	//对应月份日期
	private String date;
	//对应基本工资
	private float salary;
	//对应上班天数
	private int workdays; 
	//对应实际的基本工资
	private float realSalary;
	//对应基本工资备注
	private String salaryNotes;
	//对应订单总额
	private float orderAmount;
	//对应提成比例
	private float percentage;
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
	
	public static AddClerkPayrollApiForm newInstance(){
		return new AddClerkPayrollApiForm();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
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

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
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
