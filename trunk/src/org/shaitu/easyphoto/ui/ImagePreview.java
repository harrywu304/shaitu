package org.shaitu.easyphoto.ui;

import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.io.File;

/**
 * custom image preview accessory
 * 
 * @author whx
 */
public class ImagePreview extends JComponent
                          implements PropertyChangeListener {
    ImageIcon thumbnail = null;
    File file = null;
    int pvWidth = 145;
    int pvHeight = 50;

    public ImagePreview(JFileChooser fc) {
        setPreferredSize(new Dimension(pvWidth, pvHeight));
        fc.addPropertyChangeListener(this);
    }

    public void loadImage() {
        if (file == null) {
            thumbnail = null;
            return;
        }
        ImageIcon tmpIcon = new ImageIcon(file.getPath());
        if (tmpIcon != null) {
        	//if image width > 135, resize width to 135
            if (tmpIcon.getIconWidth() > pvWidth-10) {
                thumbnail = new ImageIcon(tmpIcon.getImage()
                		.getScaledInstance(pvWidth-10, -1,Image.SCALE_SMOOTH));
            } else { 
            	//no need to miniaturize
                thumbnail = tmpIcon;
            }
        }
    }

    public void propertyChange(PropertyChangeEvent e) {
        boolean update = false;
        String prop = e.getPropertyName();

        //If the directory changed, don't show an image.
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            file = null;
            update = true;

        //If a file became selected, find out which one.
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            file = (File) e.getNewValue();
            update = true;
        }

        //Update the preview accordingly.
        if (update) {
            thumbnail = null;
            if (isShowing()) {
                loadImage();
                repaint();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        if (thumbnail == null) {
            loadImage();
        }
        if (thumbnail != null) {
            int x = getWidth()/2 - thumbnail.getIconWidth()/2;
            int y = getHeight()/2 - thumbnail.getIconHeight()/2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            thumbnail.paintIcon(this, g, x, y);
        }
    }
}
