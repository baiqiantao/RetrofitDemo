package com.github.ikidou.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import spark.Request;
import spark.Response;
import spark.Route;

public class HeaderHandler implements Route {
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		Map<String, String> customHeaderMap = new HashMap<>();
		String showAll = request.queryParams("showAll");
		
		String[] normalHeaders = new String[]{"Accept-Encoding", "Connection", "Content-Length", "Content-Type", "Host", "User-Agent"};
		Set<String> headers = request.headers();
		
		if (!"true".equalsIgnoreCase(showAll)) {
			for (String s : normalHeaders) {
				headers.remove(s);
			}
		}
		
		if (!headers.isEmpty()) {
			
			for (String header : headers) {
				customHeaderMap.put(header, request.headers(header));
			}
			
		}
		return customHeaderMap;
	}
}
