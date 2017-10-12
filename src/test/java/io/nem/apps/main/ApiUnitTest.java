package io.nem.apps.main;

import org.junit.BeforeClass;

import io.nem.apps.builders.ConfigurationBuilder;

/**
 * The Class TransactionUnitTest.
 */
public abstract class ApiUnitTest {

	protected String MIJIN_DM_ADDRESS = "TBPJCYR4XKGPGC3JHINJBJTW57ZLWTABDFLZSTMD";
	static String networkName = "";

	@BeforeClass
	public static void init() {
		if (networkName.equals("")) {
			networkName = "testnet";
			ConfigurationBuilder.nodeNetworkName(networkName).nodeNetworkProtocol("http")
					.nodeNetworkUri("104.128.226.60").nodeNetworkPort("7890").setup();
		}
	}

}
