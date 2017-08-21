package io.nem.apps.main;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;

import io.nem.apps.builders.ConfigurationBuilder;

/**
 * The Class TransactionUnitTest.
 */
public abstract class ApiUnitTest {
	public ApiUnitTest() {
		ConfigurationBuilder.nodeNetworkName("mijinnet").nodeNetworkProtocol("http").nodeNetworkUri("a1.nem.foundation")
				.nodeNetworkPort("7895").setup();
	}

}
