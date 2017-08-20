package io.nem.spectro.main;

import org.junit.Test;

import io.nem.spectro.api.NodeApi;

public class NodeApiTest {

	@Test
	public void nodeInfoTest() {
		System.out.println(NodeApi.getNodeInfo().getMetaData().getPlatform());
	}
	
	@Test
	public void nodeInfoExtended() {
		System.out.println(NodeApi.getNodeExtendedInfo().getNode().getEndpoint());
	}
}
