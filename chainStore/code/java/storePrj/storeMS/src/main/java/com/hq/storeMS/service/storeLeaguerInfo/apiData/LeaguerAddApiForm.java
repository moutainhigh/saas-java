package com.hq.storeMS.service.storeLeaguerInfo.apiData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.GenderEnum;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class LeaguerAddApiForm {
	// 客户姓名
	private String name;
	// 性别
	private int sex;
	// 手机号
	private long phone;
	// 头像
	private String headImg;

	// 跟进人员
	private Set<Long> buserIds = new HashSet<Long>();
	// 客户扩展属性 <LeaguerExpandAttribute.id, value>
	private Map<Integer, String> expandAttrMap = new HashMap<Integer, String>();
	// 生日
	private long birthday;
	// 阴历阳历 对应dateTypeEnum
	private int dateType;
	// 身份证
	private String idCard;
	// 别名
	private String aliasName;
	// 微信号
	private String wechatNumber;
	// 介绍人
	private String recommender;
	// 来源
	private String origin;
	// 来源ID
	private int originId;
	// 标签
	private Set<String> labelIds = new HashSet<String>();
	// 联系地址
	private String address;
	// 工作单位
	private String company;
	// 职位
	private String job;

	public static LeaguerAddApiForm newInstance() {
		LeaguerAddApiForm data = new LeaguerAddApiForm();
		data.name = "未填写";
		data.sex = GenderEnum.FEMALE.ordinal();
		return data;
	}

	public Leaguer toLeaguer() {
		Leaguer data = Leaguer.newInstance();
		data.setSex(this.sex);
		data.setName(this.name);
		data.setHeadImg(this.headImg);
		data.setPhone(this.phone);
		return data;
	}

	public LeaguerDetail toLeaguerDetail() {
		LeaguerDetail detail = LeaguerDetail.newInstance();
		FastBeanCopyer.getInstance().copy(this, detail);
		return detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (StringUtils.isNoneBlank(name)) {
			this.name = name;
		}
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		if (sex != 0) {
			this.sex = sex;
		}
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getWechatNumber() {
		return wechatNumber;
	}

	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
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

	public int getOriginId() {
		return originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}

	public Set<String> getLabelIds() {
		return labelIds;
	}

	public void setLabelIds(Set<String> labelIds) {
		this.labelIds = labelIds;
	}

	public Map<Integer, String> getExpandAttrMap() {
		return expandAttrMap;
	}

	public void setExpandAttrMap(Map<Integer, String> expandAttrMap) {
		this.expandAttrMap = expandAttrMap;
	}

}
