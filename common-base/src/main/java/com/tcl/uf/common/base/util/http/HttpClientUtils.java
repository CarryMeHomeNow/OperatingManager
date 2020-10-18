package com.tcl.uf.common.base.util.http;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author youyun.xu
 * @ClassName: HttpClientUtils
 * @Description:
 * @date 2020/08/12 下午2:36
 */
public class HttpClientUtils {

	//常见的Content-Type 类型
	public static final String X_WWW_FORM_URLENCODED="application/x-www-form-urlencoded";
	public static final String APPLICATION_JSON="application/json";


	/**
	 * 发送HTTP GET请求
	 * @param url 请求链接
	 * @return String
	 */
	public static String sendGet(String url) throws IOException {
		String result = null;
		// 1.创建HTTPCLIENT对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 2.创建GET方式请求对象,并设置请求头信息
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("accept", "*/*");
		httpGet.addHeader("Content-Type", "text/plain");
		httpGet.addHeader("connection", "Keep-Alive");
		httpGet.addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		CloseableHttpResponse response = null;
		try {
			// 3.通过请求对象获取响应对象
			response = httpClient.execute(httpGet);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				// 4.获取结果实体
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 发送HTTP GET请求(自定义请求头)
	 * @param url	 请求链接
	 * @param header 请求头
	 * @return String
	 */
	public static String sendGet(String url, Map<String, String> header) throws IOException {
		String result = null;
		// 1.创建HTTPCLIENT对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 2.创建GET方式请求对象,并设置请求头信息
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("accept", "*/*");
		httpGet.addHeader("Content-Type", "text/plain");
		httpGet.addHeader("connection", "Keep-Alive");
		httpGet.addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		// 部分动态设置请求头信息(setHeader 覆盖添加请求头)
		for (String key : header.keySet()) {
			httpGet.setHeader(key, header.get(key));
		}

		CloseableHttpResponse response = null;
		try {
			// 3.通过请求对象获取响应对象
			response = httpClient.execute(httpGet);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				// 4.获取结果实体
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * 发送HTTP POST请求
	 * @param url	 请求连接
	 * @param params 请求头
	 * @return String
	 */
	public static String sendPost(String url, Map<String, String> params) throws IOException {
		String result = null;
		// 1.创建HTTPCLIENT对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 2.创建POST方式请求对象,,并设置请求头信息
		HttpPost httpPost = new HttpPost(url);

		// 3.设置HEADER信息,指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		// 4..装填参数
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 5.设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

		// 6.执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 发送HTTP POST请求(自定义请求头)
	 * @param url	 请求连接
	 * @param params 请求参数
	 * @param header 请求头
	 * @return String
	 */
	public static String sendPost(String url, Map<String, String> params, Map<String, String> header)
			throws IOException {
		String result = null;
		// 1.创建HTTPCLIENT对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 2.创建POST方式请求对象,,并设置请求头信息
		HttpPost httpPost = new HttpPost(url);

		// 3.设置HEADER信息,指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		// 部分动态设置请求头信息
		for (String key : header.keySet()) {
			httpPost.setHeader(key, header.get(key));
		}

		// 4..装填参数
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 5.设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

		// 6.执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 发送HTTP POST请求
	 * @param url	请求连接信息
	 * @param json JSON格式参数
	 * @return String
	 */
	public static String sendPost(String url, String json) throws IOException {
		String result = null;

		// 创建HTTPCLIENT对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 设置参数到请求对象中
		StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
		stringEntity.setContentEncoding("utf-8");
		httpPost.setEntity(stringEntity);

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 发送HTTP POST请求
	 * @param url	 请求连接信息
	 * @param json   JSON格式参数
	 * @param header 请求头
	 * @return String
	 */
	public static String sendPost(String url, String json,Map<String, String> header) throws IOException {
		String result = null;

		// 创建HTTPCLIENT对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		
		// 部分动态设置请求头信息
		for (String key : header.keySet()) {
			httpPost.setHeader(key, header.get(key));
		}

		// 设置参数到请求对象中
		StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
		stringEntity.setContentEncoding("utf-8");
		httpPost.setEntity(stringEntity);

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 使用 NameValuePair 来拼接URI
	 * 发送HTTP POST请求
	 * @param params	 请求连接信息
	 * @return String
	 */
	public static String nameValuePair(String url,Map<String,Object> params){
		StringBuffer stringBuffer = new StringBuffer(url);
		List<NameValuePair> nameValuePair = Lists.newArrayList();
		for (String key : params.keySet()) {
			if(params.get(key) == null){
				continue;
			};
			nameValuePair.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
		}
		String nameValuePairStr = "";
		if(!nameValuePair.isEmpty()){
			try {
				nameValuePairStr = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePair, Consts.UTF_8));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isBlank(nameValuePairStr)){
			stringBuffer.append("?").append(nameValuePairStr);
		}
		return String.valueOf(stringBuffer);
	}
}
