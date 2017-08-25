package io.nem.apps.builders;

import java.util.ArrayList;
import java.util.List;

import org.nem.core.crypto.Hash;
import org.nem.core.crypto.Signature;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;
import io.nem.apps.service.Globals;
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
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */


	public static ISigner multisig(Account multisig) {
		return new MultisigSignatureTransactionBuilder.Builder(multisig);
	}
	
	public interface ISigner {
		
		ITransaction signer(Account signer);
		ISigner startAssignSigners();
		ISigner addSigner(Account signer);
		ISigner addSigners(List<Account> signers);
		ITransaction endAssignSigners();
	}

	public interface ITransaction {

		IBuild otherTransaction(Transaction transaction);

		IBuild otherTransaction(Hash hashTransaction);

	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		IBuild timeStamp(TimeInstant timeInstance);

		IBuild signBy(Account account);

		IBuild fee(Amount amount);

		IBuild feeCalculator(TransactionFeeCalculator feeCalculator);

		IBuild deadline(TimeInstant timeInstant);

		IBuild signature(Signature signature);

		MultisigSignatureTransaction coSign();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISigner, ITransaction, IBuild {
		
		@Override
		public ISigner startAssignSigners() {
			return this;
		}
		
		@Override
		public ITransaction endAssignSigners() {
			return this;
		}

		/** The instance. */
		private MultisigSignatureTransaction instance;

		// constructor
		private TimeInstant timeStamp;
		private Account multisig;
		private Transaction otherTransaction;
		private Hash hashTransaction;
		private Signature signature;

		// secondary
		private Amount fee;
		private TransactionFeeCalculator feeCalculator;
		private Account signBy;
		private TimeInstant deadline;
		private List<Account> signers = new ArrayList<Account>();

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender
		 *            the sender
		 */
		public Builder(Account multisig) {
			this.multisig = multisig;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.MultisigTransactionBuilder.IBuild#
		 * buildAndSendMultisigTransaction()
		 */
		@Override
		public MultisigSignatureTransaction coSign() {
			if (this.timeStamp == null) {
				this.timeStamp = Globals.TIME_PROVIDER.getCurrentTime();
			}
			
			for(Account signer:this.signers) {
				if (this.otherTransaction != null) {
					instance = new MultisigSignatureTransaction(this.timeStamp, signer, this.multisig,
							this.otherTransaction);
				}
				if (this.hashTransaction != null) {
					instance = new MultisigSignatureTransaction(this.timeStamp, signer, this.multisig,
							this.hashTransaction);
				}
	
				if (this.fee == null) {
					TransactionFeeCalculator feeCalculator;
					if (this.feeCalculator != null) {
						feeCalculator = this.feeCalculator;
					} else {
						feeCalculator = Globals.getGlobalTransactionFee();
					}
					instance.setFee(feeCalculator.calculateMinimumFee(instance));
				} else {
					instance.setFee(Amount.fromNem(0));
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
				TransactionSenderUtil.sendMultisigSignatureTransaction(instance);
			}
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

		@Override
		public IBuild timeStamp(TimeInstant timeInstance) {
			this.timeStamp = timeInstance;
			return this;
		}

		@Override
		public IBuild signBy(Account account) {
			this.signBy = account;
			return this;
		}

		@Override
		public IBuild feeCalculator(TransactionFeeCalculator feeCalculator) {
			this.feeCalculator = feeCalculator;
			return this;
		}

		@Override
		public IBuild otherTransaction(Transaction transaction) {
			this.otherTransaction = transaction;
			return this;
		}


		@Override
		public IBuild otherTransaction(Hash hashTransaction) {
			this.hashTransaction = hashTransaction;
			return this;
		}



		@Override
		public ISigner addSigner(Account signer) {
			this.signers.add(signer);
			return this;
		}

		@Override
		public ISigner addSigners(List<Account> signers) {
			this.signers.addAll(signers);
			return this;
		}

		@Override
		public ITransaction signer(Account signer) {
			this.signers.add(signer);
			return this;
		}

	}

}
