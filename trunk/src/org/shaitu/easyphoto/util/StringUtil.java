/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: EasyPhoto
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Jun 28, 2008 5:12:21 PM
 *
 */
package org.shaitu.easyphoto.util;

import java.util.Collection;

/**
 * String referred method
 * @author lx5
 */
public class StringUtil {
    /**
     * check if a string is null or blank
     * @param str
     * @return
     */
    public static boolean isNullOrBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * concat objects in collection to a single string
     * @param cc collection
     * @return string
     */
    public static String joinCollection(Collection cc) {
        StringBuffer sb = new StringBuffer();
        for (Object obj : cc) {
            sb.append(obj.toString()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * format string for print, if string not empty append space to the end
     * @param str string to print
     * @return formatted string
     */
    public static String getPrintString(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        } else {
            str = str + " ";
        }
        return str;
    }
}
