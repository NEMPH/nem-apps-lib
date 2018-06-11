package io.nem.apps.model;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFactory;
import org.nem.core.serialization.Deserializer;


/**
 * The Class ExplorerTransferViewModel.
 *
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class ExplorerTransferViewModel {

    /** The transaction. */
    private Transaction transaction;

    /** The hash. */
    private Hash hash;

    /** The inner hash. */
    private Hash innerHash;

    /**
     * Instantiates a new explorer transfer view model.
     *
     * @param des the des
     */
    public ExplorerTransferViewModel(Deserializer des) {
        this.transaction = des.readObject("tx", TransactionFactory.NON_VERIFIABLE);

        this.hash = new Hash(des.readBytes("hash"));

        byte[] innerHashBytes = des.readOptionalBytes("innerHash");

        if (innerHashBytes != null) {
            innerHash = new Hash(innerHashBytes);
        }
    }

    /**
     * Gets the transaction.
     *
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Sets the transaction.
     *
     * @param transaction the new transaction
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * Gets the hash.
     *
     * @return the hash
     */
    public Hash getHash() {
        return hash;
    }

    /**
     * Sets the hash.
     *
     * @param hash the new hash
     */
    public void setHash(Hash hash) {
        this.hash = hash;
    }

    /**
     * Gets the inner hash.
     *
     * @return the inner hash
     */
    public Hash getInnerHash() {
        return innerHash;
    }

    /**
     * Sets the inner hash.
     *
     * @param innerHash the new inner hash
     */
    public void setInnerHash(Hash innerHash) {
        this.innerHash = innerHash;
    }
}
