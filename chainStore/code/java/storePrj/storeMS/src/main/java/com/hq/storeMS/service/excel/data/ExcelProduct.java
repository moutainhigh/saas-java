package com.hq.storeMS.service.excel.data;

import com.hq.storeMS.service.storeProductInfo.apiData.AddProductInfoData;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.excel.classInfo.ExcelField;

/** 
 * @ClassName: ExcelProduct 
 * @Description: 由excel表格导出来的对象 => {@link AddProductInfoData} 
 * 				 需特殊处理的字段 typeId
 * @author helen 
 * @date 2018年4月3日 下午2:30:57 
 *  
 */
@SynClass
public class ExcelProduct{
	
	@ExcelField(colName = "*名称")
	private String name;
	
	@ExcelField(colName = "*分类")
	private String typeId;
	
	@ExcelField(colName = "*售价(元)")
	private float price;
	
	@ExcelField(colName = "编号")
	private String number;
	
	@ExcelField(colName = "成本(元)")
	private float cost;
	
	@ExcelField(colName = "项目介绍")
	private String descript;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "ExcelProduct [number=" + number + ", name=" + name
				+ ", typeId=" + typeId + ", price=" + price + ", cost=" + cost
				+ ", descript=" + descript + "]";
	}
	
	
}
