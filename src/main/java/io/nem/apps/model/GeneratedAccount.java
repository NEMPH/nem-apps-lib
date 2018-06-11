package io.nem.apps.model;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Account;



/**
 * The Class GeneratedAccount.
 */
public class GeneratedAccount {
	
	/** The key pair. */
	private KeyPair keyPair;
	
	/** The account. */
	private Account account;
	
	/** The encoded address. */
	private String encodedAddress;
	
	/** The encoded public key. */
	private String encodedPublicKey;
	
	/** The encoded private key. */
	private String encodedPrivateKey;
	
	/**
	 * Gets the encoded address.
	 *
	 * @return the encoded address
	 */
	public String getEncodedAddress() {
		return encodedAddress;
	}

	/**
	 * Sets the encoded address.
	 *
	 * @param encodedAddress the new encoded address
	 */
	public void setEncodedAddress(String encodedAddress) {
		this.encodedAddress = encodedAddress;
	}

	/**
	 * Gets the encoded public key.
	 *
	 * @return the encoded public key
	 */
	public String getEncodedPublicKey() {
		return encodedPublicKey;
	}

	/**
	 * Sets the encoded public key.
	 *
	 * @param encodedPublicKey the new encoded public key
	 */
	public void setEncodedPublicKey(String encodedPublicKey) {
		this.encodedPublicKey = encodedPublicKey;
	}

	/**
	 * Gets the encoded private key.
	 *
	 * @return the encoded private key
	 */
	public String getEncodedPrivateKey() {
		return encodedPrivateKey;
	}

	/**
	 * Sets the encoded private key.
	 *
	 * @param encodedPrivateKey the new encoded private key
	 */
	public void setEncodedPrivateKey(String encodedPrivateKey) {
		this.encodedPrivateKey = encodedPrivateKey;
	}

	/**
	 * Gets the key pair.
	 *
	 * @return the key pair
	 */
	public KeyPair getKeyPair() {
		return keyPair;
	}
	
	/**
	 * Sets the key pair.
	 *
	 * @param keyPair the new key pair
	 */
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
	
	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	
	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
