package io.nem.spectro.api;

import org.nem.core.connect.client.NisApiId;

import io.nem.spectro.service.Globals;
import io.nem.spectro.util.NemSpectroNetworkResponse;
import io.nem.spectro.util.NetworkUtils;

public class AccountApi {
	
	public static void getAccountByAddress(String address) {
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_LOOK_UP.toString() + "?address="+address);
	}
	
	public static void getAllTransactions(String address, String id) {
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL.toString() + "?address="+address+"&id="+id);
	}
	
	public static void getIncomingTransactions(String address) {
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING.toString() + "?address="+address);
	}
	public static void getIncomingTransactions(String address, String id) {
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING.toString() + "?address="+address+"&id="+id);
	}
	
	public static void getOutgoingTransactions(String address){
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING.toString() + "?address="+address);
	}
	
	public static void getUnconfirmTransactions(String address){
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED.toString() + "?address="+address);
	}
	
	public static void getUnconfirmTransactions(String address, String id){
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED.toString() + "?address="+address+"&id="+id);
	}
	
	public static void getMosaics(String address){
		NemSpectroNetworkResponse response = NetworkUtils.get(Globals.NODE_ENDPOINT + Globals.URL_ACCOUNT_MOSAIC_OWNED.toString() + "?address="+address);
	}
	
}


