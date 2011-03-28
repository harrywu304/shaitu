package org.shaitu.easyphoto.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import org.shaitu.easyphoto.util.AppUtil;

import org.shaitu.easyphoto.util.FileUtil;
import org.shaitu.easyphoto.util.ImageUtil;

/**
 * custom image file chooser, image preview support
 * @author whx
 *
 */
public class ImageFileChooser extends JFileChooser {

	public ImageFileChooser() {
		setFileFilter(new ImageFilter());
		setFileView(new ImageFileView());
		setAccessory(new ImagePreviewAccessory(this));
		setMultiSelectionEnabled(true);
		setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImageFileChooser ifc = new ImageFileChooser();
		ifc.setVisible(true);
		// Create and set up the window.
		JFrame frame = new JFrame("ImageFileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add content to the window.
		frame.add(ifc);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}



	/**
	 * Accessory for image preview
	 * @author harry
	 *
	 */
	private static class ImagePreviewAccessory extends JComponent implements
			PropertyChangeListener {
		
		/**
		 * image thumbnail
		 */
		private ImageIcon thumbnail = null;
		/**
		 * selected image file
		 */
		private File selectedFile = null;
		/**
		 * width of thumbnail
		 */
		private int pvWidth = 135;
		/**
		 * height of thumbnail
		 */
		private int pvHeight = 50;
		
		private Map<String,ImageIcon> thumbnailCache = new HashMap<String,ImageIcon>(10);

		public ImagePreviewAccessory(JFileChooser fc) {
			setPreferredSize(new Dimension(pvWidth+10, pvHeight));
			fc.addPropertyChangeListener(this);
			this.setVisible(false);
		}

		public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();
				if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
					selectedFile = (File) e.getNewValue();
					if(selectedFile != null){
						String key = selectedFile.getAbsolutePath();
						if(thumbnailCache.containsKey(key)){
							thumbnail = thumbnailCache.get(key);
						} else {
							thumbnail = ImageUtil.getImageThumbnail(selectedFile, pvWidth, pvHeight);
							if(thumbnailCache.size() >= 50){
								thumbnailCache.clear();
							}
							if(thumbnail != null){
								thumbnailCache.put(selectedFile.getAbsolutePath(), thumbnail);
							}
						}
						if(thumbnail != null){
							repaint();
							setVisible(true);
						}
					}
				}

		}

		protected void paintComponent(Graphics g) {
			//draw thumbnail
			if (thumbnail != null) {
				int x = (getWidth()  - thumbnail.getIconWidth()) /2;
				int y = (getHeight() - thumbnail.getIconHeight()) / 2;

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
	
	/**
	 * File view for image
	 * @author harry
	 *
	 */
	private static class ImageFileView extends FileView {
		private static ImageIcon imageIcon = ImageUtil
				.createImageIcon("/resources/icon/image_icon_32.png");

		public Icon getIcon(File f) {
			String extension = FileUtil.getExtName(f);
			Icon icon = null;
			if (ImageUtil.isSupportedImage(extension)) {
				icon = imageIcon;
			}
			return icon;
		}
	}

	/**
	 * supported image filter
	 * 
	 * @author harry
	 * 
	 */
	private static class ImageFilter extends FileFilter {

		// Accept only supported images and directories
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
			return ImageUtil.isSupportedImage(FileUtil.getExtName(f));
		}

		public String getDescription() {
			return AppUtil.getResourceString("EasyPhoto.fcImport.supportType");
		}
	}

}
