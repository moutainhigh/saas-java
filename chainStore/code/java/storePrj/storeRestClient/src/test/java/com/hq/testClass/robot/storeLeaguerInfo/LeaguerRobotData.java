package com.hq.testClass.robot.storeLeaguerInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeLeaguerInfo.data.DateTypeEnum;

public class LeaguerRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private long storeId;

	private String leaguerId;

	// 客户姓名
	private String name;
	// 性别
	private int sex;
	// 手机号
	private long phone;
	// 跟进人员
	private Set<Long> buserIds = new HashSet<Long>();

	// 固话
	private String telephone;
	// 年龄
	private int age;
	// 阳历生日
	private long birthday;
	// 阴历阳历 对应dateTypeEnum
	private int dateType;
	// 身份证
	private String idCard;
	// 阴历生日
	private long lunarDay;
	// 别名
	private String aliasName;
	// 微信号
	private String wechatNumber;
	// 介绍人
	private String recommender;
	// 来源
	private String origin;

	// 头像
	private String headImg;
	// 联系地址
	private String address;
	// 工作单位
	private String company;
	// 职位
	private String job;

	// 对应cuser里面的id 应对C端用户查询预约列表使用
	private long cuserId;
	// 创建时间
	private long createdTime;

	public static LeaguerRobotData newInstance(int mark) {
		LeaguerRobotData data = new LeaguerRobotData();
		data.mark = mark;
		data.name = "客户-重口味看";
		data.sex = RandomUtils.nextInt(0, 2);
		data.age = mark;
		DateFormat df = new SimpleDateFormat("MMdd");
		long mmdd = Long.valueOf(df.format(new Date()))*10000;
		data.phone = 13800000000L + mark + mmdd;

		long curTime = System.currentTimeMillis();
		data.dateType = DateTypeEnum.SOLARDATE.ordinal();
		data.birthday = curTime;
		data.lunarDay = curTime;
		data.wechatNumber = "wechatNumber-" + mark;
		data.company = "company-" + mark;
		data.job = "job-" + mark;
		data.address = "address-" + mark;
		data.origin = "origin-" + mark;
		data.recommender = "recommender-" + mark;
		data.aliasName = "aliasName-" + mark;
//		data.headImg = "headImg-" + mark;
		data.idCard = UUID.randomUUID().toString().substring(0,19);
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public long getLunarDay() {
		return lunarDay;
	}

	public void setLunarDay(long lunarDay) {
		this.lunarDay = lunarDay;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
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

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
