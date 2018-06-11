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

import io.nem.apps.service.NemAppsLibGlobals;


/**
 * The Class TransactionApi.
 */
public class TransactionApi {

	/**
	 * Gets the transaction.
	 *
	 * @param hash the hash
	 * @return the transaction
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static TransactionMetaDataPair getTransaction(String hash) throws InterruptedException, ExecutionException {
		Deserializer des;
		TransactionMetaDataPair trans;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_TRANSACTION_GET, "hash=" + hash)
				.get();
		trans = new TransactionMetaDataPair(des);
		return trans;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address            the address
	 * @return the all transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}
	
	/**
	 * Gets the all transactions with a page size.
	 *
	 * @param address the address
	 * @param pageSize the page size
	 * @return the all transactions with page size
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getAllTransactionsWithPageSize(String address, String pageSize)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address + "&pageSize=" + pageSize)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address            the address
	 * @param hash            the hash
	 * @return the all transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getAllTransactionsWithAddressAndHash(String address, String hash)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
				"address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address            the address
	 * @param hash            the hash
	 * @param id            the id
	 * @return the all transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
				"address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address            the address
	 * @return the incoming transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING, "address=" + address)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address            the address
	 * @param hash            the hash
	 * @return the incoming transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
				"address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address            the address
	 * @param hash            the hash
	 * @param id            the id
	 * @return the incoming transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
				"address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address            the address
	 * @return the outgoing transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address)
			throws InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address            the address
	 * @param hash            the hash
	 * @return the outgoing transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash)
			throws InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
				"address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address            the address
	 * @param hash            the hash
	 * @param id            the id
	 * @return the outgoing transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
				"address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param address            the address
	 * @return the unconfirmed transactions
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<TransactionMetaDataPair> getUnconfirmedTransactions(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=" + address).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}


	/**
	 * Announce the Transaction.
	 *
	 * @param endpoint the endpoint
	 * @param request the request
	 * @return the completable future
	 */
	public static CompletableFuture<Deserializer> announceTransaction(final NodeEndpoint endpoint,
			final RequestAnnounce request) {
		final CompletableFuture<Deserializer> des = NemAppsLibGlobals.CONNECTOR.postAsync(endpoint,
				NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, new HttpJsonPostRequest(request));

		return des;
	}

}
