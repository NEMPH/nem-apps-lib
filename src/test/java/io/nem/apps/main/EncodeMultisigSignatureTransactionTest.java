package io.nem.apps.main;

import org.junit.Test;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import io.nem.apps.builders.MultisigSignatureTransactionBuilder;

public class EncodeMultisigSignatureTransactionTest extends NemAppsUnitTest {

	@Test
	public void testCoSign() {
		MultisigSignatureTransactionBuilder
				.multisig(new Account(new KeyPair(
						PublicKey.fromHexString("8d7cda65cf83740c3186202ee0dcd7d2736eb7cd1ec15255e874fed7ed9306b7")))) // multisig
				.signer(new Account(new KeyPair(
						PrivateKey.fromHexString("4e865e12d45ccbe6afd9f9b9f7c4ce447f214902f1bf2b5e62dad19a26eb3343"))))
				.otherTransaction(
						Hash.fromHexString("d921c19555c3c995ed57c5b173c386bf97156bdfadd8b3d4928998dce949a4c0"))
				.coSign();
	}

	@Test
	public void testCoSigners() {
		MultisigSignatureTransactionBuilder
				.multisig(new Account(new KeyPair(PublicKey.fromHexString("19d44fb99f6a347c2561827dc73dbd6b64a4b1de422cdf8e0fc4983a16609fe2")))) // multisig
				.startAssignSigners()
					.addSigner(new Account(new KeyPair(PrivateKey.fromHexString("c9d930757f69584fc414d0b2b54a0c3aa064996f9b13b70d32c89879724153c1")))) // signer																							// 1
					.addSigner(new Account(new KeyPair(PrivateKey.fromHexString("553d202f0e3793c52ec0e20f18d2eeeb6b54c3e76b005ef0567eca48aab1c2dd")))) // signer																							// 2
				.endAssignSigners()
				.otherTransaction(Hash.fromHexString("f9697ef52e6d14c0236d43a1e9a6cf7ba577e6cd929d38d5ec01d0c660ef2eb8"))
				.coSign();
	}

}
