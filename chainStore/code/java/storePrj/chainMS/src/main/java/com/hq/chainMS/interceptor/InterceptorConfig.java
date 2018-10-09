package com.hq.chainMS.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new ThreadTraceIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/unAuth/monitor/**")
		.excludePathPatterns("/unAuth/trace/**")
		.excludePathPatterns("/error");

		registry.addInterceptor(new SynDataIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/error");
		
		registry.addInterceptor(new ChainUserAuthIntcpt()).addPathPatterns("/**")
		// 需要跳过拦截的情况，统一使用unAuth 如：/appVersion/unAuth/findByName
		.excludePathPatterns("/**/unAuth/**")
		.excludePathPatterns("/sms/**")
		.excludePathPatterns("/reset/**")
		.excludePathPatterns("/chainUser/login/**")
        .excludePathPatterns("/chainUser/reg/**")
        .excludePathPatterns("/chainUser/findByPhone")
		.excludePathPatterns("/error");

		registry.addInterceptor(new ValidateDataIntcpt()).addPathPatterns("/**")
		.excludePathPatterns("/unAuth/**")
		.excludePathPatterns("/error");

	}
}
