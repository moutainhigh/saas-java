package com.hq.chainClient.testClass.robot.chainClerk;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainClient.service.chainClerk.data.adminRole.AdminRole;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AdminRoleRobotData {
	//角色名称
	private String name;
	//描述
	private String descript;
	//权限 对应 AdminPermEnum
	private Set<Integer> permSet = new HashSet<Integer>();

	public static AdminRoleRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		AdminRoleRobotData data = new AdminRoleRobotData();
		data.name="岗位"+random;
		data.descript="描述"+random;
		data.permSet.add(RandomUtils.nextInt(1, AdminPermEnum.values().length));
		return data;
	}
	
	public AdminRole toAdminRole(int index) {
		AdminRole data = AdminRole.newInstance(index);
		FastBeanCopyer.getInstance().copy(this, data);
		return data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}
}
