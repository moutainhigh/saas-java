package com.hq.chainStore.service.logger.bs;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.logger.LoggerRobot;

public class LoggerMgrTest {
	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
	}

	@Test
	public void saveLogger() {
		LoggerRobot robot = LoggerRobot.newRandom();
		robot.saveLogger();
	}
}
