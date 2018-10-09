package com.hq.chainStore.service.excel.data;

import com.hq.chainStore.service.storeProductInfo.apiData.AddProductInfoData;

/** 
 * @ClassName: ExcelProduct 
 * @Description: 由excel表格导出来的对象 => {@link AddProductInfoData} 
 * 				 需特殊处理的字段 typeId
 * @author helen 
 * @date 2018年4月3日 下午2:30:57 
 *  
 */
public class ExcelProduct{
	
	private String number;
	
	private String name;
	
	private String typeId;
	
	private float price;

	private float cost;
	
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
