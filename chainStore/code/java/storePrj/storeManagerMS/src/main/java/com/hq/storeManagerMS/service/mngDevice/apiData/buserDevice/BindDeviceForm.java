package com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice;

/** 
 * @ClassName: BindDeviceForm 
 * @Description:绑定仪器Form 
 * @author helen 
 * @date 2018年1月30日 下午1:59:36 
 *  
 */
public class BindDeviceForm {
		
	private String snCode;
	
	private long buserId;
	
	private String salesman; //客户代表
	
	private String salesmanPhone;//客户代表手机号
	
	public static BindDeviceForm newInstance(){
		
		return new BindDeviceForm();
	}
	
	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}


	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanPhone() {
		return salesmanPhone;
	}

	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}

	
}
