package com.hq.chainStore.service.excel.apiData;

import java.util.HashMap;
import java.util.Map;



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
			
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moduleType", this.moduleType);
		map.put("moduleId", this.moduleId);
		return map;
	}
	
	public static ExcelUpLoadForm newInstance() {
		return new ExcelUpLoadForm();
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
