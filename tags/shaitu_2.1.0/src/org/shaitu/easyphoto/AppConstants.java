/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 16, 2008 9:05:44 AM
 *
*/ 
package org.shaitu.easyphoto;

/**
 * constants about app
 * 
 * @author whx
 */
public class AppConstants {
	/**
	 * current app version
	 */
	public static final String CURRENT_INTERNAL_VERSION = "20110329.001";
	/**
	 * double style of exif
	 */
	public static final String EXIF_STYLE_DOUBLE = "double";
	/**
	 * single style of exif
	 */
	public static final String EXIF_STYLE_SINGLE = "single";
	/**
	 * single style of exif
	 */
	public static final String EXIF_STYLE_ROUND = "round";
	/**
	 * default image export quality
	 */
	public static final int DEFAULT_QUALITY = 80;
	/**
	 * default resize
	 */
	public static final int DEFAULT_RESIZE = 1024;
	/**
	 * default resize index
	 */
	public static final int DEFAULT_RESIZE_INDEX = 5;
    /**
     * default exif font
     */
    public static String DEFAULT_EXIF_FONT = "Palatino Linotype bolditalic 16";
    /**
     * default text watermark font
     */
    public static String DEFAULT_WATERMARK_FONT = "Arial plain 16";
    /**
     * text watermark style "tiny"
     */
    public static String WATERMARK_STYLE_TINY = "tiny";
    /**
     * text watermark style "cross"
     */
    public static String WATERMARK_STYLE_CROSS = "cross";

    /**
     * default corner size
     */
    public static final int DEFAULT_CORNER_SIZE = 30;
	/**
	 * default look and feel without instance
	 * 1)javax.swing.plaf.metal.MetalLookAndFeel
	 * 2)com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
	 */
	public static final String DEFAULT_THEME = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    /**
     * app config file
     */
    public static final String CONFIG_FILE = "config/easyphoto.properties";
    /**
     * my app config file
     */
    public static final String MY_CONFIG_FILE = "config/my_easyphoto.properties";
    public static final String SWAP_IMAGE_BASENAME =".swapimg.";
	/**
	 * App update declaration
	 */
	public static final String UPDATE_CHECK_URL = "http://shaitu.googlecode.com/svn/wiki/dist/update.txt";
    /**
     * executor of app, for windows is EasyPhoto.bat, for linux is EasyPhoto.sh
     */
	public static final String RUNNER ="EasyPhoto";
}
