package com.hq.chainStore.service.excel.apiData;

/**
 * @ClassName: ExcelUpLoadForm
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年4月2日 下午3:57:09
 * 
 */
public class ExcelUpLoadApiForm {

	private String moduleId;

	private String moduleType;// UploadModuleType

	public static ExcelUpLoadApiForm newInstance() {
		return new ExcelUpLoadApiForm();
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
}
