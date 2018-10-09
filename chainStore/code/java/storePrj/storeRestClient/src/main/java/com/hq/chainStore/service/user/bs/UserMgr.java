package com.hq.chainStore.service.user.bs;

import java.util.List;

import com.hq.chainStore.service.user.data.User;
import com.hq.chainStore.service.user.data.UserDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class UserMgr {

	public static UserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(UserMgr.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public User add(User target) {
		return UserDAO.getInstance().add(target);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(User target) {
		UserDAO.getInstance().update(target);
	}
	
	public void delete(long id) {
		UserDAO.getInstance().delete(id);
	}


	public User get(long id) {
		return UserDAO.getInstance().get(id);
	}
	public User findByPhone(long phone) {
		return UserDAO.getInstance().findByPhone(phone);
	}
	
	public List<User> list(int pageItemCount, int pageNo) {
		return UserDAO.getInstance().list(pageItemCount, pageNo);
	}
	
}
