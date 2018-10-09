package com.hq.customerRestClient.service.leaguerDetail.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.customerRestClient.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.customerRestClient.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.dao.classinfo.IndexField;

@Table(name = "leaguerDetail")
public class LeaguerDetail{
	// storeId_cuserId
	@Id
	private String id;
	@IndexField
	private long storeId;
	private long cuserId;
	
	// 客户姓名
	private String name;
	// 性别
	private int sex;
	// 手机号
	private long phone;
	//实体状态
	private int entityState;
	// 头像
	private String headImg;
	
	// 跟进人员
	private Set<Long> buserIds = new HashSet<Long>();
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
	// 联系地址
	private String address;
	// 工作单位
	private String company;
	// 职位
	private String job;
	// 创建时间
	private long createdTime;
	// 最后修改时间
	@IndexField
	private long lastUpdateTime;
	private long ver;
	
	// 最后消费时间
	private long lastConsumeTime;
	// 首次消费时间
	private long firstConsumeTime;
	// 消费总额
	private float consumeAmount;
	// 消费总次数
	private int consumeCount;
	// 客单价
	private float avgPrice;
	// 月频率
	private int monthRate;
	
	/****************************客户卡片**************************************/
	//客户绑定的会员卡 只有一张
	private LeaguerMemberCard leaguerMemberCard=new LeaguerMemberCard();
	//客户购买的次卡列表 多张    同一张次卡只能有一个  如果客户已经购买过该次卡 需要叠加该次卡使用次数
	private Map<String,LeaguerProductCard> leaguerProductCardMap = new HashMap<String,LeaguerProductCard>();
	
	private int leaguerPrdCardIndex = 1;//次卡自增id

	/** =========================遗留的字段========================= **/
	// 固话
	private String telephone;
	// 年龄
	private int age;
	// 阴历生日
	private long lunarDay;
	// 客户类型 对应CustomerTypeEnum
	private int customerType;
	// 消费等级 对应consumeLevelEnum
	private int consumeLevel;
	// 是否是星标客 对应AttentionTypeEnum
	private int attention;
	// 医美师信息
	private long beauticianId;
	// 医美师名称
	private String beauticianName;

	public static LeaguerDetail newInstance() {
		LeaguerDetail data = new LeaguerDetail();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
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

	public int getAttention() {
		return attention;
	}

	public void setAttention(int attention) {
		this.attention = attention;
	}

	public int getConsumeLevel() {
		return consumeLevel;
	}

	public void setConsumeLevel(int consumeLevel) {
		this.consumeLevel = consumeLevel;
	}

	public long getLastConsumeTime() {
		return lastConsumeTime;
	}

	public void setLastConsumeTime(long lastConsumeTime) {
		this.lastConsumeTime = lastConsumeTime;
	}

	public float getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(float consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public int getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

	public int getCustomerType() {
		return customerType;
	}

	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}

	public long getFirstConsumeTime() {
		return firstConsumeTime;
	}

	public void setFirstConsumeTime(long firstConsumeTime) {
		this.firstConsumeTime = firstConsumeTime;
	}

	public float getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(float avgPrice) {
		this.avgPrice = avgPrice;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	public int getMonthRate() {
		return monthRate;
	}

	public void setMonthRate(int monthRate) {
		this.monthRate = monthRate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
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

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public LeaguerMemberCard getLeaguerMemberCard() {
		return leaguerMemberCard;
	}

	public void setLeaguerMemberCard(LeaguerMemberCard leaguerMemberCard) {
		this.leaguerMemberCard = leaguerMemberCard;
	}

	public Map<String, LeaguerProductCard> getLeaguerProductCardMap() {
		return leaguerProductCardMap;
	}

	public void setLeaguerProductCardMap(Map<String, LeaguerProductCard> leaguerProductCardMap) {
		this.leaguerProductCardMap = leaguerProductCardMap;
	}

	public int getLeaguerPrdCardIndex() {
		return leaguerPrdCardIndex;
	}

	public void setLeaguerPrdCardIndex(int leaguerPrdCardIndex) {
		this.leaguerPrdCardIndex = leaguerPrdCardIndex;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}
	

}
