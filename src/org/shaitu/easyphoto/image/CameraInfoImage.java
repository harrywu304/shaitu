/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 5:15:32 PM
 *
*/
package org.shaitu.easyphoto.image;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import org.shaitu.easyphoto.AppConstants;
import org.shaitu.easyphoto.util.StringUtil;
import org.shaitu.easyphoto.vo.ImageActionVO;


import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;

/**
 * Decorator to append camera info(exif) to image
 * @author whx
 *
 */
public class CameraInfoImage extends BaseDecorativeImage {
	/**
	 * exif head bar size
	 */
	private static final int EXIF_SIZE_HEAD = 30;
	/**
	 * exif foot bar size
	 */
	private static final int EXIF_SIZE_FOOT = 50;
	/**
	 * exif side bar size
	 */
	private static final int EXIF_SIZE_SIDE = 40;

	/**
	 * CameraInfoImage constructor
	 * @param image image for wrap
	 */
	public CameraInfoImage(DecorativeImage di){
		super(di);
	}

	/* (non-Javadoc)
	 * @see net.dxtop.thirdeye.image.DecorativeImage#decorate()
	 */
	@Override
	public boolean decorate(ImageActionVO vo) {
		di.decorate(vo);
		this.appendExifInfo(vo);
		return true;
	}

	/**
	 * append camera info(exif) to image
	 * @return success return true, otherwise false
	 */
	private boolean appendExifInfo(ImageActionVO vo){
		//default is double black
		if(AppConstants.EXIF_STYLE_ROUND.equals(vo.getParams().getExifStyle())){
			return appendStyleRound(vo);
		} else if(AppConstants.EXIF_STYLE_SINGLE.equals(vo.getParams().getExifStyle())){
			return appendStyleSingle(vo);
		} else {
			return appendStyleDouble(vo);
		}
	}

