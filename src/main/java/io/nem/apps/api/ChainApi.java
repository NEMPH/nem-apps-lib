package io.nem.apps.api;

import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.Globals;

public class ChainApi {

	public static String getChainHeight() {
		Deserializer des;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_HEIGHT, "")
					.exceptionally(fn -> {
						fn.printStackTrace();
						return null;
					}).get();
			return des.readString("height");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getChainScore() {
		Deserializer des;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_SCORE, "")
					.exceptionally(fn -> {
						fn.printStackTrace();
						return null;
					}).get();
			return des.readString("score");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BlockHeight getChainLastBlock() {
		Deserializer des;
		try {
			des = Globals.CONNECTOR.getAsync(Globals.getNodeEndpoint(), NisApiId.NIS_REST_CHAIN_LAST_BLOCK, "")
					.exceptionally(fn -> {
						fn.printStackTrace();
						return null;
					}).get();
			return new BlockHeight(des);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
