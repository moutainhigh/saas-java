package com.hq.thirdPartyServer.service.businessCard.data;

import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * { "name": "张三", #姓名 
 * "company": ["阿里巴巴", "阿里巴巴有限公司" ], #公司结果数组，数组可能为空
 * "department": ["市场部" ], #部门结果数组，数组可能为空 
 * "title" : [ "经理" ], #职位结果数组，数组可能为空
 * "tel_cell" : ["15234563443"], #手机结果数组，数组可能为空 
 * "tel_work": ["057185212345"], * #座机结果数组，数组可能为空 
 * "addr": ["浙江省杭州市西湖区文一西路969号"], #地址结果数组，数组可能为空 
 * "email": [], * #邮箱结果数组 ，数组可能为空 
 * "request_id" : 20160822_32423dfsa23432f #请求对应的唯一表示 
 * "success": * true #识别成功与否 true/false }
 */

@SynClass
public class BusinessCardResp {
	private String serialNumber; //流水号
	
	private String name;
	private String company;
	private List<String> department;
	private List<String> title;
	private List<String> tel_cell;
	private List<String> tel_work;
	private List<String> addr;
	private List<String> email;
	private String request_id;
	private boolean success;
	
	public static BusinessCardResp newInstance() {
		return new BusinessCardResp();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<String> getDepartment() {
		return department;
	}

	public void setDepartment(List<String> department) {
		this.department = department;
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getTel_cell() {
		return tel_cell;
	}

	public void setTel_cell(List<String> tel_cell) {
		this.tel_cell = tel_cell;
	}

	public List<String> getTel_work() {
		return tel_work;
	}

	public void setTel_work(List<String> tel_work) {
		this.tel_work = tel_work;
	}

	public List<String> getAddr() {
		return addr;
	}

	public void setAddr(List<String> addr) {
		this.addr = addr;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
