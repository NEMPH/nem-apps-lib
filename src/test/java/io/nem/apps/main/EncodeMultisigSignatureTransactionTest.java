package io.nem.apps.main;

import org.junit.Before;
import org.junit.Test;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.node.NodeEndpoint;

import io.nem.apps.builders.ConfigurationBuilder;
import io.nem.apps.builders.TransactionBuilder;

public class EncodeMultisigSignatureTransactionTest extends TransactionUnitTest {

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
	
}
