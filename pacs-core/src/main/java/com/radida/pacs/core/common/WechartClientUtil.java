package com.radida.pacs.core.common;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.radida.pacs.core.common.JsonUtils;

public class WechartClientUtil {

	private static CloseableHttpClient httpClient = null;
	private static HttpPost post = null;
	private static Logger logger = LoggerFactory.getLogger(WechartClientUtil.class);

	public static Boolean sendWeChatMessage(String data, String url) throws Exception {
		httpClient = HttpClientBuilder.create().build();
		post = new HttpPost(url);
		post.addHeader("Content-type", "application/json; charset=utf-8");
		post.setHeader("Accept", "application/json");
		post.setEntity(new StringEntity(data, Charset.forName("UTF-8")));
		Long startTime = System.currentTimeMillis();
		CloseableHttpResponse response = null;
		String reponseData = null;
		Map<String, Object> pMap = null;
		try {
			response = httpClient.execute(post);
			Long endTime = System.currentTimeMillis();
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("statusCode:" + statusCode);
			logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed:" + response.getStatusLine());
				return false;
			}
			HttpEntity entity = response.getEntity();
			reponseData = EntityUtils.toString(entity);
			pMap = JsonUtils.readJson2Map(reponseData);
			EntityUtils.consume(entity);
			String code = (String) pMap.get("code");
			if ("200".equals(code)) {
				return true;
			}
		} finally {
			response.close();
		}
	
		return false;
	}

}