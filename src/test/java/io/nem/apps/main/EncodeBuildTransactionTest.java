package io.nem.apps.main;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;
import org.nem.core.utils.HexEncoder;

import io.nem.apps.builders.MultisigTransactionBuilder;
import io.nem.apps.builders.TransferTransactionBuilder;
import io.nem.apps.crypto.SecureMessageDecoder;
import io.nem.apps.crypto.SecureMessageEncoder;
import io.nem.apps.factories.AttachmentFactory;
import io.nem.apps.model.RequestAnnounceDataSignature;
import io.nem.apps.util.HexStringUtils;

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
						.fromHexString("deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763"))))
				.recipient(new Account(new KeyPair(PublicKey
						.fromHexString("36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85"))))
				.message("This is Plain - new ",MessageTypes.PLAIN).buildAndSendTransaction();
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
			TransferTransactionBuilder.sender(new Account(new KeyPair(PrivateKey
					.fromHexString("deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763"))))
			.recipient(new Account(new KeyPair(PublicKey
					.fromHexString("36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85"))))
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.attachment(AttachmentFactory.createTransferTransactionAttachmentMessage(new PlainMessage("asa".getBytes())))
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
		try {

			final SecureMessage message = SecureMessage.fromDecodedPayload(this.senderPrivateAccount,
					this.recipientPublicAccount, sampleMsg.getBytes());
			TransferTransactionBuilder
			.sender(new Account(new KeyPair(PrivateKey
					.fromHexString("deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763"))))
			.recipient(new Account(new KeyPair(PublicKey
					.fromHexString("36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85"))))
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.attachment(AttachmentFactory.createTransferTransactionAttachmentMessage(message))
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
					.attachment(AttachmentFactory.createTransferTransactionAttachmentMessage(message))
					.buildAndSendTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	@Ignore
	public void testEncryption() throws UnsupportedEncodingException {
		
		String xPvkey = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";
		String xPubkey = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";
		String payloadStr = "47414141414251414a41416741427741474141554142414141414149414151414641414141434141414144592b362b7a596745414143514141414177414141414e414141414467414141426f414141414351414141485a705a4756764c3231774e41414141416b414141425352566c4655793574634451414141414341414141496949414141414141414141414141414c6741414146467457586849646b6449574755354e5646594e6d566e56545a7259546c4c655752495a465a3556564e68646c5a5859324a535a5739774d6d5a75536b4d4141454141414141315a6a686d4e5449324e7a5a6c4e5463334d44466b4e445a694f4745334d325669597a4d345a5464694f44646d5a544d304f57466d5a444532595441774e4445354e4441334e4467354d6d49304e6d497a4d44686b4141414141413d3d";
		
		//	Crypto Engine / Assymetric Encryption
		CryptoEngine engine = CryptoEngines.ed25519Engine();
		byte[] encrypted = engine
				.createBlockCipher(
						new KeyPair(PrivateKey.fromHexString(xPvkey), engine),
						new KeyPair(PublicKey.fromHexString(xPubkey), engine))
				.encrypt("hello".getBytes());

		byte[] decrypted = engine
				.createBlockCipher(
						new KeyPair(PublicKey.fromHexString(xPubkey), engine),
						new KeyPair(PrivateKey.fromHexString(xPvkey), engine)).decrypt(encrypted);
		
		
		

		System.out.println(new String(decrypted, "UTF-8"));
		
		//	SeureMessageEncoder/Decoder
		SecureMessage secureMessage = SecureMessageEncoder.encode(xPvkey, xPubkey,"hello");
		String data = HexEncoder.getString(encrypted);
		System.out.println(data);
		
		SecureMessage secureMessageDec = SecureMessageDecoder.decode(xPubkey,xPvkey,secureMessage.getEncodedPayload());
		System.out.println(new String(secureMessageDec.getDecodedPayload(), "UTF-8"));
		
		
		SecureMessage secureMessageDec1 = SecureMessageDecoder.decode(xPubkey,xPvkey,payloadStr);
		System.out.println(new String(secureMessageDec1.getDecodedPayload(), "UTF-8"));
	}

	/**
	 * Test cb build and send transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendTransactionWithMosaic() {

		// Build a transaction and send it.
		try {
			

//			CryptoEngine engine = CryptoEngines.ed25519Engine();
//			byte[] encrypted = engine
//					.createBlockCipher(
//							new KeyPair(PrivateKey.fromHexString(xPvkey), engine),
//							new KeyPair(PublicKey.fromHexString(xPubkey), engine))
//					.encrypt("hello".getBytes());
//			
			SecureMessage message = SecureMessageEncoder.encode(this.senderPrivateAccount, this.recipientPublicAccount,
					sampleMsg);
			TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
			attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));

			TransferTransactionBuilder.sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
					.fee(Amount.ZERO).amount(Amount.fromMicroNem(0l))
					.attachment(AttachmentFactory.createTransferTransactionAttachmentMessage(message))
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
