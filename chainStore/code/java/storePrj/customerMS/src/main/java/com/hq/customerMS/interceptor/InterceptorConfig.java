package com.hq.customerMS.interceptor;

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
    	 
        registry.addInterceptor(new CUserAuthIntcpt()).addPathPatterns("/**")
        .excludePathPatterns("/cuser/login*")
    	.excludePathPatterns("/cuser/reg*")
    	.excludePathPatterns("/cuser/findByPhone/**")
    	.excludePathPatterns("/sms/**")
    	.excludePathPatterns("/logger/**")
    	.excludePathPatterns("/cuser/resetPassword/**")
    	.excludePathPatterns("/error")
    	
    	//需要跳过拦截的情况，统一使用unAuth  如：/unAuth/appVersion/findByName
        .excludePathPatterns("/unAuth/**");
    }
}

