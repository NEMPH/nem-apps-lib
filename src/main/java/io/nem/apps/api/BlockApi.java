package io.nem.apps.api;

import java.util.concurrent.ExecutionException;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Block;
import org.nem.core.model.BlockTypes;
import org.nem.core.model.VerifiableEntity.DeserializationOptions;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;
import net.minidev.json.JSONObject;

/**
 * The Class BlockApi.
 */
public class BlockApi {

	/**
	 * Gets the block by height.
	 *
	 * @param height
	 *            the height
	 * @return the block by height
	 */
	public static Block getBlockByHeight(int height) throws InterruptedException, ExecutionException {
		Deserializer des;
		JSONObject jsonHeight = new JSONObject();
		jsonHeight.put("height", height);
		des = Globals.CONNECTOR.postAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_BLOCK_AT_PUBLIC,
				new HttpJsonPostRequest(jsonHeight)).exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new Block(BlockTypes.NEMESIS, DeserializationOptions.NON_VERIFIABLE, des);
	}

	/**
	 * Gets the block after given block height.
	 *
	 * @param height
	 *            the height
	 * @return the block after given block height
	 */
	public static Block getBlockAfterGivenBlockHeight(int height) throws InterruptedException, ExecutionException {
		Deserializer des;
		JSONObject jsonHeight = new JSONObject();
		jsonHeight.put("height", height);
		des = Globals.CONNECTOR.postAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_BLOCK_AFTER_LOCAL,
				new HttpJsonPostRequest(jsonHeight)).exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new Block(BlockTypes.NEMESIS, DeserializationOptions.NON_VERIFIABLE, des);
	}
}
