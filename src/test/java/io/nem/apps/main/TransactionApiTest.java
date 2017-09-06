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
import io.nem.apps.service.Globals;

public class TransactionApiTest extends NemAppsUnitTest {

	@Test
	public void testAccountApiSingleTransaction() {
		System.out.println(TransactionApi.getTransaction("cefde07ee2e1485fcaca3e681e8c18d2c6da205cf16653deede558af7f122bf9"));
		assertNotNull(TransactionApi.getTransaction("cefde07ee2e1485fcaca3e681e8c18d2c6da205cf16653deede558af7f122bf9"));
	}
	
	@Test
	public void testAccountApiAllTransaction() {
		assertNotNull(TransactionApi.getAllTransactions(MIJIN_DM_ADDRESS));
	}
	
	@Test
	public void testAccountApiSingleTransferTransaction() {
		System.out.println(TransactionApi.getAllTransactions(MIJIN_DM_ADDRESS).get(0).getEntity().getSigner().getAddress().getPublicKey());
		System.out.println(TransactionApi.getAllTransactions(MIJIN_DM_ADDRESS).get(0).getMetaData().getHash().toString());
		System.out.println(((TransferTransaction)TransactionApi.getAllTransactions(MIJIN_DM_ADDRESS).get(0).getEntity()).getAttachment().getMessage());
		assertNotNull(TransactionApi.getAllTransactions(MIJIN_DM_ADDRESS).get(0).getClass());
	}


}
