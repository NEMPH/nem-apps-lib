package io.nem.spectro.main;

import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;
import io.nem.spectro.builders.TransactionBuilder;
import io.nem.spectro.crypto.SecureMessageEncoder;
import io.nem.spectro.factories.AttachmentFactory;
import io.nem.spectro.util.TransactionSenderUtil;

/**
 * The Class BuildTransactionTest.
 */
public class EncodeBuildTransactionTest extends TransactionUnitTest {

	/** The sample msg. */
	final String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	/**
	 * Test cb build transaction.
	 */
	@Test
	public void testCbBuildTransaction() {

		TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount)
				.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(0l).attachment(null).buildAndSendTransaction();
	}

	/**
	 * Test cb build and send transaction WO attachment.
	 */
	@Test
	public void testCbBuildAndSendTransactionWOAttachment() {

		this.senderPrivateKeyPair = new KeyPair(
				PrivateKey.fromHexString("90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec"));
		this.recipientPublicKeyPair = new KeyPair(
				PrivateKey.fromHexString("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1"));

		// Build a transaction and send it.
		try {
			TransactionBuilder.initiateTransactionBuild().sender(new Account(this.senderPrivateKeyPair))
					.recipient(new Account(this.recipientPublicKeyPair)).fee(Amount.ZERO).amount(0l).attachment(null)
					.buildAndSendTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testCbBuildAndSendStringTransaction() {

		// Build a transaction and send it.

		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, sampleMsg.getBytes());
			TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount)
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction();

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

			TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount)
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction();
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

			SecureMessage message = SecureMessageEncoder.encode(this.senderPrivateAccount, this.recipientPublicAccount,
					sampleMsg);
			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));

			TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount)
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction();
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
			MultisigTransaction multiSigTrans = TransactionBuilder.initiateMultisigTransactionBuild()
					.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.multisig(this.multiSigAccount).amount(0l)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildMultisigTransaction();

			multiSigTrans.setFee(Amount.fromNem(0));
			multiSigTrans.sign();
			TransactionSenderUtil.sendTransaction(multiSigTrans);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
