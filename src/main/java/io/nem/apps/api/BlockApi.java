package io.nem.apps.api;

import java.util.concurrent.ExecutionException;

import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Block;
import org.nem.core.model.BlockTypes;
import org.nem.core.model.VerifiableEntity.DeserializationOptions;
import org.nem.core.serialization.DeserializableList;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.model.ExplorerBlockViewModel;
import io.nem.apps.service.NemAppsLibGlobals;
import net.minidev.json.JSONObject;


/**
 * The Class BlockApi.
 */
public class BlockApi {

	/**
	 * Gets the block by height.
	 *
	 * @param height            the height
	 * @return the block by height
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static Block getBlockByHeight(int height) throws InterruptedException, ExecutionException {
		Deserializer des;
		JSONObject jsonHeight = new JSONObject();
		jsonHeight.put("height", height);
		des = NemAppsLibGlobals.CONNECTOR.postAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_BLOCK_AT_PUBLIC,
				new HttpJsonPostRequest(jsonHeight)).exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();	
		return new Block(BlockTypes.REGULAR, DeserializationOptions.NON_VERIFIABLE, des);
	}

	/**
	 * Gets no more then 10 block after given block height in form of {@link ExplorerBlockViewModel}.
	 *
	 * @param height the height
	 * @return the block after given block height
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static DeserializableList getBlockAfterGivenBlockHeight(long height) throws InterruptedException, ExecutionException {
		Deserializer des;
		JSONObject jsonHeight = new JSONObject();
		jsonHeight.put("height", height);
		des = NemAppsLibGlobals.CONNECTOR.postAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_BLOCK_AFTER_LOCAL,
													new HttpJsonPostRequest(jsonHeight)).exceptionally(fn -> {
			fn.printStackTrace();
			return null;
		}).get();

		return new DeserializableList<>(des, ExplorerBlockViewModel::new);
	}
	
	/**
	 * Gets the block by height and blocktype.
	 *
	 * @param height the height
	 * @param blockType the block type
	 * @return the block by height
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public static Block getBlockByHeight(int height, int blockType) throws InterruptedException, ExecutionException {
		Deserializer des;
		JSONObject jsonHeight = new JSONObject();
		jsonHeight.put("height", height);
		des = NemAppsLibGlobals.CONNECTOR.postAsync(NemAppsLibGlobals.getNodeEndpoint(), NisApiId.NIS_REST_BLOCK_AT_PUBLIC,
				new HttpJsonPostRequest(jsonHeight)).exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new Block(blockType, DeserializationOptions.NON_VERIFIABLE, des);
	}

}
