package io.nem.apps.model;

import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;


/**
 * The Class BinaryTransferTransaction.
 */
public class BinaryTransferTransaction extends TransferTransaction {

	/** The encrypted message. */
	private String encryptedMessage;

	/**
	 * Instantiates a new binary transfer transaction.
	 *
	 * @param timeStamp the time stamp
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param amount the amount
	 * @param attachment the attachment
	 */
	public BinaryTransferTransaction(TimeInstant timeStamp, Account sender, Account recipient, Amount amount,
			TransferTransactionAttachment attachment) {
		super(timeStamp, sender, recipient, amount, attachment);
	}

	/**
	 * Instantiates a new binary transfer transaction.
	 *
	 * @param version the version
	 * @param timeStamp the time stamp
	 * @param sender the sender
	 * @param recipient the recipient
	 * @param amount the amount
	 * @param attachment the attachment
	 */
	public BinaryTransferTransaction(int version, TimeInstant timeStamp, Account sender, Account recipient,
			Amount amount, TransferTransactionAttachment attachment) {
		super(version, timeStamp, sender, recipient, amount, attachment);
	}

	/**
	 * Gets the encrypted message.
	 *
	 * @return the encrypted message
	 */
	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	/**
	 * Sets the encrypted message.
	 *
	 * @param encryptedMessage the new encrypted message
	 */
	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}


}
