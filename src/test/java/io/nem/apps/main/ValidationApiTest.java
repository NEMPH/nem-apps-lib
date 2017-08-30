package io.nem.apps.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;import org.bouncycastle.crypto.tls.HeartbeatExtension;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.UnconfirmedTransactionMetaDataPair;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;
import org.nem.core.test.Utils;
import org.nem.core.utils.HexEncoder;

import io.nem.apps.api.AccountApi;
import io.nem.apps.api.ValidationApi;
import io.nem.apps.service.Globals;
import io.nem.apps.util.HexStringUtils;
import net.sf.json.util.NewBeanInstanceStrategy;

public class ValidationApiTest extends NemAppsUnitTest {

	@Test
	public void testValidationFail() {
		assertFalse(ValidationApi.isAddressValid("123123"));
		assertFalse(ValidationApi.isAddressValid("123123",new NodeEndpoint("http","a1.nem.foundation",7895)));
		assertFalse(ValidationApi.isAddressValid("123123","http","a1.nem.foundation",7895));
	}
	
	@Test
	public void testValidationSuccess() {
		assertTrue(ValidationApi.isAddressValid("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW"));
		assertTrue(ValidationApi.isAddressValid("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW",new NodeEndpoint("http","a1.nem.foundation",7895)));
		assertTrue(ValidationApi.isAddressValid("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW","http","a1.nem.foundation",7895));
	}
	
	@Test
	public void testLightValidation() {
		System.out.println(ValidationApi.isAddressPatternValid("NACCH2WPJYVQ3PLGMVZVRK5JI6POTJXXHLUG3P4J"));
	}
}
