package com.hq.chainStore.service.saas.apiData;

public class OPAdminRoleUpdateApiForm {
	
	private int id;
	
	private int updateType;
	
	private OPAdminRoleUpdateInfoApiData updateData;
	

	public static OPAdminRoleUpdateApiForm newInstance(){
		return new OPAdminRoleUpdateApiForm();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUpdateType() {
		return updateType;
	}
	
	public OPAdminRoleUpdateType getUpdateTypeEnum() {
		return OPAdminRoleUpdateType.valueOf(this.updateType);
	}


	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	public void setUpdateTypeEnum(OPAdminRoleUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}


	public OPAdminRoleUpdateInfoApiData getUpdateData() {
		return updateData;
	}


	public void setUpdateData(OPAdminRoleUpdateInfoApiData updateData) {
		this.updateData = updateData;
	}


	
}
