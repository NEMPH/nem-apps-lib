//package io.nem.apps.util;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Hashtable;
//
//import javax.imageio.ImageIO;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
//
//
///**
// * The Class QRCodeUtils.
// */
//public class QRCodeUtils {
//
//	/**
//	 * Creates the QR image.
//	 *
//	 * @param qrFile the qr file
//	 * @param qrCodeText the qr code text
//	 * @param size the size
//	 * @param fileType the file type
//	 * @throws WriterException the writer exception
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 */
//	@SuppressWarnings("unchecked")
//	public static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
//			throws WriterException, IOException {
//		// Create the ByteMatrix for the QR-Code that encodes the given String
//		@SuppressWarnings("rawtypes")
//		Hashtable hintMap = new Hashtable();
//		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//
//		QRCodeWriter qrCodeWriter = new QRCodeWriter();
//		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
//		// Make the BufferedImage that are to hold the QRCode
//		int matrixWidth = byteMatrix.getWidth();
//		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
//		image.createGraphics();
//
//		Graphics2D graphics = (Graphics2D) image.getGraphics();
//		graphics.setColor(Color.WHITE);
//		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
//		// Paint and save the image using the ByteMatrix
//		graphics.setColor(Color.BLACK);
//
//		for (int i = 0; i < matrixWidth; i++) {
//			for (int j = 0; j < matrixWidth; j++) {
//				if (byteMatrix.get(i, j)) {
//					graphics.fillRect(i, j, 1, 1);
//				}
//			}
//		}
//		ImageIO.write(image, fileType, qrFile);
//	}
//}
