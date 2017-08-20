package io.nem.spectro.api;

import org.nem.core.connect.client.NisApiId;

import io.nem.spectro.service.Globals;
import io.nem.spectro.util.NemSpectroNetworkResponse;
import io.nem.spectro.util.NetworkUtils;

public class ValidationApi {
	public static void validateAddress(String address) {
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.getNodeEndpoint() + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address="+address);
	}
}	
