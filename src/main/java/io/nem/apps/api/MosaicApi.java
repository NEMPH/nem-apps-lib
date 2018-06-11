package io.nem.apps.api;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.model.ncc.MosaicDefinitionMetaDataPair;
import org.nem.core.serialization.Deserializer;

import io.nem.apps.service.NemAppsLibGlobals;


/**
 * The Class MosaicApi.
 *
 * @author Konstantin Zolotukhin (zolotukhin@softmotions.com)
 */
public class MosaicApi {

    /**
     * Gets a page of mosaic definitions in a given namespace.
     *
     * @param namespaceId            the namespace Id
     * @param mosaicDatabaseId the mosaic database id
     * @return the mosaic definitions in the id
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
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

    /**
     * Gets the mosaics definition.
     *
     * @param namespaceId the namespace id
     * @return the mosaics definition
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    public static List<MosaicDefinitionMetaDataPair> getMosaicsDefinition(String namespaceId) throws InterruptedException, ExecutionException {
        return getMosaicsDefinition(namespaceId, null);
    }
}
