package com.hq.customerMS.zenmind.dao.rest.restSTImpl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hq.customerMS.common.config.CustomerMSCfgMgr;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.zenmind.dao.rest.RestResp;

@Component
public class HystrixUtil {

    public static ResponseEntity<RestResp> execute(String uri, HttpMethod method, HttpEntity<String> entity) throws RuntimeException {
        Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(uri));//被调用服务
        setter.andCommandKey(HystrixCommandKey.Factory.asKey(method.toString()));//被调用服务的一个被调用方法
        int value = CustomerMSCfgMgr.getProp().getHystrixTimeoutInMillions();
        setter.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(value));
        return new HqHystrixCommand(setter, uri, method, entity).execute();//同步执行
//        Future<Response> future = new MyHystrixCommand(setter, uri, method, entity).queue();//异步执行
//        return future.get();//需要时获取
    }

}