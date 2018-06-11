/*
 * 
 */
package io.nem.apps.api;

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
