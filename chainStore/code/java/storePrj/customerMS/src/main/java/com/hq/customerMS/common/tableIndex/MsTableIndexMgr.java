package com.hq.customerMS.common.tableIndex;

import java.util.HashSet;
import java.util.Set;

import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;

/**
 * 创建表的索引
 * @author kevin
 *
 */
public class MsTableIndexMgr {
	
	public static MsTableIndexMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MsTableIndexMgr.class);
	}
	
	private Set<Class<?>> tableIndexs = new HashSet<Class<?>>();
	
	//实例化的时候，将需要创建索引的表，加入tableIndexs
	public MsTableIndexMgr(){
		tableIndexs.add(CUser.class);
	}

	public Set<ClassInfo> getClassInfos() {
		Set<ClassInfo> classInfos = new HashSet<ClassInfo>();
		for (Class<?> classP : tableIndexs) {
			classInfos.add(new ClassInfo(classP));
		}
		return classInfos;
	}
	
}
