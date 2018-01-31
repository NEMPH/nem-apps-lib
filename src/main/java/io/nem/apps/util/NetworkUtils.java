package io.nem.apps.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MinimalField;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
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
	private static final Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

	/**
	 * Sends a request.
	 * 
	 * @param request
	 *            the request to send
	 * @return response the response.
	 */
	private static NemNetworkResponse send(HttpRequestBase request) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
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
				logger.error("HTTP connection failed with error code {}.", statusCode);
				response.setError(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	private static String getResponseContent(HttpResponse response) throws IOException {
		InputStream inputStream = response.getEntity().getContent();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
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
		post.setHeader("Content-Type", "application/json");
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
	
	public static NemNetworkResponse get(String url, Header... headers) {
		HttpGet get = new HttpGet(url);
		for(Header header:headers) {
			get.addHeader(header);
		}
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

	/**
	 * A generic method to execute any type of Http Request and constructs a
	 * response object
	 * 
	 * @param requestBase
	 *            the request that needs to be exeuted
	 * @return server response as <code>String</code>
	 */
	private static String executeRequest(HttpRequestBase requestBase) {
		String responseString = "";

		InputStream responseStream = null;
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		try {
			HttpResponse response = httpClient.execute(requestBase);
			if (response != null) {
				HttpEntity responseEntity = response.getEntity();

				if (responseEntity != null) {
					responseStream = responseEntity.getContent();
					if (responseStream != null) {
						BufferedReader br = new BufferedReader(new InputStreamReader(responseStream));
						String responseLine = br.readLine();
						String tempResponseString = "";
						while (responseLine != null) {
							tempResponseString = tempResponseString + responseLine
									+ System.getProperty("line.separator");
							responseLine = br.readLine();
						}
						br.close();
						if (tempResponseString.length() > 0) {
							responseString = tempResponseString;
						}
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (responseStream != null) {
				try {
					responseStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseString;
	}

	/**
	 * Method that builds the multi-part form data request
	 * 
	 * @param urlString
	 *            the urlString to which the file needs to be uploaded
	 * @param file
	 *            the actual file instance that needs to be uploaded
	 * @param fileName
	 *            name of the file, just to show how to add the usual form
	 *            parameters
	 * @param fileDescription
	 *            some description for the file, just to show how to add the
	 *            usual form parameters
	 * @return server response as <code>String</code>
	 */
	public static NemNetworkResponse postMultiPart(String urlString, File file) {
		HttpPost postRequest = new HttpPost(urlString);
		HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("file", file).build();
		postRequest.setEntity(httpEntity);
		return send(postRequest);
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
