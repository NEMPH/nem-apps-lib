package io.nem.apps.main;

import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;
import io.nem.apps.builders.MultisigTransactionBuilder;
import io.nem.apps.builders.TransferTransactionBuilder;
import io.nem.apps.crypto.SecureMessageEncoder;
import io.nem.apps.factories.AttachmentFactory;

/**
 * The Class BuildTransactionTest.
 */
public class EncodeBuildTransactionTest extends NemAppsUnitTest {

	/** The sample msg. */
	final String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	
	@Test
	public void testBasicTransferTans() {
		NemAnnounceResult result = TransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey
						.fromHexString("8b946dbeef18c54e3c9f1b787f7104b31255b34533b187840c3c0774c1bc9e2c"))))
				.recipient(new Account(new KeyPair(
						PublicKey.fromHexString("252b2567b3a8eda6421d40d72715e976d746a61633aa8e1d5ab466fb1c0e410e"))))
				.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l)).buildAndSendTransaction();
		System.out.println(result.getCode());
		System.out.println(result.getTransactionHash());
		System.out.println(result.getMessage());
		System.out.println(result.getTransactionHash().getRaw());
	}
	/**
	 * Test cb build transaction.
	 */
	@Test
	@Ignore
	public void testCbBuildTransaction() {

		TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
				.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l)).version(1).buildAndSendTransaction();
	}

	/**
	 * Test cb build and send transaction WO attachment.
	 */
	@Test
	public void testCbBuildAndSendTransactionWOAttachment() {

		// Build a transaction and send it.
		try {
			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l)).attachment(null).buildAndSendTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testCbBuildAndSendStringTransaction() {
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, sampleMsg.getBytes());
			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
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

			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
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

			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
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

			TransferTransaction trans = TransferTransactionBuilder
					.sender(new Account(new KeyPair(PrivateKey
							.fromHexString("d8b89745a3006e293d16b8a16294582734c6b20ca5feb6e7ca25fec9295b1145")))) // multisig
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.buildTransaction();

			MultisigTransactionBuilder.sender(this.senderPrivateAccount).otherTransaction(trans)
					.buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
