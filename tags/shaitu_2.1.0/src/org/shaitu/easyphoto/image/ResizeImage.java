/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 16, 2008 11:27:09 AM
 *
*/ 
package org.shaitu.easyphoto.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.logging.Logger;

import org.shaitu.easyphoto.vo.ImageActionVO;



/**
 * resize image to specified size
 * @author whx
 *
 */
public class ResizeImage extends BaseDecorativeImage {
	/**
	 * log instance
	 */
	private static final Logger logger = Logger.getLogger(ResizeImage.class.getName());
	
	/**
	 * RoundCornerImage constructor
	 * @param image image for wrap
	 */
	public ResizeImage(DecorativeImage di){
		super(di);
	}

	/* (non-Javadoc)
	 * @see net.dxtop.thirdeye.image.DecorativeImage#decorate()
	 */
	@Override
	public boolean decorate(ImageActionVO vo) {
		di.decorate(vo);
		this.resize(vo);
		return true;
	}
	
	/**
	 * resize image with equal ratio
	 * @return success return true, otherwise false
	 */
	private boolean resize(ImageActionVO vo){
		BufferedImage image = vo.getBufferedImage();
		int rsize = vo.getParams().getResize();
		//get source image info
		int sw = image.getWidth();
		int sh = image.getHeight();
		int type = image.getType();   
		//target size bigger than or equal source size, return true and do nothing
		if(rsize >= Math.max(sw, sh)){
			return true;
		}
		//do resize
		int tw = 0;
		int th = 0;
		double ratio = 0d;
		if(sw > sh){
			//width > height
			tw = rsize;
			ratio = (double)tw/sw;
			th = (int)(ratio*sh);
		} else {
			//width <= height
			th = rsize;
			ratio = (double)th/sh;
			tw = (int)(ratio*sw);
		}
         
        BufferedImage target = null; 
        if (type == BufferedImage.TYPE_CUSTOM) {   
            ColorModel cm = image.getColorModel();    
            WritableRaster raster = cm.createCompatibleWritableRaster(tw,    
                    th);    
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();    
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);    
        } else {  
            target = new BufferedImage(tw, th, type);  
        }    
        Graphics2D g = target.createGraphics(); 
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,    
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);    
        g.drawRenderedImage(image, AffineTransform.getScaleInstance(ratio, ratio));    
        g.dispose();   
        //update source image
        vo.setBufferedImage(target);
		return true;
	}

}
