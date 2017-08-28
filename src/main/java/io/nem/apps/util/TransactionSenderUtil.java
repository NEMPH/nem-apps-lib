package io.nem.apps.util;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.VerifiableEntity.DeserializationOptions;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.BinarySerializer;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;



/**
 * The Class TransactionSenderUtil.
 */
public class TransactionSenderUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(TransactionSenderUtil.class.getName());

	/**
	 * Send transaction.
	 *
	 * @param transaction
	 *            the transaction
	 */
	public static void sendTransaction(Transaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = send(Globals.getNodeEndpoint(), request);
		try {
			future.thenAcceptAsync(d -> {
				final NemAnnounceResult result = new NemAnnounceResult(d);
				
				switch (result.getCode()) {
				case 1:
					LOGGER.info(String.format("successfully send xem " + result.getMessage()));
					break;
				default:
					LOGGER.warning(String.format("could not send xem " + result.getMessage()));
				}
			}).exceptionally(e -> {
				e.printStackTrace();
				LOGGER.warning(String.format("could not send xem:" + e.getMessage()));
				return null;
			}).get();
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	/**
	 * Send transfer transaction.
	 *
	 * @param transaction the transaction
	 * @return the nem announce result
	 */
	public static NemAnnounceResult sendTransferTransaction(TransferTransaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = send(Globals.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();
			
			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}
	
	public static CompletableFuture<Deserializer> sendFutureTransferTransaction(TransferTransaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		return send(Globals.getNodeEndpoint(), request);
	}
	
	/**
	 * Send multi sig transaction.
	 *
	 * @param transaction the transaction
	 * @return the nem announce result
	 */
	public static NemAnnounceResult sendMultiSigTransaction(MultisigTransaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = send(Globals.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();
			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}
	
	public static CompletableFuture<Deserializer> sendFutureMultiSigTransaction(MultisigTransaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		return send(Globals.getNodeEndpoint(), request);
	}

	/**
	 * Send multisig signature transaction.
	 *
	 * @param transaction the transaction
	 * @return the nem announce result
	 */
	public static NemAnnounceResult sendMultisigSignatureTransaction(MultisigSignatureTransaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = send(Globals.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();
			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}
	
	public static CompletableFuture<Deserializer> sendFutureMultisigSignatureTransaction(MultisigSignatureTransaction transaction) {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());
		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		return send(Globals.getNodeEndpoint(), request);
	
	}
	
	/**
	 * Send.
	 *
	 * @param endpoint
	 *            the endpoint
	 * @param request
	 *            the request
	 * @return the completable future
	 */
	private static CompletableFuture<Deserializer> send(final NodeEndpoint endpoint, final RequestAnnounce request) {
		final CompletableFuture<Deserializer> des = Globals.CONNECTOR.postAsync(endpoint, NisApiId.NIS_REST_TRANSACTION_ANNOUNCE,
				new HttpJsonPostRequest(request));
		
		return des;
	}
}
