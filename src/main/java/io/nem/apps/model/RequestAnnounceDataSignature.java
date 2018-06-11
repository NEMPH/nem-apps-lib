package io.nem.apps.model;

import java.io.Serializable;


/**
 * The Class RequestAnnounceDataSignature.
 */
public class RequestAnnounceDataSignature implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
//	private String hexedEncryptedMessage;
//	private String addressFrom;
/** The data. */
//	private String addressTo;
	private String data;
	
	/** The signature. */
	private String signature;


//	public String getHexedEncryptedMessage() {
//		return hexedEncryptedMessage;
//	}
//
//	public void setHexedEncryptedMessage(String hexedEncryptedMessage) {
//		this.hexedEncryptedMessage = hexedEncryptedMessage;
//	}

	/**
 * Gets the serialversionuid.
 *
 * @return the serialversionuid
 */
public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the signature.
	 *
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * Sets the signature.
	 *
	 * @param signature the new signature
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

//	public String getAddressFrom() {
//		return addressFrom;
//	}
//
//	public void setAddressFrom(String addressFrom) {
//		this.addressFrom = addressFrom;
//	}
//
//	public String getAddressTo() {
//		return addressTo;
//	}
//
//	public void setAddressTo(String addressTo) {
//		this.addressTo = addressTo;
//	}

}
