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
	
	private String encodedAddress;
	private String encodedPublicKey;
	private String encodedPrivateKey;
	
	public String getEncodedAddress() {
		return encodedAddress;
	}

	public void setEncodedAddress(String encodedAddress) {
		this.encodedAddress = encodedAddress;
	}

	public String getEncodedPublicKey() {
		return encodedPublicKey;
	}

	public void setEncodedPublicKey(String encodedPublicKey) {
		this.encodedPublicKey = encodedPublicKey;
	}

	public String getEncodedPrivateKey() {
		return encodedPrivateKey;
	}

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
