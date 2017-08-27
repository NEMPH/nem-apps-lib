package io.nem.apps.main;

import org.junit.Test;
import io.nem.apps.api.NodeApi;

public class NodeApiTest extends NemAppsUnitTest {

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
