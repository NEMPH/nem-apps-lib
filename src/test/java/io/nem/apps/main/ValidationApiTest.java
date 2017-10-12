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
		assertFalse(ValidationApi.isAddressValid("123123",new NodeEndpoint("http","104.128.226.60",7890)));
		assertFalse(ValidationApi.isAddressValid("123123","http","104.128.226.60",7890));
	}
	
	@Test
	public void testValidationSuccess() {
		assertTrue(ValidationApi.isAddressValid("TBPJCYR4XKGPGC3JHINJBJTW57ZLWTABDFLZSTMD"));
		assertTrue(ValidationApi.isAddressValid("TBPJCYR4XKGPGC3JHINJBJTW57ZLWTABDFLZSTMD",new NodeEndpoint("http","104.128.226.60",7890)));
		assertTrue(ValidationApi.isAddressValid("TBPJCYR4XKGPGC3JHINJBJTW57ZLWTABDFLZSTMD","http","104.128.226.60",7890));
	}
	
	@Test
	public void testLightValidation() {
		assertTrue(ValidationApi.isAddressPatternValid("TBPJCY-R4XKGP-GC3JHI-NJBJTW-57ZLWT-ABDFLZ-STMD"));
		assertTrue(ValidationApi.isAddressPatternValid("TBPJCYR4XKGPGC3JHINJBJTW57ZLWTABDFLZSTMD"));
	}
}
