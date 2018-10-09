package com.hq.thirdPartyServer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

import com.hq.thirdPartyServer.common.config.ThirdPartyServerCfg;

@EnableDiscoveryClient
@SpringBootApplication
@ImportResource(locations = { "classpath:applicationContext.xml" })
@EnableConfigurationProperties({ ThirdPartyServerCfg.class }) // 开启配置属性支持
public class HqThirdPartyServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HqThirdPartyServer.class).web(true).run(args);
	}
}