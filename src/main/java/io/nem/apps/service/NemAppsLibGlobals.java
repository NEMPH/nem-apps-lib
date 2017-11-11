package io.nem.apps.service;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.primitive.Amount;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;

import io.nem.apps.factories.ConnectorFactory;
import io.nem.apps.fee.TransactionFeeCalculatorAfterForkForApp;
import io.nem.apps.util.AppPropertiesUtil;



/**
 * The Class Globals.
 */
public class NemAppsLibGlobals {

	/** The Constant NETWORK_NAME. */
	public static final String NETWORK_NAME = "mijinnet"; // testnet, mainnet,
															
															/** The Constant NETWORK_TYPE. */
															// mijinnet
	public static final long NETWORK_TYPE = 2; // 0:testnet, 1:mainnet,
												
												/** The Constant NEMSISTIME. */
												// 2:mijinnet
	public static final long NEMSISTIME = 1427587585; // the first block time
	
	/** The Constant MICRONEMS_IN_NEM. */
	public static final long MICRONEMS_IN_NEM = 1000000;
	
	/** The Constant WEBSOCKET_PORT. */
	public static final String WEBSOCKET_PORT = "7778";

	/** The Constant URL_INIT_TRANSACTION. */
	public static final String URL_INIT_TRANSACTION = "/transaction/prepare-announce";
	
	/** The Constant URL_ACCOUNT_GET. */
	public static final String URL_ACCOUNT_GET = "/account/get";
	
	/** The Constant URL_ACCOUNT_TRANSFERS_INCOMING. */
	public static final String URL_ACCOUNT_TRANSFERS_INCOMING = "/account/transfers/incoming";
	
	/** The Constant URL_ACCOUNT_TRANSFERS_OUTGOING. */
	public static final String URL_ACCOUNT_TRANSFERS_OUTGOING = "/account/transfers/outgoing";
	
	/** The Constant URL_ACCOUNT_GET_FROMPUBLICKEY. */
	public static final String URL_ACCOUNT_GET_FROMPUBLICKEY = "/account/get/from-public-key";
	
	/** The Constant URL_ACCOUNT_UNCONFIRMEDTRANSACTIONS. */
	public static final String URL_ACCOUNT_UNCONFIRMEDTRANSACTIONS = "/account/unconfirmedTransactions";
	
	/** The Constant URL_ACCOUNT_MOSAIC_OWNED. */
	public static final String URL_ACCOUNT_MOSAIC_OWNED = "/account/mosaic/owned";
	
	/** The Constant URL_TRANSACTION_ANNOUNCE. */
	public static final String URL_TRANSACTION_ANNOUNCE = "/transaction/announce";
	
	/** The Constant URL_NAMESPACE_MOSAIC_DEFINITION_PAGE. */
	public static final String URL_NAMESPACE_MOSAIC_DEFINITION_PAGE = "/namespace/mosaic/definition/page";

	/** The Constant URL_WS_W_MESSAGES. */
	public static final String URL_WS_W_MESSAGES = "/w/messages";
	
	/** The Constant URL_WS_W_API_ACCOUNT_SUBSCRIBE. */
	public static final String URL_WS_W_API_ACCOUNT_SUBSCRIBE = "/w/api/account/subscribe";
	
	/** The Constant URL_WS_TRANSACTIONS. */
	public static final String URL_WS_TRANSACTIONS = "/transactions";
	
	/** The Constant URL_WS_UNCONFIRMED. */
	public static final String URL_WS_UNCONFIRMED = "/unconfirmed";

	/** The Constant CONTENT_TYPE_TEXT_JSON. */
	public static final String CONTENT_TYPE_TEXT_JSON = "application/json";

	/** The Constant HELPER_FILE_INIT_TRANSACTION. */
	public static final String HELPER_FILE_INIT_TRANSACTION = "initTransaction_parameter.txt";
	
	/** The Constant HELPER_FILE_INIT_MULTISIG_TRANSACTION. */
	public static final String HELPER_FILE_INIT_MULTISIG_TRANSACTION = "initMultisigTransaction_parameter.txt";
	
	/** The Constant HELPER_FILE_COSIGN_MULTISIG_TRANSACTION. */
	public static final String HELPER_FILE_COSIGN_MULTISIG_TRANSACTION = "cosignMultisigTransaction_parameter.txt";
	
	/** The Constant HELPER_FILE_MONITOR_INCOMING_TRANSACTION. */
	public static final String HELPER_FILE_MONITOR_INCOMING_TRANSACTION = "monitorIncomingTransaction_parameter.txt";
	
	/** The Constant HELPER_FILE_MONITOR_MULTISIG_TRANSACTION. */
	public static final String HELPER_FILE_MONITOR_MULTISIG_TRANSACTION = "monitorMultisigTransaction_parameter.txt";

	/** The Constant TX_TYPE_INIT_MULTISIG. */
	public static final long TX_TYPE_INIT_MULTISIG = 4100;
	
	/** The Constant TX_TYPE_COSIGN_MULTISIG. */
	public static final long TX_TYPE_COSIGN_MULTISIG = 4098;

	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();

	/** The Constant NODE_ENDPOINT. */
	private static NodeEndpoint NODE_ENDPOINT;
	
	/** The fee calculator. */
	private static TransactionFeeCalculator feeCalculator = new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), null);
	private static TransactionFeeCalculator feeCalculatorMultiSig = new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), null);
	/**
	 * Gets the node endpoint.
	 *
	 * @return the node endpoint
	 */
	public static NodeEndpoint getNodeEndpoint() {
		return NODE_ENDPOINT;
	}

	/**
	 * Sets the global transaction fee.
	 *
	 * @param feeCalculator the new global transaction fee
	 */
	public static void setGlobalTransactionFee(TransactionFeeCalculator feeCalculator) {
		NemAppsLibGlobals.feeCalculator = feeCalculator;
	}
	
	public static void setGlobalMultisigTransactionFee(TransactionFeeCalculator feeCalculator) {
		NemAppsLibGlobals.feeCalculatorMultiSig = feeCalculator;
	}

	/**
	 * Gets the global transaction fee.
	 *
	 * @return the global transaction fee
	 */
	public static TransactionFeeCalculator getGlobalTransactionFee() {
		return feeCalculator;
	}
	
	public static TransactionFeeCalculator getGlobalMultisigTransactionFee() {
		return feeCalculatorMultiSig;
	}
	// = new NodeEndpoint(
	// AppPropertiesUtil.getProperty("node.endpoint.protocol"),
	// AppPropertiesUtil.getProperty("node.endpoint.uri"),
	// Integer.valueOf(AppPropertiesUtil.getProperty("node.endpoint.port")));

	/**
	 * Sets the node endpoint.
	 *
	 * @param endpoint the new node endpoint
	 */
	public static void setNodeEndpoint(NodeEndpoint endpoint) {
		NemAppsLibGlobals.NODE_ENDPOINT = endpoint;
	}

	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}