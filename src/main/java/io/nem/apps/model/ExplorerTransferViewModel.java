package io.nem.apps.model;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransactionFactory;
import org.nem.core.serialization.Deserializer;

/**
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class ExplorerTransferViewModel {

    private Transaction transaction;

    private Hash hash;

    private Hash innerHash;

    public ExplorerTransferViewModel(Deserializer des) {
        this.transaction = des.readObject("tx", TransactionFactory.NON_VERIFIABLE);

        this.hash = new Hash(des.readBytes("hash"));

        byte[] innerHashBytes = des.readOptionalBytes("innerHash");

        if (innerHashBytes != null) {
            innerHash = new Hash(innerHashBytes);
        }
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Hash getHash() {
        return hash;
    }

    public void setHash(Hash hash) {
        this.hash = hash;
    }

    public Hash getInnerHash() {
        return innerHash;
    }

    public void setInnerHash(Hash innerHash) {
        this.innerHash = innerHash;
    }
}
