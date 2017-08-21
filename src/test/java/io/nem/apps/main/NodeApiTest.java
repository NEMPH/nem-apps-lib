package io.nem.apps.main;

import org.junit.BeforeClass;
import org.junit.Test;

import io.nem.apps.api.NodeApi;
import io.nem.apps.builders.ConfigurationBuilder;

public class NodeApiTest extends ApiUnitTest {

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
