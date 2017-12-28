package com.radida.pacs.core.common;

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
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpUtils {

	public static String post(String url, List<NameValuePair> params)
			throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception("URL is empty");
		}
		// 创建HttpClient实例
		HttpClient client = new DefaultHttpClient();
		// 根据URL创建HttpPost实例
		HttpPost post = new HttpPost(url);

		// 编码格式转换
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
				HTTP.UTF_8);
		// MultipartEntity entity = new MultipartEntity();
		// 传入请求体
		post.setEntity(entity);
		// 发送请求，得到响应体
		HttpResponse response = client.execute(post);
		// 判断是否正常返回
		if (response.getStatusLine().getStatusCode() == 200) {
			// 解析数据
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			return data;
		}
		return null;
	}

	public static String postMultipart(String url, MultipartEntity entity)
			throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception("URL is empty");
		}
		// 创建HttpClient实例
		HttpClient client = new DefaultHttpClient();
		// 根据URL创建HttpPost实例
		HttpPost post = new HttpPost(url);
		System.out.println(entity.getContentType());
		// 编码格式转换
		// 传入请求体
		post.setEntity(entity);
		// 发送请求，得到响应体
		HttpResponse response = client.execute(post);
		// 判断是否正常返回
		if (response.getStatusLine().getStatusCode() == 200) {
			// 解析数据
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			return data;
		}
		return null;
	}

	public static String get(String url) throws Exception {
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		// 创建HttpClient实例
		HttpClient client = new DefaultHttpClient();
		// 根据URL创建HttpPost实例
		HttpGet get = new HttpGet(url);
		// 传入请求体
		// 发送请求，得到响应体
		HttpResponse response = client.execute(get);
		// 判断是否正常返回
		if (response.getStatusLine().getStatusCode() == 200) {
			// 解析数据
			HttpEntity resEntity = response.getEntity();
			String data = EntityUtils.toString(resEntity);
			return data;
		}
		return null;
	}

	public static String call(String url, JSONObject params_json) {
		if (StringUtils.isEmpty(url) || params_json == null) {
			return null;
		}
		String param = null;
		param = new String(Base64.encodeBase64(params_json.toString()
				.getBytes()));
		// 构造post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("params", param));
		try {
			String data = post(url, params);
			return data;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 100; i++) {
			long beginTime = System.currentTimeMillis();
			// 调用论坛接口
			StringBuffer url = new StringBuffer();
			String api_url = "http://192.168.1.10/bbs/api/rad/user.php";
			url.append(api_url + "?m=check_username");
			// url.append("?action=login");
			// url.append("&username=").append("mikebean2");
			// url.append("&password=").append("81dc9bdb52d04dc20036dbd8313ed055");
			// b1VQAAJRVgZUXVxRUAFUBVIKVgIKCQIGAwZTC1FXUQMFPg
			String data = null;
			try {

				MultipartEntity entity = new MultipartEntity();
				entity.addPart("appid", new StringBody("radida"));
				entity.addPart("appsecret", new StringBody(
						"340f7c2dcaedeae68e4a62c281c7350b"));
				entity.addPart("username", new StringBody("admin"));
				// List<NameValuePair> params = new ArrayList<>();
				//
				// params.add(new BasicNameValuePair("appid", "radida"));
				// params.add(new BasicNameValuePair("appsecret",
				// "340f7c2dcaedeae68e4a62c281c7350b"));
				// params.add(new BasicNameValuePair("username", "admin"));
				// params.add(new BasicNameValuePair("action", "login"));
				// params.add(new BasicNameValuePair("password",
				// DigestUtils.md5Hex("123456")));
				// params.add(new BasicNameValuePair("ua",
				// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36"));
				data = HttpUtils.postMultipart(url.toString(), entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("call radinet api result data : " + data);
			long endTime = System.currentTimeMillis();
			System.out.println("time=" + (endTime - beginTime));

			if ((endTime - beginTime) > 500) {
				count++;
			}

		}
		System.out.println("大于500的个数＝" + count);
	}
}
