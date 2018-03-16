package utils;

import domain.RestObject;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 16-03-18
 **/
public class RestObjectList<E extends RestObject> extends ArrayList<E> {

    public RestObjectList() {

    }

    /**
     * Function to convert all elements in this list to JsonObjects
     *
     * @param isHref is true if the caller wants all elements in href format
     * @return a JsonObject containing the converted elements
     */
    public List<JsonObject> convertAllToJson(boolean isHref) {
        List<JsonObject> jsonObjects = new ArrayList<JsonObject>();

        while (iterator().hasNext()) {
            RestObject restObject = iterator().next();

            // If isHref, convert to Href instead of Json
            if (isHref) {
                jsonObjects.add(restObject.toHref());
            }

            jsonObjects.add(restObject.toJson());
        }

        return jsonObjects;
    }

}
