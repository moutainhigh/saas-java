package com.hq.storeMS.service.excel.apiData;

import org.springframework.web.multipart.MultipartFile;


/** 
 * @ClassName: ExcelDownLoadForm 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年3月30日 上午9:07:40 
 *  
 */
/** 
* @ClassName: ExcelUpLoadForm 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author helen 
* @date 2018年4月2日 下午3:57:09 
*  
*/
public class ExcelUpLoadForm {

	private String moduleId;
	
	private String moduleType;//UploadModuleType
		
	private MultipartFile excel;
	
	public static ExcelUpLoadForm newInstance() {
		return new ExcelUpLoadForm();
	}

	public MultipartFile getExcel() {
		return excel;
	}

	public void setExcel(MultipartFile excel) {
		this.excel = excel;
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
