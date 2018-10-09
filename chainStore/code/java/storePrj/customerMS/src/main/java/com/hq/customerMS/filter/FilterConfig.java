package com.hq.customerMS.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean charsetFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("greeting");
		CharsetFilter charsetFilter = new CharsetFilter();
		registrationBean.setFilter(charsetFilter);
		registrationBean.setOrder(1);
		List<String> urlList = new ArrayList<String>();
		urlList.add("/*");
		registrationBean.setUrlPatterns(urlList);
		return registrationBean;
	}
}

