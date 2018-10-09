package com.hq.orderMS.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ValidateDataIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/error");
		
		registry.addInterceptor(new SynDataIntcpt()).addPathPatterns("/**")
        .excludePathPatterns("/error");
    	
		registry.addInterceptor(new OrderAuthIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/error");
	}
}
