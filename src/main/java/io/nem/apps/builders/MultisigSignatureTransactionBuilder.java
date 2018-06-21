package io.nem.apps.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.nem.core.crypto.Hash;
import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;
import org.nem.core.time.TimeInstant;
import io.nem.apps.service.NemAppsLibGlobals;
import io.nem.apps.util.TransactionSenderUtil;


/**
 * The Class MultisigTransactionBuilder.
 */
public class MultisigSignatureTransactionBuilder {

	/**
	 * Instantiates a new multisig transaction builder.
	 */
	private MultisigSignatureTransactionBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param multisig
	 *            the multisig
	 * @return the i sender
	 */

	public static ISigner multisig(Account multisig) {
		return new MultisigSignatureTransactionBuilder.Builder(multisig);
	}

	/**
	 * The Interface ISigner.
	 */
	public interface ISigner {

		/**
		 * Signer.
		 *
		 * @param signer
		 *            the signer
		 * @return the i transaction
		 */
		ITransaction signer(Account signer);

		/**
		 * Start assign signers.
		 *
		 * @return the i signer
		 */
		ISigner startAssignSigners();

		/**
		 * Adds the signer.
		 *
		 * @param signer
		 *            the signer
		 * @return the i signer
		 */
		ISigner addSigner(Account signer);

		/**
		 * Adds the signers.
		 *
		 * @param signers
		 *            the signers
		 * @return the i signer
		 */
		ITransaction addSigners(List<Account> signers);

		/**
		 * End assign signers.
		 *
		 * @return the i transaction
		 */
		ITransaction endAssignSigners();
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

		/**
		 * Other transaction.
		 *
		 * @param hashTransaction
		 *            the hash transaction
		 * @return the i build
		 */
		IBuild otherTransaction(Hash hashTransaction);

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
		 * Co sign.
		 *
		 * @return the multisig signature transaction
		 */
		MultisigSignatureTransaction coSign();

		/**
		 * Co sign future.
		 *
		 * @return the completable future
		 */
		CompletableFuture<Deserializer> coSignFuture();

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISigner, ITransaction, IBuild {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ISigner#
		 * startAssignSigners()
		 */
		@Override
		public ISigner startAssignSigners() {
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ISigner#
		 * endAssignSigners()
		 */
		@Override
		public ITransaction endAssignSigners() {
			return this;
		}

		/** The instance. */
		private MultisigSignatureTransaction instance;

		/** The time stamp. */
		// constructor
		private TimeInstant timeStamp;

		/** The multisig. */
		private Account multisig;

		/** The other transaction. */
		private Transaction otherTransaction;

		/** The hash transaction. */
		private Hash hashTransaction;

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

		/** The signers. */
		private List<Account> signers = new ArrayList<Account>();

		/**
		 * Instantiates a new builder.
		 *
		 * @param multisig
		 *            the multisig
		 */
		public Builder(Account multisig) {
			this.multisig = multisig;

		}

		/**
		 * Builds the multisig signature transaction.
		 *
		 * @return the multisig signature transaction
		 */
		private MultisigSignatureTransaction buildMultisigSignatureTransaction() {
			if (this.timeStamp == null) {
				this.timeStamp = NemAppsLibGlobals.TIME_PROVIDER.getCurrentTime();
			}

			for (Account signer : this.signers) {
				if (this.otherTransaction != null) {
					instance = new MultisigSignatureTransaction(this.timeStamp, signer, this.multisig,
							this.otherTransaction);
				}
				if (this.hashTransaction != null) {
					instance = new MultisigSignatureTransaction(this.timeStamp, signer, this.multisig,
							this.hashTransaction);
				}

				if (this.fee == null && this.feeCalculator == null) {
					instance.setFee(NemAppsLibGlobals.getGlobalTransactionFee(signer.getAddress().getEncoded()).calculateMinimumFee(instance));
				} else {

					if (this.fee != null) {
						instance.setFee(this.fee);
					} else if (this.feeCalculator != null) {
						TransactionFeeCalculator feeCalculator;
						if (this.feeCalculator != null) {
							feeCalculator = this.feeCalculator;
						} else {
							feeCalculator = NemAppsLibGlobals.getGlobalTransactionFee(signer.getAddress().getEncoded());
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
				instance.sign();

			}

			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.MultisigTransactionBuilder.IBuild#
		 * buildAndSendMultisigTransaction()
		 */
		@Override
		public MultisigSignatureTransaction coSign() {
			TransactionSenderUtil.sendMultisigSignatureTransaction(this.buildMultisigSignatureTransaction());
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
		public IBuild deadline(TimeInstant deadline) {
			this.deadline = deadline;
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
		 * @see io.nem.apps.builders.MultisigSignatureTransactionBuilder.IBuild#
		 * timeStamp(org.nem.core.time.TimeInstant)
		 */
		@Override
		public IBuild timeStamp(TimeInstant timeInstance) {
			this.timeStamp = timeInstance;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.MultisigSignatureTransactionBuilder.IBuild#
		 * signBy(org.nem.core.model.Account)
		 */
		@Override
		public IBuild signBy(Account account) {
			this.signBy = account;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.MultisigSignatureTransactionBuilder.IBuild#
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
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ITransaction
		 * #otherTransaction(org.nem.core.model.Transaction)
		 */
		@Override
		public IBuild otherTransaction(Transaction transaction) {
			this.otherTransaction = transaction;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ITransaction
		 * #otherTransaction(org.nem.core.crypto.Hash)
		 */
		@Override
		public IBuild otherTransaction(Hash hashTransaction) {
			this.hashTransaction = hashTransaction;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ISigner#
		 * addSigner(org.nem.core.model.Account)
		 */
		@Override
		public ISigner addSigner(Account signer) {
			this.signers.add(signer);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ISigner#
		 * addSigners(java.util.List)
		 */
		@Override
		public ITransaction addSigners(List<Account> signers) {
			this.signers.addAll(signers);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.apps.builders.MultisigSignatureTransactionBuilder.ISigner#
		 * signer(org.nem.core.model.Account)
		 */
		@Override
		public ITransaction signer(Account signer) {
			this.signers.add(signer);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.apps.builders.MultisigSignatureTransactionBuilder.IBuild#coSignFuture()
		 */
		@Override
		public CompletableFuture<Deserializer> coSignFuture() {
			return TransactionSenderUtil
					.sendFutureMultisigSignatureTransaction(this.buildMultisigSignatureTransaction());
		}

	}

}
