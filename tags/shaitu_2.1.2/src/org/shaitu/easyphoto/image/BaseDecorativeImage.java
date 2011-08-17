/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:00:38 PM
 *
*/ 
package org.shaitu.easyphoto.image;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.shaitu.easyphoto.util.ImageUtil;
import org.shaitu.easyphoto.vo.ImageActionVO;



/**
 * Base implement of DecorativeImage
 * @author harry
 *
 */
public class BaseDecorativeImage implements DecorativeImage {
	/**
	 * wrapped image
	 */
	protected DecorativeImage di;
	
	/**
	 * default constructor 
	 */
	public BaseDecorativeImage(){
	}
	
	/**
	 * DecorativeImage constructor 
	 * @param image BufferedImage instance to wrap
	 */
	public BaseDecorativeImage(DecorativeImage di){
		this.di = di;
	}
	
	@Override
	public boolean decorate(ImageActionVO vo) {
		try {
			vo.setBufferedImage(ImageIO.read(vo.getInputImageFile()));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * store image to output file
	 * @param output output file to store image instance
	 * @param quality 0~1.0, 1.0 means full quality, usually 0.75 is suitable for most scenario
	 * @return	success return true, otherwise false
	 */
	public boolean store(ImageActionVO vo){
        return ImageUtil.storeImage(vo.getBufferedImage(), vo.getOutputImageFile(),
                vo.getParams().getQuality()/100.0f);
	}
}
