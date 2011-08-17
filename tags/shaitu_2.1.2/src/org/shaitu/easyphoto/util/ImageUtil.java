/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: EasyPhoto
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Jun 28, 2008 5:12:21 PM
 *
 */
package org.shaitu.easyphoto.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

/**
 * userful methods about image
 * @author harry
 */
public class ImageUtil {
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String BMP = "bmp";
    public final static String PNG = "png";
    public final static String GIF = "gif";
    public final static String TIFF = "tiff";
    public final static String TIF = "tif";

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileUtil.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * create new image from source image
     * @param srcImg source image
     * @param targetWidth target image width
     * @param targetHeight target image height
     * @return new image with specify width and height
     */
    public static BufferedImage createNewImage(BufferedImage srcImg,int targetWidth,int targetHeight){
        BufferedImage targetImg = null;
        int type = srcImg.getType();
        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = srcImg.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetWidth,
                    targetHeight);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            targetImg = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            targetImg = new BufferedImage(targetWidth, targetHeight, type);
        }
        return targetImg;
    }
    
    /**
     * check if image supported
     * @param extName extension name of image file
     * @return true supported, else false
     */
    public static boolean isSupportedImage(String extName) {
        boolean isSupported = false;
        for (String format : ImageIO.getReaderFormatNames()) {
            if (format.equalsIgnoreCase(extName)) {
                isSupported = true;
                break;
            }
        }
        return isSupported;
    }

    /**
     * get supported image format
     * @return supported image format array
     */
    public static String[] getSupportedImageFormat(){
        return ImageIO.getReaderFormatNames();
    }

    /**
     * find images ready for proceed，include under sub folder
     * @param image ready for proceed
     * @param images file
     */
    public static void findImagesRecusive(List<File> todoImageList,File... input) {
        for (File ifile : input) {
                if (ifile.isDirectory()) {
                        //if incoming file is folder, scan recursively
                        findImagesRecusive(todoImageList,ifile.listFiles());
                } else {
                        //check if image format supported
                        String extName = FileUtil.getExtName(ifile);
                        if(isSupportedImage(extName)){
                                todoImageList.add(ifile);
                        }
                }
        }
    }

    /**
     * find images ready for proceed
     * @param image ready for proceed
     * @param images file
     */
    public static void findImages(List<File> todoImageList,File... input) {
        for (File ifile : input) {
                if (ifile.isFile()) {
                    //check if image format supported
                    String extName = FileUtil.getExtName(ifile);
                    if(isSupportedImage(extName)){
                            todoImageList.add(ifile);
                    }
                }
        }
    }
    /**
     * store BufferedImage to file
     * @param image BufferedImage
     * @param outputFile output image file
     * @param quality quality of output image
     * @return true success, else fail
     */
    public static boolean storeImage(BufferedImage image,File outputFile,float quality){
		try {
			//reconstruct folder structure for image file output
			if(outputFile.getParentFile() != null && !outputFile.getParentFile().exists()){
				outputFile.getParentFile().mkdirs();
			}
            if(outputFile.exists()){
                outputFile.delete();
            }
			//get image file suffix
			String extName = FileUtil.getExtName(outputFile);
			//get registry ImageWriter for specified image suffix
			Iterator writers = ImageIO.getImageWritersByFormatName(extName);
			ImageWriter imageWriter = (ImageWriter) writers.next();
			//set image output params
			ImageWriteParam params = new JPEGImageWriteParam(null);
			params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			params.setCompressionQuality(quality);
			params
					.setProgressiveMode(javax.imageio.ImageWriteParam.MODE_DISABLED);
			params.setDestinationType(new ImageTypeSpecifier(IndexColorModel
					.getRGBdefault(), IndexColorModel.getRGBdefault()
					.createCompatibleSampleModel(16, 16)));
			//writer image to file
			ImageOutputStream imageOutputStream = ImageIO
					.createImageOutputStream(outputFile);
			imageWriter.setOutput(imageOutputStream);
			imageWriter.write(null, new IIOImage(image, null, null), params);
			imageOutputStream.close();
			imageWriter.dispose();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
    }
    /**
     * store BufferedImage to file and then retrieve to BufferedImage
     * @param image BufferedImage
     * @param outputFile output image file
     * @param quality quality of output image
     * @return retrieve stored image
     */
    public static BufferedImage storeAndRetrieveImage(BufferedImage image,File outputFile,float quality){
        try{
            if(storeImage(image,outputFile,quality)){
                    return ImageIO.read(outputFile);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * get image thumbnail
     * @param imageFile
     */
    public static ImageIcon getImageThumbnail(File imageFile, int width, int height){
    	ImageIcon thumbnail = null;
		if (imageFile != null && imageFile.isFile()) {
			ImageIcon tmpIcon = new ImageIcon(imageFile.getPath());
			if (tmpIcon != null) {
				if (tmpIcon.getIconWidth() > width) {
					int targetHeight = width/tmpIcon.getIconWidth()*tmpIcon.getIconHeight();
					if(targetHeight>height){
						targetHeight = height;
					} else {
						targetHeight = -1;
					}
					thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(width, targetHeight,Image.SCALE_AREA_AVERAGING));
				} else {
					thumbnail = tmpIcon;
				}
			}
		}
    	return thumbnail;
    }
    
    /**
     * get image orientation type
     * @param imageFile
     * @return 0:Landscape, 1:Portrait
     */
    public static int getOrientation(File imageFile){
    	int result = 0;
    	ImageIcon image = new ImageIcon(imageFile.getPath());
    	if (image.getIconWidth() > image.getIconHeight()) {
    		result = 0;
    	} else {
    		result = 1;
    	}
    	image = null;
    	return result;
    }
    
    /**
     * get fixing preview image rsize <br/>
     * Exif not apply: calculate by formula: iHeight/iWidth*rsize < (lbHeight-10)
     * Exif apply: calculate by formula: iHeight/iWidth*rsize < (lbHeight-80-10)
     * @param imageFile
     * @param lbWidth
     * @param lbHeight
     * @param applyExif
     * @return
     */
    public static int getFixedPreviewSize(File imageFile, int lbHeight, boolean applyExif){
    	int rsize = 0;
    	ImageIcon image = new ImageIcon(imageFile.getPath());
    	int iHeight = image.getIconHeight();
    	int iWidth = image.getIconWidth();
    	image = null;
    	if(iHeight > iWidth){
    		if(!applyExif){
        		rsize = lbHeight-10;
    		} else {
    			rsize = lbHeight-90;
    		}
    	} else {
    		if(!applyExif){
    			rsize = (lbHeight-10)*iWidth/iHeight;
    		} else {
    			rsize = (lbHeight-90)*iWidth/iHeight;
    		}
    	}
    	return rsize;
    }
    
}
