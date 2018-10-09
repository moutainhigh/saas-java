package com.hq.storeManagerMS.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new SynDataIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/muser/login/**")
		.excludePathPatterns("/muser/reg/**")
		.excludePathPatterns("/error");

		registry.addInterceptor(new MUserAuthIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/muser/login/**")
		.excludePathPatterns("/muser/reg/**")
		.excludePathPatterns("/muser/findByPhone")
		.excludePathPatterns("/sms/**")
		.excludePathPatterns("/serverConfigUnAuth/**")
		.excludePathPatterns("/reset/changePassword/**")
		.excludePathPatterns("/error")
		// 需要跳过拦截的情况，统一使用unAuth 如：/unAuth/appVersion/findByName
		.excludePathPatterns("/unAuth/**")
		;
	}
}
