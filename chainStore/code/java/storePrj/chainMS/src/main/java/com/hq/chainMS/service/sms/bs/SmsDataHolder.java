package com.hq.chainMS.service.sms.bs;

import java.util.List;

import com.hq.chainMS.service.sms.apiData.QuerySmsForm;
import com.hq.chainMS.service.sms.data.Sms;
import com.hq.chainMS.service.sms.data.SmsMongoDAO;
import com.hq.chainMS.service.sms.data.SmsRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SmsDataHolder {
	
	
	public static SmsDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(SmsDataHolder.class);
	}
	
	private final String groupName="sms";
	
	//==========================MongoDao==========================
	public void addAndReturnId(Sms target) {
		SmsMongoDAO.getInstance().addAndReturnId(target);
	}

	public void updpate(Sms target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		SmsMongoDAO.getInstance().updpate(target);
	}
	
	public Sms get(long id) {
		return SmsMongoDAO.getInstance().get(id);
	}
	
	public List<Sms> findSmsList(QuerySmsForm querySmsForm) {
		return SmsMongoDAO.getInstance().findSmsList(querySmsForm);
	}
	
	//==========================RedisDao==========================
	public void save2Redis(Sms target) {
		SmsRedisDAO.getInstance().saveOne(groupName, String.valueOf(target.getPhone()), target);
	}

	public void deleteFromRedis(String phone) {
		SmsRedisDAO.getInstance().deleteOne(groupName, phone);
	}
	
	public Sms getFromRedis(String phone) {
		return SmsRedisDAO.getInstance().findByOne(groupName, phone);
	}
}
