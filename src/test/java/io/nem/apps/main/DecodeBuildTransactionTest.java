package io.nem.apps.main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import io.nem.apps.crypto.SecureMessageDecoder;


/**
 * The Class DecodeBuildTransactionTest.
 */
public class DecodeBuildTransactionTest extends NemAppsUnitTest {

	final String sampleMsg = "c621b0e0a3f20523f60f9d99394e716e598c575b9f3c06a613b5d1483805dcb22c08e09de166ac08ebbf7c3f953dda74d634e60d81ba35059a53bb6662b624d7243315d3af013630f17f2b2120869a364e39152b35aa502067166cd80c215db3c023ec4c28c9438d33d0c75e15093bbb7d84154476288d49017918f3cc2f90d9734400c45283d258068c6d2b4db5c1243ff32d008bb92d8841455f38b611fa67ecc4afc0761a5ec3931c4875850d98d64d00e87a93f1284847b6d62390ead93f714784f658c6651ae56b1cb9684339ee654204bdef601e2df33f68d51463e58d2f4cdb5570a33084717f7b24c2bdf9e745d6154c0260182f59d7901d04131aa636dc3f368fb4622a5fb8e65fc24cd3f9";
	final String simplePayload = "601882ece97b33f25e2e969877fd7b8e3e4c471e705897bc88429d86f9a8981b45edc730b7bd904e14e69f0d4c8570a550b09d18b4d6106bc2c59d7c932a52b2402de9b04ce864cf59eff9a132878fce04c722d3b60166357cdf95b382a1602b7bf16cd6f2c9008438a3526826391172";
	
	/**
	 * Test transaction hash.
	 */
	@Test
	public void testDecode() {
		
		try {
			if(isTestable())assertNotNull("Decode process completed.", SecureMessageDecoder.decode(this.senderPublicKeyPair,this.recipientPrivateKeyPair,sampleMsg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
