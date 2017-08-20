package io.nem.spectro.api;

import org.nem.core.model.Transaction;

public interface TransactionCallback {
	
	public void handle(Transaction transaction);
}
