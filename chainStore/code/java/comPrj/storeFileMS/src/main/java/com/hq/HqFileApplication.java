package com.hq;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.hq.common.config.StoreFileMSCfg;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {})
@ImportResource(locations = { "classpath:applicationContext.xml" })
@EnableConfigurationProperties({ StoreFileMSCfg.class }) // 开启配置属性支持
public class HqFileApplication {

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
	

	public static void main(String[] args) {
		// PropertyConfigurator.configure(HqApplication.class.getClassLoader().getResource("log4j.properties"));
		// new ClassPathXmlApplicationContext(new
		// String[]{"classpath:applicationContext.xml"});
		//
		new SpringApplicationBuilder(HqFileApplication.class).web(true).run(args);
	}
}