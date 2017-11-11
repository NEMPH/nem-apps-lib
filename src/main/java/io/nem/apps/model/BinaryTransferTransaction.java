package io.nem.apps.model;

import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;

public class BinaryTransferTransaction extends TransferTransaction {

	private String encryptedMessage;

	public BinaryTransferTransaction(TimeInstant timeStamp, Account sender, Account recipient, Amount amount,
			TransferTransactionAttachment attachment) {
		super(timeStamp, sender, recipient, amount, attachment);
	}

	public BinaryTransferTransaction(int version, TimeInstant timeStamp, Account sender, Account recipient,
			Amount amount, TransferTransactionAttachment attachment) {
		super(version, timeStamp, sender, recipient, amount, attachment);
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}


}
