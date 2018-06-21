package io.nem.apps.main;

import org.junit.Test;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import io.nem.apps.builders.MultisigSignatureTransactionBuilder;
import io.nem.apps.builders.MultisigTransactionBuilder;
import io.nem.apps.builders.TransferTransactionBuilder;
import io.nem.apps.factories.AttachmentFactory;

public class EncodeBuildMultisigTransactionTest extends NemAppsUnitTest {

	/** The sample msg. */
	final String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";


	/**
	 * Test cb build and send string XML transaction.
	 */
	@Test
	public void testMulitSigTransactionBuilderSecureMessage() {

		// Build a transaction and send it.
		try {

			TransferTransaction trans = TransferTransactionBuilder
					.sender(new Account(new KeyPair(PublicKey
							.fromHexString("8d7cda65cf83740c3186202ee0dcd7d2736eb7cd1ec15255e874fed7ed9306b7"))))
					.recipient(new Account(new KeyPair(PublicKey
							.fromHexString("092f13a06496c002510a6afc03f5db522664716aaeefdded450106df1624dd3d"))))
					.amount(Amount.fromNem(1l))
					.fee(Amount.fromMicroNem(500_000L))
					.message(" ",MessageTypes.PLAIN).buildTransaction();

			MultisigTransactionBuilder
					.sender(new Account(new KeyPair(PrivateKey.fromHexString("bd066f917747eb8e50d69a3bcbed829a3db0a0552581c7862f9eec706a43670d"))))
					.otherTransaction(trans)
					.buildAndSendMultisigTransaction();

			// it!
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test cb build and send transaction.
	 */
	@Test
	public void testMulitSigTransactionBuilderSimple() {

		// Build a transaction and send it.
		try {

			TransferTransaction trans = TransferTransactionBuilder
					.sender(new Account(new KeyPair(PrivateKey
							.fromHexString("83808db970cce349e60cbdb8db5bf250dcb1c1c9b6c730e0cc636a99a321ce0d")))) // multisig
					.recipient(new Account(new KeyPair(PublicKey
							.fromHexString("092f13a06496c002510a6afc03f5db522664716aaeefdded450106df1624dd3d"))))
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l)).buildTransaction();

			MultisigTransaction multiSigTrans = MultisigTransactionBuilder.sender(new Account(new KeyPair(PrivateKey
					.fromHexString("bd066f917747eb8e50d69a3bcbed829a3db0a0552581c7862f9eec706a43670d"))))
					.otherTransaction(trans).buildMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Test cb build and send file transaction.
	 */
	@Test
	public void testMulitSigTransactionBuilder() {

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
					.recipient(this.recipientPublicAccount).fee(Amount.ZERO).amount(Amount.fromMicroNem(0l)).version(0)
					.buildTransaction();

			NemAnnounceResult multisigTrans = MultisigTransactionBuilder.sender(this.senderPrivateAccount)
					.otherTransaction(trans).buildAndSendMultisigTransaction();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCoSignMultiSig() {
		MultisigSignatureTransactionBuilder
				.multisig(new Account(new KeyPair(
						PublicKey.fromHexString("19d44fb99f6a347c2561827dc73dbd6b64a4b1de422cdf8e0fc4983a16609fe2")))) // multisig
				.signer(new Account(new KeyPair(
						PrivateKey.fromHexString("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1")))) // signer
				.otherTransaction(
						Hash.fromHexString("fa20ea216d7b95d61223f99baf60871af933de1264113c2445987244a2aaaaee"))
				.coSign();

	}
}
