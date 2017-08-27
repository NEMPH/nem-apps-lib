package io.nem.apps.main;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import com.google.zxing.WriterException;

import io.nem.apps.util.QRCodeUtils;

public class GenerateQRCode {

	@Test
	public void testQrCodeGeneration() {
		String qrCodeText = "privatekey";
		String filePath = "C:\\Users\\Alvin\\a.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		try {
			QRCodeUtils.createQRImage(qrFile, qrCodeText, size, fileType);
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE");
	}

}