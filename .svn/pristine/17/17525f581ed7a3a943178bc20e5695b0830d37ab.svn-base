package com.job5156.core.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import com.google.gson.Gson;

/**
 * 异步httpclicent
 * 
 * @author zhaoman
 * 2015-6-15
 */
public class HttpAsyncUtils {
	
	private static final Log loger = LogFactory.getLog(HttpAsyncUtils.class);
	
	/**
	 * 默认utf-8编码
	 */
	public static final String LANGUAGE_DEFAULT_UTF = "UTF-8";
	
	/**
	 * 私有构造器，防止例的实例化
	 */
	private HttpAsyncUtils(){
		
	}
	
	/**
	 * post请求，json格式数据，返回字符串
	 * @param url 请求uril
	 * @param param 请求参数，会自动转为json格式数据
	 * @param referer 防采集处理，指定referer
	 * @param charset 编码
	 * @return string
	 */
	public static String postObject(String url, Object param, String referer, String charset) {
		if(StringUtils.isBlank(charset)) {
			charset = LANGUAGE_DEFAULT_UTF;
		}
		StringBuffer result = new StringBuffer();
		// 默认的配置
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		HttpPost request = null;
		try {
			httpclient.start();
			request = new HttpPost(url);
			if (StringUtils.isNotBlank(referer)) {
				request.addHeader("Referer", referer);
			}
			StringEntity entity = new StringEntity(new Gson().toJson(param), charset);
			entity.setContentEncoding(charset);
			entity.setContentType("application/json");
			request.setEntity(entity);
			Future<HttpResponse> future = httpclient.execute(request, null);
			// 获取结果
			HttpResponse response = future.get();
			//结果内容
			InputStream is = response.getEntity().getContent();
			if(is.available() > 0) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = null;
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
				reader = null;
			}
		} catch(Exception e) {
			loger.error("PostObject have error in the HttpAsyncUtils! "+e.getMessage());
		}
		finally {
			abortConnection(request,httpclient);
		}
		return result.toString();
	}
	
	/**
	 * 释放HttpClient连接
	 * @param hrb 请求对象
	 * @param httpclient DefaultHttpClient，client对象
	 */
	private static void abortConnection(HttpRequestBase hrb,
			CloseableHttpAsyncClient httpclient) {
		if(null != httpclient) {
			try {
				httpclient.close();
				httpclient = null;
			} catch (IOException e) {
				loger.error("There have IOException in the HttpAsyncUtils,PostObject! "+e.getMessage());
			}
		}
		if(null != hrb) {
			hrb.abort();
			hrb = null;
		}
	}
	
	
}
