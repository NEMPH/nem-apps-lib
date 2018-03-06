package io.nem.apps.builders;

import java.util.concurrent.CompletableFuture;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.Signature;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.serialization.BinarySerializer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.time.TimeInstant;
import io.nem.apps.factories.AttachmentFactory;
import io.nem.apps.service.NemAppsLibGlobals;
import io.nem.apps.util.TransactionSenderUtil;
import io.nem.apps.model.BinaryTransferTransaction;
import io.nem.apps.model.RequestAnnounceDataSignature;

/**
 * The Class TransactionBuilder.
 */
public class BinaryTransferTransactionBuilder {

	/**
	 * Instantiates a new transaction builder.
	 */
	private BinaryTransferTransactionBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ISender sender(Account sender) {
		return new BinaryTransferTransactionBuilder.Builder(sender);
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ISender sender(String sender) {
		return new BinaryTransferTransactionBuilder.Builder(sender);
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
		IBuild recipient(String recipient);

		/**
		 * Recipient.
		 *
		 * @param recipient
		 *            the recipient
		 * @return the i build
		 */
		IBuild recipient(Account recipient);

		/**
		 * Recipients.
		 *
		 * @param recipients
		 *            the recipients
		 * @return the i build
		 */
		IBuild recipients(Account[] recipients);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Version.
		 *
		 * @param version
		 *            the version
		 * @return the i build
		 */
		IBuild version(int version);

		/**
		 * Sign by.
		 *
		 * @param account
		 *            the account
		 * @return the i build
		 */
		IBuild signBy(Account account);

		/**
		 * Time stamp.
		 *
		 * @param timeInstance
		 *            the time instance
		 * @return the i build
		 */
		IBuild timeStamp(TimeInstant timeInstance);

		/**
		 * Fee.
		 *
		 * @param amount
		 *            the amount
		 * @return the i build
		 */
		IBuild fee(Amount amount);

		/**
		 * Fee calculator.
		 *
		 * @param feeCalculator
		 *            the fee calculator
		 * @return the i build
		 */
		IBuild feeCalculator(TransactionFeeCalculator feeCalculator);

		/**
		 * Amount.
		 *
		 * @param amount
		 *            the amount
		 * @return the i build
		 */
		IBuild amount(Amount amount);

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
		 * Adds the mosaic.
		 *
		 * @param mosaic
		 *            the mosaic
		 * @return the i build
		 */
		IBuild addMosaic(Mosaic mosaic);

		IBuild addMosaics(Mosaic... mosaic);

		/**
		 * Adds the mosaic.
		 *
		 * @param mosaic
		 *            the mosaic
		 * @param quantity
		 *            the quantity
		 * @return the i build
		 */
		IBuild addMosaic(MosaicId mosaic, Quantity quantity);

		/**
		 * Encrypted message.
		 *
		 * @param encryptedMessage
		 *            the encrypted message
		 * @return the i build
		 */
		IBuild encryptedMessage(String encryptedMessage);

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
		 * Builds the and send transaction.
		 *
		 * @return the transaction
		 */
		BinaryTransferTransaction buildTransaction();

		/**
		 * Builds the unsigned transaction.
		 *
		 * @return the binary transfer transaction
		 */
		BinaryTransferTransaction buildUnsignedTransaction();

		/**
		 * Builds the and sign transaction.
		 *
		 * @return the request announce data signature
		 */
		RequestAnnounceDataSignature buildAndSignTransaction();

		/**
		 * Builds the and send transaction.
		 *
		 * @return the nem announce result
		 */
		NemAnnounceResult buildAndSendTransaction();

		/**
		 * Builds the and send future transaction.
		 *
		 * @return the completable future
		 */
		CompletableFuture<Deserializer> buildAndSendFutureTransaction();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		/** The instance. */
		private BinaryTransferTransaction instance;

		/** The version. */
		// constructor
		private int version;

		/** The time stamp. */
		private TimeInstant timeStamp;

		/** The sender. */
		private Account sender;

		/** The recipient. */
		private Account recipient;

		/** The amount. */
		private Amount amount;

		/** The attachment. */
		private TransferTransactionAttachment attachment;

		/** The signature. */
		private Signature signature;

		/** The deadline. */
		private TimeInstant deadline;

		/** The fee. */
		// secondary
		private Amount fee;

		/** The fee calculator. */
		private TransactionFeeCalculator feeCalculator;

		/** The sign by. */
		private Account signBy;

		/** The encrypted message. */
		private String encryptedMessage;

		/** The message. */
		private String message;

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender
		 *            the sender
		 */
		public Builder(Account sender) {
			this.sender = sender;
		}

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender
		 *            the sender
		 */
		public Builder(String sender) {
			this.sender = new Account(new KeyPair(PrivateKey.fromHexString(sender)));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.BinaryTransferTransactionBuilder.ISender#
		 * recipients(org.nem.core.model.Account[])
		 */
		@Override
		public IBuild recipients(Account[] recipients) {
			// TODO Auto-generated method stub
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.BinaryTransferTransactionBuilder.ISender#
		 * recipient(java.lang.String)
		 */
		@Override
		public IBuild recipient(String recipient) {
			this.recipient = new Account(Address.fromPublicKey(PublicKey.fromHexString(recipient)));
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.spectro.builders.GenericTransactionBuilder.ISender#recipient(
		 * org.nem.core.model.Account)
		 */
		@Override
		public IBuild recipient(Account recipient) {
			this.recipient = recipient;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IRecipient#amount(java.lang.
		 * Long)
		 */
		@Override
		public IBuild amount(Amount amount) {
			this.amount = amount;
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
			this.attachment = attachment;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#buildTransaction()
		 */
		@Override
		public BinaryTransferTransaction buildTransaction() {
			if (this.timeStamp == null) {
				this.timeStamp = NemAppsLibGlobals.TIME_PROVIDER.getCurrentTime();
			}

			if (this.amount == null) {
				this.amount(Amount.fromNem(0));
			}

			if (this.version == 0) {
				instance = new BinaryTransferTransaction(this.timeStamp, this.sender, this.recipient, this.amount,
						this.attachment);
			} else {
				instance = new BinaryTransferTransaction(this.version, this.timeStamp, this.sender, this.recipient,
						this.amount, this.attachment);
			}

//			if (this.fee == null && this.feeCalculator == null) {
//				instance.setFee(NemAppsLibGlobals.getGlobalTransactionFee().calculateMinimumFee(instance));
//			} else {
//
//				if (this.fee != null) {
//					instance.setFee(this.fee);
//				} else if (this.feeCalculator != null) {
//					TransactionFeeCalculator feeCalculator;
//					if (this.feeCalculator != null) {
//						feeCalculator = this.feeCalculator;
//					} else {
//						feeCalculator = NemAppsLibGlobals.getGlobalTransactionFee();
//					}
//					instance.setFee(feeCalculator.calculateMinimumFee(instance));
//				}
//
//			}

			if (this.deadline != null) {
				instance.setDeadline(this.deadline);
			} else {
				instance.setDeadline(this.timeStamp.addHours(23));
			}
			if (this.signature != null) {
				instance.setSignature(this.signature);
			}
			if (this.signBy != null) {
				instance.signBy(this.signBy);
			}
			if (this.encryptedMessage != null) {
				instance.setEncryptedMessage(this.encryptedMessage);
			}
			instance.sign();
			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.BinaryTransferTransactionBuilder.IBuild#
		 * buildUnsignedTransaction()
		 */
		@Override
		public BinaryTransferTransaction buildUnsignedTransaction() {
			if (this.timeStamp == null) {
				this.timeStamp = NemAppsLibGlobals.TIME_PROVIDER.getCurrentTime();
			}

			if (this.amount == null) {
				this.amount(Amount.fromNem(0));
			}

			if (this.version == 0) {
				instance = new BinaryTransferTransaction(this.timeStamp, this.sender, this.recipient, this.amount,
						this.attachment);
			} else {
				instance = new BinaryTransferTransaction(this.version, this.timeStamp, this.sender, this.recipient,
						this.amount, this.attachment);
			}

			if (this.fee == null && this.feeCalculator == null) {
				instance.setFee(NemAppsLibGlobals.getGlobalTransactionFee().calculateMinimumFee(instance));
			} else {

				if (this.fee != null) {
					instance.setFee(this.fee);
				} else if (this.feeCalculator != null) {
					TransactionFeeCalculator feeCalculator;
					if (this.feeCalculator != null) {
						feeCalculator = this.feeCalculator;
					} else {
						feeCalculator = NemAppsLibGlobals.getGlobalTransactionFee();
					}
					instance.setFee(feeCalculator.calculateMinimumFee(instance));
				}

			}


			if (this.deadline != null) {
				instance.setDeadline(this.deadline);
			} else {
				instance.setDeadline(this.timeStamp.addHours(23));
			}
			if (this.signature != null) {
				instance.setSignature(this.signature);
			}
			if (this.encryptedMessage != null) {
				instance.setEncryptedMessage(this.encryptedMessage);
			}
			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#
		 * buildAndSendTransaction()
		 */
		@Override
		public NemAnnounceResult buildAndSendTransaction() {
			return TransactionSenderUtil.sendTransferTransaction(this.buildTransaction());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#fee(org.nem.core.model
		 * .primitive.Amount)
		 */
		@Override
		public IBuild fee(Amount amount) {
			this.fee = amount;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#deadline(org.nem.core.
		 * time.TimeInstant)
		 */
		@Override
		public IBuild deadline(TimeInstant deadline) {
			this.deadline = deadline;
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
			this.signature = signature;
			return this;
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
			this.message = message;
			this.encryptedMessage = message;
			Message transactionMessage = null;
			if (messageType == MessageTypes.SECURE) {
				transactionMessage = SecureMessage.fromDecodedPayload(this.sender, this.recipient, message.getBytes());
			} else {
				transactionMessage = new PlainMessage(message.getBytes());
			}

			if (this.attachment == null) {
				this.attachment = (AttachmentFactory.createTransferTransactionAttachmentMessage(transactionMessage));
			} else {
				this.attachment.setMessage(transactionMessage);
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
			this.message = new String(message);
			Message transactionMessage = null;
			if (messageType == MessageTypes.SECURE) {
				transactionMessage = SecureMessage.fromDecodedPayload(this.sender, this.recipient, message);
			} else {
				transactionMessage = new PlainMessage(message);
			}

			if (this.attachment == null) {
				this.attachment = (AttachmentFactory.createTransferTransactionAttachmentMessage(transactionMessage));
			} else {
				this.attachment.setMessage(transactionMessage);
			}

			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.GenericTransactionBuilder.IBuild#
		 * feeCalculator(org.nem.core.model.TransactionFeeCalculator)
		 */
		@Override
		public IBuild feeCalculator(TransactionFeeCalculator feeCalculator) {
			this.feeCalculator = feeCalculator;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.TransferTransactionBuilder.IBuild#version(int)
		 */
		@Override
		public IBuild version(int version) {
			this.version = version;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.TransferTransactionBuilder.IBuild#timeStamp(org.
		 * nem.core.time.TimeInstant)
		 */
		@Override
		public IBuild timeStamp(TimeInstant timeInstance) {
			this.timeStamp = timeInstance;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.TransferTransactionBuilder.IBuild#signBy(org.nem
		 * .core.model.Account)
		 */
		@Override
		public IBuild signBy(Account account) {
			this.signBy = account;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.BinaryTransferTransactionBuilder.IBuild#
		 * buildAndSendFutureTransaction()
		 */
		@Override
		public CompletableFuture<Deserializer> buildAndSendFutureTransaction() {
			return TransactionSenderUtil.sendFutureTransferTransaction(this.buildTransaction());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.BinaryTransferTransactionBuilder.IBuild#
		 * encryptedMessage(java.lang.String)
		 */
		@Override
		public IBuild encryptedMessage(String message) {
			this.encryptedMessage = message;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.BinaryTransferTransactionBuilder.IBuild#
		 * buildAndSignTransaction()
		 */
		@Override
		public RequestAnnounceDataSignature buildAndSignTransaction() {
			this.buildUnsignedTransaction().sign();
			final byte[] data = BinarySerializer.serializeToBytes(instance.asNonVerifiable());
			final RequestAnnounce request = new RequestAnnounce(data, instance.getSignature().getBytes());
			RequestAnnounceDataSignature requestAnnounceDataSignature = new RequestAnnounceDataSignature();
			requestAnnounceDataSignature.setData(
					new JsonDeserializer(JsonSerializer.serializeToJson(request), null).readString("data", 5000));
			requestAnnounceDataSignature.setSignature(
					new JsonDeserializer(JsonSerializer.serializeToJson(request), null).readString("signature", 5000));
			return requestAnnounceDataSignature;

		}

		@Override
		public IBuild addMosaic(Mosaic mosaic) {
			if (this.attachment == null) {
				this.attachment = new TransferTransactionAttachment();
			} else {
				this.attachment.addMosaic(mosaic);
			}
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.TransferTransactionBuilder.IBuild#addMosaic(org.
		 * nem.core.model.mosaic.MosaicId,
		 * org.nem.core.model.primitive.Quantity)
		 */
		@Override
		public IBuild addMosaic(MosaicId mosaic, Quantity quantity) {
			this.attachment.addMosaic(mosaic, quantity);
			return this;
		}

		@Override
		public IBuild addMosaics(Mosaic... mosaics) {
			for (Mosaic mosaic : mosaics) {
				this.attachment.addMosaic(mosaic);
			}
			return this;
		}
	}

}
