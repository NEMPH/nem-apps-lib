package io.nem.apps.main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.UnconfirmedTransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;
import io.nem.apps.api.AccountApi;
import io.nem.apps.service.Globals;

public class AccountApiTest extends NemAppsUnitTest {

	@Test
	public void testDeserializeAccount() {

		try {
			final CompletableFuture<Deserializer> des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(),
					NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=" + MIJIN_DM_ADDRESS);

			des.thenAcceptAsync(d -> {
				System.out.println(new AccountMetaDataPair(d).getEntity().getBalance());
			}).exceptionally(e -> {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}).get();

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeserializeAccountPk() {
		try {
			final CompletableFuture<Deserializer> des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(),
					NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=" + MIJIN_DM_ADDRESS);

			des.thenAcceptAsync(d -> {
				System.out.println(d.readObjectArray("data", UnconfirmedTransactionMetaDataPair::new).size());
				assertTrue(d.readObjectArray("data", UnconfirmedTransactionMetaDataPair::new).size() > 0);
			}).exceptionally(e -> {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}).get();
			assert (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAccountApiAddress() {
		assertNotNull(AccountApi.getAccountByAddress(MIJIN_DM_ADDRESS).getEntity());
	}

	@Test
	public void testAccountApiAllTransaction() {
		assertNotNull(AccountApi.getAllTransactions(MIJIN_DM_ADDRESS));
	}

	@Test
	public void testAccountApiAllOwnedMosaic() {
		assertNotNull(AccountApi.getAccountOwnedMosaic(MIJIN_DM_ADDRESS));

	}

	@Test
	public void testGenerteNewAccount() {
		assertNotNull(AccountApi.generateAccount().getAccount());
	}
}
