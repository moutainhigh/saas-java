package com.hq.chainStore.service.excel.data;

import com.hq.chainStore.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;

/**
 * @ClassName: ExcelLeaguer
 * @Description:由excel表格导出来的对象 => {@link LeaguerAddApiForm}
 * 				需特殊处理的字段 sex buserIds dateType
 * @author helen
 * @date 2018年4月3日 下午2:36:30
 * 
 *
 */

public class ExcelLeaguer {

	private String name;

	private String sex;

	private long phone;

	private String buserIds;

	private long birthday;
	
	private String dateType;

	private String idCard;

	private String aliasName;

	private String wechatNumber;
	
	private String recommender;

	private String origin;

	private String address;

	private String company;

	private String job;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(String buserIds) {
		this.buserIds = buserIds;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getWechatNumber() {
		return wechatNumber;
	}

	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "ExcelLeaguer [name=" + name + ", sex=" + sex + ", phone="
				+ phone + ", buserIds=" + buserIds + ", birthday=" + birthday
				+ ", dateType=" + dateType + ", idCard=" + idCard
				+ ", aliasName=" + aliasName + ", wechatNumber=" + wechatNumber
				+ ", recommender=" + recommender + ", origin=" + origin
				+ ", address=" + address + ", company=" + company + ", job="
				+ job + "]";
	}

}
