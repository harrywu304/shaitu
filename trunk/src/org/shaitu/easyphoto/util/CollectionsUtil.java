/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shaitu.easyphoto.util;

import java.util.ResourceBundle;
import java.util.Vector;

import org.shaitu.easyphoto.vo.OptionBean;

/**
 * useful methods about collections
 * @author harry
 */
public class CollectionsUtil {
    /**
     * MessageMapping for i18n
     */
    public static ResourceBundle messageMapping = ResourceBundle.getBundle("resources/i18n/MessageMapping");

    /**
     * get supported look and feel list
     * @return theme list
     */
    public static Vector<OptionBean> getThemeList(){
        Vector options = new Vector();
        options.add(new OptionBean(messageMapping.getString("label.theme.business"),"org.jvnet.substance.skin.SubstanceBusinessLookAndFeel"));
        options.add(new OptionBean(messageMapping.getString("label.theme.mist"),"org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel"));
        options.add(new OptionBean(messageMapping.getString("label.theme.raven"),"org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel"));
        return options;
    }

    /**
     * get supported language list
     * @return language list
     */
    public static Vector<OptionBean> getLangList(){
        Vector options = new Vector();
        options.add(new OptionBean(messageMapping.getString("label.lang.english"),java.util.Locale.ENGLISH));
        options.add(new OptionBean(messageMapping.getString("label.lang.chinese"),java.util.Locale.CHINESE));
        return options;
    }
    
}
