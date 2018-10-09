package com.hq.experienceData.service;

import java.util.Random;

public class RandomUtils {
	public static int nextInt(int mintop, int maxtop){
		maxtop = maxtop -1;
		Random random=new Random(); 
		return random.nextInt(maxtop)%(maxtop-mintop+1) + mintop; 
	}
	
	public static long nextLong(Long mintop, Long maxtop){
		maxtop = maxtop -1;
		Random random=new Random(); 
		return random.nextInt(maxtop.intValue()) % (maxtop-mintop+1) + mintop; 
	}
	
	public static float nextFloat(Float mintop, Float maxtop){
		maxtop = maxtop -1;
		Random random=new Random(); 
		return random.nextInt(maxtop.intValue()) % (maxtop-mintop+1) + mintop; 
	}
	
	public static void main(String[] args) {
		System.out.println(nextInt(10000, 99999));
		System.out.println(nextLong(10000L, 99999L));
		System.out.println(nextFloat(10000.0f, 99999.0f));
	}
}
