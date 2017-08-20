package io.nem.spectro.api;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.ncc.NemRequestResult;
import org.nem.core.node.NisNodeInfo;
import org.nem.core.node.Node;
import org.nem.core.serialization.Deserializer;

import io.nem.spectro.service.Globals;



/**
 * The Class NodeApi.
 */
public class NodeApi {

	/**
	 * Gets the node info.
	 *
	 * @return the node info
	 */
	public static Node getNodeInfo() {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_NODE_INFO, null).join();
		return new Node(des);
	}
	
	/**
	 * Gets the node extended info.
	 *
	 * @return the node extended info
	 */
	public static NisNodeInfo getNodeExtendedInfo() {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_NODE_EXTENDED_INFO, null).join();
		return new NisNodeInfo(des);
	}
	
	/**
	 * Gets the nem node heart beat.
	 *
	 * @return the nem node heart beat
	 */
	public static NemRequestResult getNemNodeHeartBeat() {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_HEARTBEAT, null).join();
		return new NemRequestResult(des);
	}
}
