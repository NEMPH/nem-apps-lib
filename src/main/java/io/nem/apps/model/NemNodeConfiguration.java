package io.nem.apps.model;

import java.io.Serializable;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.node.NodeEndpoint;


/**
 * The Class NemNodeConfiguration.
 */
public class NemNodeConfiguration implements Serializable {

	/** The nem network. */
	private String nemNetwork;

	/** The node network protocol. */
	private String nodeNetworkProtocol;

	/** The node network port. */
	private String nodeNetworkPort;

	/** The node network uri. */
	private String nodeNetworkUri;

	/** The node endpoint. */
	private NodeEndpoint nodeEndpoint;

	/** The transaction fee calculator. */
	private TransactionFeeCalculator transactionFeeCalculator;
	
	/** The transaction multisig fee calculator. */
	private TransactionFeeCalculator transactionMultisigFeeCalculator;

	/**
	 * Gets the nem network.
	 *
	 * @return the nem network
	 */
	public String getNemNetwork() {
		return nemNetwork;
	}

	/**
	 * Sets the nem network.
	 *
	 * @param nemNetwork the new nem network
	 */
	public void setNemNetwork(String nemNetwork) {
		this.nemNetwork = nemNetwork;
	}

	/**
	 * Gets the node network protocol.
	 *
	 * @return the node network protocol
	 */
	public String getNodeNetworkProtocol() {
		return nodeNetworkProtocol;
	}

	/**
	 * Sets the node network protocol.
	 *
	 * @param nodeNetworkProtocol the new node network protocol
	 */
	public void setNodeNetworkProtocol(String nodeNetworkProtocol) {
		this.nodeNetworkProtocol = nodeNetworkProtocol;
	}

	/**
	 * Gets the node network port.
	 *
	 * @return the node network port
	 */
	public String getNodeNetworkPort() {
		return nodeNetworkPort;
	}

	/**
	 * Sets the node network port.
	 *
	 * @param nodeNetworkPort the new node network port
	 */
	public void setNodeNetworkPort(String nodeNetworkPort) {
		this.nodeNetworkPort = nodeNetworkPort;
	}

	/**
	 * Gets the node network uri.
	 *
	 * @return the node network uri
	 */
	public String getNodeNetworkUri() {
		return nodeNetworkUri;
	}

	/**
	 * Sets the node network uri.
	 *
	 * @param nodeNetworkUri the new node network uri
	 */
	public void setNodeNetworkUri(String nodeNetworkUri) {
		this.nodeNetworkUri = nodeNetworkUri;
	}

	/**
	 * Gets the node endpoint.
	 *
	 * @return the node endpoint
	 */
	public NodeEndpoint getNodeEndpoint() {
		return nodeEndpoint;
	}

	/**
	 * Sets the node endpoint.
	 *
	 * @param nodeEndpoint the new node endpoint
	 */
	public void setNodeEndpoint(NodeEndpoint nodeEndpoint) {
		this.nodeEndpoint = nodeEndpoint;
	}

	/**
	 * Gets the transaction fee calculator.
	 *
	 * @return the transaction fee calculator
	 */
	public TransactionFeeCalculator getTransactionFeeCalculator() {
		return transactionFeeCalculator;
	}

	/**
	 * Sets the transaction fee calculator.
	 *
	 * @param transactionFeeCalculator the new transaction fee calculator
	 */
	public void setTransactionFeeCalculator(TransactionFeeCalculator transactionFeeCalculator) {
		this.transactionFeeCalculator = transactionFeeCalculator;
	}

	/**
	 * Gets the transaction multisig fee calculator.
	 *
	 * @return the transaction multisig fee calculator
	 */
	public TransactionFeeCalculator getTransactionMultisigFeeCalculator() {
		return transactionMultisigFeeCalculator;
	}

	/**
	 * Sets the transaction multisig fee calculator.
	 *
	 * @param transactionMultisigFeeCalculator the new transaction multisig fee calculator
	 */
	public void setTransactionMultisigFeeCalculator(TransactionFeeCalculator transactionMultisigFeeCalculator) {
		this.transactionMultisigFeeCalculator = transactionMultisigFeeCalculator;
	}

}
