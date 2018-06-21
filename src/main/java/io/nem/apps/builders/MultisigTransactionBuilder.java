package io.nem.apps.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;
import org.nem.core.time.TimeInstant;
import io.nem.apps.service.NemAppsLibGlobals;
import io.nem.apps.util.TransactionSenderUtil;


/**
 * The Class MultisigTransactionBuilder.
 */
public class MultisigTransactionBuilder {

	/**
	 * Instantiates a new multisig transaction builder.
	 */
	private MultisigTransactionBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ITransaction sender(Account sender) {
		return new MultisigTransactionBuilder.Builder(sender);
	}

	/**
	 * The Interface ITransaction.
	 */
	public interface ITransaction {

		/**
		 * Other transaction.
		 *
		 * @param transaction
		 *            the transaction
		 * @return the i build
		 */
		IBuild otherTransaction(Transaction transaction);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Time stamp.
		 *
		 * @param timeInstance
		 *            the time instance
		 * @return the i build
		 */
		IBuild timeStamp(TimeInstant timeInstance);

		/**
		 * Sign by.
		 *
		 * @param account
		 *            the account
		 * @return the i build
		 */
		IBuild signBy(Account account);

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
		 * @return the nem announce result
		 */
		NemAnnounceResult buildAndSendMultisigTransaction();

		/**
		 * Builds the and send future multisig transaction.
		 *
		 * @return the completable future
		 */
		CompletableFuture<Deserializer> buildAndSendFutureMultisigTransaction();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ITransaction, IBuild {

		/** The instance. */
		private MultisigTransaction instance;

		/** The time stamp. */
		// constructor
		private TimeInstant timeStamp;

		/** The sender. */
		private Account sender;

		/** The other transaction. */
		private Transaction otherTransaction;

		/** The signature. */
		private Signature signature;

		/** The fee. */
		// secondary
		private Amount fee;

		/** The fee calculator. */
		private TransactionFeeCalculator feeCalculator;

		/** The sign by. */
		private Account signBy;

		/** The deadline. */
		private TimeInstant deadline;

		/** The multisig signature. */
		private List<MultisigSignatureTransaction> multisigSignature = new ArrayList<MultisigSignatureTransaction>();

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender
		 *            the sender
		 */
		public Builder(Account sender) {
			this.sender = sender;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.MultisigTransactionBuilder.IBuild#
		 * buildMultisigTransaction()
		 */
		@Override
		public MultisigTransaction buildMultisigTransaction() {
			if (this.timeStamp == null) {
				this.timeStamp = NemAppsLibGlobals.TIME_PROVIDER.getCurrentTime();
			}
			instance = new MultisigTransaction(this.timeStamp, this.sender, this.otherTransaction);

			if (this.fee == null && this.feeCalculator == null) {
				instance.setFee(NemAppsLibGlobals.getGlobalMultisigTransactionFee(this.sender.getAddress().getEncoded()).calculateMinimumFee(instance));
			} else {

				if (this.fee != null) {
					instance.setFee(this.fee);
				} else if (this.feeCalculator != null) {
					TransactionFeeCalculator feeCalculator;
					if (this.feeCalculator != null) {
						feeCalculator = this.feeCalculator;
					} else {
						feeCalculator = NemAppsLibGlobals.getGlobalMultisigTransactionFee(this.sender.getAddress().getEncoded());
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
			if (this.signBy != null) {
				instance.signBy(this.signBy);
			}

			if (this.multisigSignature.size() > 0) {
				for (MultisigSignatureTransaction multiSigSignatureTransaction : this.multisigSignature) {
					instance.addSignature(multiSigSignatureTransaction);
				}
			}
			instance.sign();
			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.MultisigTransactionBuilder.IBuild#
		 * buildAndSendMultisigTransaction()
		 */
		@Override
		public NemAnnounceResult buildAndSendMultisigTransaction() {
			return TransactionSenderUtil.sendMultiSigTransaction(this.buildMultisigTransaction());
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
			this.fee = amount;
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
			this.deadline = timeInstant;
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
			this.signature = signature;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigTransactionBuilder.IBuild#timeStamp(org.
		 * nem.core.time.TimeInstant)
		 */
		@Override
		public IBuild timeStamp(TimeInstant timeInstance) {
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigTransactionBuilder.IBuild#signBy(org.nem
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
		 * @see
		 * io.nem.apps.builders.MultisigTransactionBuilder.IBuild#feeCalculator(
		 * org.nem.core.model.TransactionFeeCalculator)
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
		 * io.nem.apps.builders.MultisigTransactionBuilder.IBuild#addSignature(
		 * org.nem.core.model.MultisigSignatureTransaction)
		 */
		@Override
		public IBuild addSignature(MultisigSignatureTransaction signature) {
			this.multisigSignature.add(signature);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.MultisigTransactionBuilder.ITransaction#
		 * otherTransaction(org.nem.core.model.Transaction)
		 */
		@Override
		public IBuild otherTransaction(Transaction transaction) {
			this.otherTransaction = transaction;
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.apps.builders.MultisigTransactionBuilder.IBuild#buildAndSendFutureMultisigTransaction()
		 */
		@Override
		public CompletableFuture<Deserializer> buildAndSendFutureMultisigTransaction() {
			return TransactionSenderUtil.sendFutureMultiSigTransaction(this.buildMultisigTransaction());
		}

	}

}
