package io.nem.spectro.builders;



/**
 * The Class TransactionBuilder.
 */
public class TransactionBuilder {
	
	/**
	 * Initiate transaction build.
	 *
	 * @return the generic transaction builder
	 */
	public static GenericTransactionBuilder initiateTransactionBuild() {
		return new GenericTransactionBuilder();
	}
	
	/**
	 * Initiate multisig transaction build.
	 *
	 * @return the multisig transaction builder
	 */
	public static MultisigTransactionBuilder initiateMultisigTransactionBuild() {
		return new MultisigTransactionBuilder();
	}
	
	/**
	 * Initiate multisig signature transaction build.
	 *
	 * @return the multisig signature transaction builder
	 */
	public static MultisigSignatureTransactionBuilder initiateMultisigSignatureTransactionBuild() {
		return new MultisigSignatureTransactionBuilder();
	}
}
