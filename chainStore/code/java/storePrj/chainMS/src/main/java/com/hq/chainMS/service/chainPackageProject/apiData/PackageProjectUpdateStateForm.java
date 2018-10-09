package com.hq.chainMS.service.chainPackageProject.apiData;

public class PackageProjectUpdateStateForm {
	private String id;
	private int state;
	
	public static PackageProjectUpdateStateForm newInstance(){
		return new PackageProjectUpdateStateForm();
	}
	
	public static PackageProjectUpdateStateForm newInstance(String id, int state){
		PackageProjectUpdateStateForm data = newInstance();
		data.id=id;
		data.state=state;
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
