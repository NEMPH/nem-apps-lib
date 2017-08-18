package io.nem.spectro.main;

import org.junit.Assume;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;


/**
 * The Class TransactionUnitTest.
 */
public abstract class TransactionUnitTest {

	/** The sender private key pair. */
	protected KeyPair senderPrivateKeyPair;

	/** The sender public key pair. */
	protected KeyPair senderPublicKeyPair;

	/** The recipient private key pair. */
	protected KeyPair recipientPrivateKeyPair;

	/** The recipient public key pair. */
	protected KeyPair recipientPublicKeyPair;

	/** The multi sig key pair. */
	protected KeyPair multiSigKeyPair;

	/** The sender private account. */
	protected Account senderPrivateAccount;

	/** The recipient public account. */
	protected Account recipientPublicAccount;

	/** The multi sig account. */
	protected Account multiSigAccount;

	/**
	 * Instantiates a new transaction unit test.
	 */
	public TransactionUnitTest() {
		Assume.assumeTrue(this.isTestable());
	}

	/**
	 * Checks if is testable.
	 *
	 * @return true, if is testable
	 */
	protected boolean isTestable() {
		if (senderPrivateKeyPair == null && senderPrivateAccount == null)
			return false;
		if (recipientPublicKeyPair == null && recipientPublicAccount == null)
			return false;
		if (senderPrivateKeyPair == null)
			return false;
		if (recipientPublicKeyPair == null)
			return false;
		if (multiSigKeyPair == null)
			return false;

		return true;
		// multisig is subjective, transaction might not be
	}

	/**
	 * Checks if is multi sig testable.
	 *
	 * @return true, if is multi sig testable
	 */
	protected boolean isMultiSigTestable() {
		if (senderPrivateKeyPair == null)
			return false;
		if (recipientPublicKeyPair == null)
			return false;
		if (multiSigKeyPair == null)
			return false;

		return true;
	}

	/**
	 * Sets the key pair sender private key.
	 *
	 * @param privateKey
	 *            the private key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setKeyPairSenderPrivateKey(String privateKey) {
		this.senderPrivateKeyPair = new KeyPair(PrivateKey.fromHexString(privateKey));
		return this;
	}

	/**
	 * Sets the key pair sender public key.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setKeyPairSenderPublicKey(String publicKey) {
		this.senderPublicKeyPair = new KeyPair(PublicKey.fromHexString(publicKey));
		return this;
	}

	/**
	 * Sets the key pair recipient public key.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setKeyPairRecipientPublicKey(String publicKey) {
		this.recipientPublicKeyPair = new KeyPair(PublicKey.fromHexString(publicKey));
		return this;
	}

	/**
	 * Sets the key pair recipient private key.
	 *
	 * @param privateKey
	 *            the private key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setKeyPairRecipientPrivateKey(String privateKey) {
		this.recipientPrivateKeyPair = new KeyPair(PrivateKey.fromHexString(privateKey));
		return this;
	}

	/**
	 * Sets the key pair multisig account public key.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setKeyPairMultisigAccountPublicKey(String publicKey) {
		this.multiSigKeyPair = new KeyPair(PublicKey.fromHexString(publicKey));
		return this;
	}

	/**
	 * Sets the account sender private key.
	 *
	 * @param privateKey
	 *            the private key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setAccountSenderPrivateKey(String privateKey) {
		this.senderPrivateAccount = new Account(new KeyPair(PrivateKey.fromHexString(privateKey)));
		return this;
	}

	/**
	 * Sets the account recipient public key.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setAccountRecipientPublicKey(String publicKey) {
		this.recipientPublicAccount = new Account(new KeyPair(PublicKey.fromHexString(publicKey)));
		return this;
	}

	/**
	 * Sets the account multisig account public key.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the transaction unit test
	 */
	protected TransactionUnitTest setAccountMultisigAccountPublicKey(String publicKey) {
		this.multiSigAccount = new Account(new KeyPair(PublicKey.fromHexString(publicKey)));
		return this;
	}

}
