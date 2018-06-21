package io.nem.apps.util;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;

import net.sf.json.JSONObject;


/**
 * The Class KeyConvertor.
 */
public class KeyConvertor {

	/**
	 * get address from private key.
	 *
	 * @param privateKeyString the private key string
	 * @return the address from private key
	 */
	public static String getAddressFromPrivateKey(String privateKeyString) {
		PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
		KeyPair keyPair = new KeyPair(privateKey);
		return Address.fromPublicKey(keyPair.getPublicKey()).toString();
	}
	
	/**
	 * get public key from private key.
	 *
	 * @param privateKeyString the private key string
	 * @return the public from private key
	 */
	public static String getPublicFromPrivateKey(String privateKeyString) {
		PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
		KeyPair keyPair = new KeyPair(privateKey);
		return keyPair.getPublicKey().toString();
	}
	
	/**
	 * get address from public key.
	 *
	 * @param publicKeyString the public key string
	 * @return the address from public key
	 */
	public static String getAddressFromPublicKey(String publicKeyString) {
		PublicKey publicKey = PublicKey.fromHexString(publicKeyString);
		Address address = Address.fromPublicKey(publicKey);
		return address.toString();
	}
	
	
}
