package com.hq.customerRestClient.service.cuser.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.customerRestClient.common.validate.ValidateThreadLocal;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddByWxForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddressAddForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddressRemoveForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserAddressUpdateForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserChangeDefaultAddressForm;
import com.hq.customerRestClient.service.cuser.apiData.CUserResetPasswordData;
import com.hq.customerRestClient.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerRestClient.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.customerRestClient.service.cuser.data.DefaultFlagEnum;
import com.hq.customerRestClient.testClass.TestEnv;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class CUserClientMgrTest {
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testFindByPhone() {
		long phone = 11022222228L;
		CUser cuser = CUserClientMgr.getInstance().findByPhone(phone);
		System.out.println(JsonUtil.getInstance().toJson(cuser));
	}
	
	@Test
	public void testAddFromMs() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CuserAdd4Ms inputForm = CuserAdd4Ms.newInstance();
		inputForm.setPhone(11022222222L);
		CUser cuser = CUserClientMgr.getInstance().addFromMs(inputForm);
		System.out.println(JsonUtil.getInstance().toJson(cuser));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testUpdateFromMs() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CUser cuser = CUserClientMgr.getInstance().get(67319L);
		CuserUpdate4Ms inputForm = CuserUpdate4Ms.newInstance();
		FastBeanCopyer.getInstance().copy(cuser, inputForm);
		inputForm.setName("test");
		CUser data = CUserClientMgr.getInstance().updateFromMs(inputForm);
		System.out.println(JsonUtil.getInstance().toJson(data));
		ValidateThreadLocal.getInstance().remove();
	}
	
	@Test
	public void testRegWithWxInfo() {
		CUserAddByWxForm formInfo = CUserAddByWxForm.newInstance();
		formInfo.setWxUnionId("111111111");
		formInfo.setAge(18);
		formInfo.setGender(1);
		formInfo.setHeadImg("headImg");
		formInfo.setName("kevin");
		RestResp resp = CUserClientMgr.getInstance().regWithWxInfo(formInfo);
		System.out.println(JsonUtil.getInstance().toJson(resp));
	}
	
	@Test
	public void testResetPassword() {
		CUserResetPasswordData resetPasswordData = CUserResetPasswordData.newInstance();
		resetPasswordData.setPassword("123456");
		resetPasswordData.setPhone(13660623953L);
		resetPasswordData.setVerifyCode("1234");
		RestResp resp = CUserClientMgr.getInstance().resetPassword(resetPasswordData);
		System.out.println(JsonUtil.getInstance().toJson(resp));
	}
	
	@Test
	public void testGet() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CUser cuser = CUserClientMgr.getInstance().get(98L);
		System.out.println(JsonUtil.getInstance().toJson(cuser));
	}
	
	@Test
	public void testAddAddress() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CUserAddressAddForm addressAddData = CUserAddressAddForm.newInstance();
		addressAddData.setReceiver("王五2");
		addressAddData.setPhone("1392223331");
		addressAddData.setAddressArea("广东省 广州市 天河区");
		addressAddData.setAddressDetail("万科云1004");
		addressAddData.setIndex(5);
		addressAddData.setDefaultFlag(DefaultFlagEnum.IS_DEFAULT.ordinal());
		addressAddData.setAddressPickerValue("00003,0004");
		CUserClientMgr.getInstance().addAddress(98, addressAddData);
	}
	
	@Test
	public void testUpdateAddress() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CUserAddressUpdateForm addressUpdateData = CUserAddressUpdateForm.newInstance();
		addressUpdateData.setReceiver("张三3");
		addressUpdateData.setPhone("13600001111");
		addressUpdateData.setAddressArea("广东省 广州市 天河区");
		addressUpdateData.setAddressDetail("万科云1005");
		addressUpdateData.setId("1");
		addressUpdateData.setDefaultFlag(DefaultFlagEnum.IS_DEFAULT.ordinal());
		addressUpdateData.setAddressPickerValue("00001,0002");
		CUserClientMgr.getInstance().updateAddress(98, addressUpdateData);
	}
	
	@Test
	public void testRemoveAddress() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CUserAddressRemoveForm addressRemoveData = CUserAddressRemoveForm.newInstance();
		addressRemoveData.setAddressId(2);
		CUserClientMgr.getInstance().removeAddress(98, addressRemoveData);
	}
	
	@Test
	public void testChangeDefaultAddress() {
		ValidateThreadLocal.getInstance().setValidateInfo(1);
		CUserChangeDefaultAddressForm changeDefaultAddressData = CUserChangeDefaultAddressForm.newInstance();
		changeDefaultAddressData.setAddressId(1);
		CUserClientMgr.getInstance().changeDefaultAddress(98, changeDefaultAddressData);
	}
}
