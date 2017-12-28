package com.radida.pacs.core.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
				+ "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
		try {
			readJson2Map(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, Object> readJson2Map(String json)
			throws Exception {

		if (CommonUtils.isEmpty(json)) {
			return null;
		}

		Map<String, Object> maps = null;

		/*
		 * try { JsonGenerator jsonGenerator =
		 * objectMapper.getJsonFactory().createJsonGenerator(System.out,
		 * JsonEncoding.UTF8); } catch (IOException e) { e.printStackTrace(); }
		 */

		maps = objectMapper.readValue(json, Map.class);

		return maps;
	}

	public static String readMap2Json(Map<String, Object> map) throws Exception {
		if (map == null || map.isEmpty()) {
			return null;
		}
		StringWriter str = new StringWriter();
		objectMapper.writeValue(str, map);

		return str.toString();
	}
	
	/**
	 * 获取在线解析xml文件
	 * 
	 * @param url
	 * @return
	 */
	public static String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
