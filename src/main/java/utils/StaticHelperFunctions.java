package utils;

import com.mysql.cj.core.util.StringUtils;

/**
 * Created by Martijn van der Pol on 16-03-18
 **/
public class StaticHelperFunctions {

    /**
     * Function to check if an object is null
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        if (object.getClass() == String.class) {
            return StringUtils.isNullOrEmpty((String) object);
        }
        return object == null;
    }

    public static boolean isNull(String string) {
        return StringUtils.isNullOrEmpty(string);
    }

    public static boolean isNull(Long id) {
        return id == null;
    }

}
