package com.hq.storeMS.service.excel.apiData;

/**
 * @ClassName: CreateExcelForm
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年3月30日 下午4:19:40
 * 
 */
public class CreateExcelForm {

	private int rowNum;// 行数
	private int entityType;// leaguer、product、goods、memberShipCard
	private String filePath;
	private String sheetName;// 表格名

	public static CreateExcelForm newInstance() {
		return new CreateExcelForm();
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

}
