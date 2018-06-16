package io.nem.apps.fee;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import org.nem.core.model.MultisigAggregateModificationTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransactionTypes;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicConstants;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicUtils;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.Supply;
import io.nem.apps.api.NamespaceMosaicsApi;




/**
 * Implementation for calculating and validating transaction fees since the fee fork.
 */
public class TransactionFeeCalculatorAfterForkForApp implements TransactionFeeCalculator {
	
	/** The Constant FEE_UNIT. */
	private static final Amount FEE_UNIT = Amount.fromNem(2);
	
	/** The Constant FEE_MULTIPLIER. */
	private static final int FEE_MULTIPLIER = 3;

	/** The mosaic fee information lookup. */
	private final MosaicFeeInformationLookup mosaicFeeInformationLookup;

	/**
	 * Creates a default transaction fee calculator.
	 */
	public TransactionFeeCalculatorAfterForkForApp() {
		this(id -> null);
	}

	/**
	 * Creates a default transaction fee calculator.
	 *
	 * @param mosaicFeeInformationLookup The mosaic fee information lookup.
	 */
	public TransactionFeeCalculatorAfterForkForApp(final MosaicFeeInformationLookup mosaicFeeInformationLookup) {
		this.mosaicFeeInformationLookup = mosaicFeeInformationLookup;
	}

	/**
	 * Calculates the minimum fee for the specified transaction at the specified block height.
	 *
	 * @param transaction The transaction.
	 * @return The minimum fee.
	 */
	@Override
	public Amount calculateMinimumFee(final Transaction transaction) {
		switch (transaction.getType()) {
			case TransactionTypes.TRANSFER:
				return this.calculateMinimumFee((TransferTransaction)transaction);

			case TransactionTypes.MULTISIG_AGGREGATE_MODIFICATION:
				return calculateMinimumFee((MultisigAggregateModificationTransaction)transaction);

			case TransactionTypes.PROVISION_NAMESPACE:
			case TransactionTypes.MOSAIC_DEFINITION_CREATION:
			case TransactionTypes.MOSAIC_SUPPLY_CHANGE:
				return FEE_UNIT.multiply(10);
		}

		return FEE_UNIT.multiply(FEE_MULTIPLIER);
	}

	/**
	 * Calculate minimum fee.
	 *
	 * @param transaction the transaction
	 * @return the amount
	 */
	private Amount calculateMinimumFee(final TransferTransaction transaction) {
		final long messageFee = null == transaction.getMessage()
				? 0
				: transaction.getMessageLength() / 32 + 1;
		if (transaction.getAttachment().getMosaics().isEmpty()) {
			final long numXem = transaction.getAmount().getNumNem();
			final long transferFee = calculateXemTransferFee(numXem);
			return Amount.fromNem(messageFee + transferFee);
		}

		final long transferFee = transaction.getAttachment().getMosaics().stream()
				.map(m -> {
					MosaicFeeInformation information = this.mosaicFeeInformationLookup.findById(m.getMosaicId());
					if (null == information) {
						try {
							information = NamespaceMosaicsApi.findMosaicFeeInformationByNIS(m.getMosaicId());
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
					}
					if (null == information) {
						throw new IllegalArgumentException(String.format("unable to find fee information for '%s'", m.getMosaicId()));
					}

					return calculateMosaicTransferFee(transaction.getAmount(), m, information);
				})
				.reduce(0L, Long::sum);
		return Amount.fromNem(messageFee + transferFee);
	}

	/**
	 * Calculate xem transfer fee.
	 *
	 * @param numXem the num xem
	 * @return the long
	 */
	private static long calculateXemTransferFee(final long numXem) {
		return Math.min(25, Math.max(1L, numXem / 10_000L));
	}

	/**
	 * Calculate mosaic transfer fee.
	 *
	 * @param amount the amount
	 * @param mosaic the mosaic
	 * @param information the information
	 * @return the long
	 */
	private static long calculateMosaicTransferFee(
			final Amount amount,
			final Mosaic mosaic,
			final MosaicFeeInformation information) {
		if (0 == information.getDivisibility() && 10_000 >= information.getSupply().getRaw()) {
			return 1L;
		}

		final long xemEquivalent = calculateXemEquivalent(amount, mosaic, information.getSupply(), information.getDivisibility());
		final long xemFee = calculateXemTransferFee(xemEquivalent);
		final long mosaicTotalQuantity = MosaicUtils.toQuantity(information.getSupply(), information.getDivisibility()).getRaw();
		final long supplyRelatedAdjustment = 0 < mosaicTotalQuantity
				? (long)(0.8 * Math.log(MosaicConstants.MAX_QUANTITY / mosaicTotalQuantity))
				: 0;
		return Math.max(1L, xemFee - supplyRelatedAdjustment);
	}

	/**
	 * Calculate xem equivalent.
	 *
	 * @param amount the amount
	 * @param mosaic the mosaic
	 * @param supply the supply
	 * @param divisibility the divisibility
	 * @return the long
	 */
	private static long calculateXemEquivalent(final Amount amount, final Mosaic mosaic, final Supply supply, final int divisibility) {
		if (Supply.ZERO.equals(supply)) {
			return 0;
		}

		return BigInteger.valueOf(MosaicConstants.MOSAIC_DEFINITION_XEM.getProperties().getInitialSupply())
				.multiply(BigInteger.valueOf(mosaic.getQuantity().getRaw()))
				.multiply(BigInteger.valueOf(amount.getNumMicroNem()))
				.divide(BigInteger.valueOf(supply.getRaw()))
				.divide(BigInteger.TEN.pow(divisibility + 6))
				.longValue();
	}

	/**
	 * Calculate minimum fee.
	 *
	 * @param transaction the transaction
	 * @return the amount
	 */
	private static Amount calculateMinimumFee(final MultisigAggregateModificationTransaction transaction) {
		final int numModifications = transaction.getCosignatoryModifications().size();
		final int minCosignatoriesFee = null == transaction.getMinCosignatoriesModification() ? 0 : FEE_MULTIPLIER;
		return FEE_UNIT.multiply(5 + FEE_MULTIPLIER * numModifications + minCosignatoriesFee);
	}

	/* (non-Javadoc)
	 * @see org.nem.core.model.TransactionFeeCalculator#isFeeValid(org.nem.core.model.Transaction, org.nem.core.model.primitive.BlockHeight)
	 */
	@Override
	public boolean isFeeValid(Transaction transaction, BlockHeight blockHeight) {
		final Amount minimumFee = this.calculateMinimumFee(transaction);
		final Amount maxCacheFee = Amount.fromNem(1000); // 1000 xem is the maximum fee that helps push a transaction into the cache
		switch (transaction.getType()) {
			case TransactionTypes.MULTISIG_SIGNATURE:
				// minimumFee <= multisig signatures fee <= 1000
				// reason: during spam attack cosignatories must be able to get their signature into the cache.
				//         it is limited in order for the last cosignatory not to be able to drain the multisig account
				return 0 <= transaction.getFee().compareTo(minimumFee) && 0 >= transaction.getFee().compareTo(maxCacheFee);
		}

		return transaction.getFee().compareTo(minimumFee) >= 0;
	}
}
