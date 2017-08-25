package io.nem.apps.main;

import org.junit.Test;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import io.nem.apps.builders.TransferTransactionBuilder;
import io.nem.apps.factories.AttachmentFactory;


/**
 * The Class SingleToBcTransactionTest.
 */
public class SingleToBcTransactionTest extends TransactionUnitTest {

	/** The sample  msg. */
	final String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	/**
	 * Test cb build and send  transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendTransactionWithMosaic() {

		// Build a transaction and send it.
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, this.sampleMsg.getBytes());

			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			// attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new
			// Quantity(12));
			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.ZERO)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test cb build and send  transaction with mosaic 1.
	 */
	@Test
	public void testCbBuildAndSendTransactionWithMosaic1() {

		// Build a transaction and send it.
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, this.sampleMsg.getBytes());

			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			// attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new
			// Quantity(12));

			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.ZERO)
					.attachment(AttachmentFactory.createTransferTransactionAttachment(message))
					.buildAndSendTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
