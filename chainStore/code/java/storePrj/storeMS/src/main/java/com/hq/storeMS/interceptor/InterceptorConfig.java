package com.hq.storeMS.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new ThreadTraceIntcpt()).addPathPatterns("/**")
    	.excludePathPatterns("/monitor/**")
        .excludePathPatterns("/trace/**")
    	.excludePathPatterns("/error");

    	registry.addInterceptor(new SynDataIntcpt()).addPathPatterns("/**")
        .excludePathPatterns("/buser/login/**")
        .excludePathPatterns("/buser/reg/**")
        .excludePathPatterns("/op/**")
        .excludePathPatterns("/cm/**")
        .excludePathPatterns("/mng/**")
        .excludePathPatterns("/error");

        //平台管理员
        registry.addInterceptor(new OPAuthIntcpt()).addPathPatterns("/op/**")
        .excludePathPatterns("/op/opuser/login/**")
        .excludePathPatterns("/op/opuser/reg/**")
        .excludePathPatterns("/error");

        registry.addInterceptor(new BUserAuthIntcpt()).addPathPatterns("/**")
        .excludePathPatterns("/buser/login/**")
        .excludePathPatterns("/buser/reg/**")
        .excludePathPatterns("/buser/findByPhone")
        .excludePathPatterns("/buser/findDevUserList")
        .excludePathPatterns("/buser/loginWithTestPhone")
        .excludePathPatterns("/buser/loginWithJsCode")

        .excludePathPatterns("/buserDevice/**")
        .excludePathPatterns("/deviceDetail/**")
        .excludePathPatterns("/mngDevice/**")
        .excludePathPatterns("/mclient/**")

        .excludePathPatterns("/excel/**")
        .excludePathPatterns("/pay/**")

        .excludePathPatterns("/pay/payCallback/**")
        .excludePathPatterns("/euser/**")
        .excludePathPatterns("/sms/**")
        .excludePathPatterns("/monitor/**")
        .excludePathPatterns("/trace/**")
        .excludePathPatterns("/serverConfigUnAuth/**")
        .excludePathPatterns("/appVersionUnAuth/**")
        .excludePathPatterns("/logger/**")
        .excludePathPatterns("/reset/changePassword/**")
        .excludePathPatterns("/storeUnAuth/**")
        .excludePathPatterns("/error")

        //需要跳过拦截的情况，统一使用unAuth  如：/unAuth/appVersion/findByName
        .excludePathPatterns("/unAuth/**")
        //第三方微服务
        .excludePathPatterns("/op/**")
        .excludePathPatterns("/ms/**")
        .excludePathPatterns("/cm/**")
        .excludePathPatterns("/mng/**");

        registry.addInterceptor(new ValidateDataIntcpt()).addPathPatterns("/**")
        .excludePathPatterns("/unAuth/**")
		.excludePathPatterns("/error");
    }
}

