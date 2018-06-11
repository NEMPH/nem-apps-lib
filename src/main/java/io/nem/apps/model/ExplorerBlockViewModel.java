package io.nem.apps.model;

import java.util.List;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Block;
import org.nem.core.model.BlockTypes;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.serialization.Deserializer;


/**
 * The Class ExplorerBlockViewModel.
 *
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class ExplorerBlockViewModel {

    /** The txes. */
    private List<ExplorerTransferViewModel> txes;

    /** The block. */
    private Block block;

    /** The hash. */
    private Hash hash;

    /**
     * Instantiates a new explorer block view model.
     *
     * @param des the des
     */
    public ExplorerBlockViewModel(Deserializer des) {
        this.txes = des.readObjectArray("txes", ExplorerTransferViewModel::new);
        this.block = des.readObject("block", d -> new Block(BlockTypes.REGULAR, VerifiableEntity.DeserializationOptions.NON_VERIFIABLE, d));
        this.hash = new Hash(des.readBytes("hash"));
    }

    /**
     * Gets the txes.
     *
     * @return the txes
     */
    public List<ExplorerTransferViewModel> getTxes() {
        return txes;
    }

    /**
     * Sets the txes.
     *
     * @param txes the new txes
     */
    public void setTxes(List<ExplorerTransferViewModel> txes) {
        this.txes = txes;
    }

    /**
     * Gets the block.
     *
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Sets the block.
     *
     * @param block the new block
     */
    public void setBlock(Block block) {
        this.block = block;
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
}
