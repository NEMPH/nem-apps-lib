package io.nem.spectro.main;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.UnconfirmedTransactionMetaDataPair;
import org.nem.core.serialization.Deserializer;

import io.nem.spectro.api.AccountApi;
import io.nem.spectro.service.Globals;

public class AccountApiTest {

	@Test
	public void testDeserializeAccount() {
		try {
			final CompletableFuture<Deserializer> des = Globals.CONNECTOR.getAsync(Globals.NODE_ENDPOINT, NisApiId.NIS_REST_ACCOUNT_LOOK_UP,"address=MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW");
			
			des.thenAcceptAsync(d -> {
				
				System.out.println(new AccountMetaDataPair(d).getEntity().getBalance());
			}).exceptionally(e -> {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}).get();
			
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeserializeAccountPk() {
		try {
			final CompletableFuture<Deserializer> des = Globals.CONNECTOR.getAsync(Globals.NODE_ENDPOINT, NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED,"address=MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW");
			
			des.thenAcceptAsync(d -> {
				System.out.println(d.readObjectArray("data", UnconfirmedTransactionMetaDataPair::new).size());
				//System.out.println(new TransactionMetaDataPair(d).getMetaData().getHeight());
			}).exceptionally(e -> {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}).get();
			
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAccountApiAddress() {
		System.out.println(AccountApi.getAccountByAddress("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW").getMetaData().getStatus());
	}
	
	@Test
	public void testAccountApiAllTransaction() {
		System.out.println(AccountApi.getAllTransactions("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW").size());
	}
	
	@Test
	public void testAccountApiAllOwnedMosaic() {
		System.out.println(AccountApi.getAccountOwnedMosaic("MDVJCH6F5FXVUOFCC3PZTSXPQNPCULYQMWEGAOOW"));
	}
	
	@Test
	public void testGenerteNewAccount() {
		System.out.println(AccountApi.generateAccount().getKeyPair().hasPrivateKey());
	}
}
