package io.nem.apps.model;

import java.io.Serializable;

public class RequestAnnounceDataSignature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String encryptedMessage;
	private String addressFrom;
	private String addressTo;
	private String data;
	private String signature;

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}

	public String getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(String addressTo) {
		this.addressTo = addressTo;
	}

}
