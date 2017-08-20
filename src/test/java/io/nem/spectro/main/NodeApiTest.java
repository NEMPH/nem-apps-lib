package io.nem.spectro.main;

import org.junit.BeforeClass;
import org.junit.Test;

import io.nem.spectro.api.NodeApi;
import io.nem.spectro.builders.ConfigurationBuilder;

public class NodeApiTest {

	@BeforeClass
	public static void init() {
		ConfigurationBuilder.nodeNetworkName("mijinnet").nodeNetworkProtocol("http").nodeNetworkUri("a1.nem.foundation").nodeNetworkPort("7895").setup();
	}
	
	@Test
	public void nodeInfoTest() {
		System.out.println(NodeApi.getNodeInfo().getMetaData().getPlatform());
	}
	
	@Test
	public void nodeInfoExtendedTest() {
		System.out.println(NodeApi.getNodeExtendedInfo().getNode().getEndpoint());
	}
	
	@Test
	public void nodeHeartBeatTest() {
		System.out.println(NodeApi.getNemNodeHeartBeat().getMessage());
	}
}
