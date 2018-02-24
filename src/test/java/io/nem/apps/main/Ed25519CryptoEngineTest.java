package io.nem.apps.main;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.ed25519.Ed25519CryptoEngine;
import org.nem.core.utils.HexEncoder;

public class Ed25519CryptoEngineTest {

	@Test
	public void testSign() throws IOException {
		
		Ed25519CryptoEngine engine = (Ed25519CryptoEngine) CryptoEngines.ed25519Engine();
//		System.out.println(engine.createDsaSigner(
//				new KeyPair(PrivateKey.fromHexString("8e75544a9f90253fcd880ea73b78f3bc84e1fad032c0cd1062f5694c4fc28bcd"))).sign(
//						"hey".getBytes()).toString());
		//8b99649bd3d51c015e4d3948b4690757ea0ac9ca3b998d1039c41a13c86549ad35fa59eb31af1dac6d8c7c1bdf2ff76cc64fc39c539c5bda7b21d3f4cd369003
		;
		byte[] encrypted = engine.createBlockCipher(
				new KeyPair(PrivateKey.fromHexString("8e75544a9f90253fcd880ea73b78f3bc84e1fad032c0cd1062f5694c4fc28bcd"),engine), 
				new KeyPair(PublicKey.fromHexString("d24fcd87f3d1f661a0dc15f658cbbffb51b1a13cea3ad99acf73df9b896aed94"),engine))
				.encrypt(FileUtils.readFileToByteArray(new File("D:\\Projects\\eworkspace\\proximaxsdks\\xpx-java-sdk\\git_push.sh")));
		//03dd94ac32ecf70dd7886f4754dff8a6abf86cd615dc15f7305d3d5b623cfece98b6da809cf7f402d1532d3df7a12d7f64d16c3e636a5e7616156d054798242f
		System.out.println(HexEncoder.getString(encrypted));
		byte[] decrypted = engine.createBlockCipher(
				new KeyPair(PublicKey.fromHexString("d24fcd87f3d1f661a0dc15f658cbbffb51b1a13cea3ad99acf73df9b896aed94"),engine),
				new KeyPair(PrivateKey.fromHexString("8e75544a9f90253fcd880ea73b78f3bc84e1fad032c0cd1062f5694c4fc28bcd"),engine))
				.decrypt(HexEncoder.getBytes(HexEncoder.getString(encrypted)));
		System.out.println(new String(decrypted,"UTF-8"));
		
		//HexEncoder.getBytes("8b99649bd3d51c015e4d3948b4690757ea0ac9ca3b998d1039c41a13c86549ad35fa59eb31af1dac6d8c7c1bdf2ff76cc64fc39c539c5bda7b21d3f4cd369003")
		
	}
}
