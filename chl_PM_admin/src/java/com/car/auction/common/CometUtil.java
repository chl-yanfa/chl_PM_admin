package com.car.auction.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author liumin
 * 
 */
@SuppressWarnings("deprecation")
public class CometUtil {

	public final static String SEND = "/send";
	public final static String CONN = "/conn";
	public final static String CHARSET = "UTF-8";
	public final static Integer TIMEOUT = 30000;
	public final static String TYPE = "type";
	public final static String CONTENT = "content";
	private static Log log = LogFactory.getLog(CometUtil.class);
	
	public static String send(String url,String type,String content) {
		PostMethod post = new PostMethod(url + SEND);  
		NameValuePair type_tmp = new NameValuePair(TYPE, type);
		NameValuePair content_tmp = new NameValuePair(CONTENT, content);
		post.setRequestBody(new NameValuePair[] { type_tmp, content_tmp });
		return sending(post);
	}

	public static String sending(HttpMethod httpMethod) {
		log.info("HttpMethod start.....");
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset(CHARSET);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		client.getHttpConnectionManager().getParams()
				.setParameter("http.socket.timeout", TIMEOUT);
		httpMethod.getParams().setParameter("http.socket.timeout", TIMEOUT);
		httpMethod.getParams().setContentCharset(CHARSET);
		String status = null;
		try {
			int httpStatusCode = client.executeMethod(httpMethod);
			if (httpStatusCode == HttpStatus.SC_OK) {
				log.debug("推送成功！");
				status = RtnMsgConstants.RETURN_CODE_SUCCESS;
			} else {
				log.debug("方法执行过程中出现了错误");
				status = RtnMsgConstants.RETURN_CODE_SYSTEM_FAIL;
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		httpMethod.releaseConnection();// 释放连接
		log.info("HttpMethod ended.....");
		return status;
	}

	public static String conn(String url0, HttpServletRequest request,
			HttpServletResponse response) throws ClientProtocolException,
			IOException {
		String params = request.getQueryString();
		StringBuffer url = new StringBuffer(url0);
		url.append(CONN);
		if(params!=null){
			url.append("?");
			url.append(params);
		}
		@SuppressWarnings({ "resource" })
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url.toString());
		HttpResponse transResp = httpClient.execute(httpGet);
		int status = transResp.getStatusLine().getStatusCode();
		
		response.setStatus(status);
		String resp = EntityUtils.toString(transResp.getEntity());
		httpGet.abort();
		httpGet.releaseConnection();
		return resp;
	}

	public static void main(String args[]) {
	}
}
