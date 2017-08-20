package io.nem.spectro.builders;

import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;

import io.nem.spectro.model.SpectroMultisigTransaction;
import io.nem.spectro.service.BlockchainTransactionService;
import io.nem.spectro.service.Globals;


/**
 * The Class MultisigTransactionBuilder.
 */
public class MultisigTransactionBuilder {

	/**
	 * Instantiates a new multisig transaction builder.
	 */
	public MultisigTransactionBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public ISender sender(Account sender) {
		return new MultisigTransactionBuilder.Builder(sender);
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
		IRecipient recipient(Account recipient);
	}

	/**
	 * The Interface IRecipient.
	 */
	public interface IRecipient {

		/**
		 * Multisig.
		 *
		 * @param multisig
		 *            the multisig
		 * @return the i build
		 */
		IBuild multisig(Account multisig);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Amount.
		 *
		 * @param amount
		 *            the amount
		 * @return the i build
		 */
		IBuild amount(Long amount);

		/**
		 * Attachment.
		 *
		 * @param attachment
		 *            the attachment
		 * @return the i build
		 */
		IBuild attachment(TransferTransactionAttachment attachment);

		/**
		 * Fee.
		 *
		 * @param amount the amount
		 * @return the i build
		 */
		IBuild fee(Amount amount);

		/**
		 * Fee calculator.
		 *
		 * @param feeCalculator the fee calculator
		 * @return the i build
		 */
		IBuild feeCalculator(TransactionFeeCalculator feeCalculator);

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
		 * Adds the signature.
		 *
		 * @param signature
		 *            the signature
		 * @return the i build
		 */
		IBuild addSignature(MultisigSignatureTransaction signature);

		/**
		 * Builds the multisig transaction.
		 *
		 * @return the multisig transaction
		 */
		MultisigTransaction buildMultisigTransaction();

		/**
		 * Builds the and send multisig transaction.
		 *
		 * @return the multisig transaction
		 */
		SpectroMultisigTransaction buildAndSendMultisigTransaction();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IRecipient, IBuild {

		/** The instance. */
		private SpectroMultisigTransaction instance = new SpectroMultisigTransaction();

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
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IBuild#amount(java.lang.
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
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IRecipient#multisig(org.
		 * nem.core.model.Account)
		 */
		@Override
		public IBuild multisig(Account multisig) {
			instance.setMultisigAccount(multisig);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.ISender#recipient(org.nem.
		 * core.model.Account)
		 */
		@Override
		public IRecipient recipient(Account recipient) {
			instance.setRecipientAccount(recipient);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IBuild#attachment(org.nem.
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
		 * @see io.nem.builders.MultisigTransactionBuilder.IBuild#
		 * buildMultisigTransaction()
		 */
		@Override
		public MultisigTransaction buildMultisigTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}
			Transaction transaction = BlockchainTransactionService.createTransaction(instance.getTimeInstant(),
					instance.getSenderAccount(), instance.getRecipientAccount(), instance.getAmount(),
					instance.getAttachment());

			return (MultisigTransaction) BlockchainTransactionService.createMultisigTransaction(
					instance.getTimeInstant(), instance.getSenderAccount(), instance.getRecipientAccount(),
					instance.getAmount(), transaction);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.MultisigTransactionBuilder.IBuild#
		 * buildAndSendMultisigTransaction()
		 */
		@Override
		public SpectroMultisigTransaction buildAndSendMultisigTransaction() {
			if (instance.getTimeInstant() == null) {
				instance.setTimeInstant(Globals.TIME_PROVIDER.getCurrentTime());
			}
			BlockchainTransactionService.createAndSendMultisigTransaction(instance);
			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IBuild#fee(org.nem.core.
		 * model.primitive.Amount)
		 */
		@Override
		public IBuild fee(Amount amount) {
			instance.setFee(amount);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IBuild#deadline(org.nem.
		 * core.time.TimeInstant)
		 */
		@Override
		public IBuild deadline(TimeInstant timeInstant) {
			instance.setDeadline(timeInstant);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IBuild#signature(org.nem.
		 * core.crypto.Signature)
		 */
		@Override
		public IBuild signature(Signature signature) {
			instance.setSignature(signature);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.builders.MultisigTransactionBuilder.IBuild#addSignature(org.
		 * nem.core.model.MultisigSignatureTransaction)
		 */
		@Override
		public IBuild addSignature(MultisigSignatureTransaction signature) {
			instance.addMultisigSignature(signature);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.spectro.builders.MultisigTransactionBuilder.IBuild#feeCalculator(org.nem.core.model.TransactionFeeCalculator)
		 */
		@Override
		public IBuild feeCalculator(TransactionFeeCalculator feeCalculator) {
			instance.setFeeCalculator(feeCalculator);
			return this;
		}
	}

}
