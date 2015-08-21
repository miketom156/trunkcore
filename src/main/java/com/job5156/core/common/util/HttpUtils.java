package com.job5156.core.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * http工具类
 * 
 */
@SuppressWarnings("deprecation")
public class HttpUtils {
	
	private static final Log loger = LogFactory.getLog(HttpUtils.class);
	public static final String LANGUAGE_UTF = "UTF-8";
	public static final String LANGUAGE_GBK = "GBK";
	public static final String LANGUAGE_GB18030 = "GB18030";
    private static final int REQUEST_TIMEOUT = 10*1000; //设置请求超时10秒钟
    private static final int SO_TIMEOUT = 10*1000;     //设置等待数据超时时间10秒钟

	/**
	 * 私有构造器，防止例的实例化
	 */
	private HttpUtils() {
		super();
	}

	// 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// 自定义的恢复策略
		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context) {
			// 设置恢复策略，在发生异常时候将自动重试3次
			if (executionCount >= 3) {
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				return false;
			}
			HttpRequest request = (HttpRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				return true;
			}
			return false;
		}
	};

	// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		// 自定义响应处理
		public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String charset = EntityUtils.getContentCharSet(entity) == null ? LANGUAGE_GBK
						: EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}
	};

	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
	 * @param url 提交地址，例：http://www.g.cn
	 * @return String 响应消息，该地址的Html源码
	 */
	public static String get(String url) {
		return get(url, null, null, null);
	}

	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
	 * @param url 提交地址，例：http://www.g.cn
	 * @param referer 防采集处理，指定referer
	 * @return String 响应消息，该地址的Html源码
	 */
	public static String get(String url, String referer) {
		return get(url, null, null, referer);
	}

	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn
	 * @param url 提交地址，例：http://www.g.cn
	 * @param referer 防采集处理，指定referer
	 * @return String 响应消息，该地址的Html源码
	 */
	public static String get(String url, String charset, String referer) {
		return get(url, null, charset, referer);
	}

	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
	 * @param url 提交地址，例：http://www.g.cn
	 * @param params 查询参数集, 键/值对
	 * @return String 响应消息，该地址的Html源码
	 */
	public static String get(String url, Map<String, String> params) {
		return get(url, params, null, null);
	}

	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
	 * @param url 提交地址，例：http://www.g.cn
	 * @param params 查询参数集, 键/值对
	 * @param charset 参数提交编码集，例：utf-8
	 * @return String 响应消息，该地址的Html源码
	 */
	public static String get(String url, Map<String, String> params,
			String charset) {
		return get(url, params, charset, null);
	}

	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?param1=value1&param2=value2.....
	 * @param url 提交地址，例：http://www.g.cn
	 * @param params 查询参数集, 键/值对
	 * @param charset 参数提交编码集，例：utf-8
	 * @param referer 防采集处理，指定referer
	 * @return String 响应消息，该地址的Html源码
	 */
	public static String get(String url, Map<String, String> params,
			String charset, String referer) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		List<NameValuePair> qparams = getParamsList(params);
		if (qparams != null && qparams.size() > 0) {
			charset = (charset == null ? LANGUAGE_UTF : charset);
			String formatParams = URLEncodedUtils.format(qparams, charset);
			url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url
					.substring(0, url.indexOf("?") + 1) + formatParams);
		}
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		HttpGet hg = new HttpGet(url);
		if (StringUtils.isNotBlank(referer))
			hg.addHeader("Referer", referer);
		// 发送请求，得到响应
		String responseStr = null;
		try {
			responseStr = httpclient.execute(hg, responseHandler);
		} catch (ClientProtocolException e) {
			loger.error("客户端连接协议错误：" + e);
		} catch (IOException e) {
			loger.error("IO操作异常：" + e);
		} finally {
			abortConnection(hg, httpclient);
		}
		return responseStr;
	}

	/**
	 * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
	 * @param url 提交地址
	 * @return String 响应消息
	 */
	public static String post(String url) {
		return post(url, null, null, null);
	}

	/**
	 * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
	 * @param url 提交地址
	 * @param referer 防采集处理，指定referer
	 * @return String 响应消息
	 */
	public static String post(String url, String referer) {
		return post(url, null, null, referer);
	}

	/**
	 * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
	 * @param url 提交地址
	 * @param charset 参数提交编码集
	 * @param referer 防采集处理，指定referer
	 * @return String 响应消息
	 */
	public static String post(String url, String charset, String referer) {
		return post(url, null, charset, referer);
	}

	/**
	 * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn
	 * @param url 提交地址
	 * @param params 提交参数集, 键/值对
	 * @return String 响应消息
	 */
	public static String post(String url, Map<String, String> params) {
		return post(url, params, null, null);
	}

	/**
	 * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn....
	 * @param url 提交地址
	 * @param params 提交参数集, 键/值对
	 * @param charset 参数提交编码集
	 * @return String 响应消息
	 */
	public static String post(String url, Map<String, String> params,
			String charset) {
		return post(url, params, charset, null);
	}

	/**
	 * Post方式提交,URL中包含提交参数, 格式：http://www.g.cn....
	 * @param url 提交地址
	 * @param params 提交参数集, 键/值对
	 * @param charset 参数提交编码集
	 * @param referer 防采集处理，指定referer
	 * @return String 响应消息
	 */
	public static String post(String url, Map<String, String> params,
			String charset, String referer) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		// 创建HttpClient实例
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		HttpPost hp = new HttpPost(url);
		if (StringUtils.isNotBlank(referer))
			hp.addHeader("Referer", referer);
		if (null != params && params.size() > 0) {
			UrlEncodedFormEntity formEntity = null;
			try {
				if (StringUtils.isBlank(charset)) {
					formEntity = new UrlEncodedFormEntity(getParamsList(params));
				} else {
					formEntity = new UrlEncodedFormEntity(
							getParamsList(params), charset);
				}
			} catch (UnsupportedEncodingException e) {
				loger.error("不支持的编码集：" + e);
			}
			hp.setEntity(formEntity);
		}

		// 发送请求，得到响应
		String responseStr = null;
		try {
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (ClientProtocolException e) {
			loger.error("客户端连接协议错误：" + e);
		} catch (IOException e) {
			loger.error("IO操作异常：" + e);
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}

	/**
	 * 获取DefaultHttpClient实例
	 * @param charset 参数编码集, 可空
	 * @return DefaultHttpClient 对象
	 */
	private static DefaultHttpClient getDefaultHttpClient(final String charset) {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        HttpClient client = new DefaultHttpClient(httpParams);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		// 模拟浏览器，解决一些服务器程序只允许浏览器访问的问题
		httpclient
				.getParams()
				.setParameter(
						CoreProtocolPNames.USER_AGENT,
						"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13");
		httpclient.getParams().setParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		httpclient.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET,
				charset == null ? LANGUAGE_UTF : charset);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}

	/**
	 * 释放HttpClient连接
	 * @param hrb 请求对象
	 * @param httpclient DefaultHttpClient，client对象
	 */
	private static void abortConnection(final HttpRequestBase hrb,
			final HttpClient httpclient) {
		if (hrb != null) {
			hrb.abort();
		}
		if (httpclient != null) {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 将传入的键/值对参数转换为NameValuePair参数集
	 * @param paramsMap 参数集, 键/值对
	 * @return NameValuePair参数集
	 */
	private static List<NameValuePair> getParamsList(
			Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return params;
	}
}
