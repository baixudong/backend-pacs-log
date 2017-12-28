package com.radida.pacs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class BaixdTest {
	public void post(String url, List<NameValuePair> params) throws Exception {
		// 创建HttpClient实例
		HttpClient client = new DefaultHttpClient();
		// 根据URL创建HttpPost实例
		HttpPost post = new HttpPost(url);
		// 编码格式转换
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
		// 传入请求体
		post.setEntity(entity);
		// 发送请求，得到响应体
		HttpResponse response = client.execute(post);
		// 判断是否正常返回
		if (response.getStatusLine().getStatusCode() == 200) {
			// 解析数据
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			System.out.println(data);
		} else {
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			System.out.println(data);
		}
	}

	public void get(String url, String params) throws Exception {
		// 创建HttpClient实例
		HttpClient client = new DefaultHttpClient();
		// 根据URL创建HttpPost实例
		HttpGet get = new HttpGet(url + "?params=" + params);
		// 发送请求，得到响应体
		HttpResponse response = client.execute(get);
		// 判断是否正常返回
		if (response.getStatusLine().getStatusCode() == 200) {
			// 解析数据
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			System.out.println(data);
		} else {
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			System.out.println(data);
		}
	}

	public void get(String url, String params, String sessionID) throws Exception {
		// 创建HttpClient实例
		HttpClient client = new DefaultHttpClient();
		// 根据URL创建HttpPost实例
		if (sessionID == null) {
			url = url + "?params=" + params;
		} else {
			url = url + "?JSESSIONID=" + sessionID + "&params=" + params;
		}
		HttpGet get = new HttpGet(url);
		// 发送请求，得到响应体
		HttpResponse response = client.execute(get);
		// 判断是否正常返回
		if (response.getStatusLine().getStatusCode() == 200) {
			// 解析数据
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			System.out.println(data);
		} else {
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			System.out.println(data);
		}
	}

	public void test(String ipPort) {
		String oipPort = "127.0.0.1:9000/pacs-log";
		if (StringUtils.isNotEmpty(ipPort)) {
			oipPort = ipPort;
		}
		String url = "http://" + oipPort + "/test";
		JSONObject obj = new JSONObject();
		String param = null;
		try {
			obj.put("11", "00");
			param = new String(obj.toString());
			System.out.println(param);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 构造post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params", param));
		try {
			post(url, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void login(String ipPort) {
		String oipPort = "127.0.0.1:9000/pacs-log";
		if (StringUtils.isNotEmpty(ipPort)) {
			oipPort = ipPort;
		}
		String url = "http://" + oipPort + "/login";
		JSONObject obj = new JSONObject();
		String param = null;
		try {
			obj.put("username", "zhao");
			obj.put("password", "123456");
			param = new String(Base64.encodeBase64(obj.toString().getBytes()));
			System.out.println(param);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 构造post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params", param));
		try {
			post(url, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void list(String ipPort) {
		String oipPort = "127.0.0.1:9000/pacs-log";
		if (StringUtils.isNotEmpty(ipPort)) {
			oipPort = ipPort;
		}
		String url = "http://" + oipPort + "/list";
		JSONObject obj = new JSONObject();
		String param = null;
		try {
			obj.put("name", "");
			obj.put("page", "1");
			obj.put("count", "10");
			param = new String(Base64.encodeBase64(obj.toString().getBytes()));
			System.out.println(param);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 构造post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params", param));
		try {
			post(url, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		
		String ip_port = "";
		BaixdTest test = new BaixdTest();
		test.login(ip_port);

//		test.list(ip_port);
//		test.test(ip_port);
	}
	
}
