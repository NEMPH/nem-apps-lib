package io.nem.apps.builders;

import org.nem.core.messages.SecureMessage;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.primitive.Quantity;





/**
 * The Class SecureMessageAttachmentBuilder.
 */
public class SecureMessageAttachmentBuilder {

	/**
	 * Instantiates a new secure message attachment builder.
	 */
	public SecureMessageAttachmentBuilder() {
	}

	/**
	 * Message.
	 *
	 * @param message
	 *            the message
	 * @return the i message
	 */
	public static IMessage message(SecureMessage message) {
		return new SecureMessageAttachmentBuilder.Builder(message);
	}

	/**
	 * The Interface IMessage.
	 */
	public interface IMessage {

		/**
		 * Message.
		 *
		 * @param message
		 *            the message
		 * @return the i build
		 */
		IBuild message(SecureMessage message);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Adds the mosaic.
		 *
		 * @param mosaic
		 *            the mosaic
		 * @return the i build
		 */
		IBuild addMosaic(Mosaic mosaic);

		/**
		 * Adds the mosaic.
		 *
		 * @param mosaicId
		 *            the mosaic id
		 * @param quantity
		 *            the quantity
		 * @return the i build
		 */
		IBuild addMosaic(MosaicId mosaicId, Quantity quantity);

		/**
		 * Builds the message.
		 *
		 * @return the transfer transaction attachment
		 */
		TransferTransactionAttachment buildMessage();
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder implements IMessage, IBuild {

		/** The instance. */
		TransferTransactionAttachment instance = new TransferTransactionAttachment();

		/**
		 * Instantiates a new builder.
		 *
		 * @param message
		 *            the message
		 */
		public Builder(SecureMessage message) {
			instance.setMessage(message);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.SecureMessageAttachmentBuilder.IBuild#addMosaic(org.
		 * nem.core.model.mosaic.Mosaic)
		 */
		@Override
		public IBuild addMosaic(Mosaic mosaic) {
			instance.addMosaic(mosaic);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.SecureMessageAttachmentBuilder.IBuild#addMosaic(org.
		 * nem.core.model.mosaic.MosaicId,
		 * org.nem.core.model.primitive.Quantity)
		 */
		@Override
		public IBuild addMosaic(MosaicId mosaicId, Quantity quantity) {
			instance.addMosaic(mosaicId, quantity);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.SecureMessageAttachmentBuilder.IBuild#buildMessage()
		 */
		@Override
		public TransferTransactionAttachment buildMessage() {
			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.SecureMessageAttachmentBuilder.IMessage#message(org.
		 * nem.core.messages.SecureMessage)
		 */
		@Override
		public IBuild message(SecureMessage message) {
			instance.setMessage(message);
			return this;
		}

	}

}
