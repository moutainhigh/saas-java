package gateway_server;

import com.netflix.zuul.ZuulFilter;

public class AccessFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
//		RequestContext ctx = RequestContext.getCurrentContext();
//		HttpServletRequest request = ctx.getRequest();
//		String accessToken = request.getHeader("access_token");
//		if (accessToken == null || accessToken.trim().length() == 0) {
//			ctx.setSendZuulResponse(false);
//			ctx.setResponseStatusCode(401);
//			return null;
//		}
		return null;
	}

}