package com.hq.chainStore.service.incomePay.bs;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.incomePay.apiData.IncomePayAddForm;
import com.hq.chainStore.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.chainStore.service.incomePay.apiData.IncomePayUpdateInfoForm;
import com.hq.chainStore.service.incomePay.data.IncomePay;
import com.hq.chainStore.service.incomePay.data.IncomePayCategoryEnum;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;


public class IncomePayMgrTest {
    private static Boss boss;
    private static long storeId = 0L;

    @BeforeClass
    public static void initEnv() {
        TestEnv.initTestEnv();
        //老板登陆
        boss = Boss.newBoss(BRobot.newRandom());
        boss.getRobot().getData().setPhone(13660623953L);
        boss.getRobot().getData().setPassword("123456");
        boss.login();
        storeId = boss.getRobotStoreId();
    }

    /**
     * 添加收支
     */
    @Test
    public void testAddIncomePay() {
        AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
        BUser bUser = BUserMgr.getInstance().findByPhone(15088132621l);
        if (bUser == null) {
            AccessTokenMgr.getInstance().removeOpIdTL();
            return;
        }
        IncomePayAddForm form = IncomePayAddForm.newInstance();
        form.setBuserId(bUser.getId()).setCategory(IncomePayCategoryEnum.PAY.ordinal()).setIncomePayTime(System.currentTimeMillis())
                .setMoney(155500).setRemark("测试收支5").setTypeId(String.valueOf(1)).setStoreId(storeId);
        IncomePay incomePay = IncomePayMgr.getInstance().addIncomePay(form);
        System.out.println(JsonUtil.getInstance().toJson(incomePay));
        AccessTokenMgr.getInstance().removeOpIdTL();
    }

    /**
     * 编辑收支
     */
    @Test
    public void testUpdateIncomePay() {
        AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
        BUser bUser = BUserMgr.getInstance().findByPhone(13660623953l);
        if (bUser == null) {
            AccessTokenMgr.getInstance().removeOpIdTL();
            return;
        }
        int incomePayId = 5;
		IncomePay incomePay = IncomePayMgr.getInstance().getIncomePay(storeId, incomePayId);
        IncomePayUpdateInfoForm form = IncomePayUpdateInfoForm.newInstance();
        form.setId(incomePay.getId()).setBuserId(bUser.getId()).setCategory(IncomePayCategoryEnum.PAY.ordinal())
                .setIncomePayTime(System.currentTimeMillis()).setMoney(10000).setRemark("测试修改收支5")
                .setTypeId(String.valueOf(1)).setStoreId(storeId);
        IncomePayMgr.getInstance().updateIncomePayInfo(storeId, incomePay.getId(), form);
        incomePay = IncomePayMgr.getInstance().getIncomePay(storeId, incomePayId);
        System.out.println(JsonUtil.getInstance().toJson(incomePay));
        AccessTokenMgr.getInstance().removeOpIdTL();
    }

    /**
     * 删除收支
     */
    @Test
    public void testDeleteIncomePay() {
        AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
        int incomePayId = 16;
		IncomePayMgr.getInstance().deleteIncomePay(storeId, incomePayId);
        IncomePay incomePay = IncomePayMgr.getInstance().getIncomePay(storeId, incomePayId);
        System.out.println(JsonUtil.getInstance().toJson(incomePay));
        AccessTokenMgr.getInstance().removeOpIdTL();
    }

    /**
     * 根据 id 获取收支
     */
    @Test
    public void testGetIncomePay() {
        AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
        int incomePayId = 16;
		IncomePay incomePay = IncomePayMgr.getInstance().getIncomePay(storeId, incomePayId);
        System.out.println(JsonUtil.getInstance().toJson(incomePay));
        AccessTokenMgr.getInstance().removeOpIdTL();
    }

    /**
     * 查询 收支 列表
     */
    @Test
    public void testFindIncomePayPageInfo() {
        AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
        IncomePayQueryForm queryForm = IncomePayQueryForm.newInstance();
        queryForm.setStoreId(storeId);
        PageResp<IncomePay> pageResp = IncomePayMgr.getInstance().findIncomePayPageInfo(queryForm);
        List<IncomePay> list = pageResp.getList();
        System.out.println(list.size());
        System.out.println(JsonUtil.getInstance().toJson(list));
        AccessTokenMgr.getInstance().removeOpIdTL();
    }

}
