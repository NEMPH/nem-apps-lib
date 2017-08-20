package io.nem.spectro.api;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.node.NisNodeInfo;
import org.nem.core.node.Node;
import org.nem.core.serialization.Deserializer;

import io.nem.spectro.service.Globals;

public class NodeApi {

	public static Node getNodeInfo() {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.NODE_ENDPOINT, NisApiId.NIS_REST_NODE_INFO, null).join();
		return new Node(des);
	}
	
	public static NisNodeInfo getNodeExtendedInfo() {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.NODE_ENDPOINT, NisApiId.NIS_REST_NODE_EXTENDED_INFO, null).join();
		return new NisNodeInfo(des);
	}
}
