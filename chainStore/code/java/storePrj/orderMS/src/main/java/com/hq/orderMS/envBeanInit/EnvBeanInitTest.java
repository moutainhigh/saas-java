package com.hq.orderMS.envBeanInit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/** 
 * @author zenmind 
 */  
@Component
@Profile("prd") //开发环境时生效 
public class EnvBeanInitTest{
	
}
