package io.nem.apps.main;

import static org.junit.Assert.assertFalse;
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
import io.nem.apps.api.ValidationApi;
import io.nem.apps.service.Globals;

public class ValidationApiTest extends NemAppsUnitTest {

	@Test
	public void testValidationFail() {
		assertFalse(ValidationApi.isAddressValid("123123"));
	}
	
	@Test
	public void testValidationSuccess() {
		assertTrue(ValidationApi.isAddressValid("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW"));
	}
}
