package io.nem.apps.service;

import java.util.HashMap;
import java.util.Map;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.primitive.Amount;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;
import io.nem.apps.factories.ConnectorFactory;
import io.nem.apps.model.NemNodeConfiguration;

/**
 * The Class Globals.
 */
public class NemAppsLibGlobals {

	public static final String CONTENT_TYPE_TEXT_JSON = "application/json";
	/** The Constant URL_NAMESPACE_MOSAIC_DEFINITION_PAGE. */
	public static final String URL_NAMESPACE_MOSAIC_DEFINITION_PAGE = "/namespace/mosaic/definition/page";

	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();

	/** The Constant NODE_ENDPOINT. */
	private static NodeEndpoint NODE_ENDPOINT;

	/** The fee calculator. */
	private static TransactionFeeCalculator feeCalculator = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);
	private static TransactionFeeCalculator feeCalculatorMultiSig = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);
	
	private static Map<String,NemNodeConfiguration> nemNodeConfigurations = new HashMap<String, NemNodeConfiguration>();

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
	 * @param feeCalculator
	 *            the new global transaction fee
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
	/**
	 * Sets the node endpoint.
	 *
	 * @param endpoint
	 *            the new node endpoint
	 */
	public static void setNodeEndpoint(NodeEndpoint endpoint) {
		NemAppsLibGlobals.NODE_ENDPOINT = endpoint;
	}
	
	public static Map<String, NemNodeConfiguration> getNemNodeConfigurations() {
		return nemNodeConfigurations;
	}

	public static void setNemNodeConfigurations(Map<String, NemNodeConfiguration> nemNodeConfigurations) {
		NemAppsLibGlobals.nemNodeConfigurations = nemNodeConfigurations;
	}



	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}