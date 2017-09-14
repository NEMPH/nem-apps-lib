package io.nem.apps.main;

import static org.junit.Assert.assertNotNull;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import io.nem.apps.api.ChainApi;

public class ChainApiTest extends NemAppsUnitTest {

	
	@Test
	public void testChainHeight() {
		try {
			assertNotNull(ChainApi.getChainHeight());
		} catch (InterruptedException | ExecutionException e) {
			assert(false);
		}
	}

	
	@Test
	public void testLastBlock() {
		try {
			System.out.println(ChainApi.getChainLastBlock());
			assertNotNull(ChainApi.getChainLastBlock());
		} catch (InterruptedException | ExecutionException e) {
			assert(false);
		}
	}
	
	@Test
	public void testScore() {
		try {
			System.out.println(ChainApi.getChainScore());
			assertNotNull(ChainApi.getChainScore());
		} catch (InterruptedException | ExecutionException e) {
			assert(false);
		}
	}
	
}
