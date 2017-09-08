package io.nem.apps.main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.UnconfirmedTransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;
import io.nem.apps.api.AccountApi;
import io.nem.apps.api.BlockApi;
import io.nem.apps.service.Globals;

public class BlockApiTest extends NemAppsUnitTest {


	@Test
	public void testBlockHash() {
		try {
			assertNotNull(BlockApi.getBlock("aeac0c3eeb1d2b441d012eadbe497b11ee8b67a8d5e0e981c698f2c7253637aa"));
		} catch (InterruptedException | ExecutionException e) {
			assert(false);
		}
	}
	
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
