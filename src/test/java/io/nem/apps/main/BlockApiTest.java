package io.nem.apps.main;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import io.nem.apps.api.BlockApi;

public class BlockApiTest extends NemAppsUnitTest {

	
	@Test
	public void testBlockHeight() {
		try {
			assertTrue(BlockApi.getBlockByHeight(428917).getTransactions().size() > 0);
		} catch (InterruptedException | ExecutionException e) {
			assert(false);
		}
	}

	
	@Test
	public void testGivenBlockHeight() {
		try {
			assertTrue(BlockApi.getBlockAfterGivenBlockHeight(428917).getTransactions().size() > 0);
		} catch (InterruptedException | ExecutionException e) {
			assert(false);
		}
	}
	
}
