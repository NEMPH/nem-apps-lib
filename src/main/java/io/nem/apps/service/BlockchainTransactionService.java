package io.nem.apps.service;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;
import io.nem.apps.util.TransactionSenderUtil;



/**
 * The Class TransactionService.
 */
public class BlockchainTransactionService {


	/**
	 * Creates the and send transaction.
	 *
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount) {

		final Transaction transaction = createTransaction(NemAppsLibGlobals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, null);

		TransactionSenderUtil.sendTransaction(transaction);
	}



	/**
	 * Creates the and send transaction.
	 *
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 * @param attachment
	 *            the attachment
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount,
			final TransferTransactionAttachment attachment) {

		final Transaction transaction = createTransaction(NemAppsLibGlobals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, attachment);

		TransactionSenderUtil.sendTransaction(transaction);
	}

	/**
	 * Creates the transaction.
	 *
	 * @param timeInstant
	 *            the time instant
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 * @param attachment
	 *            the attachment
	 * @return the transaction
	 */
	public static Transaction createTransaction(final TimeInstant timeInstant, final Account sender,
			final Account recipient, final long amount, final TransferTransactionAttachment attachment) {

		final TransferTransaction transaction = new TransferTransaction(timeInstant, // instant
				sender, recipient, // recipient
				Amount.fromMicroNem(amount), // amount in micro xem
				attachment); // attachment (message, mosaics)

		if (transaction.getFee() == null) {
			transaction.setFee(NemAppsLibGlobals.getGlobalTransactionFee().calculateMinimumFee(transaction));
		} else {
			transaction.setFee(Amount.fromNem(0));
		}

		transaction.setDeadline(timeInstant.addHours(23));
		return transaction;
	}


	/**
	 * Creates the multisig transaction.
	 *
	 * @param timeInstant
	 *            the time instant
	 * @param sender
	 *            the sender
	 * @param recipient
	 *            the recipient
	 * @param amount
	 *            the amount
	 * @param transaction
	 *            the transaction
	 * @return the transaction
	 */
	public static Transaction createMultisigTransaction(final TimeInstant timeInstant, final Account sender,
			final Account recipient, final long amount, final Transaction transaction) {

		final MultisigTransaction multiSigTransaction = new MultisigTransaction(timeInstant, sender, transaction);
		multiSigTransaction.setDeadline(timeInstant.addHours(23));

		if (multiSigTransaction.getFee() == null) {
			TransactionFeeCalculator feeCalculator = NemAppsLibGlobals.getGlobalTransactionFee();
			multiSigTransaction.setFee(feeCalculator.calculateMinimumFee(multiSigTransaction));
		} else {
			multiSigTransaction.setFee(Amount.fromNem(0));
		}

		return multiSigTransaction;
	}

	/**
	 * Creates the multisig signature transaction.
	 *
	 * @param timeInstant            the time instant
	 * @param sender            the sender
	 * @param multisig            the multisig
	 * @param amount            the amount
	 * @param hashTransaction the hash transaction
	 * @return the transaction
	 */
	public static Transaction createMultisigSignatureTransaction(final TimeInstant timeInstant, final Account sender,
			final Account multisig, final long amount, final Hash hashTransaction) {

		final MultisigSignatureTransaction multiSigSignedTransaction = new MultisigSignatureTransaction(timeInstant,
				sender, multisig, hashTransaction);
		multiSigSignedTransaction.setDeadline(timeInstant.addHours(23));

		if (multiSigSignedTransaction.getFee() == null) {
			TransactionFeeCalculator feeCalculator = NemAppsLibGlobals.getGlobalTransactionFee();
			multiSigSignedTransaction.setFee(feeCalculator.calculateMinimumFee(multiSigSignedTransaction));
		} else {
			multiSigSignedTransaction.setFee(Amount.fromNem(0));
		}

		return multiSigSignedTransaction;
	}
}
