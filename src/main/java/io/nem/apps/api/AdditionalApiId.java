package io.nem.apps.api;

import org.nem.core.node.ApiId;

/**
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public enum AdditionalApiId implements ApiId {
    NIS_REST_ACCOUNT_MOSAIC_DEFINITIONS_BATCH_LOOK_UP("/namespace/mosaic/definition/page"),
    NIS_REST_ACCOUNT_HISTORICAL_DATA("/account/historical/get");

    private String address;

    AdditionalApiId(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }
}
