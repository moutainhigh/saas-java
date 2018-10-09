package gateway_server;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@SpringCloudApplication
public class GatewayServer {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayServer.class).web(true).run(args);
	}

	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}

	/**
	 * tomcat参数配置优化
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			public void customize(Connector connector) {
				Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
				// 设置最大连接数
				protocol.setMaxConnections(2000);
				// 设置最大线程数
				protocol.setMaxThreads(2000);
				protocol.setConnectionTimeout(30000);
			}
		});
		return tomcatFactory;
	}

	// @Bean
	// public ZuulFallbackProvider routeStoreMSZuulFallbackProvider() {
	// return new StoreMSFallbackProvider("storems");
	// }

	// @Bean
	// public PatternServiceRouteMapper serviceRouteMapper(){
	// return new
	// PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)-(?<alias>ws^.+)",
	// "${alias}/${version}/${name}");
	// }

	// @Bean
	// public CorsFilter corsFilter(){
	// final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
	// new UrlBasedCorsConfigurationSource();
	// final CorsConfiguration corsConfiguration = new CorsConfiguration();
	// corsConfiguration.setAllowCredentials(true);
	// corsConfiguration.addAllowedOrigin("*");
	// corsConfiguration.addAllowedHeader("*");
	// corsConfiguration.addAllowedMethod("*");
	// urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
	// corsConfiguration);
	// return new CorsFilter(urlBasedCorsConfigurationSource);
	// }

}
