package org.shaitu.easyphoto.ui;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

import org.shaitu.easyphoto.util.FileUtil;
import org.shaitu.easyphoto.util.ImageUtil;



/**
 * custom file view
 * 
 * @author whx
 */
public class ImageFileView extends FileView {
    ImageIcon imageIcon = ImageUtil.createImageIcon("/resources/icon/image_icon_32.png");

    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }

    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }

    public String getTypeDescription(File f) {
        String extension = FileUtil.getExtName(f);
        String type = null;

        if (extension != null) {
            if (extension.equals(ImageUtil.JPEG) ||
                extension.equals(ImageUtil.JPG)) {
                type = "JPEG Image";
            } else if (extension.equals(ImageUtil.GIF)){
                type = "GIF Image";
            } else if (extension.equals(ImageUtil.TIFF) ||
                       extension.equals(ImageUtil.TIF)) {
                type = "TIFF Image";
            } else if (extension.equals(ImageUtil.PNG)){
                type = "PNG Image";
            }
        }
        return type;
    }

    public Icon getIcon(File f) {
        String extension = FileUtil.getExtName(f);
        Icon icon = null;
        if (ImageUtil.isSupportedImage(extension)) {
        	icon = imageIcon;
        }
        return icon;
    }
}
