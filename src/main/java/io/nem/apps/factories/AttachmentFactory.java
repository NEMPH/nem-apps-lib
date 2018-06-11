package io.nem.apps.factories;

import org.nem.core.model.Message;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.mosaic.Mosaic;





/**
 * A factory for creating Attachment objects.
 */
public class AttachmentFactory {

	/**
	 * Creates a new Attachment object.
	 *
	 * @return the transfer transaction attachment
	 */
	public static TransferTransactionAttachment createTransferTransactionAttachment() {
		return new TransferTransactionAttachment();
	}

	/**
	 * Creates a new Attachment object.
	 *
	 * @param message
	 *            the message
	 * @return the transfer transaction attachment
	 */
	public static TransferTransactionAttachment createTransferTransactionAttachmentMessage(Message message) {
		return new TransferTransactionAttachment(message);
	}
	
	/**
	 * Creates a new Attachment object.
	 *
	 * @param mosaic the mosaic
	 * @return the transfer transaction attachment
	 */
	public static TransferTransactionAttachment createTransferTransactionAttachmentMosaic(Mosaic mosaic) {
		TransferTransactionAttachment attachment = new TransferTransactionAttachment();
		attachment.addMosaic(mosaic);
		return attachment;
	}

}
