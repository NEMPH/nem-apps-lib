package io.nem.apps.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

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