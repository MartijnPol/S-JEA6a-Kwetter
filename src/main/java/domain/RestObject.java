package domain;

import javax.json.JsonObject;

/**
 * Created by Martijn van der Pol on 16-03-18
 **/
public interface RestObject {

    JsonObject toJson();

    JsonObject toHref();

}
