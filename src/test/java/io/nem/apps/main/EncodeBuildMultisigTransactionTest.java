package io.nem.apps.main;

import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.primitive.Amount;
import org.nem.core.node.NodeEndpoint;

import io.nem.apps.builders.ConfigurationBuilder;
import io.nem.apps.builders.TransactionBuilder;

/**
 * The Class BuildTransactionTest.
 */
public class EncodeBuildMultisigTransactionTest extends TransactionUnitTest {

	/** The sample msg. */
	final String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	@Before
	public void init() {
		ConfigurationBuilder.nodeNetworkName("mijinnet")
				.nodeEndpoint(new NodeEndpoint("http", "a1.nem.foundation", 7895)).setup();
		this.setAccountSenderPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec");
		this.setAccountRecipientPublicKey("fa20ea216d7b95d61223f99baf60871af933de1264113c2445987244a2aaaaee");
	}
	
	@Test
	public void testCoSign() {
		TransactionBuilder.initiateMultisigSignatureTransactionBuild()
		.sender(new Account(new KeyPair(PrivateKey.fromHexString("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1")))) // signer
		.multisig(new Account(new KeyPair(PublicKey.fromHexString("19d44fb99f6a347c2561827dc73dbd6b64a4b1de422cdf8e0fc4983a16609fe2")))) // multisig
		.otherTransaction(Hash.fromHexString("fa20ea216d7b95d61223f99baf60871af933de1264113c2445987244a2aaaaee"))
		.coSign();

	}
	
	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testCbBuildAndSendStringTransaction() {

		// Build a transaction and send it.
		try {

			this.setAccountSenderPrivateKey("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec"); // cosigner
			this.setAccountRecipientPublicKey("a70bf981bdb62c5d4e44b25ca2629108a394c7aaf18eec50dc405b1e44d712d4"); // recipient
			
			TransferTransaction trans = TransactionBuilder.initiateTransactionBuild()
					.sender(new Account(new KeyPair(PrivateKey
							.fromHexString("d8b89745a3006e293d16b8a16294582734c6b20ca5feb6e7ca25fec9295b1145")))) // multisig
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.buildTransaction();

			MultisigTransaction multiSigTrans = TransactionBuilder.initiateMultisigTransactionBuild()
					.sender(this.senderPrivateAccount)
					.otherTransaction(trans)
					.buildMultisigTransaction();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send string XML transaction.
	 */
	@Test
	public void testCbBuildAndSendStringXMLTransaction() {

		// Build a transaction and send it.
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, this.sampleMsg.getBytes());

			TransferTransaction trans = TransactionBuilder.initiateTransactionBuild()
					.sender(new Account(new KeyPair(PrivateKey
							.fromHexString("d8b89745a3006e293d16b8a16294582734c6b20ca5feb6e7ca25fec9295b1145")))) // multisig
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.buildTransaction();

			TransactionBuilder.initiateMultisigTransactionBuild()
					.sender(this.senderPrivateAccount)
					.otherTransaction(trans)
					.buildAndSendMultisigTransaction();
			
			// it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendTransactionWithMosaic() {

		// Build a transaction and send it.
		try {
			TransferTransaction trans = TransactionBuilder.initiateTransactionBuild()
					.sender(new Account(new KeyPair(PrivateKey
							.fromHexString("d8b89745a3006e293d16b8a16294582734c6b20ca5feb6e7ca25fec9295b1145")))) // multisig
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.buildTransaction();

			TransactionBuilder.initiateMultisigTransactionBuild()
					.sender(this.senderPrivateAccount)
					.otherTransaction(trans)
					.buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test cb build and send file transaction.
	 */
	@Test
	public void testCbBuildAndSendFileTransaction() {

		SecureMessage message = null;
		try {
			message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount, this.recipientPublicAccount,
					sampleMsg.getBytes());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Build a transaction and send it.
		try {
			TransferTransaction trans = TransactionBuilder.initiateTransactionBuild()
					.sender(new Account(new KeyPair(PrivateKey
							.fromHexString("d8b89745a3006e293d16b8a16294582734c6b20ca5feb6e7ca25fec9295b1145")))) // multisig
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.version(0)
					.buildTransaction();

			MultisigTransaction multisigTrans = TransactionBuilder.initiateMultisigTransactionBuild()
					.sender(this.senderPrivateAccount)
					.otherTransaction(trans)
					.buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
