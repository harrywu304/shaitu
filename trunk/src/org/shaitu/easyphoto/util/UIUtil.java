/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: EasyPhoto
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Jun 28, 2008 5:12:21 PM
 *
 */
package org.shaitu.easyphoto.util;

import java.awt.Font;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

import org.shaitu.easyphoto.vo.OptionBean;

/**
 * userful methods about GUI
 * @author harry
 */
public class UIUtil {
    /**
     * retrieve buttongroup
     * @param btg buttongroup
     * @param value actionCommand of selected button
     */
    public static void retrieveButtonGroup(ButtonGroup btg, String value){
        if(btg == null){
            return;
        }
        Enumeration<AbstractButton> e = btg.getElements();
        while(e.hasMoreElements()){
            AbstractButton bt = e.nextElement();
            if(bt.getActionCommand().equals(value)){
                btg.setSelected(bt.getModel(), true);
                break;
            }
        }
    }

    /**
     * get value of selected item in a JComboBox
     * @param cb JComboBox
     * @return selected value
     */
    public static String getSelectedValue(JComboBox cb){
        if(cb == null){
            return "";
        }
        return ((OptionBean)cb.getSelectedItem()).getValue();
    }

    /**
     * get value of selected item in a JComboBox
     * @param cb JComboBox
     * @return selected value
     */
    public static String getSelectedValue(ButtonGroup btg){
        if(btg == null){
            return "";
        }
        return btg.getSelection().getActionCommand();
    }

    /**
     * get font description for retrieve
     * @param font seelcted font
     * @return font description
     */
	public static String getFontDescription(Font font){
		if(font != null){
			return font.getName() + " " + getFontStyleName(font.getStyle())
			+" " + font.getSize();
		}
		return "";
	}

    /**
     * get font style name
     * @param style font style
     * @return style name
     */
	private static String getFontStyleName(int style)
	{
        String result = "";
        switch(style){
            case Font.BOLD:
                result="BOLD";break;
            case Font.ITALIC:
                result="ITALIC";break;
            case Font.BOLD+Font.ITALIC:
                result="BOLDITALIC";break;
            default:
                result="PLAIN";break;
        }
		return result;
	}
	
//	public static Font getRecommendedFont(Font font, String content){
//		Font result = font;
//    	if(font.canDisplayUpTo(content) == -1){
//    		return font;
//    	}
//    	
//	}

}
