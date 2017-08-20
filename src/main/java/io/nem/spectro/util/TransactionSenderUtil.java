package io.nem.spectro.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.NetworkInfos;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.BinarySerializer;
import org.nem.core.serialization.Deserializer;

import io.nem.spectro.service.Globals;

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
		final CompletableFuture<Deserializer> future = send(Globals.NODE_ENDPOINT, request);
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
				LOGGER.warning(String.format("could not send xem:" + e.getMessage()));
				return null;
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
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
