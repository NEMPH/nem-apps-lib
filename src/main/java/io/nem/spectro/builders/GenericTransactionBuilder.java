package io.nem.spectro.builders;

import org.nem.core.crypto.Signature;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;
import io.nem.spectro.factories.AttachmentFactory;
import io.nem.spectro.model.SpectroTransaction;
import io.nem.spectro.service.BlockchainTransactionService;
import io.nem.spectro.service.Globals;

/**
 * The Class TransactionBuilder.
 */
public class GenericTransactionBuilder {

	/**
	 * Instantiates a new transaction builder.
	 */
	public GenericTransactionBuilder() {
		// create this object via TransactionBuilder.
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public ISender sender(Account sender) {
		return new GenericTransactionBuilder.Builder(sender);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {

		/**
		 * Recipient.
		 *
		 * @param recipient
		 *            the recipient
		 * @return the i recipient
		 */
		IBuild recipient(Account recipient);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		IBuild fee(Amount amount);

		IBuild fee(TransactionFeeCalculator feeCalculator);

		/**
		 * Amount.
		 *
		 * @param amount
		 *            the amount
		 * @return the i build
		 */
		IBuild amount(Long amount);

		/**
		 * Message.
		 *
		 * @param message
		 *            the message
		 * @param messageType
		 *            the message type
		 * @return the i build
		 */
		IBuild message(String message, int messageType);

		/**
		 * Message.
		 *
		 * @param message
		 *            the message
		 * @param messageType
		 *            the message type
		 * @return the i build
		 */
		IBuild message(byte[] message, int messageType);

		/**
		 * Attachment.
		 *
		 * @param attachment
		 *            the attachment
		 * @return the i build
		 */
		IBuild attachment(TransferTransactionAttachment attachment);

		/**
		 * Deadline.
		 *
		 * @param timeInstant
		 *            the time instant
		 * @return the i build
		 */
		IBuild deadline(TimeInstant timeInstant);

		/**
		 * Signature.
		 *
		 * @param signature
		 *            the signature
		 * @return the i build
		 */
		IBuild signature(Signature signature);

		/**
		 * Builds the transaction.
		 *
		 * @return the transfer transaction
		 */
		TransferTransaction buildTransaction();

		/**
		 * Builds the and send transaction.
		 *
		 * @return the transaction
		 */
		SpectroTransaction buildAndSendTransaction();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		/** The instance. */
		private SpectroTransaction instance = new SpectroTransaction();

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender
		 *            the sender
		 */
		public Builder(Account sender) {
			instance.setSenderAccount(sender);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IRecipient#amount(java.lang.
		 * Long)
		 */
		@Override
		public IBuild amount(Long amount) {
			instance.setAmount(amount);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#attachment(org.nem.
		 * core.model.TransferTransactionAttachment)
		 */
		@Override
		public IBuild attachment(TransferTransactionAttachment attachment) {
			instance.setAttachment(attachment);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#
		 * buildAndSendTransaction()
		 */
		@Override
		public SpectroTransaction buildAndSendTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}

			BlockchainTransactionService.createAndSendTransaction(instance);
			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#fee(org.nem.core.model
		 * .primitive.Amount)
		 */
		@Override
		public IBuild fee(Amount amount) {
			instance.setFee(amount);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#deadline(org.nem.core.
		 * time.TimeInstant)
		 */
		@Override
		public IBuild deadline(TimeInstant timeInstant) {
			instance.setDeadline(timeInstant);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#signature(org.nem.core
		 * .crypto.Signature)
		 */
		@Override
		public IBuild signature(Signature signature) {
			instance.setSignature(signature);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#buildTransaction()
		 */
		@Override
		public TransferTransaction buildTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}
			TransferTransaction trans = (TransferTransaction) BlockchainTransactionService.createTransaction(instance);

			return trans;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.spectro.builders.TransactionBuilder.IBuild#message(java.lang.
		 * String, org.nem.core.model.MessageTypes)
		 */
		@Override
		public IBuild message(String message, int messageType) {
			Message transactionMessage = null;
			if (messageType == MessageTypes.SECURE) {
				transactionMessage = SecureMessage.fromDecodedPayload(instance.getSenderAccount(),
						instance.getRecipientAccount(), message.getBytes());
			} else {
				transactionMessage = new PlainMessage(message.getBytes());
			}

			if (instance.getAttachment() == null) {
				instance.setAttachment(AttachmentFactory.createTransferTransactionAttachment(transactionMessage));
			} else {
				instance.getAttachment().setMessage(transactionMessage);
			}

			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.spectro.builders.TransactionBuilder.IBuild#message(byte[],
		 * org.nem.core.model.MessageTypes)
		 */
		@Override
		public IBuild message(byte[] message, int messageType) {
			Message transactionMessage = null;
			if (messageType == MessageTypes.SECURE) {
				transactionMessage = SecureMessage.fromDecodedPayload(instance.getSenderAccount(),
						instance.getRecipientAccount(), message);
			} else {
				transactionMessage = new PlainMessage(message);
			}

			if (instance.getAttachment() == null) {
				instance.setAttachment(AttachmentFactory.createTransferTransactionAttachment(transactionMessage));
			} else {
				instance.getAttachment().setMessage(transactionMessage);
			}
			return this;
		}

		@Override
		public IBuild fee(TransactionFeeCalculator feeCalculator) {
			instance.setFeeCalculator(feeCalculator);
			return this;
		}

		@Override
		public IBuild recipient(Account recipient) {
			instance.setRecipientAccount(recipient);
			return this;
		}

	}

}
