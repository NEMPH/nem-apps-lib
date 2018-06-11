package io.nem.apps.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.model.AccountHistoricalData;
import io.nem.apps.model.GeneratedAccount;
import io.nem.apps.service.NemAppsLibGlobals;


/**
 * The Class AccountApi.
 */
public class AccountApi {

	/**
	 * Gets the account by address.
	 *
	 * @param address            the address
	 * @return the account by address
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static AccountMetaDataPair getAccountByAddress(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=" + address)
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new AccountMetaDataPair(des);
	}

	/**
	 * Gets the account owned mosaic.
	 *
	 * @param address            the address
	 * @return the account owned mosaic
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<Mosaic> getAccountOwnedMosaic(String address) throws InterruptedException, ExecutionException {
		Deserializer des;
		List<Mosaic> list;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(),
				NisApiId.NIS_REST_ACCOUNT_MOSAIC_OWNED, "address=" + address).get();
		list = (ArrayList<Mosaic>) des.readObjectArray("data", Mosaic::new);
		return list;
	}

	
	/**
	 * Get the list of Harvest Info for the account.
	 *
	 * @param address the address
	 * @return the account harvest info
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static List<HarvestInfo> getAccountHarvestInfo(String address)
			throws InterruptedException, ExecutionException {
		Deserializer des;
		List<HarvestInfo> list;
		des = NemAppsLibGlobals.CONNECTOR
				.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_HARVESTS, "address=" + address)
				.get();
		list = (ArrayList<HarvestInfo>) des.readObjectArray("data", HarvestInfo::new);

		return list;
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
		ga.setEncodedAddress(account.getAddress().getEncoded());
		ga.setEncodedPrivateKey(kp.getPrivateKey().toString());
		ga.setEncodedPublicKey(kp.getPublicKey().toString());
		return ga;
	}

	/**
	 * Gets a of mosaic definitions in a given namespace.
	 *
	 * @param address the address
	 * @param startHeight the start height
	 * @param endHeight the end height
	 * @param increment the increment
	 * @return the mosaic definitions in the id
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
    public static List<AccountHistoricalData> getAccountHistoricalData(Address address, long startHeight, long endHeight, int increment)
            throws InterruptedException, ExecutionException {
        Deserializer des;
        des = NemAppsLibGlobals.CONNECTOR.getAsync(
                NemAppsLibGlobals.getNodeEndpoint(),
                AdditionalApiId.NIS_REST_ACCOUNT_HISTORICAL_DATA,
                "startHeight=" + startHeight + "&endHeight=" + endHeight + "&increment=" + increment + "&address=" + address.getEncoded()
        ).get();
        return des.readObjectArray("data", AccountHistoricalData::new);
    }

    /**
     * Gets a of mosaic definitions in a given namespace.
     *
     * @param address the address
     * @param height the height
     * @return the mosaic definitions in the id
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
	public static AccountHistoricalData getAccountHistoricalData(Address address, long height) throws InterruptedException, ExecutionException {
		return getAccountHistoricalData(address, height, height, 1).get(0);
	}
}
