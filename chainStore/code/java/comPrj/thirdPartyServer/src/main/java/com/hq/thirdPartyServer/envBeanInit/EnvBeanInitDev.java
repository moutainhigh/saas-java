package com.hq.thirdPartyServer.envBeanInit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/** 
 * @author zenmind 
 * 其中@Profile用来指定工作环境，例如示例中为"dev",那么该类只会在配置文件为“dev”的环境下，才会调用该类 
 * 这个@EnableBinding注解标识用于开启rabbit消息调试，开发环境，可以不需要
 */  
@Component
@Profile("dev") //开发环境时生效 
public class EnvBeanInitDev{
	/**
	 * 浏览器跨域问题
	 * 1.如果请求经过gateway进来，由于gatewayServer已经统一设置了跨域处理，此时不能再次设置
	 * 2.保留这个Bean，用于开发环境使用。
	 * 
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter(){
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
