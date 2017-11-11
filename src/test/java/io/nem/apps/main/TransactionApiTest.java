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
import io.nem.apps.api.TransactionApi;
import io.nem.apps.service.NemAppsLibGlobals;

public class TransactionApiTest extends NemAppsUnitTest {

	@Test
	public void testAccountApiSingleTransaction() {
		try {
			assertNotNull(
					TransactionApi.getTransaction("e5b0bf467711bfea9964dc7b3e854da655bd3e50b5e7177735acfe0ff37bf217"));
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
		assert(true);
	}

	@Test
	public void testAccountApiAllTransaction() {
		try {
			assertNotNull(TransactionApi.getAllTransactions(TEST_DM_ADDRESS));
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
	}

	@Test
	public void testAccountApiSingleTransferTransaction() {
		try {
			assertNotNull(TransactionApi.getAllTransactions(TEST_DM_ADDRESS).get(0).getClass());
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
	}

}
