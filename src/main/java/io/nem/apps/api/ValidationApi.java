package io.nem.apps.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.node.NodeEndpoint;

import io.nem.apps.service.Globals;
import io.nem.apps.util.NemNetworkResponse;
import io.nem.apps.util.NetworkUtils;
import net.sf.json.JSONObject;

/**
 * The Class ValidationApi.
 */
public class ValidationApi {

	/**
	 * Validate address.
	 *
	 * @param address
	 *            the address
	 */
	public static boolean isAddressValid(String address) {
		NemNetworkResponse response = NetworkUtils
				.get(Globals.getNodeEndpoint() + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address=" + address);

		return !response.isError();
	}
	
	public static boolean isAddressPatternValid(String address) {
		Pattern pattern = Pattern.compile("@\"[nN]{1,1}[a-zA-Z0-9]{5,5}\\-[a-zA-Z0-9]{6,6}\\-[a-zA-Z0-9]{6,6}\\-[a-zA-Z0-9]{6,6}\\-[a-zA-Z0-9]{6,6}\\-[a-zA-Z0-9]{4,4}\"");
		return !pattern.matcher(address).matches();
	}
	
	public static boolean isAddressValid(String address,NodeEndpoint nodeEndpoint) {
		NemNetworkResponse response = NetworkUtils
				.get(nodeEndpoint + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address=" + address);

		return !response.isError();
	}
	
	public static boolean isAddressValid(String address,String protocol, String host, int port) {
		NodeEndpoint nodeEndpoint = new NodeEndpoint(protocol, host, port);
		NemNetworkResponse response = NetworkUtils
				.get(nodeEndpoint + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address=" + address);

		return !response.isError();
	}
}
