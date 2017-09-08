/*
 * 
 */
package io.nem.apps.api;

import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;

/**
 * The Class ChainApi.
 */
public class ChainApi {

	/**
	 * Gets the chain height.
	 *
	 * @return the chain height
	 */
	public static String getChainHeight() throws InterruptedException, ExecutionException {
		Deserializer des;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_HEIGHT, "")
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return des.readString("height");
	}

	/**
	 * Gets the chain score.
	 *
	 * @return the chain score
	 */
	public static String getChainScore() throws InterruptedException, ExecutionException {
		Deserializer des;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_SCORE, "")
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
	 */
	public static BlockHeight getChainLastBlock() throws InterruptedException, ExecutionException {
		Deserializer des;
		des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_LAST_BLOCK, "")
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new BlockHeight(des);
	}
}
