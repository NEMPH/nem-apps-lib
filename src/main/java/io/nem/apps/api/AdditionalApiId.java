package io.nem.apps.api;

import org.nem.core.node.ApiId;


/**
 * The Enum AdditionalApiId.
 *
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public enum AdditionalApiId implements ApiId {
    
    /** The nis rest account mosaic definitions batch look up. */
    NIS_REST_ACCOUNT_MOSAIC_DEFINITIONS_BATCH_LOOK_UP("/namespace/mosaic/definition/page"),
    
    /** The nis rest account historical data. */
    NIS_REST_ACCOUNT_HISTORICAL_DATA("/account/historical/get");

    /** The address. */
    private String address;

    /**
     * Instantiates a new additional api id.
     *
     * @param address the address
     */
    AdditionalApiId(String address) {
        this.address = address;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return address;
    }
}
