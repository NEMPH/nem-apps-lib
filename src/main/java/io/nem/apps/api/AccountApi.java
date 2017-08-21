package io.nem.apps.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.KeyPairViewModel;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.MosaicDefinitionMetaDataPair;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;

/**
 * The Class AccountApi.
 */
public class AccountApi {

	/**
	 * Gets the account by address.
	 *
	 * @param address
	 *            the address
	 * @return the account by address
	 */
	public static AccountMetaDataPair getAccountByAddress(String address) {
		Deserializer des;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=" + address)
					.exceptionally(fn -> {
						fn.printStackTrace();
						return null;
					})
					.get();
			return new AccountMetaDataPair(des);
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
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address)
					.get();
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data",
					TransactionMetaDataPair::new);
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
			list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data",
					TransactionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Gets the account owned mosaic.
	 *
	 * @param address
	 *            the address
	 * @return the account owned mosaic
	 */
	public static List<MosaicDefinitionMetaDataPair> getAccountOwnedMosaic(String address) {
		Deserializer des;
		List<MosaicDefinitionMetaDataPair> list;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_MOSAIC_OWNED, "address=" + address)
					.get();
			list = (ArrayList<MosaicDefinitionMetaDataPair>) des.readObjectArray("data",
					MosaicDefinitionMetaDataPair::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generate account.
	 *
	 * @return the key pair view model
	 */
	public static KeyPairViewModel generateAccount() {
		Deserializer des;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_GENERATE, null).get();
			return new KeyPairViewModel(des);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

}
