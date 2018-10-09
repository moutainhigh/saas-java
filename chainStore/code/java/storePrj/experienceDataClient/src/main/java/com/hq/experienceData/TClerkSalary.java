package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;


public class TClerkSalary {
	private float salary;
	private float percentage;

	public static List<TClerkSalary> buildTClerkSalaryList() {
		List<TClerkSalary> list = new ArrayList<TClerkSalary>();
		for (int i = 0; i < 10; i++) {
			TClerkSalary data = new TClerkSalary();
			data.setPercentage(RandomUtils.nextFloat(5.0f, 20.0f));
			data.setSalary(RandomUtils.nextInt(5000, 20000));
			list.add(data);
		}
		Collections.shuffle(list);
		return list.subList(0, DataConstants.OTHER_COUNT);
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

}
