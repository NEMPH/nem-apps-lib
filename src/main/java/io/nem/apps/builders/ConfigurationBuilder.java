package io.nem.apps.builders;

import org.nem.core.model.NetworkInfos;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.node.NodeEndpoint;

import io.nem.apps.api.TransactionCallback;
import io.nem.apps.service.Globals;



/**
 * The Class ConfigurationBuilder.
 */
public class ConfigurationBuilder {

	/**
	 * Instantiates a new configuration builder.
	 */
	private ConfigurationBuilder() {

	}
	
	public static INodeProtocol init() {
		return new ConfigurationBuilder.Builder();
	}

	/**
	 * Node network name.
	 *
	 * @param networkName
	 *            the network name
	 * @return the i node protocol
	 */
	public static INodeProtocol nodeNetworkName(String networkName) {
		return new ConfigurationBuilder.Builder(networkName);
	}

	/**
	 * The Interface INodeProtocol.
	 */
	public interface INodeProtocol {

		/**
		 * Node network protocol.
		 *
		 * @param protocol
		 *            the protocol
		 * @return the i node uri
		 */
		INodeUri nodeNetworkProtocol(String protocol);

		/**
		 * Node endpoint.
		 *
		 * @param nodeEndpoint
		 *            the node endpoint
		 * @return the i build
		 */
		IBuild nodeEndpoint(NodeEndpoint nodeEndpoint);
	}

	/**
	 * The Interface INodeUri.
	 */
	public interface INodeUri {

		/**
		 * Node network uri.
		 *
		 * @param uri
		 *            the uri
		 * @return the i node port
		 */
		INodePort nodeNetworkUri(String uri);

	}

	/**
	 * The Interface INodePort.
	 */
	public interface INodePort {

		/**
		 * Node network port.
		 *
		 * @param port
		 *            the port
		 * @return the i build
		 */
		IBuild nodeNetworkPort(String port);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Transaction fee.
		 *
		 * @param feeCalculator the fee calculator
		 */
		IBuild transactionFee(TransactionFeeCalculator feeCalculator);

		/**
		 * Transaction multisig fee.
		 *
		 * @param feeCalculator the fee calculator
		 */
		IBuild transactionMultisigFee(TransactionFeeCalculator feeCalculator);

		/**
		 * Transaction before handler.
		 *
		 * @param cb
		 *            the cb
		 */
		IBuild transactionBeforeHandler(TransactionCallback cb);

		/**
		 * Transaction after handler.
		 *
		 * @param cb
		 *            the cb
		 */
		IBuild transactionAfterHandler(TransactionCallback cb);

		/**
		 * Setup.
		 */
		void setup();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements INodeProtocol, INodeUri, INodePort, IBuild {

		/** The node network protocol. */
		private String nodeNetworkProtocol;

		/** The node network port. */
		private String nodeNetworkPort;

		/** The node network uri. */
		private String nodeNetworkUri;

		/** The node endpoint. */
		private NodeEndpoint nodeEndpoint;

		/**
		 * Instantiates a new builder.
		 *
		 * @param networkName
		 *            the network name
		 */
		public Builder(String networkName) {
			if(networkName.equals("mainnet")) {
				NetworkInfos.setDefault(NetworkInfos.getMainNetworkInfo());
			}else if(networkName.equals("testnet")) {
				NetworkInfos.setDefault(NetworkInfos.getTestNetworkInfo());
			}else if(networkName.equals("mijinnet")) {
				NetworkInfos.setDefault(NetworkInfos.getMijinNetworkInfo());
			}
		}
		
		public Builder() {}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.ConfigurationBuilder.INodeProtocol#
		 * nodeNetworkProtocol(java.lang.String)
		 */
		@Override
		public INodeUri nodeNetworkProtocol(String protocol) {
			this.nodeNetworkProtocol = protocol;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.ConfigurationBuilder.INodePort#
		 * nodeNetworkPort(java.lang.String)
		 */
		@Override
		public IBuild nodeNetworkPort(String port) {
			this.nodeNetworkPort = port;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.spectro.builders.ConfigurationBuilder.INodeUri#nodeNetworkUri(
		 * java.lang.String)
		 */
		@Override
		public INodePort nodeNetworkUri(String uri) {
			this.nodeNetworkUri = uri;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.ConfigurationBuilder.IBuild#
		 * transactionAfterHandler(io.nem.spectro.api.TransactionCallback)
		 */
		@Override
		public IBuild transactionAfterHandler(TransactionCallback cb) {
			// TODO Auto-generated method stub
			return this;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.ConfigurationBuilder.IBuild#
		 * transactionBeforeHandler(io.nem.spectro.api.TransactionCallback)
		 */
		@Override
		public IBuild transactionBeforeHandler(TransactionCallback cb) {
			// TODO Auto-generated method stub
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.ConfigurationBuilder.IBuild#setup()
		 */
		@Override
		public void setup() {
			if(Globals.getNodeEndpoint() != null) return;
			if (nodeEndpoint == null) {
				nodeEndpoint = new NodeEndpoint(this.nodeNetworkProtocol, this.nodeNetworkUri,
						Integer.valueOf(this.nodeNetworkPort));
			}
			Globals.setNodeEndpoint(nodeEndpoint);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.ConfigurationBuilder.INodeProtocol#
		 * nodeEndpoint(org.nem.core.node.NodeEndpoint)
		 */
		@Override
		public IBuild nodeEndpoint(NodeEndpoint nodeEndpoint) {
			this.nodeEndpoint = nodeEndpoint;
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.spectro.builders.ConfigurationBuilder.IBuild#transactionFee(org.nem.core.model.TransactionFeeCalculator)
		 */
		@Override
		public IBuild transactionFee(TransactionFeeCalculator feeCalculator) {
			Globals.setGlobalTransactionFee(feeCalculator);
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.spectro.builders.ConfigurationBuilder.IBuild#transactionMultisigFee(org.nem.core.model.TransactionFeeCalculator)
		 */
		@Override
		public IBuild transactionMultisigFee(TransactionFeeCalculator feeCalculator) {
			Globals.setGlobalMultisigTransactionFee(feeCalculator);
			return this;
		}

	}

}
