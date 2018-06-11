package io.nem.apps.factories;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;





/**
 * A factory for creating Entity objects.
 */
public class EntityFactory {

	/**
	 * Creates a new Entity object.
	 *
	 * @param address
	 *            the address
	 * @return the account
	 */
	public static Account buildAccount(Address address) {
		return new Account(address);

	}

	/**
	 * Creates a new Entity object.
	 *
	 * @param keyPair
	 *            the key pair
	 * @return the account
	 */
	public static Account buildAccount(KeyPair keyPair) {
		return new Account(keyPair);
	}

	/**
	 * Creates a new Entity object.
	 *
	 * @param privateKeyHex
	 *            the private key hex
	 * @return the account
	 */
	public static Account buildAccountFromPrivateKey(String privateKeyHex) {
		return new Account(new KeyPair(PrivateKey.fromHexString(privateKeyHex)));
	}

	/**
	 * Builds the account from public key.
	 *
	 * @param publicKeyHex
	 *            the public key hex
	 * @return the account
	 */
	public static Account buildAccountFromPublicKey(String publicKeyHex) {
		return new Account(new KeyPair(PublicKey.fromHexString(publicKeyHex)));
	}

	/**
	 * Builds the key pair from public key.
	 *
	 * @param publicKeyHex
	 *            the public key hex
	 * @return the key pair
	 */
	public static KeyPair buildKeyPairFromPublicKey(String publicKeyHex) {
		return new KeyPair(PublicKey.fromHexString(publicKeyHex));
	}

	/**
	 * Builds the key pair from private key.
	 *
	 * @param publicKeyHex
	 *            the public key hex
	 * @return the key pair
	 */
	public static KeyPair buildKeyPairFromPrivateKey(String publicKeyHex) {
		return new KeyPair(PrivateKey.fromHexString(publicKeyHex));
	}

}
