package io.nem.spectro.main;

import java.io.IOException;

import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransactionAttachment;

import io.nem.spectro.api.AccountApi;
import io.nem.spectro.builders.GenericTransactionBuilder;
import io.nem.spectro.builders.TransactionBuilder;
import io.nem.spectro.factories.AttachmentFactory;
import io.nem.spectro.factories.EntityFactory;


/**
 * The Class StressTestAppTransaction.
 */
public class StressTestAppTransaction extends TransactionUnitTest {

	/**
	 * Instantiates a new stress test app transaction.
	 */
	public StressTestAppTransaction() {
		String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
				+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
				+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
				+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

		try {

			for (int i = 0; i < 500; i++) {
				System.out.println(
						AccountApi.getAccountByAddress("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW").getMetaData().getStatus());
//				final Account senderAccount = EntityFactory.buildAccountFromPrivateKey("<privatekey>");
//				final Account recipientAccount = EntityFactory.buildAccountFromPublicKey("<public>");
//
//				SecureMessage message;
//				try {
//					message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
//							sampleMsg.getBytes());
//
//					TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
//
//					TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
//							.amount(0l).attachment(AttachmentFactory.createTransferTransactionAttachment(message))
//							.buildAndSendTransaction();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new StressTestAppTransaction();
	}
}
