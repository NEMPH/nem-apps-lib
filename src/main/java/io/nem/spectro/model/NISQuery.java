package io.nem.spectro.model;

import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.primitive.Supply;
import io.nem.spectro.service.Globals;
import io.nem.spectro.util.NemSpectroNetworkResponse;
import io.nem.spectro.util.NetworkUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NISQuery {

	public static MosaicFeeInformation findMosaicFeeInformationByNIS(MosaicId mosaicId){
		
		
		NemSpectroNetworkResponse queryResult = NetworkUtils.get(Globals.URL_NAMESPACE_MOSAIC_DEFINITION_PAGE + "?namespace=" + mosaicId.getNamespaceId().toString());
		
		
		JSONObject json = JSONObject.fromObject(queryResult.getResponse());
		if(json==null || !json.containsKey("data") || json.getJSONArray("data").size()==0){
			return null;
		}
		JSONArray array = json.getJSONArray("data");
		for(int i=0;i<array.size();i++){
			JSONObject item = array.getJSONObject(i);
			JSONObject mosaic = item.getJSONObject("mosaic");
			JSONObject id = mosaic.getJSONObject("id");
			if(mosaicId.getName().equals(id.getString("name"))){
				JSONArray properties = mosaic.getJSONArray("properties");
				String initialSupply = "";
				String divisibility = "";
				for(int j=0;j<properties.size();j++){
					JSONObject property = properties.getJSONObject(j);
					if("initialSupply".equals(property.getString("name"))){
						initialSupply = property.getString("value");
					} else if("divisibility".equals(property.getString("name"))){
						divisibility = property.getString("value");
					}
				}
				if(!"".equals(initialSupply) && !"".equals(divisibility)){
					return new MosaicFeeInformation(Supply.fromValue(Long.valueOf(initialSupply)), Integer.valueOf(divisibility));
				}
			}
		}
		return null;
	}
	
}