	/**
	 * single style
	 * @return success return true, otherwise false
	 */
	private boolean appendStyleSingle(ImageActionVO vo){
		try {
			BufferedImage srcImg = vo.getBufferedImage();
			//create new BufferedImage instance to draw more elements
			BufferedImage targetImg = null;
			//get source image size info
			int targetWidth = srcImg.getWidth();
			int targetHeight = srcImg.getHeight()+EXIF_SIZE_FOOT;
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
	        //get graphics by target image
	        Graphics2D g = targetImg.createGraphics();
	        //draw src image
	        g.drawImage(srcImg, null, 0, 0);
	        //draw decorative white lines
	        g.drawLine(0, targetHeight-EXIF_SIZE_FOOT, targetWidth, targetHeight-EXIF_SIZE_FOOT);
	        //draw exif info
    		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		g.setFont(vo.getParams().getExifFont());
    		FontMetrics metrics = g.getFontMetrics();
	        String appendInfo = getPrintableExifInfo(vo);
	        if(!StringUtil.isNullOrBlank(appendInfo)){
		        int infoWidth = metrics.stringWidth(appendInfo);
		        int infoAscent = metrics.getAscent();
		        g.drawString(appendInfo,targetWidth-infoWidth-4,targetHeight-EXIF_SIZE_FOOT/2+infoAscent/2);
	        }
	        //dispose Graphics2D instance and context
	        g.dispose();
	        vo.setBufferedImage(targetImg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * double style, default
	 * @return success return true, otherwise false
	 */
	private boolean appendStyleDouble(ImageActionVO vo){
		try {
			BufferedImage srcImg = vo.getBufferedImage();
			//create new BufferedImage instance to draw more elements
			BufferedImage targetImg = null;
			//get source image size info
			int targetWidth = srcImg.getWidth();
			int targetHeight = srcImg.getHeight()+EXIF_SIZE_HEAD+EXIF_SIZE_FOOT;
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
	        Graphics2D g = targetImg.createGraphics();
	        //draw incoming buffered image to target buffered image
	        g.drawImage(srcImg, null, 0, EXIF_SIZE_HEAD);
	        //draw decorative white lines
	        g.drawLine(0, EXIF_SIZE_HEAD-1, targetWidth, EXIF_SIZE_HEAD-1);
	        g.drawLine(0, targetHeight-EXIF_SIZE_FOOT-1, targetWidth, targetHeight-EXIF_SIZE_FOOT-1);
	      //draw exif info
    		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		g.setFont(vo.getParams().getExifFont());
    		FontMetrics metrics = g.getFontMetrics();
	        String appendInfo = getPrintableExifInfo(vo);
	        if(!StringUtil.isNullOrBlank(appendInfo)){
		        int infoWidth = metrics.stringWidth(appendInfo);
		        int infoAscent = metrics.getAscent();
		        g.drawString(appendInfo,targetWidth-infoWidth-4,targetHeight-EXIF_SIZE_FOOT/2+infoAscent/2);
	        }
	        //dispose Graphics2D instance and context
	        g.dispose();
	        vo.setBufferedImage(targetImg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * round style
	 * @return success return true, otherwise false
	 */
	private boolean appendStyleRound(ImageActionVO vo){
		try {
			BufferedImage srcImg = vo.getBufferedImage();
			//get source image size info
			int targetWidth = srcImg.getWidth()+EXIF_SIZE_SIDE*2;
			int targetHeight = srcImg.getHeight()+EXIF_SIZE_HEAD+EXIF_SIZE_FOOT;
			int type = srcImg.getType();
			//create new BufferedImage instance to draw more elements
	        BufferedImage targetImg = null;
	        if (type == BufferedImage.TYPE_CUSTOM) {
	            ColorModel cm = srcImg.getColorModel();
	            WritableRaster raster = cm.createCompatibleWritableRaster(targetWidth,
	                    targetHeight);
	            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
	            targetImg = new BufferedImage(cm, raster, alphaPremultiplied, null);
	        } else {
	            targetImg = new BufferedImage(targetWidth, targetHeight, type);
	        }
	        Graphics2D g = targetImg.createGraphics();
	        //draw incoming buffered image to target buffered image
	        g.drawImage(srcImg, null, EXIF_SIZE_SIDE, EXIF_SIZE_HEAD);
	        //draw decorative white lines
	        g.drawRect(EXIF_SIZE_SIDE-1, EXIF_SIZE_HEAD-1, srcImg.getWidth()+1, srcImg.getHeight()+1);
	        g.setFont(vo.getParams().getExifFont());
    		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        String appendInfo = getPrintableExifInfo(vo);
	        if(!StringUtil.isNullOrBlank(appendInfo)){
		        FontMetrics metrics = g.getFontMetrics();
		        int infoWidth = metrics.stringWidth(appendInfo);
		        int infoAscent = metrics.getAscent();
		        g.drawString(appendInfo,targetWidth-infoWidth-EXIF_SIZE_SIDE-4,targetHeight-EXIF_SIZE_FOOT/2+infoAscent/2);
	        }
	        //dispose Graphics2D instance and context
	        g.dispose();
	        vo.setBufferedImage(targetImg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * get exif info needed to be appended
	 * @param vo ImageActionVO
	 * @return printable exif info
	 */
	private String getPrintableExifInfo(ImageActionVO vo){
		try {
			StringBuffer appendInfo = new StringBuffer();
		    //get image meta object
			Metadata metadata = JpegMetadataReader.readMetadata(vo.getInputImageFile());
			Directory exifDic = metadata.getDirectory(ExifDirectory.class);
			//camera module
			if(vo.getParams().isAppendCamera()){
				appendInfo.append(StringUtil.getPrintString(
						exifDic.getDescription(ExifDirectory.TAG_MODEL)));
			}
			//focal length
			if(vo.getParams().isAppendFocal()){
				appendInfo.append(StringUtil.getPrintString(
						exifDic.getInt(ExifDirectory.TAG_FOCAL_LENGTH)+" mm"));
			}
			//aperture
			if(vo.getParams().isAppendApt()){
				appendInfo.append(StringUtil.getPrintString(
						exifDic.getDescription(ExifDirectory.TAG_FNUMBER)));
			}
			//exposure time
			if(vo.getParams().isAppendExp()){
				appendInfo.append(StringUtil.getPrintString(
						exifDic.getDescription(ExifDirectory.TAG_EXPOSURE_TIME)));
			}
			//iso
			if(vo.getParams().isAppendIso()){
				appendInfo.append(StringUtil.getPrintString(
						exifDic.getDescription(ExifDirectory.TAG_ISO_EQUIVALENT)));
			}
			//take photo time
			if(vo.getParams().isAppendDate()){
				appendInfo.append(StringUtil.getPrintString(
						exifDic.getDescription(ExifDirectory.TAG_DATETIME)));
			}
			//author sign
			if(!StringUtil.isNullOrBlank(vo.getParams().getSign())){
				appendInfo.append(StringUtil.getPrintString(vo.getParams().getSign()));
			}
			if(appendInfo.length() > 0){
				appendInfo.deleteCharAt(appendInfo.length()-1);
			}
	        return appendInfo.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

}
