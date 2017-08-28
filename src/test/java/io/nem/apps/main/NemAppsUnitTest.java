package io.nem.apps.main;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.TransactionFeeCalculatorAfterFork;
import org.nem.core.node.NodeEndpoint;

import io.nem.apps.builders.ConfigurationBuilder;

/**
 * The Class TransactionUnitTest.
 */
public abstract class NemAppsUnitTest {

	protected String MIJIN_DM_ADDRESS = "MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW";
	
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

	static String networkName = "";

	/**
	 * Instantiates a new transaction unit test.
	 */
	public NemAppsUnitTest() {
		// Assume.assumeTrue(this.isTestable());
	}

	@BeforeClass
	public static void init() {
		System.out.println(networkName);
		if (networkName.equals("")) {
			networkName = "mijinnet";
			ConfigurationBuilder.nodeNetworkName(networkName).nodeNetworkProtocol("http")
					.nodeNetworkUri("a1.nem.foundation").nodeNetworkPort("7895")
					.transactionFee(new TransactionFeeCalculatorAfterFork())
					.setup();
		}
	}

	/**
	 * Checks if is testable.
	 *
	 * @return true, if is testable
	 */
	protected boolean isTestable() {
		if (senderPrivateKeyPair != null && senderPrivateAccount != null)
			return true;
		if (recipientPublicKeyPair != null && recipientPublicAccount != null)
			return true;

		return false;
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
	protected NemAppsUnitTest setKeyPairSenderPrivateKey(String privateKey) {
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
	protected NemAppsUnitTest setKeyPairSenderPublicKey(String publicKey) {
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
	protected NemAppsUnitTest setKeyPairRecipientPublicKey(String publicKey) {
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
	protected NemAppsUnitTest setKeyPairRecipientPrivateKey(String privateKey) {
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
	protected NemAppsUnitTest setKeyPairMultisigAccountPublicKey(String publicKey) {
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
	protected NemAppsUnitTest setAccountSenderPrivateKey(String privateKey) {
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
	protected NemAppsUnitTest setAccountRecipientPublicKey(String publicKey) {
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
	protected NemAppsUnitTest setAccountMultisigAccountPublicKey(String publicKey) {
		this.multiSigAccount = new Account(new KeyPair(PublicKey.fromHexString(publicKey)));
		return this;
	}

}
