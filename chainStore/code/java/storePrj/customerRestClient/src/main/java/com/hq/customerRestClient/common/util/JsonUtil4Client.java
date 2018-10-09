package com.hq.customerRestClient.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.hq.customerRestClient.common.restClientResp.PageResp;

public class JsonUtil4Client {
	private static JsonUtil4Client instance = new JsonUtil4Client();

	public static JsonUtil4Client getInstance() {
		return instance;
	}

	public <T> T fromJson(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	public <T> List<T> parseList(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}

	private final TypeReference<PageResp<Object>> objTypeReference = new TypeReference<PageResp<Object>>(){};
	@SuppressWarnings("unchecked")
	public <T> PageResp<T> parseTPage(String json, Class<T> clz) {
		PageResp<T> page = (PageResp<T>)JSON.parseObject(json, objTypeReference);
		if (page.getList() != null) {
			List<T> list = JSONArray.parseArray(page.getList().toString(), clz);
			page.setList(list);
		}
		return page;
	}

}
