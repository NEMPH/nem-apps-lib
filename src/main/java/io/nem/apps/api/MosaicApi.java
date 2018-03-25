package io.nem.apps.api;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.model.ncc.MosaicDefinitionMetaDataPair;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.NemAppsLibGlobals;

/**
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class MosaicApi {

    /**
     * Gets a page of mosaic definitions in a given namespace.
     *
     * @param namespaceId
     *            the namespace Id
     * @param id
     *            the lat mosaic id to be included
     * @return the mosaic definitions in the id
     */
    public static List<MosaicDefinitionMetaDataPair> getMosaicsDefinition(String namespaceId, Long mosaicDatabaseId)
            throws InterruptedException, ExecutionException {
        Deserializer des;
        des = NemAppsLibGlobals.CONNECTOR.getAsync(NemAppsLibGlobals.getNodeEndpoint(),
                                                   AdditionalApiId.NIS_REST_ACCOUNT_MOSAIC_DEFINITIONS_BATCH_LOOK_UP,
                                                   "namespace=" + namespaceId + (mosaicDatabaseId == null? "" : "&id=" + mosaicDatabaseId))
                                         .get();
        return des.readObjectArray("data", MosaicDefinitionMetaDataPair::new);
    }

    public static List<MosaicDefinitionMetaDataPair> getMosaicsDefinition(String namespaceId) throws InterruptedException, ExecutionException {
        return getMosaicsDefinition(namespaceId, null);
    }
}
