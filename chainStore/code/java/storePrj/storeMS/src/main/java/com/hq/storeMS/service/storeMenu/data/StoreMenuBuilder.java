package com.hq.storeMS.service.storeMenu.data;

import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreMenuBuilder {

	public static StoreMenuBuilder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMenuBuilder.class);
	}

	public StoreMenu build() {
		StoreMenu target = StoreMenu.newInstance();
		// 仪器权限
		target.addFirstMenu(buildDeviceMenu());
		// 保密权限
		target.addFirstMenu(buildSecrMenu());
		// 数据权限
		target.addFirstMenu(buildDataMenu());
		// 管理权限
		target.addFirstMenu(buildMgrMenu());
		// 会员管理
		target.addFirstMenu(buildMbMenu());
		// 店务流程
		target.addFirstMenu(buildWfMenu());
		return target;
	}

	private FirstMenu buildDeviceMenu() {
		FirstMenu menu = FirstMenu.newInstance("仪器管理");
		menu.setSort(6);
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.DEVICE_ADMIN));
		return menu;
	}

	private FirstMenu buildSecrMenu() {
		FirstMenu menu = FirstMenu.newInstance("保密权限");
		menu.setSort(5);
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.PHONE_ADMIN));// 电话号码可见
		return menu;
	}

	private FirstMenu buildDataMenu() {
		FirstMenu menu = FirstMenu.newInstance("数据权限");
		menu.setSort(4);
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.REPORT_ADMIN)); // 数据报表
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.INCOME_PAY_ADMIN)); // 收入支出
		return menu;
	}

	private FirstMenu buildMgrMenu() {
		FirstMenu menu = FirstMenu.newInstance("管理权限");
		menu.setSort(3);
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.PRODUCT_ADMIN), // 项目
				SecondMenu.newInstance(StoreAdminPermEnum.PACKAGE_ADMIN), // 套餐
				SecondMenu.newInstance(StoreAdminPermEnum.GOODS_ADMIN), // 商品
				SecondMenu.newInstance(StoreAdminPermEnum.CARD_ADMIN), // 卡包
				SecondMenu.newInstance(StoreAdminPermEnum.CLERK_ADMIN), // 员工
				SecondMenu.newInstance(StoreAdminPermEnum.BONUS_ADMIN), // 提成
				SecondMenu.newInstance(StoreAdminPermEnum.ARREARAGE_ADMIN),// 欠款
				SecondMenu.newInstance(StoreAdminPermEnum.STORE_CONFIG_ADMIN),// 设置管理
				SecondMenu.newInstance(StoreAdminPermEnum.DAYSNAPSHOT_ADMIN),// 交班日结
				SecondMenu.newInstance(StoreAdminPermEnum.OPLOG_ADMIN),// 操作日志
				SecondMenu.newInstance(StoreAdminPermEnum.SYNDATA_ADMIN) //数据获取
		);
		return menu;
	}

	private FirstMenu buildMbMenu() {
		FirstMenu menu = FirstMenu.newInstance("会员管理");
		menu.setSort(2);
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.LEAGUER_ADMIN));// 会员档案
		return menu;
	}

	private FirstMenu buildWfMenu() {
		FirstMenu menu = FirstMenu.newInstance("店务流程");
		menu.setSort(1);
		menu.addSecondMenu(SecondMenu.newInstance(StoreAdminPermEnum.APPOINTMENT_ADMIN), // 预约
				SecondMenu.newInstance(StoreAdminPermEnum.RECHARGE_ADMIN), // 会员充值
				SecondMenu.newInstance(StoreAdminPermEnum.PURCHASE_ADMIN), // 开单
				SecondMenu.newInstance(StoreAdminPermEnum.ORDER_ADMIN)// 订单
		);
		return menu;
	}

}
