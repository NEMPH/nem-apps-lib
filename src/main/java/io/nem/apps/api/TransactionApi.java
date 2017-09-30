/*
 * 
 */
package io.nem.apps.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;

/**
 * The Class TransactionApi.
 */
public class TransactionApi {

	public static TransactionMetaDataPair getTransaction(String hash) throws InterruptedException, ExecutionException {
		Deserializer des;
		TransactionMetaDataPair trans;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_TRANSACTION_GET, "hash=" + hash)
				.get();
		trans = new TransactionMetaDataPair(des);
		return trans;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @return the all transactions
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @return the all transactions
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address, String hash)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
				"address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @param id
	 *            the id
	 * @return the all transactions
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
				"address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @return the incoming transactions
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING, "address=" + address)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @return the incoming transactions
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
				"address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @param id
	 *            the id
	 * @return the incoming transactions
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
				"address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @return the outgoing transactions
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address)
			throws InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @return the outgoing transactions
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash)
			throws InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
				"address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @param id
	 *            the id
	 * @return the outgoing transactions
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
				"address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param address
	 *            the address
	 * @return the unconfirmed transactions
	 */
	public static List<TransactionMetaDataPair> getUnconfirmedTransactions(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=" + address).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}


	/**
	 * Announce the Transaction
	 * @param endpoint
	 * @param request
	 * @return
	 */
	public static CompletableFuture<Deserializer> announceTransaction(final NodeEndpoint endpoint,
			final RequestAnnounce request) {
		final CompletableFuture<Deserializer> des = Globals.CONNECTOR.postAsync(endpoint,
				NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, new HttpJsonPostRequest(request));

		return des;
	}

}
