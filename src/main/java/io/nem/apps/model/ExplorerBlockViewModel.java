package io.nem.apps.model;

import java.util.List;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Block;
import org.nem.core.model.BlockTypes;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.serialization.Deserializer;

/**
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class ExplorerBlockViewModel {

    private List<ExplorerTransferViewModel> txes;

    private Block block;

    private Hash hash;

    public ExplorerBlockViewModel(Deserializer des) {
        this.txes = des.readObjectArray("txes", ExplorerTransferViewModel::new);
        this.block = des.readObject("block", d -> new Block(BlockTypes.REGULAR, VerifiableEntity.DeserializationOptions.NON_VERIFIABLE, d));
        this.hash = new Hash(des.readBytes("hash"));
    }

    public List<ExplorerTransferViewModel> getTxes() {
        return txes;
    }

    public void setTxes(List<ExplorerTransferViewModel> txes) {
        this.txes = txes;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Hash getHash() {
        return hash;
    }

    public void setHash(Hash hash) {
        this.hash = hash;
    }
}
