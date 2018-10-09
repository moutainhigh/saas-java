package com.hq.testClass.robot.euser.robot;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.euser.apiData.EUserAddForm;
import com.hq.chainStore.service.euser.apiData.EUserUpdateCountData;
import com.hq.chainStore.service.euser.apiData.EUserUpdateForm;
import com.hq.chainStore.service.euser.apiData.EUserUpdateType;
import com.hq.chainStore.service.euser.bs.EUserMgr;
import com.hq.chainStore.service.euser.data.EUser;

/** 
 * @ClassName: EUserRobot 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月20日 下午2:17:48 
 *  
 */
public class EUserRobot {

	private EUserRobotData data;
	
	public static EUserRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static EUserRobot newInstance(int mark){
		EUserRobot robot = new EUserRobot();
		robot.data = EUserRobotData.newInstance(mark);
		return robot;
	}
	
	public EUser add(){
		EUserAddForm addForm = new EUserAddForm();
		addForm.setName(data.getName());
		addForm.setPhone(data.getPhone());
		addForm.setCount(data.getCount());
		EUser euser = EUserMgr.getInstance().createEUser(addForm);
		return euser;
	}
	
	
	public EUser getById(){
		return EUserMgr.getInstance().get(data.getId());
	}
	
	public EUser getByEUserId(long id){
		return EUserMgr.getInstance().get(id);
	}
	
	public List<EUser> getList(Integer pageItemCount,Integer pageNo){
		return EUserMgr.getInstance().getList(pageItemCount,pageNo);
	}
	
	public void updateCount(EUser euser){
		EUserUpdateCountData updateCountData = EUserUpdateCountData.newInstance();
		updateCountData.setEuserId(euser.getId());
		updateCountData.setCount(euser.getCount()+1);
		EUserUpdateForm updateForm = EUserUpdateForm.newInstance();
		updateForm.setUpdateCountData(updateCountData);
		updateForm.setUpdateType(EUserUpdateType.updateCount.ordinal());
		EUserMgr.getInstance().update(data.getId(), updateForm);
	}
	
	public EUserRobotData getData() {
		return data;
	}

	public void setData(EUserRobotData data) {
		this.data = data;
	}

	public long getId(){
		return this.data.getId();
	}
	
	public String getName(){
		return this.data.getName();
	}
}
