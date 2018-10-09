package com.hq.chainStore.service.serverConfig.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ServerConfig {
	
	private String host;
	
	public static ServerConfig newInstance(){
		return new ServerConfig();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
