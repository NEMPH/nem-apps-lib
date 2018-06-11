/*
 * 
 */
package io.nem.apps.api;
import java.util.regex.Pattern;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;
import io.nem.apps.service.NemAppsLibGlobals;
import io.nem.apps.util.NemNetworkResponse;
import io.nem.apps.util.NetworkUtils;



/**
 * The Class ValidationApi.
 */
public class ValidationApi {

	/**
	 * Validate address.
	 *
	 * @param address            the address
	 * @return true, if is address valid
	 */
	public static boolean isAddressValid(String address) {
		NemNetworkResponse response = NetworkUtils
				.get(NemAppsLibGlobals.getNodeEndpoint() + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address=" + address);

		return !response.isError();
	}
	
	/**
	 * Checks if is address pattern valid.
	 *
	 * @param address the address
	 * @return true, if is address pattern valid
	 */
	public static boolean isAddressPatternValid(String address) {
		String unDash = address.replace("-", "");
		Pattern pattern = Pattern.compile("[nN|mM|tT]{1,1}[a-zA-Z0-9]{5,5}[a-zA-Z0-9]{6,6}[a-zA-Z0-9]{6,6}[a-zA-Z0-9]{6,6}[a-zA-Z0-9]{6,6}[a-zA-Z0-9]{6,6}[a-zA-Z0-9]{4,4}");
		return pattern.matcher(unDash).matches();
	}
	
	/**
	 * Checks if is address valid.
	 *
	 * @param address the address
	 * @param nodeEndpoint the node endpoint
	 * @return true, if is address valid
	 */
	public static boolean isAddressValid(String address,NodeEndpoint nodeEndpoint) {
		NemNetworkResponse response = NetworkUtils
				.get(nodeEndpoint + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address=" + address);

		return !response.isError();
	}
	
	/**
	 * Checks if is address valid.
	 *
	 * @param address the address
	 * @param protocol the protocol
	 * @param host the host
	 * @param port the port
	 * @return true, if is address valid
	 */
	public static boolean isAddressValid(String address,String protocol, String host, int port) {
		NodeEndpoint nodeEndpoint = new NodeEndpoint(protocol, host, port);
		NemNetworkResponse response = NetworkUtils
				.get(nodeEndpoint + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address=" + address);

		return !response.isError();
	}
}
