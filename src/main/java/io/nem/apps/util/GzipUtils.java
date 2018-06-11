package io.nem.apps.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;




/**
 * The Class GzipUtils.
 */
public class GzipUtils {

	/**
	 * Compress.
	 *
	 * @param str the str
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] compress(final String str) throws IOException {
		if ((str == null) || (str.length() == 0)) {
			return null;
		}
		ByteArrayOutputStream obj = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(obj);
		gzip.write(str.getBytes("UTF-8"));
		gzip.flush();
		gzip.close();
		return obj.toByteArray();
	}

	/**
	 * Decompress.
	 *
	 * @param compressed the compressed
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String decompress(final byte[] compressed) throws IOException {
		String outStr = "";
		if ((compressed == null) || (compressed.length == 0)) {
			return "";
		}
		if (isCompressed(compressed)) {
			GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				outStr += line;
			}
		} else {
			outStr = new String(compressed);
		}
		return outStr;
	}

	/**
	 * Checks if is compressed.
	 *
	 * @param compressed the compressed
	 * @return true, if is compressed
	 */
	public static boolean isCompressed(final byte[] compressed) {
		return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC))
				&& (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
	}
}
