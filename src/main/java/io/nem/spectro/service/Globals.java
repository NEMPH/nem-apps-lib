package io.nem.spectro.service;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;

import io.nem.spectro.factories.ConnectorFactory;
import io.nem.spectro.util.AppPropertiesUtil;

/**
 * The Class Globals.
 */
public class Globals {

	public static final String NETWORK_NAME = "mijinnet"; // testnet, mainnet,
															// mijinnet
	public static final long NETWORK_TYPE = 2; // 0:testnet, 1:mainnet, 
												// 2:mijinnet
	public static final long NEMSISTIME = 1427587585; // the first block time
	public static final long MICRONEMS_IN_NEM = 1000000;
	public static final String WEBSOCKET_PORT = "7778";

	public static final String URL_INIT_TRANSACTION = "/transaction/prepare-announce";
	public static final String URL_ACCOUNT_GET = "/account/get";
	public static final String URL_ACCOUNT_TRANSFERS_INCOMING = "/account/transfers/incoming";
	public static final String URL_ACCOUNT_TRANSFERS_OUTGOING = "/account/transfers/outgoing";
	public static final String URL_ACCOUNT_GET_FROMPUBLICKEY = "/account/get/from-public-key";
	public static final String URL_ACCOUNT_UNCONFIRMEDTRANSACTIONS = "/account/unconfirmedTransactions";
	public static final String URL_ACCOUNT_MOSAIC_OWNED = "/account/mosaic/owned";
	public static final String URL_TRANSACTION_ANNOUNCE = "/transaction/announce";
	public static final String URL_NAMESPACE_MOSAIC_DEFINITION_PAGE = "/namespace/mosaic/definition/page";

	public static final String URL_WS_W_MESSAGES = "/w/messages";
	public static final String URL_WS_W_API_ACCOUNT_SUBSCRIBE = "/w/api/account/subscribe";
	public static final String URL_WS_TRANSACTIONS = "/transactions";
	public static final String URL_WS_UNCONFIRMED = "/unconfirmed";

	public static final String CONTENT_TYPE_TEXT_JSON = "application/json";

	public static final String HELPER_FILE_INIT_TRANSACTION = "initTransaction_parameter.txt";
	public static final String HELPER_FILE_INIT_MULTISIG_TRANSACTION = "initMultisigTransaction_parameter.txt";
	public static final String HELPER_FILE_COSIGN_MULTISIG_TRANSACTION = "cosignMultisigTransaction_parameter.txt";
	public static final String HELPER_FILE_MONITOR_INCOMING_TRANSACTION = "monitorIncomingTransaction_parameter.txt";
	public static final String HELPER_FILE_MONITOR_MULTISIG_TRANSACTION = "monitorMultisigTransaction_parameter.txt";

	public static final long TX_TYPE_INIT_MULTISIG = 4100;
	public static final long TX_TYPE_COSIGN_MULTISIG = 4098;

	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();

	/** The Constant NODE_ENDPOINT. */
	private static NodeEndpoint NODE_ENDPOINT;
	
	public static NodeEndpoint getNodeEndpoint() {
		return NODE_ENDPOINT;
	}
	//= new NodeEndpoint(
//			AppPropertiesUtil.getProperty("node.endpoint.protocol"), AppPropertiesUtil.getProperty("node.endpoint.uri"),
//			Integer.valueOf(AppPropertiesUtil.getProperty("node.endpoint.port")));
	
	public static void setNodeEndpoint(NodeEndpoint endpoint) {
		Globals.NODE_ENDPOINT = endpoint;
	}
	
	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}