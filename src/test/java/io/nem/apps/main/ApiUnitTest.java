package io.nem.apps.main;

import org.junit.BeforeClass;

import io.nem.apps.builders.ConfigurationBuilder;

/**
 * The Class TransactionUnitTest.
 */
public abstract class ApiUnitTest {

	protected String MIJIN_DM_ADDRESS = "MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW";
	static String networkName = "";

	@BeforeClass
	public static void init() {
		if (networkName.equals("")) {
			networkName = "mijinnet";
			ConfigurationBuilder.nodeNetworkName(networkName).nodeNetworkProtocol("http")
					.nodeNetworkUri("a1.dfintech.com").nodeNetworkPort("7895").setup();
		}
	}

}
