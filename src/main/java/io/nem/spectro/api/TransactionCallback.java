package io.nem.spectro.api;

import org.nem.core.model.Transaction;



/**
 * The Interface TransactionCallback.
 */
public interface TransactionCallback {
	
	/**
	 * Handle.
	 *
	 * @param transaction the transaction
	 */
	public void handle(Transaction transaction);
}
