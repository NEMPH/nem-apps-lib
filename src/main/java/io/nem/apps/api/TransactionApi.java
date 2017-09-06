/*
 * 
 */
package io.nem.apps.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;


/**
 * The Class TransactionApi.
 */
public class TransactionApi {

	
	public static TransactionMetaDataPair getTransaction(String hash) {
		Deserializer des;
		TransactionMetaDataPair trans;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_TRANSACTION_GET, "hash=" + hash)
					.get();
			trans = new TransactionMetaDataPair(des);
			return trans;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @return the all transactions
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address)
					.get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address the address
	 * @param hash the hash
	 * @return the all transactions
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address, String hash) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
					"address=" + address + "&hash=" + hash).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address the address
	 * @param hash the hash
	 * @param id the id
	 * @return the all transactions
	 */
	public static List<TransactionMetaDataPair> getAllTransactions(String address, String hash, String id) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL,
					"address=" + address + "&hash=" + hash + "&id=" + id).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @return the incoming transactions
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
					"address=" + address).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address the address
	 * @param hash the hash
	 * @return the incoming transactions
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
					"address=" + address + "&hash=" + hash).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address the address
	 * @param hash the hash
	 * @param id the id
	 * @return the incoming transactions
	 */
	public static List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash, String id) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING,
					"address=" + address + "&hash=" + hash + "&id=" + id).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @return the outgoing transactions
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address) {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
					"address=" + address).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address the address
	 * @param hash the hash
	 * @return the outgoing transactions
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash) {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
					"address=" + address + "&hash=" + hash).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address the address
	 * @param hash the hash
	 * @param id the id
	 * @return the outgoing transactions
	 */
	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash, String id) {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING,
					"address=" + address + "&hash=" + hash + "&id=" + id).get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param address
	 *            the address
	 * @return the unconfirmed transactions
	 */
	public static List<TransactionMetaDataPair> getUnconfirmedTransactions(String address) {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=" + address)
					.get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
