package io.nem.spectro.service;

import java.util.logging.Logger;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.NetworkInfos;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.time.TimeInstant;

import io.nem.spectro.fee.TransactionFeeCalculatorAfterForkForApp;
import io.nem.spectro.model.SpectroMultisigTransaction;
import io.nem.spectro.model.SpectroTransaction;
import io.nem.spectro.util.AppPropertiesUtil;
import io.nem.spectro.util.TransactionSenderUtil;

/**
 * The Class TransactionService.
 */
public class BlockchainTransactionService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(BlockchainTransactionService.class.getName());

	static {
		NetworkInfos.setDefault(NetworkInfos.fromFriendlyName(AppPropertiesUtil.getProperty("node.endpoint.networkname")));
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
	 */
	public static void createAndSendTransaction(final Account sender, final Account recipient, final long amount) {

		final Transaction transaction = createTransaction(Globals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
				amount, null);

		TransactionSenderUtil.sendTransaction(transaction);
	}

	/**
	 * Creates the and send transaction.
	 *
	 * @param tBlock
	 *            the t block
	 */
	public static void createAndSendTransaction(final SpectroTransaction tBlock) {

		final Transaction transaction = createTransaction(tBlock.getTimeInstant(), tBlock.getSenderAccount(),
				tBlock.getRecipientAccount(), tBlock.getAmount(), tBlock.getAttachment());
		transaction.sign();
		TransactionSenderUtil.sendTransaction(transaction);

	}

	/**
	 * Creates the and send multisig transaction.
	 *
	 * @param tBlock
	 *            the t block
	 */
	public static void createAndSendMultisigTransaction(final SpectroMultisigTransaction tBlock) {

		final Transaction transaction = createTransaction(tBlock.getTimeInstant(), tBlock.getMultisigAccount(),
				tBlock.getRecipientAccount(), tBlock.getAmount(), tBlock.getAttachment());

		final Transaction multiSigSignedTransaction = createMultisigTransaction(tBlock.getTimeInstant(),
				tBlock.getSenderAccount(), tBlock.getRecipientAccount(), tBlock.getAmount(), transaction);

		multiSigSignedTransaction.sign();
		TransactionSenderUtil.sendTransaction(multiSigSignedTransaction);
	}

	/**
	 * Creates the and send multisig signature transaction.
	 *
	 * @param tBlock
	 *            the t block
	 */
	public static void createAndSendMultisigSignatureTransaction(final SpectroMultisigTransaction tBlock) {

		final Transaction transaction = createTransaction(tBlock.getTimeInstant(), tBlock.getMultisigAccount(),
				tBlock.getRecipientAccount(), tBlock.getAmount(), tBlock.getAttachment());

		final Transaction multiSigSignedTransaction = createMultisigSignatureTransaction(tBlock.getTimeInstant(),
				tBlock.getSenderAccount(), tBlock.getMultisigAccount(), tBlock.getAmount(), transaction);

		multiSigSignedTransaction.sign();
		TransactionSenderUtil.sendTransaction(multiSigSignedTransaction);
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

		final Transaction transaction = createTransaction(Globals.TIME_PROVIDER.getCurrentTime(), sender, recipient,
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

		if(transaction.getFee()==null){
			TransactionFeeCalculatorAfterForkForApp feeCalculator = new TransactionFeeCalculatorAfterForkForApp();
			transaction.setFee(feeCalculator.calculateMinimumFee(transaction));
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

		if(multiSigTransaction.getFee()==null){
			TransactionFeeCalculatorAfterForkForApp feeCalculator = new TransactionFeeCalculatorAfterForkForApp();
			multiSigTransaction.setFee(feeCalculator.calculateMinimumFee(multiSigTransaction));
		} else {
			multiSigTransaction.setFee(Amount.fromNem(0));
		}
		
		return multiSigTransaction;
	}

	/**
	 * Creates the multisig signature transaction.
	 *
	 * @param timeInstant
	 *            the time instant
	 * @param sender
	 *            the sender
	 * @param multisig
	 *            the multisig
	 * @param amount
	 *            the amount
	 * @param transaction
	 *            the transaction
	 * @return the transaction
	 */
	public static Transaction createMultisigSignatureTransaction(final TimeInstant timeInstant, final Account sender,
			final Account multisig, final long amount, final Transaction transaction) {

		final MultisigSignatureTransaction multiSigSignedTransaction = new MultisigSignatureTransaction(timeInstant,
				sender, multisig, transaction);
		multiSigSignedTransaction.setDeadline(timeInstant.addHours(23));
		
		if(multiSigSignedTransaction.getFee()==null){
			TransactionFeeCalculatorAfterForkForApp feeCalculator = new TransactionFeeCalculatorAfterForkForApp();
			multiSigSignedTransaction.setFee(feeCalculator.calculateMinimumFee(multiSigSignedTransaction));
		} else {
			multiSigSignedTransaction.setFee(Amount.fromNem(0));
		}

		return multiSigSignedTransaction;
	}
}
