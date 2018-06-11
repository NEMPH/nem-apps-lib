/*
 * 
 */
package io.nem.apps.api;

import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.NemAppsLibGlobals;


/**
 * The Class ChainApi.
 */
public class ChainApi {

	/**
	 * Gets the chain height.
	 *
	 * @return the chain height
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static String getChainHeight() throws InterruptedException, ExecutionException {
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_HEIGHT, "")
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return des.readInt("height").toString();
	}

	/**
	 * Gets the chain score.
	 *
	 * @return the chain score
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static String getChainScore() throws InterruptedException, ExecutionException {
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_SCORE, "")
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return des.readString("score");
	}

	/**
	 * Gets the chain last block.
	 *
	 * @return the chain last block
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static BlockHeight getChainLastBlock() throws InterruptedException, ExecutionException {
		Deserializer des;
		des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_LAST_BLOCK, "")
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new BlockHeight(des);
	}
}
