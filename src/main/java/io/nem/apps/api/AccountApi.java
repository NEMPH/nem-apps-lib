/*
 * 
 */
package io.nem.apps.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.KeyPairViewModel;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.MosaicDefinitionMetaDataPair;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.model.GeneratedAccount;
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
					}).get();
			return new AccountMetaDataPair(des);
		} catch (InterruptedException | ExecutionException e) {
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
	public static List<Mosaic> getAccountOwnedMosaic(String address) {
		Deserializer des;
		List<Mosaic> list;
		try {
			des = Globals.CONNECTOR
					.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_MOSAIC_OWNED, "address=" + address)
					.get();
			list = (ArrayList<Mosaic>) des.readObjectArray("data", Mosaic::new);
			return list;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Generate account.
	 *
	 * @return the key pair view model
	 */
	public static GeneratedAccount generateAccount() {
		GeneratedAccount ga = new GeneratedAccount();
		final KeyPair kp = new KeyPair();
		final Account account = new Account(kp);
		ga.setKeyPair(kp);
		ga.setAccount(account);
		return ga;
	}

}
