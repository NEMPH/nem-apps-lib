package io.nem.apps.util;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;

import net.sf.json.JSONObject;

public class KeyConvertor {

	/**
	 * get address from private key
	 * @param privateKeyString
	 * @return
	 */
	public static String getAddressFromPrivateKey(String privateKeyString) {
		PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
		KeyPair keyPair = new KeyPair(privateKey);
		return Address.fromPublicKey(keyPair.getPublicKey()).toString();
	}
	
	/**
	 * get public key from private key
	 * @param privateKeyString
	 * @return
	 */
	public static String getPublicFromPrivateKey(String privateKeyString) {
		PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
		KeyPair keyPair = new KeyPair(privateKey);
		return keyPair.getPublicKey().toString();
	}
	
	/**get address from public key
	 * @param publicKeyString
	 * @return
	 */
	public static String getAddressFromPublicKey(String publicKeyString) {
		PublicKey publicKey = PublicKey.fromHexString(publicKeyString);
		Address address = Address.fromPublicKey(publicKey);
		return address.toString();
	}
	
	/**
	 * get public Key from address
	 * @param addressString
	 * @return
	 */
	public static String getPublicKeyFromAddress(String addressString) {
		String queryResult = HttpClientUtils.get(Constants.URL_ACCOUNT_GET + "?address=" + addressString);
		JSONObject queryAccount = JSONObject.fromObject(queryResult);
		return queryAccount.getJSONObject("account").getString("publicKey");
	}
	
}
