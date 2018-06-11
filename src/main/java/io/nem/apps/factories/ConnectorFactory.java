package io.nem.apps.factories;

import org.nem.core.connect.ErrorResponseDeserializerUnion;
import org.nem.core.connect.HttpMethodClient;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.Account;
import org.nem.core.node.ApiId;





/**
 * A factory for creating Connector objects.
 */
public class ConnectorFactory {

	/** The Constant CLIENT. */
	private static final HttpMethodClient<ErrorResponseDeserializerUnion> CLIENT = createHttpMethodClient();

	/**
	 * Creates a new Connector object.
	 *
	 * @return the default async nem connector
	 */
	public static DefaultAsyncNemConnector<ApiId> createConnector() {
		final DefaultAsyncNemConnector<ApiId> connector = new DefaultAsyncNemConnector<>(CLIENT, r -> {
			throw new RuntimeException(r.toString());
		});
		connector.setAccountLookup(Account::new);
		return connector;
	}

	/**
	 * Creates a new Connector object.
	 *
	 * @return the http method client< error response deserializer union>
	 */
	private static HttpMethodClient<ErrorResponseDeserializerUnion> createHttpMethodClient() {
		final int connectionTimeout = 4000;
		final int socketTimeout = 10000;
		final int requestTimeout = 30000;
		return new HttpMethodClient<>(connectionTimeout, socketTimeout, requestTimeout);
	}
}
