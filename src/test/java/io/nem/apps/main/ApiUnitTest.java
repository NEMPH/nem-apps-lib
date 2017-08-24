package io.nem.apps.main;


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
