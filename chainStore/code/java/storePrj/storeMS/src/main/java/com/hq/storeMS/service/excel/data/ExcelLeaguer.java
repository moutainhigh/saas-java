package com.hq.storeMS.service.excel.data;

import com.hq.storeMS.service.storeLeaguerInfo.apiData.LeaguerAddApiForm;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.excel.classInfo.ExcelField;

/**
 * @ClassName: ExcelLeaguer
 * @Description:由excel表格导出来的对象 => {@link LeaguerAddApiForm}
 * 				需特殊处理的字段 sex buserIds dateType
 * @author helen
 * @date 2018年4月3日 下午2:36:30
 * 
 *
 */

@SynClass
public class ExcelLeaguer {

	@ExcelField(colName = "*姓名")
	private String name;

	@ExcelField(colName = "*性别")
	private String sex;

	@ExcelField(colName = "*手机号")
	private long phone;

	@ExcelField(colName = "跟进人员")
	private String buserIds;

	@ExcelField(colName = "生日")
	private long birthday;
	
	@ExcelField(colName = "阳历(阴历)")
	private String dateType;

	@ExcelField(colName = "身份证")
	private String idCard;

	@ExcelField(colName = "昵称")
	private String aliasName;

	@ExcelField(colName = "微信号")
	private String wechatNumber;
	
	@ExcelField(colName = "介绍人")
	private String recommender;

//	@ExcelField(colName = "来源")
//	private String origin;

	@ExcelField(colName = "联系地址")
	private String address;

	@ExcelField(colName = "工作单位")
	private String company;

	@ExcelField(colName = "职位")
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

//	public String getOrigin() {
//		return origin;
//	}
//
//	public void setOrigin(String origin) {
//		this.origin = origin;
//	}

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
		return "ExcelLeaguer [name=" + name + ", sex=" + sex + ", phone=" + phone + ", buserIds=" + buserIds
				+ ", birthday=" + birthday + ", dateType=" + dateType + ", idCard=" + idCard + ", aliasName="
				+ aliasName + ", wechatNumber=" + wechatNumber + ", recommender=" + recommender + ", address=" + address
				+ ", company=" + company + ", job=" + job + "]";
	}

}
