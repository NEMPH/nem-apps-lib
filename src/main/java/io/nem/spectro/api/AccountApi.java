package io.nem.spectro.api;

import java.util.ArrayList;
import java.util.List;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.KeyPairViewModel;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.MosaicDefinitionMetaDataPair;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;
import io.nem.spectro.service.Globals;

public class AccountApi {

	public static AccountMetaDataPair getAccountByAddress(String address) {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=" + address).join();
		return new AccountMetaDataPair(des);
	}

	public static List<TransactionMetaDataPair> getAllTransactions(String address) {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address).join();
		List<TransactionMetaDataPair> list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	public static List<TransactionMetaDataPair> getIncomingTransactions(String address) {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING, "address=" + address)
				.join();
		List<TransactionMetaDataPair> list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	public static List<TransactionMetaDataPair> getOutgoingTransactions(String address) {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address)
				.join();
		List<TransactionMetaDataPair> list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	public static List<TransactionMetaDataPair> getUnconfirmedTransactions(String address) {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=" + address).join();
		List<TransactionMetaDataPair> list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	public static List<MosaicDefinitionMetaDataPair> getAccountOwnedMosaic(String address) {
		Deserializer des = Globals.CONNECTOR
				.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_MOSAIC_OWNED, "address=" + address).join();
		List<MosaicDefinitionMetaDataPair> list = (ArrayList<MosaicDefinitionMetaDataPair>) des.readObjectArray("data", MosaicDefinitionMetaDataPair::new);
		return list;
	}

	public static KeyPairViewModel generateAccount() {
		Deserializer des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_ACCOUNT_GENERATE, null)
				.join();
		return new KeyPairViewModel(des);
	}

}
