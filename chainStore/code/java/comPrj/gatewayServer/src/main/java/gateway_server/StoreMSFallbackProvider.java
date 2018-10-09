package gateway_server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

public class StoreMSFallbackProvider implements ZuulFallbackProvider {
	private String route = null;  
	
	public StoreMSFallbackProvider(String routeP){
		this.route = routeP;
	}
	
	@Override
	public String getRoute() {
		if (this.route == null) {
			this.route = "storems";
		}  
        return this.route;
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			@Override
			public HttpHeaders getHeaders() {
				// 设置header
				HttpHeaders headers = new HttpHeaders();
				MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mediaType);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				// 响应体
				return new ByteArrayInputStream("{\"code\":400,\"tips\":\"service is unavailable, please try it again later!\"}" .getBytes());
			}

			@Override
			public String getStatusText() throws IOException {
				// 返回状态文本
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				// 返回数字类型的状态码
				return this.getStatusCode().value();
			}

			@Override
			public void close() {
			}
		};
	}
}
