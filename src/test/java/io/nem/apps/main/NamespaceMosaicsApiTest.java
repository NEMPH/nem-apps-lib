package io.nem.apps.main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.UnconfirmedTransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;
import io.nem.apps.api.AccountApi;
import io.nem.apps.api.NamespaceMosaicsApi;
import io.nem.apps.api.TransactionApi;
import io.nem.apps.service.NemAppsLibGlobals;

public class NamespaceMosaicsApiTest extends NemAppsUnitTest {

	@Test
	public void testGetNamespaceRootPage() {
		try {
			
			System.out.println(NamespaceMosaicsApi.getNamespaceRootPage());
			assertNotNull(NamespaceMosaicsApi.getNamespaceRootPage());
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
		assert(true);
	}

	@Test
	public void testGetNamespace() {
		try {
			System.out.println(NamespaceMosaicsApi.getNamespace("proximax"));
			assertNotNull(NamespaceMosaicsApi.getNamespace("proximax"));
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
	}

	@Test
	public void testGetNamespaceMosaicDefinitionPage() {
		try {
			System.out.println(NamespaceMosaicsApi.getNamespaceMosaicDefinitionPage("proximax").get(0).getEntity().getId().getNamespaceId());
			System.out.println(NamespaceMosaicsApi.getNamespaceMosaicDefinitionPage("proximax").get(0).getEntity().getId().getName());
			assertNotNull(NamespaceMosaicsApi.getNamespaceMosaicDefinitionPage("proximax").get(0).getEntity().getId().getNamespaceId());
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
	}
	
	@Test
	public void testGetNamespaceMosaicDefinitionPageName() {
		try {
			System.out.println(NamespaceMosaicsApi.getNamespaceMosaicDefinitionPage("landregistry1","1930").get(0).getEntity().getId());
			assertNotNull(NamespaceMosaicsApi.getNamespaceMosaicDefinitionPage("landregistry1","1930"));
		} catch (InterruptedException | ExecutionException e) {
			assert (false);
		}
	}

}
