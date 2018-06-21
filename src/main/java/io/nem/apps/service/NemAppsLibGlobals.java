package io.nem.apps.service;

import java.util.HashMap;
import java.util.Map;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicDefinition;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;
import io.nem.apps.api.AccountApi;
import io.nem.apps.api.NamespaceMosaicsApi;
import io.nem.apps.factories.ConnectorFactory;
import io.nem.apps.model.NemNodeConfiguration;


/**
 * The Class Globals.
 */
public class NemAppsLibGlobals {

	/** The Constant CONTENT_TYPE_TEXT_JSON. */
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
	
	/** The fee calculator multi sig. */
	private static TransactionFeeCalculator feeCalculatorMultiSig = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);
	
	/** The nem node configurations. */
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

	/**
	 * Sets the global multisig transaction fee.
	 *
	 * @param feeCalculator the new global multisig transaction fee
	 */
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

	/**
	 * Gets the global multisig transaction fee.
	 *
	 * @return the global multisig transaction fee
	 */
	public static TransactionFeeCalculator getGlobalMultisigTransactionFee() {
		return feeCalculatorMultiSig;
	}
	
	public static TransactionFeeCalculator getGlobalTransactionFee(String address) {
		return new FeeUnitAwareTransactionFeeCalculator(
				Amount.fromMicroNem(50_000L), mosaicInfoLookupForSender(address));
	}

	/**
	 * Gets the global multisig transaction fee.
	 *
	 * @return the global multisig transaction fee
	 */
	public static TransactionFeeCalculator getGlobalMultisigTransactionFee(String address) {
		return new FeeUnitAwareTransactionFeeCalculator(
				Amount.fromMicroNem(50_000L), mosaicInfoLookupForSender(address));
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
	
	/**
	 * Gets the nem node configurations.
	 *
	 * @return the nem node configurations
	 */
	public static Map<String, NemNodeConfiguration> getNemNodeConfigurations() {
		return nemNodeConfigurations;
	}

	/**
	 * Sets the nem node configurations.
	 *
	 * @param nemNodeConfigurations the nem node configurations
	 */
	public static void setNemNodeConfigurations(Map<String, NemNodeConfiguration> nemNodeConfigurations) {
		NemAppsLibGlobals.nemNodeConfigurations = nemNodeConfigurations;
	}



	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
	
	private static MosaicFeeInformationLookup mosaicInfoLookupForSender(final String sender) {

        return id -> {
            final String namespaceToLookup = id.getNamespaceId().getRoot().toString();
            final String mosaicNameToLookup = id.getName();

            if (namespaceToLookup.equals("nem") && mosaicNameToLookup.equals("xem"))
                return null;

        	try {
				for (Mosaic mosaic : AccountApi.getAccountOwnedMosaic(sender)) {
					final String namespace = mosaic.getMosaicId().getNamespaceId().getRoot().toString();
					final String mosaicName = mosaic.getMosaicId().getName();

					if (namespace.equals(namespaceToLookup) && mosaicName.equals(mosaicNameToLookup)) {
						MosaicDefinition mosaicDefinition = NamespaceMosaicsApi.getMosaicInformation(namespace,mosaicName);
						return new MosaicFeeInformation(
						        Supply.fromValue(mosaicDefinition.getProperties().getInitialSupply()),
                                mosaicDefinition.getProperties().getDivisibility());
					}
				}
                //	error - meaning the sender doesn't own the mosaics attached.
                throw new RuntimeException("Mosaic not found on Sender");

			} catch (Exception e) {
				throw new RuntimeException("Exception occured while getting Mosaic Information."  + e.getMessage());
			}
        	

        };
    }
}