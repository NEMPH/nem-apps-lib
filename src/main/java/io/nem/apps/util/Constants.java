package io.nem.apps.util;
 



/**
 * The Class Constants.
 */
public class Constants {

	/** The Constant NETWORK_NAME. */
	public static final String NETWORK_NAME = "mijinnet"; //testnet, mainnet, mijinnet
	
	/** The Constant NETWORK_TYPE. */
	public static final long NETWORK_TYPE = 2; //0:testnet, 1:mainnet, 2:mijinnet
	
	/** The Constant NEMSISTIME. */
	public static final long NEMSISTIME = 1427587585; //the first block time
	
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
	
	/** The Constant TX_TYPE_INIT_MULTISIG. */
	public static final long TX_TYPE_INIT_MULTISIG = 4100;
	
	/** The Constant TX_TYPE_COSIGN_MULTISIG. */
	public static final long TX_TYPE_COSIGN_MULTISIG = 4098;
}
