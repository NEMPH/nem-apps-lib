package io.nem.apps.main;

import static org.junit.Assert.assertNotNull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Test;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;
import io.nem.apps.api.TransactionApi;
import io.nem.apps.builders.ConfigurationBuilder;
import io.nem.apps.crypto.SecureMessageDecoder;
import io.nem.apps.util.KeyConvertor;


/**
 * The Class DecodeBuildTransactionTest.
 */
public class DecodeBuildTransactionTest extends NemAppsUnitTest {

	final String sampleMsg = "a2d88a2d00161be3a3b4a0a62ae5125789bc56aafdea774370e2a8e3cbf80c11dd1f330ef32b86e84becdc38c3882291518c05000a42c90b0826d17a80375a7635ed3980a1d3b09abdcbf26ea6b71dfd23b1d5e68b2d1b40d58389f14ad5aa44f8b339083f6831d57fd916c67bf9c0a4518193d5d0e12d7bbf30777d85bbd4da32291eb71e223205dfc03f1bb86ee255";
	final String simplePayload = "a2d88a2d00161be3a3b4a0a62ae5125789bc56aafdea774370e2a8e3cbf80c11dd1f330ef32b86e84becdc38c3882291518c05000a42c90b0826d17a80375a7635ed3980a1d3b09abdcbf26ea6b71dfd23b1d5e68b2d1b40d58389f14ad5aa44f8b339083f6831d57fd916c67bf9c0a4518193d5d0e12d7bbf30777d85bbd4da32291eb71e223205dfc03f1bb86ee255";
	
	/**
	 * Test transaction hash.
	 */
	@Test
	public void testDecode() {
		
		try {
			assertNotNull("Decode process completed.", SecureMessageDecoder.decode(this.senderPublicKeyPair,this.recipientPrivateKeyPair,simplePayload));
			if(isTestable())assertNotNull("Decode process completed.", SecureMessageDecoder.decode(this.senderPublicKeyPair,this.recipientPrivateKeyPair,simplePayload));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
