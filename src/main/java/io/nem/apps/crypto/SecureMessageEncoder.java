package io.nem.apps.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;

import io.nem.apps.factories.EntityFactory;





/**
 * The Class SecureMessagePayloadEncoder.
 *
 * @author Alvin
 */
public class SecureMessageEncoder {

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey
	 *            the sender private key
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param Message
	 *            the message
	 * @return the secure message
	 */
	public static SecureMessage encode(Account senderPrivateKey, Account recipientPublicKey, String Message) {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, Message.getBytes());
	}

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey
	 *            the sender private key
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param Message
	 *            the message
	 * @return the secure message
	 */
	public static SecureMessage encode(Account senderPrivateKey, Account recipientPublicKey, byte[] Message) {
		return SecureMessage.fromDecodedPayload(senderPrivateKey, recipientPublicKey, Message);
	}

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey
	 *            the sender private key
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param Message
	 *            the message
	 * @return the secure message
	 */
	public static SecureMessage encode(String senderPrivateKey, String recipientPublicKey, String Message) {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, Message.getBytes());
	}

	/**
	 * Encode.
	 *
	 * @param senderPrivateKey
	 *            the sender private key
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param Message
	 *            the message
	 * @return the secure message
	 */
	public static SecureMessage encode(String senderPrivateKey, String recipientPublicKey, byte[] Message) {
		final Account senderAccount = EntityFactory.buildAccountFromPrivateKey(senderPrivateKey);
		final Account recipientAccount = EntityFactory.buildAccountFromPublicKey(recipientPublicKey);
		return SecureMessage.fromDecodedPayload(senderAccount, recipientAccount, Message);
	}

}
