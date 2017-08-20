package io.nem.spectro.builders;

import org.nem.core.model.NetworkInfos;
import org.nem.core.node.NodeEndpoint;

import io.nem.spectro.api.TransactionCallback;
import io.nem.spectro.service.Globals;

public class ConfigurationBuilder {

	private ConfigurationBuilder() {
		
	}

	public static INodeProtocol nodeNetworkName(String networkName) {
		return new ConfigurationBuilder.Builder(networkName);
	}

	public interface INodeProtocol {
		INodeUri nodeNetworkProtocol(String protocol);

		IBuild nodeEndpoint(NodeEndpoint nodeEndpoint);
	}

	public interface INodeUri {
		INodePort nodeNetworkUri(String uri);

	}

	public interface INodePort {

		IBuild nodeNetworkPort(String port);
	}

	public interface IBuild {
		void transactionBeforeHandler(TransactionCallback cb);

		void transactionAfterHandler(TransactionCallback cb);

		void setup();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements INodeProtocol, INodeUri, INodePort, IBuild {

		private String nodeNetworkProtocol;
		private String nodeNetworkPort;
		private String nodeNetworkUri;
		private NodeEndpoint nodeEndpoint;

		public Builder(String networkName) {
			NetworkInfos.setDefault(NetworkInfos.fromFriendlyName(networkName));
		}

		@Override
		public INodeUri nodeNetworkProtocol(String protocol) {
			this.nodeNetworkPort = protocol;
			return this;
		}

		@Override
		public IBuild nodeNetworkPort(String port) {
			this.nodeNetworkPort = port;
			return this;
		}

		@Override
		public INodePort nodeNetworkUri(String uri) {
			this.nodeNetworkUri = uri;
			return this;
		}

		@Override
		public void transactionAfterHandler(TransactionCallback cb) {
			// TODO Auto-generated method stub

		}

		@Override
		public void transactionBeforeHandler(TransactionCallback cb) {
			// TODO Auto-generated method stub
		}

		@Override
		public void setup() {
			if (nodeEndpoint == null) {
				nodeEndpoint = new NodeEndpoint(this.nodeNetworkProtocol, this.nodeNetworkUri,
						Integer.valueOf(this.nodeNetworkPort));
			}
			Globals.setNodeEndpoint(nodeEndpoint);
		}

		@Override
		public IBuild nodeEndpoint(NodeEndpoint nodeEndpoint) {
			this.nodeEndpoint = nodeEndpoint;
			return this;
		}

	}

}
