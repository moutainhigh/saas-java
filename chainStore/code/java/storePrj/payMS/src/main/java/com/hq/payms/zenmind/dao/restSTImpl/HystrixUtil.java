package com.hq.payms.zenmind.dao.restSTImpl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.zenmind.dao.rest.RestResp;

@Component
public class HystrixUtil {

    public static ResponseEntity<RestResp> execute(String uri, HttpMethod method, HttpEntity<String> entity) throws RuntimeException {
    	int value = PayMSCfgMgr.getProp().getHystrixTimeoutInMillions(); 
    	Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(uri))
         	.andCommandKey(HystrixCommandKey.Factory.asKey(method.toString()))
         	.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(uri))
         	.andCommandPropertiesDefaults(
                 HystrixCommandProperties.Setter()
                         .withCircuitBreakerRequestVolumeThreshold(2)
                         .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000)
                         .withFallbackEnabled(true)
                         .withExecutionIsolationThreadInterruptOnTimeout(true)
                         .withExecutionTimeoutInMilliseconds(value));
        return new HqHystrixCommand(setter, uri, method, entity).execute();//同步执行
//        Future<Response> future = new MyHystrixCommand(setter, uri, method, entity).queue();//异步执行
//        return future.get();//需要时获取
    }

}