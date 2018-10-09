package com.hq.thirdPartyServer.service.sms.data;

/*
* Copyright 2017 Alibaba Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class SyncApiClientSms extends BaseApiClient {
    public final static String GROUP_HOST = "sms.market.alicloudapi.com";

    private SyncApiClientSms(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<SyncApiClientSms.Builder, SyncApiClientSms>{

        @Override
        protected SyncApiClientSms build(BuilderParams params) {
            return new SyncApiClientSms(params);
        }
    }

    public static Builder newBuilder(){
        return new SyncApiClientSms.Builder();
    }

    public static SyncApiClientSms getInstance(){
        return getApiClassInstance(SyncApiClientSms.class);
    }

    public ApiResponse singleSendSms(String TemplateCode, String SignName, String RecNum, String ParamString) {
        String _apiPath = "/singleSendSms";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.GET, GROUP_HOST, _apiPath);

        _apiRequest.addParam("TemplateCode", TemplateCode, ParamPosition.QUERY, false);
        _apiRequest.addParam("SignName", SignName, ParamPosition.QUERY, false);
        _apiRequest.addParam("RecNum", RecNum, ParamPosition.QUERY, false);
        _apiRequest.addParam("ParamString", ParamString, ParamPosition.QUERY, false);

        return syncInvoke(_apiRequest);
    }

}

