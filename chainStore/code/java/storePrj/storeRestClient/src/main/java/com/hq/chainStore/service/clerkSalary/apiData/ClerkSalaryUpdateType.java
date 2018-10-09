package com.hq.chainStore.service.clerkSalary.apiData;

public enum ClerkSalaryUpdateType {

	RemoveClerkSalaryRecord("删除薪酬记录"),
	AddClerkSalaryRecord("修改薪酬"),
	AddClerkPayroll("创建工资单"),
	;
	
	private String descript;
	
	private ClerkSalaryUpdateType(String descript){
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}
	
	public static ClerkSalaryUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
}
