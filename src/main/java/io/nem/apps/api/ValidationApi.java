package io.nem.apps.api;

import org.nem.core.connect.client.NisApiId;

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
}
