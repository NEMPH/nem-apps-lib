package io.nem.apps.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import io.nem.apps.service.NemAppsLibGlobals;




/**
 * The Class HttpClientUtils.
 */
public class HttpClientUtils {

	/** The default host. */
	public static String defaultHost = NemAppsLibGlobals.getNodeEndpoint().getBaseUrl().getHost();
	
	/** The default port. */
	public static String defaultPort = String.valueOf(NemAppsLibGlobals.getNodeEndpoint().getBaseUrl().getPort());
	
	/** The default ws port. */
	public static String defaultWsPort = "7778";
	
	

	/**
	 * Http Post.
	 *
	 * @param requestUrl the request url
	 * @param params the params
	 * @return the string
	 */
	public static String post(String requestUrl, String params) {
		
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = "http://" + defaultHost + ":" + defaultPort + requestUrl;
		HttpPost method = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			if (params != null) {
				StringEntity entity = new StringEntity(params);
				entity.setContentType(NemAppsLibGlobals.CONTENT_TYPE_TEXT_JSON);
				method.setEntity(entity);
			}
			response = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Http Get.
	 *
	 * @param requestUrl the request url
	 * @return the string
	 */
	public static String get(String requestUrl) {
		String result = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = "http://" + defaultHost + ":" + defaultPort + requestUrl;
		HttpGet method = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
