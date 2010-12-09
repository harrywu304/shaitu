/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:00:38 PM
 *
*/ 
package org.shaitu.easyphoto.image;

import org.shaitu.easyphoto.vo.ImageActionVO;

/**
 * Image with decorative and store function 
 * @author whx
 *  
 */
public interface DecorativeImage {
	
	/**
	 * render image with decorate action
	 * @return success return true, otherwise false
	 */
	public boolean decorate(ImageActionVO vo);
	
	/**
	 * store image to output file
	 * @return success return true, otherwise false
	 */
	public boolean store(ImageActionVO vo);	
	

}
