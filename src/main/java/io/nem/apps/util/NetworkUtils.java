package io.nem.apps.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * The Class NetworkUtils.
 */
public class NetworkUtils {

	/**
	 * The logger.
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(NetworkUtils.class);

	/**
	 * Sends a request.
	 * 
	 * @param request
	 *            the request to send
	 * @return response the response.
	 */
	private static NemNetworkResponse send(HttpRequestBase request) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
		logger.debug("HTTP request: {}", request.getRequestLine().toString());
		HttpResponse httpResponse = null;
		String responseContent = null;
		NemNetworkResponse response = new NemNetworkResponse();

		try {

			httpResponse = httpClient.execute(request);
			responseContent = getResponseContent(httpResponse);

			// Does some logging.
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			logger.debug("HTTP Status Code: {}", statusCode);
			// If the status code is > 400 there was an error.
			if (statusCode >= 400) {
				logger.error("HTTP connection failed with error code {}.",
						statusCode);
				response.setError(true);
			}

		} catch (Exception e) {
			logger.error("Error during HTTP connection: ", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.error("Error while closing HTTP connection: ", e);
			}
		}

		response.setResponse(responseContent);
		return response;
	}

	/**
	 * Utility method that converts an HttpResponse to a String.
	 *
	 * @param response
	 *            the response to convert.
	 * @return a String with the response content.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static String getResponseContent(HttpResponse response)
			throws IOException {
		InputStream inputStream = response.getEntity().getContent();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				inputStream);
		InputStreamReader inputStreamReader = new InputStreamReader(
				bufferedInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		StringBuilder builder = new StringBuilder();
		String output = null;
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}
		String responseContent = builder.toString();
		logger.debug("Raw response: {}", responseContent);
		return responseContent;
	}

	/**
	 * Utility to send a POST request.
	 * 
	 * @param url
	 *            the url we need to send the post request to.
	 * @param entity
	 *            the entity that containts the object we need to pass as part
	 *            of the post request.
	 * @return the post response.
	 */
	public static NemNetworkResponse post(String url, StringEntity entity) {
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type","application/json");
		post.setEntity(entity);
		return send(post);
	}

	/**
	 * Utility to send a GET request.
	 * 
	 * @param url
	 *            the url we need to send the get request to.
	 * @return the get response.
	 */
	public static NemNetworkResponse get(String url) {
		HttpGet get = new HttpGet(url);
		return send(get);
	}

	/**
	 * Sends a DELETE request with a body.
	 * 
	 * @param url
	 *            the url where to send the DELETE request.
	 * @param entity
	 *            the entity to DELETE.
	 * @return the delete response.
	 */
	public static NemNetworkResponse delete(String url, StringEntity entity) {
		HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
		delete.setEntity(entity);
		return send(delete);
	}
	
	/**
	 * Sends a DELETE request.
	 * 
	 * @param url
	 *            the url where to send the DELETE request.
	 * @return the delete response.
	 */
	public static NemNetworkResponse delete(String url) {
		HttpDelete delete = new HttpDelete(url);
		return send(delete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NetworkUtils []";
	}

}
